package pmansy.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import pmansy.demo.domain.Medication;

public interface MedicationRepository extends JpaRepository<Medication, Long> {
    Page<Medication> findByPatientId(Long patientId, Pageable pageable);
    Page<Medication> findByPatientMrn(String mrn, Pageable pageable);
    Page<Medication> findByPatientIdAndStatus(Long patientId, String status, Pageable pageable);
}
