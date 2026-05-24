package com.example.hospitalmanagement.controller;

import com.example.hospitalmanagement.entity.Patient;
import com.example.hospitalmanagement.repository.PatientRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
public class PatientController {

    @Autowired
    PatientRepository repository;

    @GetMapping("/")
    public String home() {
        return "index";
    }

    @GetMapping("/patients")
    public String patientsPage() {
        return "patients";
    }

    @PostMapping("/savePatient")
    public String savePatient(@ModelAttribute Patient patient) {

        repository.save(patient);

        return "redirect:/patients";
    }
}