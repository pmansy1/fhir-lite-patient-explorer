INSERT INTO patients (first_name, last_name, dob, gender, phone, email, id) VALUES
    ('Alex','Johnson','1988-04-12','male','555-111-2222','alex.j@example.com', 1),
    ('Brianna','Nguyen','1995-10-03','female','555-333-4444','b.nguyen@example.com', 2),
    ('Carlos','Ramirez','1979-02-17','male','555-555-0101','carlos.ramirez@example.com', 3),
    ('Diana','Patel','1986-07-29','female','555-555-0102','d.patel@example.com',4),
    ('Ethan','O''Connor','1992-11-05','male','555-555-0103','ethan.oconnor@example.com', 5),
    ('Fatima','Ali','2000-03-14','female','555-555-0104','fatima.ali@example.com', 6),
    ('George','Chen','1975-09-22','male','555-555-0105','george.chen@example.com', 7),
    ('Hannah','Mueller','1989-12-01','female','555-555-0106','hannah.mueller@example.com',8),
    ('Isaac','Kowalski','1998-06-18','male','555-555-0107','isaac.kowalski@example.com', 9),
    ('Jade','Williams','1983-01-27','female','555-555-0108','jade.williams@example.com' 10)
    ON CONFLICT DO NOTHING;

-- Observations
INSERT INTO observations(patient_id,type,value,unit,observed_at) VALUES
    (1,'BP_SYS',122,'mmHg', now() - interval '2 days'),
    (1,'BP_DIA',78 ,'mmHg', now() - interval '2 days'),
    (1,'HR'    ,72 ,'bpm' , now() - interval '2 days'),
    (1,'TEMP'  ,98.6,'F'  , now() - interval '1 day');

-- Medications
INSERT INTO medications(patient_id,name,dosage,frequency,start_date,end_date) VALUES
    (1,'Lisinopril','10 mg','daily', current_date - 120, null),
    (1,'Metformin' ,'500 mg','BID' , current_date - 300, null);

-- Encounters
INSERT INTO encounters(patient_id,type,location,start_time,end_time) VALUES
    (1,'Annual Checkup','Trinity Clinic', now() - interval '40 days', now() - interval '40 days' + interval '30 minutes'),
    (1,'Televisit','Virtual', now() - interval '10 days', now() - interval '10 days' + interval '20 minutes');

-- Care gaps
INSERT INTO care_gaps(patient_id,description,due_date) VALUES
    (1,'Flu vaccine due', date_trunc('day', now()) + interval '20 days'),
    (1,'Annual eye exam overdue', date_trunc('day', now()) - interval '15 days');
