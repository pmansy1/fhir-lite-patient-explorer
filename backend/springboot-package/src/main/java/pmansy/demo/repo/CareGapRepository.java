package pmansy.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import pmansy.demo.domain.CareGap;

public interface CareGapRepository extends JpaRepository<CareGap, Long> {
    Page<CareGap> findByPatientId(Long patientId, Pageable pageable);
    Page<CareGap> findByPatientMrn(String mrn, Pageable pageable);
    Page<CareGap> findByPatientIdAndStatus(Long patientId, String status, Pageable pageable);
}
