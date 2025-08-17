package com.vanillacare.medibot2.controller;


import com.vanillacare.medibot2.dto.*;
import com.vanillacare.medibot2.dto.response.OperationResponse;
import com.vanillacare.medibot2.service.PatientService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/patient")
public class PatientController {

    private final PatientService patientService;

    public PatientController(
            @Qualifier("Android") PatientService patientService) {
        this.patientService = patientService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> createPatient(
            @Valid @RequestBody PatientCreateRequest req) {
        String id = patientService.createPatient(req.toDto());
        return ResponseEntity.ok(new OperationResponse(id, "saved"));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateById(
            @PathVariable String id,
            @RequestBody PatientUpdateRequest req) {
        patientService.updatePatientById(id, req);
        return ResponseEntity.ok(new OperationResponse(id, "updated"));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteById(
            @PathVariable String id) {
        patientService.deletePatientById(id);
        return ResponseEntity.ok(new OperationResponse(id, "deleted"));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable String id) {
        PatientDto dto = patientService.getPatientById(id);
        return (dto == null) ? ResponseEntity.notFound().build() : ResponseEntity.ok(PatientView.from(dto));
    }
}
