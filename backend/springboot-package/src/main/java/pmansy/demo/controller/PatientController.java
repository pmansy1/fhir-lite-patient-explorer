package pmansy.demo.controller;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pmansy.demo.domain.Patient;
import pmansy.demo.exception.NotFoundException;
import pmansy.demo.repo.PatientRepository;

import java.util.List;

@RestController
@RequestMapping("/api/patients")
public class PatientController {

    private final PatientRepository patients;

    public PatientController(PatientRepository patients) {
        this.patients = patients;
    }

    // GET /api/patients  -> list all
    @GetMapping
    public List<Patient> list() {
        return patients.findAll();
    }

    // GET /api/patients/{id}  -> by numeric id
    @GetMapping("/{id}")
    public Patient get(@PathVariable Long id) {
        return patients.findById(id)
                .orElseThrow(() -> new NotFoundException("Patient id " + id + " not found"));
    }

    // GET /api/patients/by-mrn/{mrn}  -> by MRN
    @GetMapping("/by-mrn/{mrn}")
    public Patient getByMrn(@PathVariable String mrn) {
        return patients.findByMrn(mrn)
                .orElseThrow(() -> new NotFoundException("Patient MRN " + mrn + " not found"));
    }

    // POST /api/patients  -> create (MRN must be unique)
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Patient create(@Valid @RequestBody Patient payload) {
        if (payload.getId() != null) payload = stripId(payload);
        if (patients.existsByMrn(payload.getMrn())) {
            throw new IllegalArgumentException("MRN already exists: " + payload.getMrn());
        }
        return patients.save(payload);
    }

    // PUT /api/patients/{id}  -> full update
    @PutMapping("/{id}")
    public Patient update(@PathVariable Long id, @Valid @RequestBody Patient payload) {
        Patient existing = patients.findById(id)
                .orElseThrow(() -> new NotFoundException("Patient id " + id + " not found"));
        existing.setMrn(payload.getMrn());
        existing.setFirstName(payload.getFirstName());
        existing.setLastName(payload.getLastName());
        existing.setDateOfBirth(payload.getDateOfBirth());
        existing.setGender(payload.getGender());
        return patients.save(existing);
    }

    // DELETE /api/patients/{id}
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        if (!patients.existsById(id)) throw new NotFoundException("Patient id " + id + " not found");
        patients.deleteById(id);
    }

    private static Patient stripId(Patient p) {
        // helper to avoid client-sent id controlling PKs
        try {
            var clazz = p.getClass();
            var idField = clazz.getDeclaredField("id");
            idField.setAccessible(true);
            idField.set(p, null);
        } catch (Exception ignored) {}
        return p;
    }
}
