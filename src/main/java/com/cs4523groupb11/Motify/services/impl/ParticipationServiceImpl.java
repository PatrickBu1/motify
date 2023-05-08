package com.cs4523groupb11.Motify.services.impl;


import com.cs4523groupb11.Motify.entities.Challenge;
import com.cs4523groupb11.Motify.entities.Participation;
import com.cs4523groupb11.Motify.entities.User;
import com.cs4523groupb11.Motify.entities.abstraction.ChallengeWorkload;
import com.cs4523groupb11.Motify.entities.derived.QuantityWorkload;
import com.cs4523groupb11.Motify.entities.derived.TimeWorkload;
import com.cs4523groupb11.Motify.entities.enums.TimeUnit;
import com.cs4523groupb11.Motify.repositories.ChallengeRepository;
import com.cs4523groupb11.Motify.repositories.ParticipationRepository;
import com.cs4523groupb11.Motify.repositories.UserRepository;
import com.cs4523groupb11.Motify.services.ParticipationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.Period;
import java.time.temporal.Temporal;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class ParticipationServiceImpl implements ParticipationService {

    private final ParticipationRepository participationRepository;

    private final UserRepository userRepository;

    private final ChallengeRepository challengeRepository;


    @Autowired
    public ParticipationServiceImpl(ParticipationRepository participationRepository,
                                    UserRepository userRepository,
                                    ChallengeRepository challengeRepository) {
        this.participationRepository = participationRepository;
        this.userRepository = userRepository;
        this.challengeRepository = challengeRepository;
    }


    @Transactional
    public Optional<List<Participation>> getAllParticipationByEmail(String email, Boolean isPrivate){
        Optional<User> opUser = userRepository.findByEmail(email);
        if (opUser.isEmpty()) {return Optional.empty();}
        List<Participation> res = participationRepository.findAllByOwnerAndIsPrivate(opUser.get(), isPrivate);
        return Optional.of(res);
    }

    @Transactional
    public Optional<List<Participation>> getAllParticipationByUserId(String id, Boolean isPrivate){
        Optional<User> opUser = userRepository.findById(id);
        if (opUser.isEmpty()) {return Optional.empty();}
        List<Participation> res = participationRepository.findAllByOwnerAndIsPrivate(opUser.get(), isPrivate);
        return Optional.of(res);
    }

    @Transactional
    public Optional<Participation> getOneParticipationByEmailAndChallengeId(String email, String cid){
        Optional<User> opUser = userRepository.findByEmail(email);
        Optional<Challenge> opChallenge = challengeRepository.findById(cid);
        if (opUser.isEmpty() || opChallenge.isEmpty()) {return Optional.empty();}
        return participationRepository.findByOwnerAndChallenge(opUser.get(), opChallenge.get());
    }

    @Transactional
    public Optional<List<User>> getParticipantsByPublicChallengeId(String cid){
        Optional<Challenge> opChallenge = challengeRepository.findById(cid);
        return opChallenge.map(participationRepository::findAllUserByChallenge);
    }


    @Transactional
    public Optional<List<Pair<Challenge, Boolean>>> getSelfChallengesByDate(String email, Date date) {
        Optional<User> opUser = userRepository.findByEmail(email);
        if (opUser.isEmpty()) {return Optional.empty();}
        List<Participation> pList = participationRepository.findAllIsActiveTrueByOwner(opUser.get());
        List<Pair<Challenge, Boolean>> resList = new ArrayList<>();

        try{
            Calendar cal = Calendar.getInstance();
            cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
            Date monday = cal.getTime();
            cal.setTime(date);
            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
            Date firstDayOfYear = formatter.parse("01-01-"+cal.get(Calendar.YEAR));
            Date firstDayOfMonth = formatter.parse(cal.get(Calendar.MONTH) + "-01-" + cal.get(Calendar.YEAR));

            for (Participation p: pList){
                Challenge c = p.getChallenge();
                if(p.getIsHabit()){
                    List<Date> completedDates = p.getCompletedDates();
                    if (completedDates.size() == 0){
                        resList.add(Pair.of(c, true));
                    }else{
                        Date lastCheckin = date;
                        for (int i=completedDates.size()-1; i>=0; i--){
                            if (completedDates.get(i).before(date)){
                                lastCheckin = completedDates.get(i);
                                break;
                            }
                        }
                        if (!(c.getIsOngoing() || (!c.getIsOngoing()
                                && date.before(c.getEndDate())
                                && date.after(c.getStartDate())))){
                            p.setIsActive(false);
                            participationRepository.saveAndFlush(p);
                            continue;
                        }
                        Calendar morningCal = Calendar.getInstance();
                        morningCal.setTime(date);
                        morningCal.set(Calendar.HOUR_OF_DAY, 0);
                        morningCal.set(Calendar.MINUTE, 0);
                        morningCal.set(Calendar.SECOND, 0);
                        morningCal.set(Calendar.MILLISECOND, 0);
                        Date morning = morningCal.getTime();
                        if (c.getFrequency() == TimeUnit.DAY && lastCheckin.after(morning)){
                            resList.add(Pair.of(c, false));
                            continue;
                        }
                        if (c.getFrequency() == TimeUnit.WEEK && lastCheckin.after(monday)){
                            resList.add(Pair.of(c, false));
                            continue;
                        }
                        if (c.getFrequency() == TimeUnit.MONTH && lastCheckin.after(firstDayOfMonth)){
                            resList.add(Pair.of(c, false));
                            continue;
                        }
                        if (c.getFrequency() == TimeUnit.YEAR && lastCheckin.after(firstDayOfYear)){
                            resList.add(Pair.of(c, false));
                            continue;
                        }
                        resList.add(Pair.of(c, true));
                    }
                }else{
                    resList.add(Pair.of(c, true));
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            return Optional.empty();
        }
        return Optional.of(resList);
    }


    @Transactional
    public Optional<Participation> checkIn(String email, String cid, Integer amount, Duration duration){
        Optional<User> opUser = userRepository.findByEmail(email);
        Optional<Challenge> opChallenge = challengeRepository.findById(cid);
        if (opUser.isEmpty() || opChallenge.isEmpty()) { return Optional.empty();}
        Optional<Participation> opP = participationRepository.findByOwnerAndChallenge(opUser.get(), opChallenge.get());
        if (opP.isEmpty()) { return Optional.empty();}
        Participation p = opP.get();
        if (p.getIsHabit()){
            List<Date> completedDates = p.getCompletedDates();
            completedDates.add(new Date());
            p.setCompletedDates(completedDates);
            int size = completedDates.size();
            if (size > 1){
                Date lastCheckin = completedDates.get(size-2);
                Date thisCheckin = completedDates.get(size-1);
                long diff = Math.abs(thisCheckin.getTime() - lastCheckin.getTime());
                long diffDays = java.util.concurrent.TimeUnit.DAYS.convert(diff, java.util.concurrent.TimeUnit.MILLISECONDS);
                if (diffDays <= 2){
                    p.setStreak(p.getStreak() + 1);
                }
            }else{
                p.setStreak(1);
            }
        }else{
            ChallengeWorkload cw = p.getChallenge().getWorkload();
            if (p.getIsQuantity()){
                if (amount >= ((QuantityWorkload) cw).getAmount() - p.getProgress()){
                    p.setIsActive(false);
                }
                p.setProgress(p.getProgress() + amount);
            }else{
                int cmp = duration.compareTo(((TimeWorkload) cw).getDuration().minus(p.getDurationProgress()));
                if (cmp >= 0){
                    p.setIsActive(false);
                }
                p.setDurationProgress(p.getDurationProgress().plus(duration));
            }
        }
        participationRepository.saveAndFlush(p);
        return Optional.of(p);
    }

    public Optional<Participation> unCheckIn(String email, String id){
        Optional<User> opUser = userRepository.findByEmail(email);
        Optional<Challenge> opChallenge = challengeRepository.findById(id);
        if (opChallenge.isEmpty() || opUser.isEmpty()){return Optional.empty();}
        Optional<Participation> opP = participationRepository.findByOwnerAndChallenge(opUser.get(), opChallenge.get());
        if (opP.isEmpty()){
            return Optional.empty();
        }
        Participation p = opP.get();
        if (!p.getIsHabit() || p.getCompletedDates().size() == 0){
            return Optional.empty();
        }
        List<Date> newCompletedDates = p.getCompletedDates();
        newCompletedDates.remove(newCompletedDates.size()-1);
        p.setCompletedDates(newCompletedDates);
        Participation saved = participationRepository.saveAndFlush(p);
        return Optional.of(saved);
    }

    @Transactional
    public void addParticipationEntry(String email, String cid) throws NoSuchElementException{
        Optional<User> opUser = userRepository.findByEmail(email);
        Optional<Challenge> opChallenge = challengeRepository.findById(cid);
        if (opUser.isEmpty() || opChallenge.isEmpty()) { throw new NoSuchElementException();}
        Boolean isHabit = opChallenge.get().getFrequency() != null;
        Boolean isQuantity = opChallenge.get().getWorkload() instanceof QuantityWorkload;
        Integer progress = (isQuantity)? 0: null;
        Duration duration = (isQuantity)? null: Duration.ofHours(0).plusMinutes(0);

        Participation newParticipation = new Participation(
                opUser.get(), opChallenge.get(), opChallenge.get().getIsPrivate(), isHabit, true,
                isQuantity, progress, duration, Collections.emptyList(), 0
        );
        participationRepository.saveAndFlush(newParticipation);
    }

    @Transactional
    public void deleteParticipationEntry(String email, String cid) throws NoSuchElementException{
        Optional<User> opUser = userRepository.findByEmail(email);
        Optional<Challenge> opChallenge = challengeRepository.findById(cid);
        if (opUser.isEmpty() || opChallenge.isEmpty()) { throw new NoSuchElementException();}
        participationRepository.deleteByOwnerAndChallenge(opUser.get(), opChallenge.get());
    }

}
