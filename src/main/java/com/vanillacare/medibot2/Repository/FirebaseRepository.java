package com.vanillacare.medibot2.Repository;

import com.google.cloud.firestore.FieldValue;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.cloud.firestore.SetOptions;
import com.vanillacare.medibot2.dto.PatientDto;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

@Component
@Qualifier("Firebase")
public class FirebaseRepository implements PatientRepository{

    private final Firestore db;

    public FirebaseRepository(Firestore db){
        this.db = db;
    }

    @Override
    public void registerPatient(PatientDto dto) {
        // define the collection name
        String collection = "patients";

        String id = (dto.patientId() == null || dto.patientId().isBlank())
                ? db.collection(collection).document().getId()
                : dto.patientId();

        Map<String, Object> data = new HashMap<>();
        data.put("name", dto.name());
        data.put("guardianId", dto.guardianId());
        data.put("birthday", dto.birthday()); // or convert to Timestamp
        data.put("phone", dto.phone());
        data.put("notes", dto.notes());
        data.put("createdAt", FieldValue.serverTimestamp());
        data.put("updatedAt", FieldValue.serverTimestamp());

        try {
            db.collection(collection).document(id)
                    .set(data, SetOptions.merge())
                    .get();
            System.out.println("Firestore: saved patient id=" + id);
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException("Firestore save failed for id=" + id, e);
        }
    }

    //helpers

    public PatientDto getPatientById(String id) {
        try {
            var snap = db.collection("patients").document(id).get().get();
            if(!snap.exists()) return null;
            return new PatientDto(
                    snap.getId(),
                    snap.getString("name"),
                    snap.getString("guardianId"),
                    snap.getString("birthday"),
                    snap.getString("phone"),
                    snap.getString("notes")
            );
        } catch (Exception e) {
            throw new RuntimeException("Firestore get failed for id=" + id, e);
        }
    }

    public void updatePatient(String id, Map<String, Object> patch) {
        if (patch == null) patch = new HashMap<>();
        patch.put("updatedAt", FieldValue.serverTimestamp());
        try {
            db.collection("patients").document(id)
                    .set(patch, SetOptions.merge())
                    .get();
            System.out.println("Firestore: updated patient id=" + id);
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException("Firestore update failed for id=" + id, e);
        }
    }

    public void deletePatient(String id) {
        try {
            db.collection("patients").document(id).delete().get();
            System.out.println("Firestore: deleted patient id=" + id);
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException("Firestore delete failed for id=" + id, e);
        }
    }

    public QuerySnapshot findByGuardian(String guardianId) {
        try {
            return db.collection("patients")
                    .whereEqualTo("guardianId", guardianId)
                    .get().get();
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException("Firestore query failed for guardianId=" + guardianId, e);
        }
    }
}
