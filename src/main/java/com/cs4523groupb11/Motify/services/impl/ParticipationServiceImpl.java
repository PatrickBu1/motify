package com.cs4523groupb11.Motify.services.impl;


import com.cs4523groupb11.Motify.entities.Challenge;
import com.cs4523groupb11.Motify.entities.Participation;
import com.cs4523groupb11.Motify.entities.User;
import com.cs4523groupb11.Motify.entities.derived.QuantityWorkload;
import com.cs4523groupb11.Motify.entities.enums.TimeUnit;
import com.cs4523groupb11.Motify.repositories.ChallengeRepository;
import com.cs4523groupb11.Motify.repositories.ParticipationRepository;
import com.cs4523groupb11.Motify.repositories.UserRepository;
import com.cs4523groupb11.Motify.services.ParticipationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.Period;
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
    public Optional<List<Challenge>> getSelfChallengesByDate(String email, Date date) {
        Optional<User> opUser = userRepository.findByEmail(email);
        if (opUser.isEmpty()) {return Optional.empty();}
        List<Participation> pList = participationRepository.findAllIsActiveTrueByOwner(opUser.get());
        List<Challenge> resList = new ArrayList<>();

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
                List<Date> completedDates = p.getCompletedDates();
                Date lastCheckin = completedDates.get(completedDates.size() - 1);
                if (!(c.getIsOngoing() || (!c.getIsOngoing()
                        && date.before(c.getEndDate())
                        && date.after(c.getStartDate())))){continue;}
                if (c.getFrequency() == TimeUnit.WEEK && lastCheckin.after(monday)){continue;}
                if (c.getFrequency() == TimeUnit.MONTH && lastCheckin.after(firstDayOfMonth)){continue;}
                if (c.getFrequency() == TimeUnit.YEAR && lastCheckin.after(firstDayOfYear)){continue;}
                resList.add(c);
            }
        }catch (Exception e){
            return Optional.empty();
        }
        return Optional.of(resList);
    }


//    @Transactional
//    public Optional<Integer> checkIn(String email, String cid, Integer amount, Period period){
//        Optional<User> opUser = userRepository.findByEmail(email);
//        Optional<Challenge> opChallenge = challengeRepository.findById(cid);
//        if (opUser.isEmpty() || opChallenge.isEmpty()) { return Optional.empty();}
//        Optional<Participation> opP = participationRepository.findByOwnerAndChallenge(opUser.get(), opChallenge.get());
//        if (opP.isEmpty()) { return Optional.empty();}
//        Participation p = opP.get();
//        if (p.getIsHabit()){
//            List<Date> completedDates = p.getCompletedDates();
//            completedDates.add(new Date());
//            p.setCompletedDates(completedDates);
//        }else{
//            Challenge c = opChallenge.get();
//            if (c.getWorkload() instanceof QuantityWorkload){
//                if (amount > ((QuantityWorkload) c.getWorkload()).getAmount() - p.getProgress()){
//                    return Optional.empty();
//                }
//                p.setProgress(p.getProgress() + amount);
//            }
//        }
//
//    }

    @Transactional
    public void cancelCheckIn(){

    }

    @Transactional
    public void addParticipationEntry(String email, String cid) throws NoSuchElementException{
        Optional<User> opUser = userRepository.findByEmail(email);
        Optional<Challenge> opChallenge = challengeRepository.findById(cid);
        if (opUser.isEmpty() || opChallenge.isEmpty()) { throw new NoSuchElementException();}
        Boolean isHabit = opChallenge.get().getFrequency() != null;
        Participation newParticipation = new Participation(
                opUser.get(), opChallenge.get(), isHabit, true,
                0, Collections.emptyList(), 0
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
