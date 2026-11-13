CREATE TABLE medical_records (
    medical_record_id SERIAL PRIMARY KEY,
    patient_id INTEGER NOT NULL,
    staff_id INTEGER NOT NULL,
    desease_name VARCHAR(100) NOT NULL,
    outcome VARCHAR(10),
    outcome_date DATE,
    progress_s TEXT NOT NULL,
    progress_o TEXT NOT NULL,
    progress_a TEXT NOT NULL,
    progress_p TEXT NOT NULL,
    procedure TEXT NOT NULL,
    follow_up_date DATE,
    medical_record_date DATE NOT NULL,
    has_prescription BOOLEAN NOT NULL,

    CONSTRAINT fk_medical_records_patient
        FOREIGN KEY (patient_id)
        REFERENCES patients(patient_id),
    CONSTRAINT fk_medical_records_staff
        FOREIGN KEY (staff_id)
        REFERENCES staffs(staff_id)
);