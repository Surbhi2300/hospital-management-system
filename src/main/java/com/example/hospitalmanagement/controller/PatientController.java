package com.example.hospitalmanagement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
    public String patientsPage(
            @RequestParam(required = false) String error,
            Model model) {

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

        model.addAttribute("error", error);

        return "patients";
    }

    @PostMapping("/savePatient")
    public String savePatient(@ModelAttribute Patient patient) {

        List<Patient> patients = repository.findAll();

        int icuCount = 0;
        int generalCount = 0;
        int emergencyCount = 0;

        for (Patient p : patients) {

            if (p.getWard().equals("ICU")) {
                icuCount++;
            }

            if (p.getWard().equals("General")) {
                generalCount++;
            }

            if (p.getWard().equals("Emergency")) {
                emergencyCount++;
            }
        }

        // Ward Capacity Logic

        if (patient.getWard().equals("ICU") && icuCount >= 5) {
            return "redirect:/patients?error=ICU Full";
        }

        if (patient.getWard().equals("General") && generalCount >= 10) {
            return "redirect:/patients?error=General Ward Full";
        }

        if (patient.getWard().equals("Emergency") && emergencyCount >= 3) {
            return "redirect:/patients?error=Emergency Ward Full";
        }

        repository.save(patient);

        return "redirect:/patients";
    }
}