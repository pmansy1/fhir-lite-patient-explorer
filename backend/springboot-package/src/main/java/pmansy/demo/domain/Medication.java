package pmansy.demo.domain;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "medications")
public class Medication {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "patient_id", nullable = false)
    private Patient patient;

    @Column(name = "name", nullable = false, length = 128)
    private String name; // e.g., Metformin

    @Column(name = "dosage", length = 64)
    private String dosage; // e.g., 500 mg BID

    @Column(name = "status", length = 32)
    private String status; // e.g., ACTIVE, COMPLETED, ON_HOLD

    @Column(name = "start_date")
    private LocalDate startDate;

    @Column(name = "end_date")
    private LocalDate endDate;

    public Medication() {}

    public Medication(Patient patient, String name, String dosage, String status, LocalDate startDate, LocalDate endDate) {
        this.patient = patient;
        this.name = name;
        this.dosage = dosage;
        this.status = status;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    // getters/setters
    public Long getId() { return id; }
    public Patient getPatient() { return patient; }
    public void setPatient(Patient patient) { this.patient = patient; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getDosage() { return dosage; }
    public void setDosage(String dosage) { this.dosage = dosage; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public LocalDate getStartDate() { return startDate; }
    public void setStartDate(LocalDate startDate) { this.startDate = startDate; }
    public LocalDate getEndDate() { return endDate; }
    public void setEndDate(LocalDate endDate) { this.endDate = endDate; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Medication)) return false;
        Medication that = (Medication) o;
        return Objects.equals(id, that.id);
    }
    @Override
    public int hashCode() { return Objects.hash(id); }
}
