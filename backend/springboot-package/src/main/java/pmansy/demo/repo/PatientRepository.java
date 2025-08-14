package pmansy.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import pmansy.demo.domain.Patient;
import java.util.List;

public interface PatientRepository extends JpaRepository<Patient, Long> {
    List<Patient> findTop25ByLastNameIgnoreCaseStartingWith(String lastNamePrefix);
    List<Patient> findTop25ByFirstNameIgnoreCaseStartingWith(String firstNamePrefix);
}
