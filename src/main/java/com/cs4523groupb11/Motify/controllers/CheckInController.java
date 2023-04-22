package com.cs4523groupb11.Motify.controllers;

import com.cs4523groupb11.Motify.DTO.detailed_entity.CheckInDataDTO;
import com.cs4523groupb11.Motify.entities.CheckInData;
import com.cs4523groupb11.Motify.security.JwtTokenUtility;
import com.cs4523groupb11.Motify.services.CheckInService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@RestController
@RequestMapping("/api/checkin")
@PreAuthorize("hasRole('ROLE_USER')")
public class CheckInController {

    private final CheckInService checkInService;

    private final JwtTokenUtility jwt;

    @Autowired
    public CheckInController(CheckInService checkInService, JwtTokenUtility jwt) {
        this.checkInService = checkInService;
        this.jwt = jwt;
    }


    @GetMapping("/getPageByChallengeId/{id}/{page}/{size}")
    public ResponseEntity<Page<CheckInDataDTO>> getPageByChallengeId(@RequestHeader(name = "Authorization") String auth,
                                                                     @PathVariable String id,
                                                                     @PathVariable Integer page,
                                                                     @PathVariable Integer size){
        String username = jwt.getUsernameFromJwtToken(jwt.getFromHeader(auth));
        Optional<Page<CheckInData>> resPage = checkInService.getPageByChallengeId(username, id, page, size);
        return resPage.map(checkInData -> ResponseEntity.ok(checkInData.map(CheckInDataDTO::fromEntity)))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @GetMapping("/getAllByChallengeId/{id}")
    public ResponseEntity<List<CheckInDataDTO>> getAllByChallengeId(@RequestHeader(name = "Authorization") String auth,
                                                                    @PathVariable String id){
        String username = jwt.getUsernameFromJwtToken(jwt.getFromHeader(auth));
        Optional<List<CheckInData>> resList = checkInService.getAllByChallengeId(username, id);
        return resList
                .map(checkInData -> ResponseEntity.ok(checkInData.stream().map(CheckInDataDTO::fromEntity).toList()))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }


    @PostMapping("/checkIn ")
    public ResponseEntity<?> checkIn(@RequestHeader(name = "Authorization") String auth,
                                     @RequestBody CheckInDataDTO data){
        String username = jwt.getUsernameFromJwtToken(jwt.getFromHeader(auth));
        try{
            checkInService.checkIn(username, data);
        }catch(DataIntegrityViolationException e){
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok().build();
    }


    @DeleteMapping("/deleteCheckIn/{id} ")
    public ResponseEntity<?> deleteCheckIn(@RequestHeader(name = "Authorization") String auth,
                                           @PathVariable String id){
        String username = jwt.getUsernameFromJwtToken(jwt.getFromHeader(auth));
        try{
            checkInService.deleteCheckIn(username, id);
        }catch(NoSuchElementException e){
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok().build();
    }

}
