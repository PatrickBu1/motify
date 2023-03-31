package com.cs4523groupb11.Motify.controllers;

import com.cs4523groupb11.Motify.entities.CheckInData;
import com.cs4523groupb11.Motify.entities.PublicChallenge;
import com.cs4523groupb11.Motify.payload.CheckInDataPayload;
import com.cs4523groupb11.Motify.services.UserChallengeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/userChallenge")
public class UserChallengeController {

    @Autowired
    private UserChallengeService userChallengeService;

    @GetMapping("/getAllPublicChallengesByUserId/{id}")
    public ResponseEntity<List<String>> getAllPublicChallengesByUserId(@PathVariable String id){
        Optional<List<String>> opList = userChallengeService.getAllPublicChallengeIdsByUserId(id);
        return opList.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @GetMapping("/getParticipantsByPublicChallengeId/{id}")
    public ResponseEntity<List<String>> getParticipantsByPublicChallengeId(@PathVariable String id){
        Optional<List<String>> opList = userChallengeService.getParticipantIdsByPublicChallengeId(id);
        return opList.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @PostMapping("/join/{userId}/{pcId}")
    public ResponseEntity<PublicChallenge> joinPublicChallenge(@PathVariable("userId") String userId, @PathVariable("pcId") String pcId){
        Optional<PublicChallenge> opPc = userChallengeService.joinPublicChallenge(userId, pcId);
        return opPc.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @PostMapping("/exit/{userId}/{pcId}")
    public ResponseEntity<PublicChallenge> quitPublicChallenge(@PathVariable("userId") String userId, @PathVariable("pcId") String pcId){
        Optional<PublicChallenge> opPc = userChallengeService.quitPublicChallenge(userId, pcId);
        return opPc.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @PostMapping("/checkIn/{userId}/{pcId}")
    public ResponseEntity<CheckInData> checkInPublicChallenge(@RequestBody CheckInData checkInData){


    }

    @DeleteMapping("/removeCheckInData/{checkInDataId}/{date}")
    public ResponseEntity<?> deleteCheckInDataById(@PathVariable String checkInDataId){

    }

}
