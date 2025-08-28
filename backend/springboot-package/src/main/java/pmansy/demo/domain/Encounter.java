package pmansy.demo.domain;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "encounters")
public class Encounter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "patient_id", nullable = false)
    private Patient patient;

    @Column(name = "type", nullable = false, length = 64)
    private String type; // e.g., OUTPATIENT, INPATIENT, ER

    @Column(name = "reason", length = 256)
    private String reason;

    @Column(name = "encounter_at", nullable = false)
    private LocalDateTime encounterAt;

    @Column(name = "location", length = 128)
    private String location;

    public Encounter() {}

    public Encounter(Patient patient, String type, String reason, LocalDateTime encounterAt, String location) {
        this.patient = patient;
        this.type = type;
        this.reason = reason;
        this.encounterAt = encounterAt;
        this.location = location;
    }

    // getters/setters
    public Long getId() { return id; }
    public Patient getPatient() { return patient; }
    public void setPatient(Patient patient) { this.patient = patient; }
    public String getType() { return type; }
    public void setType(String type) { this.type = type; }
    public String getReason() { return reason; }
    public void setReason(String reason) { this.reason = reason; }
    public LocalDateTime getEncounterAt() { return encounterAt; }
    public void setEncounterAt(LocalDateTime encounterAt) { this.encounterAt = encounterAt; }
    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Encounter)) return false;
        Encounter that = (Encounter) o;
        return Objects.equals(id, that.id);
    }
    @Override
    public int hashCode() { return Objects.hash(id); }
}
