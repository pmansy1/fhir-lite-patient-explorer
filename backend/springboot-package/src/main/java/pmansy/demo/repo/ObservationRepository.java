package pmansy.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import pmansy.demo.domain.Observation;

public interface ObservationRepository extends JpaRepository<Observation, Long> {
    Page<Observation> findByPatientIdOrderByObservedAtDesc(Long patientId, Pageable pageable);
    Page<Observation> findByPatientMrnOrderByObservedAtDesc(String mrn, Pageable pageable);
    Page<Observation> findByPatientIdAndTypeOrderByObservedAtDesc(Long patientId, String type, Pageable pageable);
}
