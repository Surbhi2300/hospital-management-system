package com.example.hospitalmanagement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.hospitalmanagement.entity.Patient;
import com.example.hospitalmanagement.repository.PatientRepository;

@RestController

@RequestMapping("/patients")

public class PatientController {

    @Autowired
    PatientRepository repository;

    @GetMapping
    public List<Patient> getAllPatients(){

        return repository.findAll();
    }

    @PostMapping
    public Patient savePatient(
            @RequestBody Patient patient){

        return repository.save(patient);
    }

}