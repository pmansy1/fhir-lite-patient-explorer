package pmansy.demo.controller;

import org.springframework.web.bind.annotation.*;
import pmansy.demo.repo.PatientRepository;
import pmansy.demo.domain.Patient;
import java.util.List;

@RestController
@RequestMapping("/api/patients")
public class PatientController {
    private final PatientRepository repo;
    public PatientController(PatientRepository repo){ this.repo = repo; }

    // /api/patients?search=alex
    @GetMapping
    public List<Patient> search(@RequestParam(required=false) String search) {
        if (search == null || search.isBlank()) return repo.findAll().stream().limit(25).toList();
        var s = search.trim();
        var byLast = repo.findTop25ByLastNameIgnoreCaseStartingWith(s);
        return byLast.isEmpty() ? repo.findTop25ByFirstNameIgnoreCaseStartingWith(s) : byLast;
    }

    @GetMapping("/{id}")
    public Patient get(@PathVariable Long id) { return repo.findById(id).orElseThrow(); }
}
