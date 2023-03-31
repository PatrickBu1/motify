package com.cs4523groupb11.Motify.services.impl;

import com.cs4523groupb11.Motify.entities.ChallengeCategory;
import com.cs4523groupb11.Motify.entities.PublicChallenge;
import com.cs4523groupb11.Motify.entities.User;
import com.cs4523groupb11.Motify.repositories.PublicChallengeRepository;
import com.cs4523groupb11.Motify.repositories.UserRepository;
import com.cs4523groupb11.Motify.services.PublicChallengeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static com.cs4523groupb11.Motify.utils.JpaProjectionMapper.publicChallengeIdMap;

@Service
public class PublicChallengeServiceImpl implements PublicChallengeService {

    @Autowired
    private PublicChallengeRepository publicChallengeRepository;

    @Autowired
    private UserRepository userRepository;

    public Optional<PublicChallenge> getById(String id){
        return publicChallengeRepository.findById(id);
    }

    public List<String> getAllIds(){
        return publicChallengeIdMap(publicChallengeRepository.findAllIds());
    }

    public List<String> getAllIdsByNameContaining(String name){
        return publicChallengeIdMap(publicChallengeRepository.findAllIdsByNameContaining(name));
    }

    public List<String> getAllIdsByCategory(ChallengeCategory category){
        return publicChallengeIdMap(publicChallengeRepository.findAllIdsByCategory(category));
    }

    @Transactional
    public Optional<List<String>> getAllIdsByOwnerId(String id){
        Optional<User> opUser = userRepository.findById(id);
        return opUser.map(user -> publicChallengeIdMap(publicChallengeRepository.findAllIdsByOwner(user)));
    }

    @Transactional
    public Optional<PublicChallenge> create(PublicChallenge pc){
        if (pc.getId() != null ||
                publicChallengeRepository.existsByOwnerAndName(pc.getOwner(), pc.getName())){
            return Optional.empty();
        }
        return Optional.of(publicChallengeRepository.save(pc));
    }

    @Transactional
    public Optional<PublicChallenge> deleteById(String id){
        Optional<PublicChallenge> resPc = publicChallengeRepository.findById(id);
        if (resPc.isPresent()){
            publicChallengeRepository.deleteById(id);
            return resPc;
        }
        return Optional.empty();
    }

    @Transactional
    public Optional<PublicChallenge> update(PublicChallenge pc){
        PublicChallenge resPc;
        if (publicChallengeRepository.existsById(pc.getId())){
            try{
                resPc = publicChallengeRepository.save(pc);
            }catch(DataIntegrityViolationException e){
                return Optional.empty();
            }
            return Optional.of(resPc);
        }
        return Optional.empty();
    }

}
