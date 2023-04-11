package com.cs4523groupb11.Motify.services;

import com.cs4523groupb11.Motify.entities.Challenge;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

public interface ChallengeService {
    public Optional<Challenge> getById(String id, String username);

    public Page<Challenge> getPage(Integer page, Integer size);

    public Optional<List<Challenge>> getAllPrivateChallenges(String username);

    public Optional<List<Challenge>> getAllPublicChallengesByOwner(String id);

    public Optional<String> create(Challenge c);

    public Optional<String> update(Challenge c);

    public void delete(String id) throws NoSuchElementException;

}