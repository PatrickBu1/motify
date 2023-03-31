package com.cs4523groupb11.Motify.repositories;

import com.cs4523groupb11.Motify.entities.CheckInData;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CheckInDataRepository extends JpaRepository<CheckInData, String> {

}
