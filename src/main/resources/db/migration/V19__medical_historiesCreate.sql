CREATE TABLE medical_histories (
	medical_history_id SERIAL PRIMARY KEY,
	questionnaire_id INTEGER NOT NULL,
	desease_name VARCHAR(100) NOT NULL,
	medical_record_id INTEGER,

	CONSTRAINT fk_medical_histories_questionnaire_id
		FOREIGN KEY (questionnaire_id)
		REFERENCES questionnaires(questionnaire_id),
	CONSTRAINT fk_medical_histories_medical_record_id
		FOREIGN KEY (medical_record_id)
		REFERENCES medical_records(medical_record_id)
);
