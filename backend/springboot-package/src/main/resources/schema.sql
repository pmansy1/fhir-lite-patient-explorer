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
