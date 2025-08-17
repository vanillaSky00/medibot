package com.vanillacare.medibot2.dto;


public record PatientDto(
        String patientId,   // null/blank => auto-id
        String name,
        String guardianId,
        String birthday,
        String phone,
        String notes
) {}