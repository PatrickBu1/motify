package com.cs4523groupb11.Motify.repositories;

import com.cs4523groupb11.Motify.entities.Challenge;
import com.cs4523groupb11.Motify.entities.CheckInData;
import com.cs4523groupb11.Motify.entities.User;
import io.micrometer.common.lang.NonNullApi;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@NonNullApi
@Repository
public interface CheckInDataRepository extends JpaRepository<CheckInData, String> {
    List<CheckInData> findAllByOwnerAndChallenge(User owner, Challenge challenge);

    Page<CheckInData> findPageByChallenge(Challenge challenge, Pageable pageable);

    List<CheckInData> findAllByChallenge(Challenge challenge);

    void deleteById(String id);
}
