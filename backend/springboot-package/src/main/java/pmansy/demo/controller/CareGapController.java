package pmansy.demo.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import pmansy.demo.domain.CareGap;
import pmansy.demo.exception.NotFoundException;
import pmansy.demo.repo.CCareGapRepositoryFix;

@RestController
@RequestMapping("/api/care-gaps")
public class CareGapController {

    private final pmansy.demo.repo.CareGapRepository careGaps;

    public CareGapController(pmansy.demo.repo.CareGapRepository careGaps) {
        this.careGaps = careGaps;
    }

    @GetMapping
    public Page<CareGap> list(Pageable pageable) {
        return careGaps.findAll(pageable);
    }

    @GetMapping("/{id}")
    public CareGap get(@PathVariable Long id) {
        return careGaps.findById(id)
                .orElseThrow(() -> new NotFoundException("Care gap " + id + " not found"));
    }

    @GetMapping("/by-patient/{patientId}")
    public Page<CareGap> byPatient(@PathVariable Long patientId, Pageable pageable) {
        return careGaps.findByPatientId(patientId, pageable);
    }

    @GetMapping("/by-mrn/{mrn}")
    public Page<CareGap> byMrn(@PathVariable String mrn, Pageable pageable) {
        return careGaps.findByPatientMrn(mrn, pageable);
    }

    @GetMapping("/by-patient/{patientId}/status/{status}")
    public Page<CareGap> byPatientAndStatus(@PathVariable Long patientId,
                                            @PathVariable String status,
                                            Pageable pageable) {
        return careGaps.findByPatientIdAndStatus(patientId, status, pageable);
    }
}
