package com.cs4523groupb11.Motify.entities.jpaprojections;


import com.cs4523groupb11.Motify.entities.PrivateChallenge;
import org.springframework.data.rest.core.config.Projection;

@Projection(name = "privateChallengeIdProjection", types = { PrivateChallenge.class })
public interface PrivateChallengeIdProjection {
    String getId();
}
