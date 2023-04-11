package com.cs4523groupb11.Motify.services;


import com.cs4523groupb11.Motify.DTO.detailed_entity.UserDTO;
import com.cs4523groupb11.Motify.entities.User;
import com.cs4523groupb11.Motify.exceptions.InvalidDataException;

import java.util.List;
import java.util.Optional;

public interface UserService {
    Optional<User> findById(String userId);
    List<User> findListByIds(List<String> idList);
    Optional<User> findByUsername(String username);
    Optional<User> findByEmail(String email);

}
