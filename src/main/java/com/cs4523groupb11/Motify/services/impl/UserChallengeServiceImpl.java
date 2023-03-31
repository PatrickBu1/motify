package com.cs4523groupb11.Motify.services.impl;


import com.cs4523groupb11.Motify.entities.CheckInData;
import com.cs4523groupb11.Motify.entities.PrivateChallenge;
import com.cs4523groupb11.Motify.entities.PublicChallenge;
import com.cs4523groupb11.Motify.entities.User;
import com.cs4523groupb11.Motify.payload.CheckInDataPayload;
import com.cs4523groupb11.Motify.repositories.CheckInDataRepository;
import com.cs4523groupb11.Motify.repositories.PrivateChallengeRepository;
import com.cs4523groupb11.Motify.repositories.PublicChallengeRepository;
import com.cs4523groupb11.Motify.repositories.UserRepository;
import com.cs4523groupb11.Motify.services.UserChallengeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static com.cs4523groupb11.Motify.utils.JpaProjectionMapper.publicChallengeIdMap;
import static com.cs4523groupb11.Motify.utils.JpaProjectionMapper.userIdMap;

@Service
public class UserChallengeServiceImpl implements UserChallengeService {

    @Autowired
    private PublicChallengeRepository publicChallengeRepository;

    @Autowired
    private PrivateChallengeRepository privateChallengeRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CheckInDataRepository checkInDataRepository;


    @Transactional
    public Optional<List<String>> getAllPublicChallengeIdsByUserId(String id){
        Optional<User> opUser = userRepository.findById(id);
        return opUser.map(user -> publicChallengeIdMap(publicChallengeRepository.findAllIdsByParticipantsContaining(user)));
    }

    public Optional<List<String>> getParticipantIdsByPublicChallengeId(String id){
        Optional<PublicChallenge> opPc = publicChallengeRepository.findById(id);
        return opPc.map(publicChallenge -> userIdMap(userRepository.findAllByPublicChallengeListContaining(publicChallenge)));
    }

    @Transactional
    public Optional<PublicChallenge> joinPublicChallenge(String userId, String pcId){
        Optional<User> opUser = userRepository.findById(userId);
        Optional<PublicChallenge> opPc = publicChallengeRepository.findById(pcId);
        if (opUser.isEmpty() || opPc.isEmpty()){
            return Optional.empty();
        }
        User user = opUser.get();
        PublicChallenge pc = opPc.get();
        if (!user.getPublicChallengeList().contains(pc)) {
            user.getPublicChallengeList().add(pc);
            return Optional.of(pc);
        }
        return Optional.empty();
    }

    @Transactional
    public Optional<PublicChallenge> quitPublicChallenge(String userId, String pcId){
        Optional<User> opUser = userRepository.findById(userId);
        Optional<PublicChallenge> opPc = publicChallengeRepository.findById(pcId);
        if (opUser.isEmpty() || opPc.isEmpty()){
            return Optional.empty();
        }
        User user = opUser.get();
        PublicChallenge pc = opPc.get();
        if (!user.getPublicChallengeList().contains(pc)){
            return Optional.empty();
        }
        user.getPublicChallengeList().remove(pc);
        userRepository.save(user);
        return Optional.of(pc);
    }

    @Transactional
    public Optional<CheckInData> checkInToPublicChallenge(CheckInData checkInData){
        Optional<User> opUser = userRepository.findById(checkInData.getOwner().getId());
        Optional<PublicChallenge> opPc = publicChallengeRepository.findById(checkInData.getPublicChallenge().getId());
        if (opPc.isEmpty() || opUser.isEmpty() || checkInData.getId() != null){
            return Optional.empty();
        }
        checkInDataRepository.save(checkInData);
        return Optional.of(checkInData);
    }

    @Transactional
    public Optional<CheckInData> deleteCheckInDataById(String checkInDataId){
        Optional<CheckInData> opCID = checkInDataRepository.findById(checkInDataId);
        if (opCID.isPresent()){
            checkInDataRepository.deleteById(checkInDataId);
            return opCID;
        }
        return Optional.empty();
    }

    public CheckInDataPayload checkInDataToPayload(CheckInData cd){
        return new CheckInDataPayload(cd.getOwner().getId(), cd.getContent(), cd.getDate(),
                cd.getPrivateChallenge().getId(),
                cd.getPublicChallenge().getId());
    }

    @Transactional
    public Optional<CheckInData> payloadToCheckInData(CheckInDataPayload cdp){
        Optional<User> opUser = userRepository.findById(cdp.getOwner());
        Optional<PublicChallenge> opPuc = publicChallengeRepository.findById(cdp.getPublicChallenge());
        Optional<PrivateChallenge> opPrc = privateChallengeRepository.findById(cdp.getPrivateChallenge());
        if (opUser.isPresent() && !(opPuc.isPresent() && opPrc.isPresent())){
            CheckInData res = new CheckInData(opUser.get(), cdp.getContent(), cdp.getDate(),
                    null, null);
            if (opPuc.isPresent()){
                res.setPublicChallenge(opPuc.get());
            }else opPrc.ifPresent(res::setPrivateChallenge);
            return Optional.of(res);
        }
        return Optional.empty();
    }
}
