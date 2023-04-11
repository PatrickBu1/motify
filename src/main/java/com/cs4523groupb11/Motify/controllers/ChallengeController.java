package com.cs4523groupb11.Motify.controllers;

import com.cs4523groupb11.Motify.DTO.abbreviated_entity.ChallengeDTOShort;
import com.cs4523groupb11.Motify.DTO.detailed_entity.ChallengeDTO;
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
@PreAuthorize("hasRole('USER')")
public class ChallengeController {

    @Autowired
    private ChallengeService challengeService;

    @Autowired
    private JwtTokenUtility jwt;

    /*
    Get one challenge by ID.
    Illegal access: can't get other user's private challenge data.
    returns a ChallengeDTO if retrieved, 404 if ID does not exist or illegal access.
     */
    @GetMapping("/getOne/{id}")
    public ResponseEntity<ChallengeDTO> getById(@RequestHeader(name = "Authorization") String auth,
                                               @PathVariable String id){
        String username = jwt.getUsernameFromJwtToken(jwt.getFromHeader(auth));
        Optional<Challenge> opChallenge = challengeService.getById(id, username);
        return opChallenge.map(challenge -> ResponseEntity.ok(ChallengeDTO.fromEntity(challenge)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    /*
    Get a page of public challenges in abbreviated form.
    returns a Page of public challenges if retrieved, 404 if ID does not exist or illegal access.
     */
    @GetMapping("/getPublicPage/{page}/{size}")
    public ResponseEntity<Page<ChallengeDTOShort>> getPublicPage(@PathVariable Integer page,
                                                                 @PathVariable Integer size){
        Page<Challenge> resPage = challengeService.getPage(page, size);
        return ResponseEntity.ok(resPage.map(ChallengeDTOShort::fromEntity));
    }

    /*
    Get all private challenges created by one user.
     */

    @GetMapping("/getAllPrivate")
    public ResponseEntity<List<ChallengeDTO>> getAllPrivate(@RequestHeader(name = "Authorization") String auth) {
        String username = jwt.getUsernameFromJwtToken(jwt.getFromHeader(auth));
        Optional<List<Challenge>> res = challengeService.getAllPrivateChallenges(username);
        return res.map(challenges -> ResponseEntity.ok(challenges.stream().map(ChallengeDTO::fromEntity).toList()))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    /*
    Get all public challenges created by one user.
    Returns 400 if id is incorrect (user does not exist)
     */
    @GetMapping("/getAllPublicByOwner/{id}")
    public ResponseEntity<List<ChallengeDTO>> getAllPublicByOwner(@PathVariable String id) {
        Optional<List<Challenge>> opList = challengeService.getAllPublicChallengesByOwner(id);
        return opList.map(challenges -> ResponseEntity.ok(challenges.stream().map(ChallengeDTO::fromEntity).toList()))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    /*
    Creates a public / private challenge.
    returns 400 if name + owner pair exists in database.
     */
    @PostMapping("/create")
    public ResponseEntity<String> create(@RequestBody ChallengeDTO challengeDTO){
        Challenge challenge = ChallengeDTO.toEntity(challengeDTO);
        Optional<String> opId = challengeService.create(challenge);
        return opId.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.badRequest().build());
    }

    /*
    Updates a public / private challenge.
    returns 400 if name + owner pair exists in database after modification.
     */
    @PutMapping("/update")
    public ResponseEntity<String> update(@RequestBody ChallengeDTO challengeDTO){
        Challenge challenge = ChallengeDTO.toEntity(challengeDTO);
        Optional<String> opId = challengeService.update(challenge);
        return opId.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.badRequest().build());
    }

    /*
    Deletes a public / private challenge.
    returns 400 if ID is not found or the user has no permission to update.
     */
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id){
        try{
            challengeService.delete(id);
        }catch (NoSuchElementException e){
            ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok().build();
    }

}
