INSERT INTO patients (first_name, last_name, dob, gender, phone, email) VALUES
    ('Alex','Johnson','1988-04-12','male','555-111-2222','alex.j@example.com'),
    ('Brianna','Nguyen','1995-10-03','female','555-333-4444','b.nguyen@example.com')
    ON CONFLICT DO NOTHING;
