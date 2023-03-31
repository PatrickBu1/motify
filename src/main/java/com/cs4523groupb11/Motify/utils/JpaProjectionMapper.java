package com.cs4523groupb11.Motify.utils;

import com.cs4523groupb11.Motify.entities.jpaprojections.CheckInDataIdProjection;
import com.cs4523groupb11.Motify.entities.jpaprojections.PrivateChallengeIdProjection;
import com.cs4523groupb11.Motify.entities.jpaprojections.PublicChallengeIdProjection;
import com.cs4523groupb11.Motify.entities.jpaprojections.UserIdProjection;

import java.util.List;
import java.util.stream.Collectors;

public class JpaProjectionMapper {
    public static List<String> publicChallengeIdMap(List<PublicChallengeIdProjection> projList){
        return projList.stream()
                .map(PublicChallengeIdProjection::getId)
                .collect(Collectors.toList());
    }

    public static List<String> privateChallengeIdMap(List<PrivateChallengeIdProjection> projList){
        return projList.stream()
                .map(PrivateChallengeIdProjection::getId)
                .collect(Collectors.toList());
    }

    public static List<String> checkInDataIdMap(List<CheckInDataIdProjection> projList){
        return projList.stream()
                .map(CheckInDataIdProjection::getId)
                .collect(Collectors.toList());
    }

    public static List<String> userIdMap(List<UserIdProjection> projList){
        return projList.stream()
                .map(UserIdProjection::getId)
                .collect(Collectors.toList());
    }
}
