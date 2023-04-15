package com.cs4523groupb11.Motify.services.impl;


import com.cs4523groupb11.Motify.entities.Challenge;
import com.cs4523groupb11.Motify.entities.Participation;
import com.cs4523groupb11.Motify.entities.User;
import com.cs4523groupb11.Motify.entities.derived.GoalContent;
import com.cs4523groupb11.Motify.repositories.ChallengeRepository;
import com.cs4523groupb11.Motify.repositories.ParticipationRepository;
import com.cs4523groupb11.Motify.repositories.UserRepository;
import com.cs4523groupb11.Motify.services.ParticipationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class ParticipationServiceImpl implements ParticipationService {

    private final ParticipationRepository participationRepository;

    private final UserRepository userRepository;

    private final ChallengeRepository challengeRepository;


    @Autowired
    public ParticipationServiceImpl(ParticipationRepository participationRepository, UserRepository userRepository, ChallengeRepository challengeRepository) {
        this.participationRepository = participationRepository;
        this.userRepository = userRepository;
        this.challengeRepository = challengeRepository;
    }


    @Transactional
    public Optional<List<Participation>> getAllParticipationByUsername(String username, Boolean isPrivate){
        Optional<User> opUser = userRepository.findByUsername(username);
        if (opUser.isEmpty()) {return Optional.empty();}
        List<Participation> res = participationRepository.findAllByUserAndIsPrivate(opUser.get(), isPrivate);
        return Optional.of(res);
    }

    @Transactional
    public Optional<List<Participation>> getAllParticipationByUserId(String id, Boolean isPrivate){
        Optional<User> opUser = userRepository.findById(id);
        if (opUser.isEmpty()) {return Optional.empty();}
        List<Participation> res = participationRepository.findAllByUserAndIsPrivate(opUser.get(), isPrivate);
        return Optional.of(res);
    }

    @Transactional
    public Optional<Participation> getOneParticipationByUsernameAndChallengeId(String username, String cid){
        Optional<User> opUser = userRepository.findByUsername(username);
        Optional<Challenge> opChallenge = challengeRepository.findById(cid);
        if (opUser.isEmpty() || opChallenge.isEmpty()) {return Optional.empty();}
        return participationRepository.findByUserAndChallenge(opUser.get(), opChallenge.get());
    }

    @Transactional
    public Optional<List<User>> getParticipantsByPublicChallengeId(String cid){
        Optional<Challenge> opChallenge = challengeRepository.findById(cid);
        return opChallenge.map(challenge -> participationRepository.findAllUserByChallenge(challenge));

    }


    @Transactional
    public void addParticipationEntry(String userId, String cid) throws NoSuchElementException{
        Optional<User> opUser = userRepository.findById(userId);
        Optional<Challenge> opChallenge = challengeRepository.findById(cid);
        if (opUser.isEmpty() || opChallenge.isEmpty()) { throw new NoSuchElementException();}
        Boolean isProgressBased = opChallenge.get().getContent() instanceof GoalContent;
        Participation newParticipation = new Participation(
                opUser.get(), opChallenge.get(), isProgressBased, true,
                0, Collections.emptyList(), 0
        );
        participationRepository.saveAndFlush(newParticipation);
    }

    @Transactional
    public void deleteParticipationEntry(String userId, String cid) throws NoSuchElementException{
        Optional<User> opUser = userRepository.findById(userId);
        Optional<Challenge> opChallenge = challengeRepository.findById(cid);
        if (opUser.isEmpty() || opChallenge.isEmpty()) { throw new NoSuchElementException();}
        participationRepository.deleteByUserAndChallenge(opUser.get(), opChallenge.get());
    }

}
