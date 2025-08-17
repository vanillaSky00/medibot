package com.vanillacare.medibot2.service;

import com.vanillacare.medibot2.dto.PatientDto;

public interface PatientService {
    void registerPatient(PatientDto dto);
    PatientDto getPatientById(String id);
}
