CREATE TABLE IF NOT EXISTS patients (
    id SERIAL PRIMARY KEY,
    first_name VARCHAR(64) NOT NULL,
    last_name  VARCHAR(64) NOT NULL,
    dob DATE NOT NULL,
    gender VARCHAR(16),
    phone VARCHAR(32),
    email VARCHAR(128)

    );
CREATE INDEX IF NOT EXISTS idx_patients_name ON patients(last_name, first_name);

-- Observations (vitals, labs, etc.)
CREATE TABLE IF NOT EXISTS observations (
    id SERIAL PRIMARY KEY,
    patient_id INT NOT NULL REFERENCES patients(id) ON DELETE CASCADE,
    type VARCHAR(32) NOT NULL,           -- e.g., BP_SYS, BP_DIA, HR, TEMP
    value NUMERIC(10,2) NOT NULL,
    unit VARCHAR(16) NOT NULL,
    observed_at TIMESTAMP NOT NULL
    );
CREATE INDEX IF NOT EXISTS idx_obs_patient_time ON observations(patient_id, observed_at DESC);

-- Medications
CREATE TABLE IF NOT EXISTS medications (
    id SERIAL PRIMARY KEY,
    patient_id INT NOT NULL REFERENCES patients(id) ON DELETE CASCADE,
    name VARCHAR(128) NOT NULL,
    dosage VARCHAR(64),
    frequency VARCHAR(64),
    start_date DATE NOT NULL,
    end_date DATE
    );
CREATE INDEX IF NOT EXISTS idx_meds_patient ON medications(patient_id);

-- Encounters (visits)
CREATE TABLE IF NOT EXISTS encounters (
    id SERIAL PRIMARY KEY,
    patient_id INT NOT NULL REFERENCES patients(id) ON DELETE CASCADE,
    type VARCHAR(64) NOT NULL,           -- "Annual Checkup", "ER", "Televisit"
    location VARCHAR(128),
    start_time TIMESTAMP NOT NULL,
    end_time   TIMESTAMP
    );
CREATE INDEX IF NOT EXISTS idx_enc_patient_time ON encounters(patient_id, start_time DESC);

-- Care gaps (preventive items due)
CREATE TABLE IF NOT EXISTS care_gaps (
    id SERIAL PRIMARY KEY,
    patient_id INT NOT NULL REFERENCES patients(id) ON DELETE CASCADE,
    description VARCHAR(256) NOT NULL,
    due_date DATE
    );
CREATE INDEX IF NOT EXISTS idx_cg_patient_due ON care_gaps(patient_id, due_date);

