package com.example.hospitalmanagement.repository;

import com.example.hospitalmanagement.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepository
extends JpaRepository<Patient,Long>{

}