CREATE TABLE shared_patients (
	share_patient_id SERIAL PRIMARY KEY,
	patient_id INTEGER NOT NULL,
	shared_by_hospital_id INTEGER NOT NULL,
	share_with_hospital_id INTEGER NOT NULL,
	referral_id INTEGER NOT NULL,
	shared_start_at DATE NOT NULL,
	shared_end_at DATE,
	is_deleted BOOLEAN NOT NULL,

	CONSTRAINT fk_shared_patients_patient_id
		FOREIGN KEY (patient_id)
		REFERENCES patients(patient_id)
);
