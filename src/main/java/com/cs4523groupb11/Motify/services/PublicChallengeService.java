package com.cs4523groupb11.Motify.services;

import com.cs4523groupb11.Motify.entities.ChallengeCategory;
import com.cs4523groupb11.Motify.entities.PublicChallenge;

import java.util.List;
import java.util.Optional;

public interface PublicChallengeService {
    Optional<PublicChallenge> getById(String id);

    List<String> getAllIds();

    List<String> getAllIdsByNameContaining(String name);

    List<String> getAllIdsByCategory(ChallengeCategory category);

    Optional<List<String>> getAllIdsByOwnerId(String id);

    Optional<PublicChallenge> create(PublicChallenge pc);

    Optional<PublicChallenge> deleteById(String id);

    Optional<PublicChallenge> update(PublicChallenge pc);

}
