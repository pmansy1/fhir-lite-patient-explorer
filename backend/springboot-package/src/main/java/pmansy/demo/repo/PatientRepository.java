package pmansy.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import pmansy.demo.domain.Patient;

import java.util.Optional;

public interface PatientRepository extends JpaRepository<Patient, Long> {
    Optional<Patient> findByMrn(String mrn);
    boolean existsByMrn(String mrn);
}