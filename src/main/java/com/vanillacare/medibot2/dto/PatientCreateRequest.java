package com.vanillacare.medibot2.dto;

import jakarta.validation.constraints.NotBlank;

public record PatientCreateRequest(
        @NotBlank String name,
        String guardianId,
        String birthday,
        String phone,
        String notes
) {
    public PatientDto toDto() {
        return new PatientDto(null, name, guardianId, birthday, phone, notes);
    }
}
