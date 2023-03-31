package com.cs4523groupb11.Motify.services;

import com.cs4523groupb11.Motify.entities.CheckInData;
import com.cs4523groupb11.Motify.entities.PublicChallenge;
import com.cs4523groupb11.Motify.entities.User;

import java.util.List;
import java.util.Optional;

public interface UserChallengeService {
    Optional<List<String>> getAllPublicChallengeIdsByUserId(String id);

    Optional<List<String>> getParticipantIdsByPublicChallengeId(String id);

    Optional<PublicChallenge> joinPublicChallenge(String userId, String pcId);

    Optional<PublicChallenge> quitPublicChallenge(String userId, String pcId);

    Optional<CheckInData> checkInToPublicChallenge(CheckInData checkInData);

    Optional<CheckInData> deleteCheckInDataById(String checkInDataId);

}
