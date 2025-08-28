package pmansy.demo.domain;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "observations")
public class Observation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Many observations belong to one patient
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "patient_id", nullable = false)
    private Patient patient;

    @Column(name = "type", nullable = false, length = 32)
    private String type; // e.g., BP_SYSTOLIC, HEART_RATE, A1C

    // Avoid reserved word "value"
    @Column(name = "measurement_value", nullable = false, precision = 10, scale = 2)
    private BigDecimal value;

    @Column(name = "unit", nullable = false, length = 16)
    private String unit; // e.g., mmHg, bpm, %

    @Column(name = "observed_at", nullable = false)
    private LocalDateTime observedAt;

    public Observation() {}

    public Observation(Patient patient, String type, BigDecimal value, String unit, LocalDateTime observedAt) {
        this.patient = patient;
        this.type = type;
        this.value = value;
        this.unit = unit;
        this.observedAt = observedAt;
    }

    // getters/setters
    public Long getId() { return id; }
    public Patient getPatient() { return patient; }
    public void setPatient(Patient patient) { this.patient = patient; }
    public String getType() { return type; }
    public void setType(String type) { this.type = type; }
    public BigDecimal getValue() { return value; }
    public void setValue(BigDecimal value) { this.value = value; }
    public String getUnit() { return unit; }
    public void setUnit(String unit) { this.unit = unit; }
    public LocalDateTime getObservedAt() { return observedAt; }
    public void setObservedAt(LocalDateTime observedAt) { this.observedAt = observedAt; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Observation)) return false;
        Observation that = (Observation) o;
        return Objects.equals(id, that.id);
    }
    @Override
    public int hashCode() { return Objects.hash(id); }
}
