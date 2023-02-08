package com.cs4523groupb11.Motify.controllers;

import com.cs4523groupb11.Motify.entities.PrivateChallenge;
import com.cs4523groupb11.Motify.services.PrivateChallengeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/private")
public class PrivateChallengeController {

    private PrivateChallengeService privateChallengeService;

    @Autowired
    public PrivateChallengeController(PrivateChallengeService privateChallengeService){
        this.privateChallengeService = privateChallengeService;
    }

    @GetMapping("/getByUser/{userId}")
    public ResponseEntity<List<PrivateChallenge>> getPrivateChallengeByUserId(@PathVariable String userId){
        // TODO: Get a list of private challenges by userId
        return ResponseEntity.ok(Collections.emptyList());
    }

    @GetMapping("/getByPcId/{pcId}")
    public ResponseEntity<PrivateChallenge> getPrivateChallengeByPcId(@PathVariable String pcId){
        // TODO: Get a private challenges by pcId
        return ResponseEntity.ok(new PrivateChallenge());
    }

    @PostMapping("/add/{pcId}")
    public ResponseEntity<?> addPrivateChallenge(@RequestBody PrivateChallenge body){
        // TODO: Add a private challenge
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/delete/{pcId}")
    public ResponseEntity<?> deletePrivateChallenge(@PathVariable String pcId){
        // TODO: delete a private challenge
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/modify/{pcId}")
    public ResponseEntity<?> modifyPrivateChallenge(@RequestBody PrivateChallenge body){
        // TODO: modify a private challenge
        return ResponseEntity.noContent().build();
    }
}
