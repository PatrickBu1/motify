package com.cs4523groupb11.Motify.controllers;

import com.cs4523groupb11.Motify.DTO.request_non_entity.DiscussionPostRequest;
import com.cs4523groupb11.Motify.DTO.entity.DiscussionDTO;
import com.cs4523groupb11.Motify.entities.Discussion;
import com.cs4523groupb11.Motify.security.JwtTokenUtility;
import com.cs4523groupb11.Motify.services.DiscussionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@RestController
@RequestMapping("/api/discussion")
@PreAuthorize("hasRole('ROLE_USER')")
public class DiscussionController {

    private final DiscussionService discussionService;

    private final JwtTokenUtility jwt;

    @Autowired
    public DiscussionController(DiscussionService discussionService, JwtTokenUtility jwt) {
        this.discussionService = discussionService;
        this.jwt = jwt;
    }


    @GetMapping("/getPageByChallengeId/{id}/{page}/{size}")
    public ResponseEntity<Page<DiscussionDTO>> getPageByChallengeId(@RequestHeader(name = "Authorization") String auth,
                                                                    @PathVariable String id,
                                                                    @PathVariable Integer page,
                                                                    @PathVariable Integer size){
        String email = jwt.getFromHeader(auth);
        Optional<Page<Discussion>> resPage = discussionService.getPageByChallengeId(email, id, page, size);
        return resPage.map(checkInData -> ResponseEntity.ok(checkInData.map(DiscussionDTO::fromEntity)))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @GetMapping("/getAllByChallengeId/{id}")
    public ResponseEntity<List<DiscussionDTO>> getAllByChallengeId(@RequestHeader(name = "Authorization") String auth,
                                                                   @PathVariable String id){
        String email = jwt.getFromHeader(auth);
        Optional<List<Discussion>> resList = discussionService.getAllByChallengeId(email, id);
        return resList
                .map(checkInData -> ResponseEntity.ok(checkInData.stream().map(DiscussionDTO::fromEntity).toList()))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }


    @PostMapping("/post")
    public ResponseEntity<DiscussionDTO> post(@RequestHeader(name = "Authorization") String auth,
                                     @RequestParam(value = "file", required = false) MultipartFile image,
                                     @RequestBody DiscussionPostRequest request){
        String email = jwt.getFromHeader(auth);
        Optional<Discussion> opDiscussion = discussionService.post(email, request, image);
        return opDiscussion.map(value -> ResponseEntity.ok(DiscussionDTO.fromEntity(value)))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }


    @DeleteMapping("/deletePost/{id} ")
    public ResponseEntity<?> deletePost(@RequestHeader(name = "Authorization") String auth,
                                           @PathVariable String id){
        String email = jwt.getFromHeader(auth);
        try{
            discussionService.deletePost(email, id);
        }catch(NoSuchElementException e){
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok().build();
    }

}
