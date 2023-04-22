package com.cs4523groupb11.Motify.controllers;

import com.cs4523groupb11.Motify.DTO.detailed_entity.ParticipationDTO;
import com.cs4523groupb11.Motify.DTO.detailed_entity.UserDTO;
import com.cs4523groupb11.Motify.entities.Participation;
import com.cs4523groupb11.Motify.entities.User;
import com.cs4523groupb11.Motify.security.JwtTokenUtility;
import com.cs4523groupb11.Motify.services.ParticipationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@RestController
@RequestMapping("/api/userChallenge")
@PreAuthorize("hasRole('ROLE_USER')")
public class ParticipationController {

    private final ParticipationService participationService;

    private final JwtTokenUtility jwt;

    @Autowired
    public ParticipationController(ParticipationService participationService, JwtTokenUtility jwt) {
        this.participationService = participationService;
        this.jwt = jwt;
    }

    @GetMapping("/getAllSelfPublicParticipation")
    public ResponseEntity<List<ParticipationDTO>> getAllSelfPublicParticipation(
            @RequestHeader(name = "Authorization") String auth){
        String username = jwt.getUsernameFromJwtToken(jwt.getFromHeader(auth));
        Optional<List<Participation>> opList =
                participationService.getAllParticipationByUsername(username, false);
        return opList.map(participations ->
                ResponseEntity.ok(participations.stream().map(ParticipationDTO::fromEntity).toList()))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/getAllSelfPrivateParticipation")
    public ResponseEntity<List<ParticipationDTO>> getAllSelfPrivateParticipation(
            @RequestHeader(name = "Authorization") String auth){
        String username = jwt.getUsernameFromJwtToken(jwt.getFromHeader(auth));
        Optional<List<Participation>> opList =
                participationService.getAllParticipationByUsername(username, true);
        return opList.map(participations ->
                        ResponseEntity.ok(participations.stream().map(ParticipationDTO::fromEntity).toList()))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/getOneSelfParticipation/{id}")
    public ResponseEntity<ParticipationDTO> getOneSelfParticipation(@RequestHeader(name = "Authorization") String auth,
                                                                 @PathVariable String id){
        String username = jwt.getUsernameFromJwtToken(jwt.getFromHeader(auth));
        Optional<Participation> opParticipation =
                participationService.getOneParticipationByUsernameAndChallengeId(username, id);
        return opParticipation.map(participation -> ResponseEntity.ok(ParticipationDTO.fromEntity(participation)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("getJoinedChallengesByUserId/{id}")
    public ResponseEntity<List<String>> getJoinedPublicChallengesByUserId(@PathVariable String id){
        Optional<List<Participation>> opList = participationService.getAllParticipationByUserId(id, false);
        return opList.map(participations -> ResponseEntity.ok(participations.stream()
                .map(p -> p.getChallenge().getId()).toList()))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/getParticipantsByChallengeId/{id}")
    public ResponseEntity<List<UserDTO>> getParticipantsByPublicChallengeId(@PathVariable String id){
        Optional<List<User>> opList = participationService.getParticipantsByPublicChallengeId(id);
        return opList.map(users -> ResponseEntity.ok(users.stream().map(UserDTO::fromEntity).toList()))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/join/{id}")
    public ResponseEntity<Void> joinPublicChallenge(@RequestHeader(name = "Authorization") String auth,
                                                         @PathVariable String id){
        String username = jwt.getUsernameFromJwtToken(jwt.getFromHeader(auth));
        try{
            participationService.addParticipationEntry(username, id);
        }catch (NoSuchElementException e){
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok().build();
    }

    @PostMapping("/exit/{id}")
    public ResponseEntity<Void> quitPublicChallenge(@RequestHeader(name = "Authorization") String auth,
                                                         @PathVariable String id){
        String username = jwt.getUsernameFromJwtToken(jwt.getFromHeader(auth));
        try{
            participationService.deleteParticipationEntry(username, id);
        }catch (NoSuchElementException e){
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok().build();
    }



}
