package com.cs4523groupb11.Motify.repositories;

import com.cs4523groupb11.Motify.entities.PrivateChallenge;
import com.cs4523groupb11.Motify.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PrivateChallengeRepository extends JpaRepository<PrivateChallenge, String> {

        Optional<PrivateChallenge> findById(String id);

        boolean existsById(String id);

        Optional<List<PrivateChallenge>> findAllByOwner(User owner);

        boolean existsByOwnerAndName(User user, String Name);

        void deleteById(String id);

}
