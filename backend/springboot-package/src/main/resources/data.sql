-- Seed two patients
INSERT INTO patients (mrn, first_name, last_name, date_of_birth, gender)
VALUES
    ('MRN-0001', 'Ava', 'Johnson', '1988-03-12', 'female'),
    ('MRN-0002', 'Noah', 'Williams', '1979-11-05', 'male')
    ON CONFLICT DO NOTHING;

-- Use generated IDs: in Postgres/H2 (PostgreSQL mode), we can look them up
-- Patient 1
INSERT INTO encounters (patient_id, type, reason, encounter_at, location)
SELECT p.id, 'OUTPATIENT', 'Annual physical', TIMESTAMP '2025-01-15 09:30:00', 'Hartford Clinic'
FROM patients p WHERE p.mrn = 'MRN-0001';

INSERT INTO medications (patient_id, name, dosage, status, start_date, end_date)
SELECT p.id, 'Metformin', '500 mg BID', 'ACTIVE', '2024-10-01', NULL
FROM patients p WHERE p.mrn = 'MRN-0001';

INSERT INTO observations (patient_id, type, measurement_value, unit, observed_at)
SELECT p.id, 'A1C', 6.4, '%', TIMESTAMP '2025-01-15 09:40:00'
FROM patients p WHERE p.mrn = 'MRN-0001';

INSERT INTO observations (patient_id, type, measurement_value, unit, observed_at)
SELECT p.id, 'BP_SYSTOLIC', 128, 'mmHg', TIMESTAMP '2025-01-15 09:41:00'
FROM patients p WHERE p.mrn = 'MRN-0001';

INSERT INTO care_gaps (patient_id, gap_type, status, detected_on, due_on, resolved_on)
SELECT p.id, 'A1C_SCREENING', 'CLOSED', '2024-12-20', '2025-01-31', '2025-01-15'
FROM patients p WHERE p.mrn = 'MRN-0001';

-- Patient 2
INSERT INTO encounters (patient_id, type, reason, encounter_at, location)
SELECT p.id, 'ER', 'Chest pain', TIMESTAMP '2025-02-10 20:15:00', 'Trinity ER'
FROM patients p WHERE p.mrn = 'MRN-0002';

INSERT INTO medications (patient_id, name, dosage, status, start_date, end_date)
SELECT p.id, 'Lisinopril', '10 mg QD', 'ACTIVE', '2025-02-12', NULL
FROM patients p WHERE p.mrn = 'MRN-0002';

INSERT INTO observations (patient_id, type, measurement_value, unit, observed_at)
SELECT p.id, 'HEART_RATE', 92, 'bpm', TIMESTAMP '2025-02-10 20:20:00'
FROM patients p WHERE p.mrn = 'MRN-0002';

INSERT INTO observations (patient_id, type, measurement_value, unit, observed_at)
SELECT p.id, 'BP_DIASTOLIC', 88, 'mmHg', TIMESTAMP '2025-02-10 20:20:00'
FROM patients p WHERE p.mrn = 'MRN-0002';

INSERT INTO care_gaps (patient_id, gap_type, status, detected_on, due_on, resolved_on)
SELECT p.id, 'FLU_VACCINE', 'OPEN', '2024-10-01', '2025-03-01', NULL
FROM patients p WHERE p.mrn = 'MRN-0002';

-- Seed three more patients
INSERT INTO patients (mrn, first_name, last_name, date_of_birth, gender)
VALUES
    ('MRN-0003', 'Mia', 'Chen', '1995-07-21', 'female'),
    ('MRN-0004', 'Liam', 'Garcia', '1968-02-02', 'male'),
    ('MRN-0005', 'Sophia', 'Patel', '2003-06-18', 'nonbinary')
    ON CONFLICT DO NOTHING;

-- Patient 3
INSERT INTO encounters (patient_id, type, reason, encounter_at, location)
SELECT p.id, 'OUTPATIENT', 'Diabetes follow-up', TIMESTAMP '2025-03-12 10:00:00', 'Westside Clinic'
FROM patients p WHERE p.mrn = 'MRN-0003';

INSERT INTO medications (patient_id, name, dosage, status, start_date, end_date)
SELECT p.id, 'Atorvastatin', '20 mg QHS', 'ACTIVE', '2024-09-15', NULL
FROM patients p WHERE p.mrn = 'MRN-0003';

INSERT INTO observations (patient_id, type, measurement_value, unit, observed_at)
SELECT p.id, 'LDL', 142, 'mg/dL', TIMESTAMP '2025-03-12 10:15:00'
FROM patients p WHERE p.mrn = 'MRN-0003';

INSERT INTO observations (patient_id, type, measurement_value, unit, observed_at)
SELECT p.id, 'BP_SYSTOLIC', 134, 'mmHg', TIMESTAMP '2025-03-12 10:16:00'
FROM patients p WHERE p.mrn = 'MRN-0003';

INSERT INTO care_gaps (patient_id, gap_type, status, detected_on, due_on, resolved_on)
SELECT p.id, 'LDL_SCREENING', 'CLOSED', '2025-03-12', '2025-03-31', '2025-03-12'
FROM patients p WHERE p.mrn = 'MRN-0003';

-- Patient 4
INSERT INTO encounters (patient_id, type, reason, encounter_at, location)
SELECT p.id, 'INPATIENT', 'Pneumonia', TIMESTAMP '2024-12-05 14:30:00', 'St. Mary''s Hospital'
FROM patients p WHERE p.mrn = 'MRN-0004';

INSERT INTO medications (patient_id, name, dosage, status, start_date, end_date)
SELECT p.id, 'Azithromycin', '500 mg QD', 'ACTIVE', '2024-12-05', NULL
FROM patients p WHERE p.mrn = 'MRN-0004';

INSERT INTO observations (patient_id, type, measurement_value, unit, observed_at)
SELECT p.id, 'O2_SAT', 93, '%', TIMESTAMP '2024-12-05 14:50:00'
FROM patients p WHERE p.mrn = 'MRN-0004';

INSERT INTO observations (patient_id, type, measurement_value, unit, observed_at)
SELECT p.id, 'TEMP', 38.3, 'C', TIMESTAMP '2024-12-05 14:52:00'
FROM patients p WHERE p.mrn = 'MRN-0004';

INSERT INTO care_gaps (patient_id, gap_type, status, detected_on, due_on, resolved_on)
SELECT p.id, 'PNEUMO_VACCINE', 'OPEN', '2024-12-06', '2025-01-31', NULL
FROM patients p WHERE p.mrn = 'MRN-0004';

-- Patient 5
INSERT INTO encounters (patient_id, type, reason, encounter_at, location)
SELECT p.id, 'URGENT_CARE', 'Ankle sprain', TIMESTAMP '2025-04-22 18:10:00', 'QuickCare Urgent'
FROM patients p WHERE p.mrn = 'MRN-0005';

INSERT INTO medications (patient_id, name, dosage, status, start_date, end_date)
SELECT p.id, 'Ibuprofen', '400 mg PRN', 'ACTIVE', '2025-04-22', NULL
FROM patients p WHERE p.mrn = 'MRN-0005';

INSERT INTO observations (patient_id, type, measurement_value, unit, observed_at)
SELECT p.id, 'WEIGHT', 68.0, 'kg', TIMESTAMP '2025-04-22 18:20:00'
FROM patients p WHERE p.mrn = 'MRN-0005';

INSERT INTO observations (patient_id, type, measurement_value, unit, observed_at)
SELECT p.id, 'BP_DIASTOLIC', 76, 'mmHg', TIMESTAMP '2025-04-22 18:21:00'
FROM patients p WHERE p.mrn = 'MRN-0005';

INSERT INTO care_gaps (patient_id, gap_type, status, detected_on, due_on, resolved_on)
SELECT p.id, 'TETANUS_VACCINE', 'OPEN', '2025-04-22', '2025-06-30', NULL
FROM patients p WHERE p.mrn = 'MRN-0005';
