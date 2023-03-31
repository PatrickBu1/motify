package com.cs4523groupb11.Motify.controllers;

import com.cs4523groupb11.Motify.entities.PrivateChallenge;
import com.cs4523groupb11.Motify.services.PrivateChallengeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/private")
public class PrivateChallengeController {

    @Autowired
    private PrivateChallengeService privateChallengeService;

    @GetMapping("/findById/{id}")
    public ResponseEntity<PrivateChallenge> findById(@PathVariable String id){
        Optional<PrivateChallenge> opPc = privateChallengeService.findById(id);
        return opPc.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/findAllByOwnerId/{id}")
    public ResponseEntity<List<PrivateChallenge>> findAllByOwnerId(@PathVariable String id){
        Optional<List<PrivateChallenge>> opList = privateChallengeService.findAllByOwnerId(id);
        return opList.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/create")
    public ResponseEntity<PrivateChallenge> create(@RequestBody PrivateChallenge pc){
        Optional<PrivateChallenge> opPc = privateChallengeService.create(pc);
        return opPc.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<PrivateChallenge> delete(@PathVariable String id){
        Optional<PrivateChallenge> opPc = privateChallengeService.deleteById(id);
        return opPc.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @PutMapping("/update")
    public ResponseEntity<PrivateChallenge> update(@RequestBody PrivateChallenge pc){
        Optional<PrivateChallenge> opPc = privateChallengeService.update(pc);
        return opPc.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.badRequest().build());
    }
}
