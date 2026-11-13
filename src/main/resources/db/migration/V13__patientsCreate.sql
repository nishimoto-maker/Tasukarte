CREATE TABLE patients (
	patient_id SERIAL PRIMARY KEY,
	hospital_id INTEGER NOT NULL,
	name VARCHAR(255) NOT NULL,
	kata VARCHAR(255) NOT NULL,
	birth_day DATE NOT NULL,
	gender SMALLINT NOT NULL,
	phone_number VARCHAR(20) NOT NULL,
	email_address VARCHAR(255) NOT NULL,
	address VARCHAR(255) NOT NULL,
	post_code VARCHAR(7) NOT NULL,
	blood_type VARCHAR(2) NOT NULL,
	rh BOOLEAN NOT NULL,
	allergy BOOLEAN NOT NULL,
	weight NUMERIC(5,2) NOT NULL,
	height NUMERIC(5,2) NOT NULL,
	created_at TIMESTAMP NOT NULL,
	updated_at TIMESTAMP,

	CONSTRAINT fk_patients_hospital_id
		FOREIGN KEY (hospital_id)
		REFERENCES hospital_management(hospital_id)
);
