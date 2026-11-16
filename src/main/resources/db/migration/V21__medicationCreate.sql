CREATE TABLE medication (
	medication_id SERIAL PRIMARY KEY,
	medical_record_id INTEGER NOT NULL,
	medication_name VARCHAR(128) NOT NULL,
	usage_dosage TEXT NOT NULL,
	prescription_days INT NOT NULL,

	CONSTRAINT fk_medication_medical_record_id
		FOREIGN KEY (medical_record_id)
		REFERENCES medical_records(medical_record_id)
);
