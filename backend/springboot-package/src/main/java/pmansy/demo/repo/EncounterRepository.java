package pmansy.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import pmansy.demo.domain.Encounter;

public interface EncounterRepository extends JpaRepository<Encounter, Long> {
    Page<Encounter> findByPatientIdOrderByEncounterAtDesc(Long patientId, Pageable pageable);
    Page<Encounter> findByPatientMrnOrderByEncounterAtDesc(String mrn, Pageable pageable);
    Page<Encounter> findByPatientIdAndTypeOrderByEncounterAtDesc(Long patientId, String type, Pageable pageable);
}
