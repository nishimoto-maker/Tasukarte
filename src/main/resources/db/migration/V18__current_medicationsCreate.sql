CREATE TABLE current_medications (
	current_medication_id SERIAL PRIMARY KEY,
	questionnaire_id INTEGER NOT NULL,
	medicine_name VARCHAR(100) NOT NULL,

	CONSTRAINT fk_current_medications_questionnaire_id
		FOREIGN KEY (questionnaire_id)
		REFERENCES questionnaires(questionnaire_id)
);
