package com.cs4523groupb11.Motify.controllers;

import com.cs4523groupb11.Motify.DTO.entity.ChallengeDTO;
import com.cs4523groupb11.Motify.DTO.entity.ParticipationDTO;
import com.cs4523groupb11.Motify.DTO.entity.UserDTO;
import com.cs4523groupb11.Motify.DTO.request_non_entity.CheckInRequest;
import com.cs4523groupb11.Motify.entities.Challenge;
import com.cs4523groupb11.Motify.entities.Participation;
import com.cs4523groupb11.Motify.entities.User;
import com.cs4523groupb11.Motify.security.JwtTokenUtility;
import com.cs4523groupb11.Motify.services.ParticipationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@RestController
@RequestMapping("/api/participation")
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
        String email = jwt.getFromHeader(auth);
        Optional<List<Participation>> opList =
                participationService.getAllParticipationByEmail(email, false);
        return opList.map(participations ->
                ResponseEntity.ok(participations.stream().map(ParticipationDTO::fromEntity).toList()))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/getAllSelfPrivateParticipation")
    public ResponseEntity<List<ParticipationDTO>> getAllSelfPrivateParticipation(
            @RequestHeader(name = "Authorization") String auth){
        String username = jwt.getFromHeader(auth);
        Optional<List<Participation>> opList =
                participationService.getAllParticipationByEmail(username, true);
        return opList.map(participations ->
                        ResponseEntity.ok(participations.stream().map(ParticipationDTO::fromEntity).toList()))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/getOneSelfParticipation/{id}")
    public ResponseEntity<ParticipationDTO> getOneSelfParticipation(@RequestHeader(name = "Authorization") String auth,
                                                                 @PathVariable String id){
        String email = jwt.getFromHeader(auth);
        Optional<Participation> opParticipation =
                participationService.getOneParticipationByEmailAndChallengeId(email, id);
        return opParticipation.map(participation -> ResponseEntity.ok(ParticipationDTO.fromEntity(participation)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("getJoinedPublicChallengesByUserId/{id}")
    public ResponseEntity<List<String>> getJoinedPublicChallengesByUserId(@PathVariable String id){
        Optional<List<Participation>> opList = participationService.getAllParticipationByUserId(id, false);
        return opList.map(participations -> ResponseEntity.ok(participations.stream()
                .map(p -> p.getChallenge().getId()).toList()))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/getParticipantsByPublicChallengeId/{id}")
    public ResponseEntity<List<UserDTO>> getParticipantsByPublicChallengeId(@PathVariable String id){
        Optional<List<User>> opList = participationService.getParticipantsByPublicChallengeId(id);
        return opList.map(users -> ResponseEntity.ok(users.stream().map(UserDTO::fromEntity).toList()))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/getSelfChallengesByDate/{date}")
    public ResponseEntity<List<ChallengeDTO>> getSelfChallengesByDate(@RequestHeader(name = "Authorization") String auth,
                                                                           @PathVariable Date date){
        String email = jwt.getFromHeader(auth);
        Optional<List<Challenge>> res = participationService.getSelfChallengesByDate(email, date);
        return res.map(challenges -> ResponseEntity.ok(challenges.stream().map(ChallengeDTO::fromEntity).toList()))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @PostMapping("/checkin")
    public ResponseEntity<ParticipationDTO> checkIn(@RequestHeader(name="Authorization") String auth,
                                                    @RequestBody CheckInRequest req
                                                    ){
        String email = jwt.getFromHeader(auth);
        Optional<Participation> res = participationService.checkIn(email, req.getChallengeId(), req.getAmount(),
                req.getDuration());
        return res.map(participation -> ResponseEntity.ok(ParticipationDTO.fromEntity(participation)))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }


    @PostMapping("/joinPublic/{id}")
    public ResponseEntity<Void> joinPublicChallenge(@RequestHeader(name = "Authorization") String auth,
                                                         @PathVariable String id){
        String email = jwt.getFromHeader(auth);
        try{
            participationService.addParticipationEntry(email, id);
        }catch (NoSuchElementException e){
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok().build();
    }

    @PostMapping("/quitPublic/{id}")
    public ResponseEntity<Void> quitPublicChallenge(@RequestHeader(name = "Authorization") String auth,
                                                         @PathVariable String id){
        String email = jwt.getFromHeader(auth);
        try{
            participationService.deleteParticipationEntry(email, id);
        }catch (NoSuchElementException e){
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok().build();
    }



}
