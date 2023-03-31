package com.cs4523groupb11.Motify.entities.jpaprojections;

import com.cs4523groupb11.Motify.entities.PublicChallenge;
import org.springframework.data.rest.core.config.Projection;

@Projection(name = "publicChallengeIdProjection", types = { PublicChallenge.class })
public interface PublicChallengeIdProjection {
    String getId();
}
