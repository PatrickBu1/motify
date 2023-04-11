package com.cs4523groupb11.Motify.services.impl;

import com.cs4523groupb11.Motify.entities.Challenge;
import com.cs4523groupb11.Motify.entities.User;
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

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class ChallengeServiceImpl implements ChallengeService {
    @Autowired
    ChallengeRepository challengeRepository;

    @Autowired
    UserRepository userRepository;

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

    public Optional<String> create(Challenge challenge){
        Challenge saved;
        try{
            saved = challengeRepository.saveAndFlush(challenge);
        }catch(DataIntegrityViolationException e){
            return Optional.empty();
        }
        return Optional.of(saved.getId());
    }

    @Transactional
    public Optional<String> update(Challenge challenge){
        if(challengeRepository.existsById(challenge.getId())){
            try {
                challengeRepository.save(challenge);
            }catch(DataIntegrityViolationException e){
                return Optional.empty();
            }
            return Optional.of(challenge.getId());
        }
        return Optional.empty();
    }

    @Transactional
    public void delete(String id) {
        Optional<Challenge> pc = challengeRepository.findById(id);
        if (pc.isPresent()) {
            challengeRepository.deleteById(id);
        }
        throw new NoSuchElementException();
    }
}
