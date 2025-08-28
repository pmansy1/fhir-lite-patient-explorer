package pmansy.demo.domain;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "care_gaps")
public class CareGap {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "patient_id", nullable = false)
    private Patient patient;

    @Column(name = "gap_type", nullable = false, length = 64)
    private String gapType; // e.g., A1C_SCREENING, BP_CONTROL, FLU_VACCINE

    @Column(name = "status", nullable = false, length = 32)
    private String status; // e.g., OPEN, CLOSED

    @Column(name = "detected_on")
    private LocalDate detectedOn;

    @Column(name = "due_on")
    private LocalDate dueOn;

    @Column(name = "resolved_on")
    private LocalDate resolvedOn;

    public CareGap() {}

    public CareGap(Patient patient, String gapType, String status, LocalDate detectedOn, LocalDate dueOn, LocalDate resolvedOn) {
        this.patient = patient;
        this.gapType = gapType;
        this.status = status;
        this.detectedOn = detectedOn;
        this.dueOn = dueOn;
        this.resolvedOn = resolvedOn;
    }

    // getters/setters
    public Long getId() { return id; }
    public Patient getPatient() { return patient; }
    public void setPatient(Patient patient) { this.patient = patient; }
    public String getGapType() { return gapType; }
    public void setGapType(String gapType) { this.gapType = gapType; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public LocalDate getDetectedOn() { return detectedOn; }
    public void setDetectedOn(LocalDate detectedOn) { this.detectedOn = detectedOn; }
    public LocalDate getDueOn() { return dueOn; }
    public void setDueOn(LocalDate dueOn) { this.dueOn = dueOn; }
    public LocalDate getResolvedOn() { return resolvedOn; }
    public void setResolvedOn(LocalDate resolvedOn) { this.resolvedOn = resolvedOn; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CareGap)) return false;
        CareGap careGap = (CareGap) o;
        return Objects.equals(id, careGap.id);
    }
    @Override
    public int hashCode() { return Objects.hash(id); }
}
