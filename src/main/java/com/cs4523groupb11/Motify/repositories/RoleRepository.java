package com.cs4523groupb11.Motify.repositories;


import com.cs4523groupb11.Motify.entities.Role;
import com.cs4523groupb11.Motify.entities.enums.RoleType;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository {
    Optional<Role> findByName(RoleType name);
}
