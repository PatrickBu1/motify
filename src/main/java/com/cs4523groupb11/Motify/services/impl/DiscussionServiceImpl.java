package com.cs4523groupb11.Motify.services.impl;


import com.cs4523groupb11.Motify.DTO.request_non_entity.DiscussionPostRequest;
import com.cs4523groupb11.Motify.entities.Challenge;
import com.cs4523groupb11.Motify.entities.Discussion;
import com.cs4523groupb11.Motify.entities.User;
import com.cs4523groupb11.Motify.repositories.ChallengeRepository;
import com.cs4523groupb11.Motify.repositories.DiscussionRepository;
import com.cs4523groupb11.Motify.repositories.UserRepository;
import com.cs4523groupb11.Motify.services.DiscussionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class DiscussionServiceImpl implements DiscussionService {

    private final DiscussionRepository discussionRepository;

    private final ChallengeRepository challengeRepository;

    private final UserRepository userRepository;

    @Value("${spring.servlet.multipart.location}")
    private String serverFilePath;

    @Autowired
    public DiscussionServiceImpl(DiscussionRepository discussionRepository,
                                 ChallengeRepository challengeRepository, UserRepository userRepository) {
        this.discussionRepository = discussionRepository;
        this.challengeRepository = challengeRepository;
        this.userRepository = userRepository;
    }


    @Transactional
    public Optional<Page<Discussion>> getPageByChallengeId(String email, String id, Integer page, Integer size){
        Optional<Challenge> opChallenge = challengeRepository.findById(id);
        if (opChallenge.isEmpty() ||
                (opChallenge.get().getIsPrivate() && !opChallenge.get().getOwner().getEmail().equals(email))){
            return Optional.empty();
        }
        Pageable pageable = PageRequest.of(page, size, Sort.by("date").descending());
        return Optional.of(discussionRepository.findPageByChallenge(opChallenge.get(), pageable));
    }

    @Transactional
    public Optional<List<Discussion>> getAllByChallengeId(String email, String id){
        Optional<Challenge> opChallenge = challengeRepository.findById(id);
        if (opChallenge.isEmpty() ||
                (opChallenge.get().getIsPrivate() && !opChallenge.get().getOwner().getEmail().equals(email))){
            return Optional.empty();
        }
        return Optional.of(discussionRepository.findAllByChallenge(opChallenge.get()));
    }

    @Transactional
    public Optional<Discussion> post(String email, DiscussionPostRequest req, MultipartFile image) {
        Optional<User> opUser = userRepository.findById(req.getOwnerId());
        Optional<Challenge> opChallenge = challengeRepository.findById(req.getChallengeId());
        if (opChallenge.isEmpty() || opUser.isEmpty() || !opUser.get().getEmail().equals(email)){
            return Optional.empty();
        }

        Date currTime = new Date();
        String filename = opUser.get().getId() + "_discussion_" + currTime + "_" + req.getChallengeId() + ".jpg";
        Path filePath = Paths.get(serverFilePath + "/" + filename);

        Discussion discussion = new Discussion(opUser.get(), req.getContent(),
                currTime, opChallenge.get(), (image != null)? filename: null);

        Discussion res;
        try{
            res = discussionRepository.saveAndFlush(discussion);
        }catch(Exception e){
            return Optional.empty();
        }

        try {
            if (image != null) {
                Files.copy(image.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
            }
        }catch(Exception e){    // IOException
            return Optional.empty();
        }

        return Optional.of(res);

    }

    @Transactional
    public void deletePost(String email, String id) throws NoSuchElementException{
        Optional<User> opUser = userRepository.findByEmail(email);
        Optional<Discussion> opCid = discussionRepository.findById(id);
        if (opCid.isEmpty() || opUser.isEmpty()){
            throw new NoSuchElementException();
        }
        discussionRepository.deleteById(id);
    }


}
