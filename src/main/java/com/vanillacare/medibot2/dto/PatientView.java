package com.vanillacare.medibot2.dto;


public record PatientView(
        String id,
        String name,
        String guardianId,
        String birthday,
        String phone,
        String notes,
        String status   // e.g. "saved", "updated", "deleted"
) {
    public static PatientView from(PatientDto dto) {
        return new PatientView(
                dto.patientId(),
                dto.name(),
                dto.guardianId(),
                dto.birthday(),
                dto.phone(),
                dto.notes(),
                null  // default: no status unless you want
        );
    }
}
