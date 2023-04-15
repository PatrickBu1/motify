package com.cs4523groupb11.Motify.services.impl;


import com.cs4523groupb11.Motify.DTO.detailed_entity.CheckInDataDTO;
import com.cs4523groupb11.Motify.entities.Challenge;
import com.cs4523groupb11.Motify.entities.CheckInData;
import com.cs4523groupb11.Motify.entities.User;
import com.cs4523groupb11.Motify.repositories.ChallengeRepository;
import com.cs4523groupb11.Motify.repositories.CheckInDataRepository;
import com.cs4523groupb11.Motify.repositories.UserRepository;
import com.cs4523groupb11.Motify.services.CheckInService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class CheckInServiceImpl implements CheckInService {

    private final CheckInDataRepository checkInDataRepository;

    private final ChallengeRepository challengeRepository;

    private final UserRepository userRepository;

    @Autowired
    public CheckInServiceImpl(CheckInDataRepository checkInDataRepository, ChallengeRepository challengeRepository, UserRepository userRepository) {
        this.checkInDataRepository = checkInDataRepository;
        this.challengeRepository = challengeRepository;
        this.userRepository = userRepository;
    }


    @Transactional
    public Optional<Page<CheckInData>> getPageByChallengeId(String username, String id, Integer page, Integer size){
        Optional<Challenge> opChallenge = challengeRepository.findById(id);
        if (opChallenge.isEmpty() ||
                (opChallenge.get().getIsPrivate() && !opChallenge.get().getOwner().getUsername().equals(username))){
            return Optional.empty();
        }
        Pageable pageable = PageRequest.of(page, size, Sort.by("date").descending());
        return Optional.of(checkInDataRepository.findPageByChallenge(opChallenge.get(), pageable));
    }

    @Transactional
    public Optional<List<CheckInData>> getAllByChallengeId(String username, String id){
        Optional<Challenge> opChallenge = challengeRepository.findById(id);
        if (opChallenge.isEmpty() ||
                (opChallenge.get().getIsPrivate() && !opChallenge.get().getOwner().getUsername().equals(username))){
            return Optional.empty();
        }
        return Optional.of(checkInDataRepository.findAllByChallenge(opChallenge.get()));
    }

    @Transactional
    public void checkIn(String username, CheckInDataDTO dto) {
        Optional<User> opUser = userRepository.findByUsername(username);
        Optional<Challenge> opChallenge = challengeRepository.findById(dto.getChallenge());
        if (opChallenge.isEmpty() || opUser.isEmpty()){
            throw new NoSuchElementException();
        }
        CheckInData checkInData = new CheckInData(opUser.get(), dto.getTitle(), dto.getContent(),
                dto.getDate(), opChallenge.get());
        checkInDataRepository.saveAndFlush(checkInData);
    }

    @Transactional
    public void deleteCheckIn(String username, String id) throws NoSuchElementException{
        Optional<User> opUser = userRepository.findByUsername(username);
        Optional<CheckInData> opCid = checkInDataRepository.findById(id);
        if (opCid.isEmpty() || opUser.isEmpty()){
            throw new NoSuchElementException();
        }
        checkInDataRepository.deleteById(id);
    }
}
