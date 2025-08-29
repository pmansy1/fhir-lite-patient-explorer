package pmansy.demo.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import pmansy.demo.domain.Observation;
import pmansy.demo.exception.NotFoundException;
import pmansy.demo.repo.ObservationRepository;

@RestController
@RequestMapping("/api/observations")
public class ObservationController {

    private final ObservationRepository observations;

    public ObservationController(ObservationRepository observations) {
        this.observations = observations;
    }

    // GET /api/observations
    @GetMapping
    public Page<Observation> list(Pageable pageable) {
        return observations.findAll(pageable);
    }

    // GET /api/observations/{id}
    @GetMapping("/{id}")
    public Observation get(@PathVariable Long id) {
        return observations.findById(id)
                .orElseThrow(() -> new NotFoundException("Observation " + id + " not found"));
    }

    // GET /api/patients/{patientId}/observations
    @GetMapping("/by-patient/{patientId}")
    public Page<Observation> byPatient(@PathVariable Long patientId, Pageable pageable) {
        return observations.findByPatientIdOrderByObservedAtDesc(patientId, pageable);
    }

    // GET /api/observations/by-mrn/{mrn}
    @GetMapping("/by-mrn/{mrn}")
    public Page<Observation> byMrn(@PathVariable String mrn, Pageable pageable) {
        return observations.findByPatientMrnOrderByObservedAtDesc(mrn, pageable);
    }

    // GET /api/observations/by-patient/{patientId}/type/{type}
    @GetMapping("/by-patient/{patientId}/type/{type}")
    public Page<Observation> byPatientAndType(@PathVariable Long patientId,
                                              @PathVariable String type,
                                              Pageable pageable) {
        return observations.findByPatientIdAndTypeOrderByObservedAtDesc(patientId, type, pageable);
    }
}
