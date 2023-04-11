package com.cs4523groupb11.Motify.services;

import com.cs4523groupb11.Motify.entities.CheckInData;
import com.cs4523groupb11.Motify.entities.Participation;
import com.cs4523groupb11.Motify.entities.User;
import jakarta.servlet.http.Part;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

public interface ParticipationService {
    Optional<List<Participation>> getAllParticipationByUsername(String username, Boolean isPrivate);

    Optional<List<Participation>> getAllParticipationByUserId(String id);

    Optional<Participation> getOneParticipationByUsernameAndChallengeId(String username, String challengeId);

    Optional<List<User>> getParticipantsByPublicChallengeId(String id);

    void joinPublicChallenge(String username, String pcId) throws NoSuchElementException;

    void quitPublicChallenge(String username, String pcId) throws NoSuchElementException;

}
