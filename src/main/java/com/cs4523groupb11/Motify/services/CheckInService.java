package com.cs4523groupb11.Motify.services;

import com.cs4523groupb11.Motify.DTO.detailed_entity.CheckInDataDTO;
import com.cs4523groupb11.Motify.entities.CheckInData;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

public interface CheckInService {
    Optional<Page<CheckInData>> getPageByChallengeId(String username, String id, Integer page, Integer size);

    Optional<List<CheckInData>> getAllByChallengeId(String username, String id);

    void checkIn(String username, CheckInDataDTO dto);

    void deleteCheckIn(String username, String id) throws NoSuchElementException;
}
