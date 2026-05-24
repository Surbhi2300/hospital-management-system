package com.example.hospitalmanagement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.hospitalmanagement.entity.Patient;
import com.example.hospitalmanagement.repository.PatientRepository;

@Controller
public class PatientController {

    @Autowired
    PatientRepository repository;

    @GetMapping("/")
    public String home() {
        return "index";
    }

    @GetMapping("/patients")
    public String patientsPage(Model model) {

        List<Patient> patients = repository.findAll();

        model.addAttribute("patients", patients);

        String icuStatus = "Empty";
        String generalStatus = "Empty";
        String emergencyStatus = "Empty";

        for (Patient patient : patients) {

            if (patient.getWard().equals("ICU")) {
                icuStatus = "Occupied";
            }

            if (patient.getWard().equals("General")) {
                generalStatus = "Occupied";
            }

            if (patient.getWard().equals("Emergency")) {
                emergencyStatus = "Occupied";
            }
        }

        model.addAttribute("icuStatus", icuStatus);
        model.addAttribute("generalStatus", generalStatus);
        model.addAttribute("emergencyStatus", emergencyStatus);

        return "patients";
    }

    @PostMapping("/savePatient")
    public String savePatient(@ModelAttribute Patient patient) {

        repository.save(patient);

        return "redirect:/patients";
    }
}