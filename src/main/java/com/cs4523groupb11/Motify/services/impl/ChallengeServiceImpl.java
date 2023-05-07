package com.cs4523groupb11.Motify.services.impl;

import com.cs4523groupb11.Motify.DTO.entity.ChallengeDTO;
import com.cs4523groupb11.Motify.DTO.entity.QuantityWorkloadDTO;
import com.cs4523groupb11.Motify.DTO.entity.TimeWorkloadDTO;
import com.cs4523groupb11.Motify.DTO.entity.abstraction.ChallengeWorkloadDTO;
import com.cs4523groupb11.Motify.entities.Challenge;
import com.cs4523groupb11.Motify.entities.User;
import com.cs4523groupb11.Motify.entities.abstraction.ChallengeWorkload;
import com.cs4523groupb11.Motify.entities.derived.QuantityWorkload;
import com.cs4523groupb11.Motify.entities.derived.TimeWorkload;
import com.cs4523groupb11.Motify.entities.enums.ChallengeCategory;
import com.cs4523groupb11.Motify.entities.enums.TimeUnit;
import com.cs4523groupb11.Motify.repositories.ChallengeRepository;
import com.cs4523groupb11.Motify.repositories.UserRepository;
import com.cs4523groupb11.Motify.services.ChallengeService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public Optional<Challenge> getById(String id, String email){
        Optional<User> opUser = userRepository.findByEmail(email);
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

    public List<Challenge> getAllPublic(){
        return challengeRepository.findAllByIsPrivateFalse();
    }


    @Transactional
    public Optional<List<Challenge>> getAllPrivateChallenges(String email){
        Optional<User> opUser = userRepository.findByEmail(email);
        if (opUser.isEmpty()) {return Optional.empty();}
        List<Challenge> res = challengeRepository.findByIsPrivateTrueAndOwner(opUser.get());
        return Optional.of(res);
    }

    @Transactional
    public Optional<List<Challenge>> getAllPublicChallengesByOwner(String userId){
        Optional<User> opUser = userRepository.findById(userId);
        return opUser.map(challengeRepository::findByIsPrivateFalseAndOwner);
    }

    @Transactional
        public Optional<Challenge> create(String email, ChallengeDTO dto){
        Optional<User> opUser = userRepository.findById(dto.getOwnerId());
        if (opUser.isEmpty() || !opUser.get().getEmail().equals(email)){
            return Optional.empty();
        }
        ChallengeWorkloadDTO cwDto = dto.getWorkload();
        ChallengeWorkload cw;
        if (cwDto instanceof QuantityWorkloadDTO){
            cw = new QuantityWorkload(((QuantityWorkloadDTO) cwDto).getAmount(), ((QuantityWorkloadDTO) cwDto).getUnit());
        }else{
            cw = new TimeWorkload(((TimeWorkloadDTO) cwDto).getDuration());
        }
        TimeUnit tu = (dto.getFrequency() == null)? null: TimeUnit.valueOf(dto.getFrequency());
        Challenge c = new Challenge(opUser.get(), dto.getName(), dto.getDescription(), dto.getIsPrivate(),
                ChallengeCategory.valueOf(dto.getCategory()), dto.getIsOngoing(), dto.getStartDate(),
                dto.getEndDate(), tu, cw, new Date());
        Challenge saved;
        try{
            saved = challengeRepository.saveAndFlush(c);
        }catch(Exception e){
            return Optional.empty();
        }
        return Optional.of(saved);
    }

    @Transactional
    public Boolean update(String email, ChallengeDTO dto){
        Optional<Challenge> opChallenge = challengeRepository.findById(dto.getId());
        Optional<User> opUser = userRepository.findById(dto.getOwnerId());
        if (opChallenge.isEmpty() ||
                opUser.isEmpty() ||
                !opChallenge.get().getOwner().getEmail().equals(email) ||
                !opUser.get().getEmail().equals(email)) {
            return false;
        }
        ChallengeWorkloadDTO cwDto = dto.getWorkload();
        ChallengeWorkload cw;
        if (cwDto instanceof QuantityWorkloadDTO){
            cw = new QuantityWorkload(((QuantityWorkloadDTO) cwDto).getAmount(), ((QuantityWorkloadDTO) cwDto).getUnit());
        }else{
            cw = new TimeWorkload(((TimeWorkloadDTO) cwDto).getDuration());
        }
        TimeUnit tu = (dto.getFrequency() == null)? null: TimeUnit.valueOf(dto.getFrequency());
        Challenge c = new Challenge(opUser.get(), dto.getName(), dto.getDescription(), dto.getIsPrivate(),
                ChallengeCategory.valueOf(dto.getCategory()), dto.getIsOngoing(), dto.getStartDate(),
                dto.getEndDate(), tu, cw, dto.getCreatedAt());
        c.setId(dto.getId());
        try{
            challengeRepository.saveAndFlush(c);
        }catch(Exception e){
            return false;
        }
        return true;
    }

    @Transactional
    public void delete(String email, String id) {
        Optional<Challenge> pc = challengeRepository.findById(id);
        if (pc.isPresent() && pc.get().getOwner().getEmail().equals(email)) {
            challengeRepository.deleteById(id);
        }
        throw new NoSuchElementException();
    }
}
