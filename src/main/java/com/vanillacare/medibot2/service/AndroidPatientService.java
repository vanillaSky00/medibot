package com.vanillacare.medibot2.service;

import com.vanillacare.medibot2.Repository.PatientRepository;
import com.vanillacare.medibot2.dto.PatientDto;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
@Qualifier("Android")
public class AndroidPatientService implements PatientService{
    private final PatientRepository patientRepository;

    public AndroidPatientService(
            @Qualifier("Firebase") PatientRepository patientRepository
    ){
        this.patientRepository = patientRepository;
    }

    public void registerPatient(PatientDto dto){
        patientRepository.registerPatient(dto);
    }

    public PatientDto getPatientById(String id){
        return patientRepository.getPatientById(id);
    }
}
