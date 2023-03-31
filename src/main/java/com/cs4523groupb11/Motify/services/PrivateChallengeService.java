package com.cs4523groupb11.Motify.services;

import com.cs4523groupb11.Motify.entities.PrivateChallenge;

import java.util.List;
import java.util.Optional;

public interface PrivateChallengeService {
    public Optional<PrivateChallenge> findById(String id);

    public Optional<List<PrivateChallenge>> findAllByOwnerId(String userId);

    public Optional<PrivateChallenge> create(PrivateChallenge pc);

    public Optional<PrivateChallenge> update(PrivateChallenge pc);

    public Optional<PrivateChallenge> deleteById(String id);

}
