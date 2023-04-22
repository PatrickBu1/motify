package com.cs4523groupb11.Motify.repositories;

import com.cs4523groupb11.Motify.entities.User;
import io.micrometer.common.lang.NonNullApi;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@NonNullApi
@Repository
public interface UserRepository extends JpaRepository<User, String> {

    Optional<User> findById(String id);

    boolean existsById(String id);

    List<User> findAllByUsername(String username);

    boolean existsByUsername(String username);

    Optional<User> findByEmail(String email);

    boolean existsByEmail(String email);

    List<User> findAllByIdIn(List<String> userIds);

    void deleteById(String id);

}
