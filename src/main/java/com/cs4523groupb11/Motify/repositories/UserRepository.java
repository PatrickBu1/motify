package com.cs4523groupb11.Motify.repositories;

import com.cs4523groupb11.Motify.entities.PublicChallenge;
import com.cs4523groupb11.Motify.entities.User;
import com.cs4523groupb11.Motify.entities.jpaprojections.UserIdProjection;
import jakarta.annotation.Nonnull;
import jakarta.servlet.http.PushBuilder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
f
    Optional<User> findById(String id);

    boolean existsById(String id);

    Optional<UserIdProjection> findByUsername(String username);

    boolean existsByUsername(String username);

    Optional<UserIdProjection> findByEmail(String email);

    boolean existsByEmail(String email);

    Optional<UserIdProjection> findByUsernameAndPassword(String username, String password);

    boolean existsByUsernameAndPassword(String username, String password);

    List<UserIdProjection> findAllByPublicChallengeListContaining(PublicChallenge pc);

    void deleteById(String id);

}
