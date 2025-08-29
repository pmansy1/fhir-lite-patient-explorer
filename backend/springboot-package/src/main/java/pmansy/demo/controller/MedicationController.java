package pmansy.demo.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import pmansy.demo.domain.Medication;
import pmansy.demo.exception.NotFoundException;
import pmansy.demo.repo.MedicationRepository;

@RestController
@RequestMapping("/api/medications")
public class MedicationController {

    private final MedicationRepository meds;

    public MedicationController(MedicationRepository meds) {
        this.meds = meds;
    }

    @GetMapping
    public Page<Medication> list(Pageable pageable) {
        return meds.findAll(pageable);
    }

    @GetMapping("/{id}")
    public Medication get(@PathVariable Long id) {
        return meds.findById(id).orElseThrow(() -> new NotFoundException("Medication " + id + " not found"));
    }

    @GetMapping("/by-patient/{patientId}")
    public Page<Medication> byPatient(@PathVariable Long patientId, Pageable pageable) {
        return meds.findByPatientId(patientId, pageable);
    }

    @GetMapping("/by-mrn/{mrn}")
    public Page<Medication> byMrn(@PathVariable String mrn, Pageable pageable) {
        return meds.findByPatientMrn(mrn, pageable);
    }

    @GetMapping("/by-patient/{patientId}/status/{status}")
    public Page<Medication> byPatientAndStatus(@PathVariable Long patientId,
                                               @PathVariable String status,
                                               Pageable pageable) {
        return meds.findByPatientIdAndStatus(patientId, status, pageable);
    }
}
