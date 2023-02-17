package com.cs4523groupb11.Motify.repositories;

import com.cs4523groupb11.Motify.entities.PrivateChallenge;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PrivateChallengeRepository extends JpaRepository<PrivateChallenge, String> {
        Optional<PrivateChallenge> findBySomeField(String someField);
}
