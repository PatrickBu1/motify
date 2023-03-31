package com.cs4523groupb11.Motify.repositories;


import com.cs4523groupb11.Motify.entities.Role;
import com.cs4523groupb11.Motify.entities.RoleEnum;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository {
    Optional<Role> findByName(RoleEnum name);
}
