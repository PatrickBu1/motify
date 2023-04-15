package com.cs4523groupb11.Motify.repositories;


import com.cs4523groupb11.Motify.entities.Challenge;
import com.cs4523groupb11.Motify.entities.Participation;
import com.cs4523groupb11.Motify.entities.User;
import io.micrometer.common.lang.NonNullApi;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

@NonNullApi
public interface ParticipationRepository extends JpaRepository<Participation, String> {

    List<Participation> findAllByUserAndIsPrivate(User user, Boolean isPrivate);

    Optional<Participation> findByUserAndChallenge(User user, Challenge challenge);

//    @Query("SELECT DISTINCT p.challenge FROM Participation p WHERE p.user = :user and p.isPrivate = false")
//    List<Challenge> findAllChallengeByIsPrivateFalseAndUser(User user);

    @Query("SELECT DISTINCT p.user FROM Participation p WHERE p.challenge = :challenge")
    List<User> findAllUserByChallenge(Challenge challenge);

    void deleteByUserAndChallenge(User user, Challenge challenge);
}
