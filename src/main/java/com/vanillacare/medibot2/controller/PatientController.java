package com.vanillacare.medibot2.controller;

import com.vanillacare.medibot2.Repository.PatientRepository;
import com.vanillacare.medibot2.dto.PatientDto;
import com.vanillacare.medibot2.service.PatientService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/patient")
public class PatientController {
    private final PatientService patientService;

    public PatientController(
            @Qualifier("Android") PatientService patientService
    ){
        this.patientService = patientService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> createPatient(@RequestBody PatientDto dto){
        patientService.registerPatient(dto);
        return ResponseEntity.ok().body("Saved");
    }



    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable String id){
        var dto = patientService.getPatientById(id);
        return (dto == null) ? ResponseEntity.notFound().build() : ResponseEntity.ok(dto);
    }
}
