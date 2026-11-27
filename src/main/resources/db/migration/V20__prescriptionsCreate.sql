CREATE TABLE prescriptions (
	medical_record_id INTEGER PRIMARY KEY,
	remarks TEXT,
	issue_at TIMESTAMP NOT NULL,
	created_at TIMESTAMP,
	updated_at TIMESTAMP,

	CONSTRAINT fk_prescriptions_medical_record_id
		FOREIGN KEY (medical_record_id)
		REFERENCES medical_records(medical_record_id)
);
