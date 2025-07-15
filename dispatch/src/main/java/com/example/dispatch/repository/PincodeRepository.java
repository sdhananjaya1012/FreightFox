package com.example.dispatch.repository;

import com.example.dispatch.model.Pincodes;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PincodeRepository extends JpaRepository<Pincodes, Long> {


}