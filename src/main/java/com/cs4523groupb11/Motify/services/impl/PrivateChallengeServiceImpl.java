package com.cs4523groupb11.Motify.services.impl;

import com.cs4523groupb11.Motify.entities.PrivateChallenge;
import com.cs4523groupb11.Motify.entities.User;
import com.cs4523groupb11.Motify.repositories.PrivateChallengeRepository;
import com.cs4523groupb11.Motify.repositories.UserRepository;
import com.cs4523groupb11.Motify.services.PrivateChallengeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class PrivateChallengeServiceImpl implements PrivateChallengeService {
    @Autowired
    PrivateChallengeRepository privateChallengeRepository;

    @Autowired
    UserRepository userRepository;

    public Optional<PrivateChallenge> findById(String id){
        return privateChallengeRepository.findById(id);
    }

    @Transactional
    public Optional<List<PrivateChallenge>> findAllByOwnerId(String userId){
        Optional<User> opUser = userRepository.findById(userId);
        if (opUser.isPresent()){
            return privateChallengeRepository.findAllByOwner(opUser.get());
        }
        return Optional.empty();
    }

    @Transactional
    public Optional<PrivateChallenge> create(PrivateChallenge pc){
        if (privateChallengeRepository.existsByOwnerAndName(pc.getOwner(), pc.getName())){
            return Optional.empty();
        }
        PrivateChallenge savedPc = privateChallengeRepository.saveAndFlush(pc);
        return Optional.of(savedPc);
    }

    @Transactional
    public Optional<PrivateChallenge> deleteById(String id){
        Optional<PrivateChallenge> pc = privateChallengeRepository.findById(id);
        if (pc.isPresent()){
            privateChallengeRepository.deleteById(id);
            return pc;
        }
        return Optional.empty();
    }

    @Transactional
    public Optional<PrivateChallenge> update(PrivateChallenge pc){
        if(privateChallengeRepository.existsById(pc.getId())){
            try {
                return Optional.of(privateChallengeRepository.save(pc));
            }catch(DataIntegrityViolationException e){
                return Optional.empty();
            }
        }
        return Optional.empty();
    }
}
