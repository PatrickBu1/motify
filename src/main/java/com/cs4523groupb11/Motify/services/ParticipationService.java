package com.cs4523groupb11.Motify.services;

import com.cs4523groupb11.Motify.entities.Challenge;
import com.cs4523groupb11.Motify.entities.Participation;
import com.cs4523groupb11.Motify.entities.User;

import java.time.Period;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

public interface ParticipationService {
    Optional<List<Participation>> getAllParticipationByEmail(String email, Boolean isPrivate);

    Optional<List<Participation>> getAllParticipationByUserId(String id, Boolean isPrivate);

    Optional<Participation> getOneParticipationByEmailAndChallengeId(String email, String challengeId);

    Optional<List<User>> getParticipantsByPublicChallengeId(String id);

    Optional<List<Challenge>> getSelfChallengesByDate(String email, Date date);

    void addParticipationEntry(String email, String pcId) throws NoSuchElementException;

    void deleteParticipationEntry(String email, String pcId) throws NoSuchElementException;

//    Optional<Integer> checkIn(String email, String cid, Integer amount, Period period);

}
