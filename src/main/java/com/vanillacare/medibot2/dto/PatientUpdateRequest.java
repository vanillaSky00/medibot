package com.vanillacare.medibot2.dto;

public record PatientUpdateRequest(
        String name,
        String guardianId,
        String birthday,
        String phone,
        String notes
) {}
