CREATE TABLE questionnaires (
	questionnaire_id SERIAL PRIMARY KEY,
	patient_id INTEGER NOT NULL,
	visit_purpose TEXT NOT NULL,
	symptom TEXT NOT NULL,
	symptom_onset TEXT NOT NULL,

	CONSTRAINT fk_questionnaires_patient_id
		FOREIGN KEY (patient_id)
		REFERENCES patients(patient_id)
);
