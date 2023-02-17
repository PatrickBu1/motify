package com.cs4523groupb11.Motify.repositories;

import com.cs4523groupb11.Motify.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
    Optional<User> findByUserId(String userId);

    Optional<User> findByUsername(String username);

    Optional<User> findByEmail(String email);

    Optional<User> findByUsernameAndPassword(String username, String password);

    Optional<User> createUser(User user);
    Optional<User> deleteUserByUserId(String userId);
    Optional<User> updateUser(User user);

}
