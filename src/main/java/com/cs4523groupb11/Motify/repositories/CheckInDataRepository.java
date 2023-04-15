package com.cs4523groupb11.Motify.repositories;

import com.cs4523groupb11.Motify.entities.Challenge;
import com.cs4523groupb11.Motify.entities.CheckInData;
import com.cs4523groupb11.Motify.entities.User;
import io.micrometer.common.lang.NonNullApi;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

@NonNullApi
public interface CheckInDataRepository extends JpaRepository<CheckInData, String> {
    List<CheckInData> findAllByUserAndChallenge(User user, Challenge challenge);

    Page<CheckInData> findPageByChallenge(Challenge challenge, Pageable pageable);

    List<CheckInData> findAllByChallenge(Challenge challenge);

    void deleteById(String id);
}
