-- Patients (root)
CREATE TABLE IF NOT EXISTS patients (
    id BIGSERIAL PRIMARY KEY,
    mrn VARCHAR(64) NOT NULL UNIQUE,
    first_name VARCHAR(64) NOT NULL,
    last_name VARCHAR(64) NOT NULL,
    date_of_birth DATE NOT NULL,
    gender VARCHAR(32)
    );

-- Encounters
CREATE TABLE IF NOT EXISTS encounters (
    id BIGSERIAL PRIMARY KEY,
    patient_id BIGINT NOT NULL REFERENCES patients(id) ON DELETE CASCADE,
    type VARCHAR(64) NOT NULL,
    reason VARCHAR(256),
    encounter_at TIMESTAMP NOT NULL,
    location VARCHAR(128)
    );

-- Medications
CREATE TABLE IF NOT EXISTS medications (
    id BIGSERIAL PRIMARY KEY,
    patient_id BIGINT NOT NULL REFERENCES patients(id) ON DELETE CASCADE,
    name VARCHAR(128) NOT NULL,
    dosage VARCHAR(64),
    status VARCHAR(32),
    start_date DATE,
    end_date DATE
    );

-- Observations
CREATE TABLE IF NOT EXISTS observations (
    id BIGSERIAL PRIMARY KEY,
    patient_id BIGINT NOT NULL REFERENCES patients(id) ON DELETE CASCADE,
    type VARCHAR(32) NOT NULL,
    measurement_value NUMERIC(10,2) NOT NULL,
    unit VARCHAR(16) NOT NULL,
    observed_at TIMESTAMP NOT NULL
    );

-- Care Gaps
CREATE TABLE IF NOT EXISTS care_gaps (
    id BIGSERIAL PRIMARY KEY,
    patient_id BIGINT NOT NULL REFERENCES patients(id) ON DELETE CASCADE,
    gap_type VARCHAR(64) NOT NULL,
    status VARCHAR(32) NOT NULL,
    detected_on DATE,
    due_on DATE,
    resolved_on DATE
    );

-- Indexes
CREATE INDEX IF NOT EXISTS idx_encounters_patient   ON encounters(patient_id);
CREATE INDEX IF NOT EXISTS idx_medications_patient  ON medications(patient_id);
CREATE INDEX IF NOT EXISTS idx_observations_patient ON observations(patient_id);
CREATE INDEX IF NOT EXISTS idx_care_gaps_patient    ON care_gaps(patient_id);
CREATE INDEX IF NOT EXISTS idx_observations_type    ON observations(type);
