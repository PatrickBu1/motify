package com.cs4523groupb11.Motify.controllers;

import com.cs4523groupb11.Motify.entities.ChallengeCategory;
import com.cs4523groupb11.Motify.entities.PrivateChallenge;
import com.cs4523groupb11.Motify.entities.PublicChallenge;
import com.cs4523groupb11.Motify.services.PrivateChallengeService;
import com.cs4523groupb11.Motify.services.PublicChallengeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.yaml.snakeyaml.util.EnumUtils;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/public")
public class PublicChallengeController {

    @Autowired
    private PublicChallengeService publicChallengeService;

    @GetMapping("/get/{id}")
    public ResponseEntity<PublicChallenge> getById(@PathVariable String id){
        Optional<PublicChallenge> opPc = publicChallengeService.getById(id);
        return opPc.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/getAllIds")
    public ResponseEntity<List<String>> getAll(){
        return ResponseEntity.ok(publicChallengeService.getAllIds());
    }

    @GetMapping("/getAllIdsByNameContaining/{name}")
    public ResponseEntity<List<String>> getAllIdsByNameContaining(@PathVariable String name){
        return ResponseEntity.ok(publicChallengeService.getAllIdsByNameContaining(name));
    }

    @GetMapping("/getAllIdsByCategory/{category}")
    public ResponseEntity<?> getAllIdsByCategory(String category){
        ChallengeCategory c;
        try{
            c = ChallengeCategory.valueOf(category);
        }catch(IllegalArgumentException e){
            return ResponseEntity.badRequest().body(String.format(
                    "Category %s does not confirm with " + "any of ChallengeCategory types", category));
        }
        return ResponseEntity.ok(publicChallengeService.getAllIdsByCategory(c));
    }

    @GetMapping("/getAllIdsByOwnerId/{id}")
    public ResponseEntity<List<String>> getAllIdsByOwnerId(@PathVariable String id){
        Optional<List<String>> opList = publicChallengeService.getAllIdsByOwnerId(id);
        return opList.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody PublicChallenge pc){
        Optional<PublicChallenge> opPc = publicChallengeService.create(pc);
        return opPc.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteById(@PathVariable String id){
        Optional<PublicChallenge> opPc = publicChallengeService.deleteById(id);
        return opPc.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @PutMapping("/update")
    public ResponseEntity<?> update(@RequestBody PublicChallenge pc){
        Optional<PublicChallenge> opPc = publicChallengeService.update(pc);
        return opPc.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.badRequest().build());
    }

}
