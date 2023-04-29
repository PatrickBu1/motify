package com.cs4523groupb11.Motify.services.impl;

import com.cs4523groupb11.Motify.entities.User;
import com.cs4523groupb11.Motify.repositories.UserRepository;
import com.cs4523groupb11.Motify.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {


    private final UserRepository userRepository;

    @Value("${spring.servlet.multipart.location}")
    private String serverFilePath;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Optional<User> findById(String userId) {
        return userRepository.findById(userId);
    }

    public List<User> findListByIds(List<String> idList){
        List<User> userList = userRepository.findAllByIdIn(idList);
        if (userList.size() != idList.size()){
            return Collections.emptyList();
        }
        return userList;
    }

    public List<User> findAllByUsername(String username){
        return userRepository.findAllByUsername(username);
    }

    public Optional<User> findByEmail(String email){
        return userRepository.findByEmail(email);
    }

    @Transactional
    public Optional<User> setProfilePicture(String email, MultipartFile image){
        Optional<User> opUser = userRepository.findByEmail(email);
        if (opUser.isEmpty() || image == null){
            return Optional.empty();
        }
        User user = opUser.get();
        String filename = user.getId() + "_profile.jpg";
        Path filePath = Paths.get(serverFilePath + "/" + filename);
        try{
            Files.copy(image.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
            user.setProfileImagePath(filename);
            userRepository.saveAndFlush(user);
            return Optional.of(user);
        }catch (Exception e){
           return Optional.empty();
        }
    }

}
