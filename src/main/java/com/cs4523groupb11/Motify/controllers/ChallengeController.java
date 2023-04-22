package com.cs4523groupb11.Motify.controllers;

import com.cs4523groupb11.Motify.DTO.entity.ChallengeDTO;
import com.cs4523groupb11.Motify.entities.Challenge;
import com.cs4523groupb11.Motify.security.JwtTokenUtility;
import com.cs4523groupb11.Motify.services.ChallengeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@RestController
@RequestMapping("/api/challenge")
@PreAuthorize("hasRole('ROLE_USER')")
public class ChallengeController {

    private final ChallengeService challengeService;

    private final JwtTokenUtility jwt;

    @Autowired
    public ChallengeController(ChallengeService challengeService, JwtTokenUtility jwt) {
        this.challengeService = challengeService;
        this.jwt = jwt;
    }


    @GetMapping("/getById/{id}")
    public ResponseEntity<ChallengeDTO> getById(@RequestHeader(name = "Authorization") String auth,
                                               @PathVariable String id){
        String email = jwt.getFromHeader(auth);
        Optional<Challenge> opChallenge = challengeService.getById(id, email);
        return opChallenge.map(challenge -> ResponseEntity.ok(ChallengeDTO.fromEntity(challenge)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }


    @GetMapping("/getPublicPage/{page}/{size}")
    public ResponseEntity<Page<ChallengeDTO>> getPublicPage(@PathVariable Integer page,
                                                                 @PathVariable Integer size){
        Page<Challenge> resPage = challengeService.getPage(page, size);
        return ResponseEntity.ok(resPage.map(ChallengeDTO::fromEntity));
    }


    @GetMapping("/getAllPrivate")
    public ResponseEntity<List<ChallengeDTO>> getAllPrivate(@RequestHeader(name = "Authorization") String auth) {
        String username = jwt.getFromHeader(auth);
        Optional<List<Challenge>> res = challengeService.getAllPrivateChallenges(username);
        return res.map(challenges -> ResponseEntity.ok(challenges.stream().map(ChallengeDTO::fromEntity).toList()))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }


    @GetMapping("/getAllPublicByOwner/{id}")
    public ResponseEntity<List<ChallengeDTO>> getAllPublicByOwner(@PathVariable String id) {
        Optional<List<Challenge>> opList = challengeService.getAllPublicChallengesByOwner(id);
        return opList.map(challenges -> ResponseEntity.ok(challenges.stream().map(ChallengeDTO::fromEntity).toList()))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }


    @PostMapping("/create")
    public ResponseEntity<String> create(@RequestHeader(name = "Authorization") String auth,
                                         @RequestBody ChallengeDTO challengeDTO){
        String username = jwt.getFromHeader(auth);
        Optional<String> opId = challengeService.create(username, challengeDTO);
        return opId.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.badRequest().build());
    }


    @PutMapping("/update")
    public ResponseEntity<String> update(@RequestHeader(name = "Authorization") String auth,
                                         @RequestBody ChallengeDTO challengeDTO){
        String username = jwt.getFromHeader(auth);
        Optional<String> opId = challengeService.update(username, challengeDTO);
        return opId.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.badRequest().build());
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@RequestHeader(name = "Authorization") String auth,
                                       @PathVariable String id){
        String username = jwt.getFromHeader(auth);
        try{
            challengeService.delete(username, id);
        }catch (NoSuchElementException e){
            ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok().build();
    }
}
