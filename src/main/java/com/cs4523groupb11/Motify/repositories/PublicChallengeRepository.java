package com.cs4523groupb11.Motify.repositories;

import com.cs4523groupb11.Motify.entities.ChallengeCategory;
import com.cs4523groupb11.Motify.entities.PublicChallenge;
import com.cs4523groupb11.Motify.entities.User;
import com.cs4523groupb11.Motify.entities.jpaprojections.PublicChallengeIdProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PublicChallengeRepository extends JpaRepository<PublicChallenge, String> {
    Optional<PublicChallenge> findById(String id);

    boolean existsById(String id);

    List<PublicChallengeIdProjection> findAllIds();

    List<PublicChallengeIdProjection> findAllIdsByNameContaining(String name);

    List<PublicChallengeIdProjection> findAllIdsByCategory(ChallengeCategory category);

    List<PublicChallengeIdProjection> findAllIdsByOwner(User owner);

    List<PublicChallengeIdProjection> findAllIdsByParticipantsContaining(User participant);

    boolean existsByOwnerAndName(User user, String Name);

    void deleteById(String id);
}
