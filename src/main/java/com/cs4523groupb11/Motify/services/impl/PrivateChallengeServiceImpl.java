package com.cs4523groupb11.Motify.services.impl;

import com.cs4523groupb11.Motify.entities.PrivateChallenge;
import com.cs4523groupb11.Motify.repositories.PrivateChallengeRepository;
import com.cs4523groupb11.Motify.services.PrivateChallengeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PrivateChallengeServiceImpl implements PrivateChallengeService {

    @Autowired
    PrivateChallengeRepository privateChallengeRepository;

    public PrivateChallenge findById(String privateChallengeId){
        return privateChallengeRepository.
    }
}
