CREATE TABLE allergies (
	allergy_id SERIAL PRIMARY KEY,
	patient_id INTEGER NOT NULL,
	allergy_type VARCHAR(100) NOT NULL,
	allerty_symptom TEXT NOT NULL,

	CONSTRAINT fk_allergies_patient_id
		FOREIGN KEY (patient_id)
		REFERENCES patients(patient_id)
);
