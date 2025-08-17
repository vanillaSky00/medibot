package com.vanillacare.medibot2.Repository;

import com.vanillacare.medibot2.dto.PatientDto;

import java.util.Map;

public interface PatientRepository {
    void registerPatient(PatientDto dto);
    PatientDto getPatientById(String id);
    void updatePatient(String id, Map<String, Object> patch);
    void deletePatient(String id);
}
