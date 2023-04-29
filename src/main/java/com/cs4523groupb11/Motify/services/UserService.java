package com.cs4523groupb11.Motify.services;


import com.cs4523groupb11.Motify.entities.User;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

public interface UserService {
    Optional<User> findById(String userId);
    List<User> findListByIds(List<String> idList);
    List<User> findAllByUsername(String username);
    Optional<User> findByEmail(String email);
    Optional<User> setProfilePicture(String email, MultipartFile file);
}
