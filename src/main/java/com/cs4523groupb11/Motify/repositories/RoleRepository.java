package com.cs4523groupb11.Motify.repositories;


import com.cs4523groupb11.Motify.entities.Role;
import com.cs4523groupb11.Motify.entities.enums.RoleType;
import io.micrometer.common.lang.NonNullApi;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@NonNullApi
public interface RoleRepository extends JpaRepository<Role, Integer> {
    Optional<Role> findByName(RoleType name);
}
