package pmansy.demo.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import pmansy.demo.domain.Encounter;
import pmansy.demo.exception.NotFoundException;
import pmansy.demo.repo.EncounterRepository;

@RestController
@RequestMapping("/api/encounters")
public class EncounterController {

    private final EncounterRepository encounters;

    public EncounterController(EncounterRepository encounters) {
        this.encounters = encounters;
    }

    @GetMapping
    public Page<Encounter> list(Pageable pageable) {
        return encounters.findAll(pageable);
    }

    @GetMapping("/{id}")
    public Encounter get(@PathVariable Long id) {
        return encounters.findById(id)
                .orElseThrow(() -> new NotFoundException("Encounter " + id + " not found"));
    }

    @GetMapping("/by-patient/{patientId}")
    public Page<Encounter> byPatient(@PathVariable Long patientId, Pageable pageable) {
        return encounters.findByPatientIdOrderByEncounterAtDesc(patientId, pageable);
    }

    @GetMapping("/by-mrn/{mrn}")
    public Page<Encounter> byMrn(@PathVariable String mrn, Pageable pageable) {
        return encounters.findByPatientMrnOrderByEncounterAtDesc(mrn, pageable);
    }

    @GetMapping("/by-patient/{patientId}/type/{type}")
    public Page<Encounter> byPatientAndType(@PathVariable Long patientId,
                                            @PathVariable String type,
                                            Pageable pageable) {
        return encounters.findByPatientIdAndTypeOrderByEncounterAtDesc(patientId, type, pageable);
    }
}
