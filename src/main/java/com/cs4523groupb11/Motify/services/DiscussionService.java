package com.cs4523groupb11.Motify.services;

import com.cs4523groupb11.Motify.DTO.request_non_entity.DiscussionPostRequest;
import com.cs4523groupb11.Motify.entities.Discussion;
import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

public interface DiscussionService {
    Optional<Page<Discussion>> getPageByChallengeId(String username, String id, Integer page, Integer size);

    Optional<List<Discussion>> getAllByChallengeId(String username, String id);

    Optional<Discussion> post(String username, DiscussionPostRequest dto, MultipartFile image);

    void deletePost(String username, String id) throws NoSuchElementException;
}
