package com.cs4523groupb11.Motify.controllers;

import com.cs4523groupb11.Motify.entities.PrivateChallenge;
import com.cs4523groupb11.Motify.entities.PublicChallenge;
import com.cs4523groupb11.Motify.services.PrivateChallengeService;
import com.cs4523groupb11.Motify.services.PublicChallengeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/public")
public class PublicChallengeController {

    private PublicChallengeService publicChallengeService;

    @Autowired
    public PublicChallengeController(PublicChallengeService publicChallengeService){
        this.publicChallengeService = publicChallengeService;
    }
    @GetMapping("/get/{pcId}")
    public ResponseEntity<PublicChallenge> getPublicChallenge(@PathVariable String pcId){
        // TODO: Get a public challenges by pcId
        return ResponseEntity.ok(new PublicChallenge());
    }

    @PostMapping("/add")
    public ResponseEntity<?> addPublicChallenge(@RequestBody PublicChallenge body){
        // TODO: Add a public challenge
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/delete/{pcId}")
    public ResponseEntity<?> deletePublicChallenge(@PathVariable String pcId){
        // TODO: delete a public challenge
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/modify")
    public ResponseEntity<?> modifyPublicChallenge(@RequestBody PrivateChallenge body){
        // TODO: modify a public challenge
        return ResponseEntity.noContent().build();
    }

}
