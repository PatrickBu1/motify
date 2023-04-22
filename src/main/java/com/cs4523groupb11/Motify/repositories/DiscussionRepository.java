package com.cs4523groupb11.Motify.repositories;

import com.cs4523groupb11.Motify.entities.Challenge;
import com.cs4523groupb11.Motify.entities.Discussion;
import com.cs4523groupb11.Motify.entities.User;
import io.micrometer.common.lang.NonNullApi;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@NonNullApi
@Repository
public interface DiscussionRepository extends JpaRepository<Discussion, String> {
    List<Discussion> findAllByOwnerAndChallenge(User owner, Challenge challenge);

    Page<Discussion> findPageByChallenge(Challenge challenge, Pageable pageable);

    List<Discussion> findAllByChallenge(Challenge challenge);

    void deleteById(String id);
}
