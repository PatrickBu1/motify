package com.cs4523groupb11.Motify.repositories;

import com.cs4523groupb11.Motify.entities.Challenge;
import com.cs4523groupb11.Motify.entities.User;
import io.micrometer.common.lang.NonNullApi;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;

import java.util.List;
import java.util.Optional;

@NonNullApi
public interface ChallengeRepository extends JpaRepository<Challenge, String> {

        Optional<Challenge> findById(String id);

        Page<Challenge> findByIsPrivateFalse(Pageable pageable);

        List<Challenge> findByIsPrivateTrueAndOwner(User owner);

        List<Challenge> findByIsPrivateFalseAndOwner(User owner);

        boolean existsById(String id);

        // boolean existsByOwnerAndName(User user, String Name);

        void deleteById(String id);

}
