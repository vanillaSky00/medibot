package com.vanillacare.medibot2.service;

import com.vanillacare.medibot2.dto.PatientDto;
import com.vanillacare.medibot2.dto.PatientUpdateRequest;

public interface PatientService {
    String createPatient(PatientDto dto);
    void deletePatientById(String id);
    void updatePatientById(String id, PatientUpdateRequest patientUpdateRequest);
    PatientDto getPatientById(String id);
}
