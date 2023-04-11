package com.cs4523groupb11.Motify.services.impl;


import com.cs4523groupb11.Motify.entities.Challenge;
import com.cs4523groupb11.Motify.entities.CheckInData;
import com.cs4523groupb11.Motify.entities.Participation;
import com.cs4523groupb11.Motify.entities.User;
import com.cs4523groupb11.Motify.DTO.detailed_entity.CheckInDataDTO;
import com.cs4523groupb11.Motify.repositories.CheckInDataRepository;
import com.cs4523groupb11.Motify.repositories.ChallengeRepository;
import com.cs4523groupb11.Motify.repositories.UserRepository;
import com.cs4523groupb11.Motify.services.ParticipationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ParticipationServiceImpl implements ParticipationService {


    public Optional<List<Participation>> getAllParticipationByUsername(String username, Boolean isPrivate){
        return Optional.empty();
    }

    public Optional<List<Participation>> getAllParticipationByUserId(String id){
        return Optional.empty();
    }

    @Transactional
    public Optional<Participation> getOneParticipationByUsernameAndChallengeId(String username, String id){
        return Optional.empty();
    }

    public Optional<List<User>> getParticipantsByPublicChallengeId(String id){
        return Optional.empty();
    }

    @Transactional
    public void joinPublicChallenge(String userId, String pcId){

    }

    @Transactional
    public void quitPublicChallenge(String userId, String pcId){

    }

}
