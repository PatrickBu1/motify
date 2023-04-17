package com.cs4523groupb11.Motify.services.impl;

import com.cs4523groupb11.Motify.DTO.detailed_entity.ChallengeDTO;
import com.cs4523groupb11.Motify.DTO.detailed_entity.abstraction.ChallengeContentDTO;
import com.cs4523groupb11.Motify.DTO.detailed_entity.abstraction.ChallengeWorkloadDTO;
import com.cs4523groupb11.Motify.DTO.detailed_entity.derived.HabitContentDTO;
import com.cs4523groupb11.Motify.DTO.detailed_entity.derived.QuantityWorkloadDTO;
import com.cs4523groupb11.Motify.DTO.detailed_entity.derived.TimeWorkloadDTO;
import com.cs4523groupb11.Motify.entities.Challenge;
import com.cs4523groupb11.Motify.entities.User;
import com.cs4523groupb11.Motify.entities.abstraction.ChallengeContent;
import com.cs4523groupb11.Motify.entities.abstraction.ChallengeWorkload;
import com.cs4523groupb11.Motify.entities.derived.GoalContent;
import com.cs4523groupb11.Motify.entities.derived.HabitContent;
import com.cs4523groupb11.Motify.entities.derived.QuantityWorkload;
import com.cs4523groupb11.Motify.entities.derived.TimeWorkload;
import com.cs4523groupb11.Motify.entities.enums.ChallengeCategory;
import com.cs4523groupb11.Motify.entities.enums.TimeUnit;
import com.cs4523groupb11.Motify.repositories.ChallengeRepository;
import com.cs4523groupb11.Motify.repositories.UserRepository;
import com.cs4523groupb11.Motify.services.ChallengeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class ChallengeServiceImpl implements ChallengeService {
    private final ChallengeRepository challengeRepository;

    private final UserRepository userRepository;

    @Autowired
    public ChallengeServiceImpl(ChallengeRepository challengeRepository, UserRepository userRepository) {
        this.challengeRepository = challengeRepository;
        this.userRepository = userRepository;
    }

    @Transactional
    public Optional<Challenge> getById(String id, String username){
        Optional<User> opUser = userRepository.findByUsername(username);
        Optional<Challenge> opChallenge = challengeRepository.findById(id);
        if (opUser.isEmpty() || opChallenge.isEmpty()){return Optional.empty();}
        User user = opUser.get();
        Challenge challenge = opChallenge.get();
        if (challenge.getIsPrivate() && !challenge.getOwner().getId().equals(user.getId())){
            return Optional.empty();
        }
        return Optional.of(challenge);
    }

    public Page<Challenge> getPage(Integer page, Integer size){
        Pageable pageable = PageRequest.of(page, size, Sort.by("name"));
        return challengeRepository.findByIsPrivateFalse(pageable);
    }


    @Transactional
    public Optional<List<Challenge>> getAllPrivateChallenges(String username){
        Optional<User> opUser = userRepository.findByUsername(username);
        if (opUser.isEmpty()) {return Optional.empty();}
        List<Challenge> res = challengeRepository.findByIsPrivateTrueAndOwner(opUser.get());
        return Optional.of(res);
    }

    @Transactional
    public Optional<List<Challenge>> getAllPublicChallengesByOwner(String userId){
        Optional<User> opUser = userRepository.findById(userId);
        return opUser.map(user -> challengeRepository.findByIsPrivateFalseAndOwner(user));
    }

    @Transactional
    public Optional<String> create(String userame, ChallengeDTO dto){
        Optional<User> opUser = userRepository.findByUsername(dto.getOwner());
        if (opUser.isEmpty()){return Optional.empty();}
        ChallengeContentDTO ccDto = dto.getContent();
        ChallengeWorkloadDTO cwDto = ccDto.getWorkload();
        ChallengeContent cc;
        ChallengeWorkload cw;
        if (cwDto instanceof QuantityWorkloadDTO){
            cw = new QuantityWorkload(cwDto.getAmount(), ((QuantityWorkloadDTO) cwDto).getUnit());
        }else{
            cw = new TimeWorkload(cwDto.getAmount(), TimeUnit.valueOf(((TimeWorkloadDTO) cwDto).getTimeUnit()));
        }
        if (ccDto instanceof HabitContentDTO){
            cc = new HabitContent(cw, TimeUnit.valueOf(((HabitContentDTO) ccDto).getFrequency()),
                    ((HabitContentDTO) ccDto).getTimeBound(), ((HabitContentDTO) ccDto).getStartDate(),
                    ((HabitContentDTO) ccDto).getEndDate());
        }else{
            cc = new GoalContent(cw);
        }
        Challenge c = new Challenge(opUser.get(), dto.getName(), dto.getDescription(), dto.getPrivate(),
                ChallengeCategory.valueOf(dto.getCategory()), cc, new Date());
        Challenge saved;
        try{
            saved = challengeRepository.saveAndFlush(c);
        }catch(DataIntegrityViolationException e){
            return Optional.empty();
        }
        return Optional.of(saved.getId());
    }

    @Transactional
    public Optional<String> update(String username, ChallengeDTO dto){
        Optional<Challenge> opChallenge = challengeRepository.findById(dto.getId());
        Optional<User> opUser = userRepository.findByUsername(dto.getOwner());
        if (opChallenge.isEmpty() ||
                opUser.isEmpty() ||
                !opChallenge.get().getOwner().getUsername().equals(username) ||
                !opUser.get().getUsername().equals(username)) {
            return Optional.empty();
        }
        ChallengeContentDTO ccDto = dto.getContent();
        ChallengeWorkloadDTO cwDto = ccDto.getWorkload();
        ChallengeContent cc;
        ChallengeWorkload cw;
        if (cwDto instanceof QuantityWorkloadDTO){
            cw = new QuantityWorkload(cwDto.getAmount(), ((QuantityWorkloadDTO) cwDto).getUnit());
        }else{
            cw = new TimeWorkload(cwDto.getAmount(), TimeUnit.valueOf(((TimeWorkloadDTO) cwDto).getTimeUnit()));
        }
        if (ccDto instanceof HabitContentDTO){
            cc = new HabitContent(cw, TimeUnit.valueOf(((HabitContentDTO) ccDto).getFrequency()),
                    ((HabitContentDTO) ccDto).getTimeBound(), ((HabitContentDTO) ccDto).getStartDate(),
                    ((HabitContentDTO) ccDto).getEndDate());
        }else{
            cc = new GoalContent(cw);
        }
        Challenge c = new Challenge(opUser.get(), dto.getName(), dto.getDescription(), dto.getPrivate(),
                ChallengeCategory.valueOf(dto.getCategory()), cc, dto.getCreatedAt());
        Challenge saved;
        try{
            saved = challengeRepository.saveAndFlush(c);
        }catch(DataIntegrityViolationException e){
            return Optional.empty();
        }
        return Optional.of(saved.getId());
    }

    @Transactional
    public void delete(String username, String id) {
        Optional<Challenge> pc = challengeRepository.findById(id);
        if (pc.isPresent()) {
            challengeRepository.deleteById(id);
        }
        throw new NoSuchElementException();
    }
}
