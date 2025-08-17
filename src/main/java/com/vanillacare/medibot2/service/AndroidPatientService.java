package com.vanillacare.medibot2.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vanillacare.medibot2.Repository.PatientRepository;
import com.vanillacare.medibot2.dto.PatientDto;
import com.vanillacare.medibot2.dto.PatientUpdateRequest;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import java.util.Map;
import java.util.Objects;

@Component
@Qualifier("Android")
public class AndroidPatientService implements PatientService{
    private final PatientRepository patientRepository;
    private final ObjectMapper mapper; // Spring Boot auto-configures one

    public AndroidPatientService(
            @Qualifier("Firebase") PatientRepository patientRepository,
            ObjectMapper mapper
    ){
        this.patientRepository = patientRepository;
        this.mapper = mapper;
    }

    @Override
    public String createPatient(PatientDto dto) {
        String id = java.util.UUID.randomUUID().toString();
        PatientDto dtoWithId = new PatientDto(
                id, dto.name(), dto.guardianId(), dto.birthday(), dto.phone(), dto.notes()
        );
        patientRepository.createPatient(dtoWithId);
        return id;
    }

    @Override
    public void deletePatientById(String id) {
        patientRepository.deletePatient(id);
    }

    @Override
    public void updatePatientById(String id, PatientUpdateRequest req) {
        Map<String, Object> patch = mapper.convertValue(req, new TypeReference<>() {
        });
        // remove nulls so only provided fields are patched
        patch.values().removeIf(Objects::isNull);
        if (patch.isEmpty()) return;
        patientRepository.updatePatient(id, patch);
    }

    public PatientDto getPatientById(String id){
        return patientRepository.getPatientById(id);
    }
}
