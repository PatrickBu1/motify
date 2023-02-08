package com.cs4523groupb11.Motify.controllers;

import com.cs4523groupb11.Motify.entities.CheckInData;
import com.cs4523groupb11.Motify.entities.PublicChallenge;
import com.cs4523groupb11.Motify.services.UserChallengeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.quartz.QuartzTransactionManager;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/userChallenge")
public class UserChallengeController {
    private UserChallengeService userChallengeService;

    @Autowired
    public UserChallengeController(UserChallengeService userChallengeService){
        this.userChallengeService = userChallengeService;
    }

    @GetMapping("/getPublicChallengesByUserId/{userId}")
    public ResponseEntity<List<PublicChallenge>> getPublicChallengesByUserId(@PathVariable String userId){
        //TODO
        return ResponseEntity.ok(Collections.emptyList());
    }

    @GetMapping("/getUsersByPcId/{pcId}")
    public ResponseEntity<List<String>> getUsersByPcId(@PathVariable String pcId){
        //TODO
        return ResponseEntity.ok(Collections.emptyList());
    }

    @PostMapping("/join/{userId}/{pcId}")
    public ResponseEntity<?> joinPublicChallenge(@PathVariable("userId") String userId, @PathVariable("pcId") String pcId){
        //TODO
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/exit/{userId}/{pcId}")
    public ResponseEntity<?> exitPublicChallenge(@PathVariable("userId") String userId, @PathVariable("pcId") String pcId){
        //TODO
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/checkIn/{userId}/{pcId}")
    public ResponseEntity<?> checkInPublicChallenge(@PathVariable("userId") String userId,
                                                    @PathVariable("pcId") String pcId,
                                                    @RequestBody CheckInData body){
        //TODO
        return ResponseEntity.noContent().build();
    }

}
