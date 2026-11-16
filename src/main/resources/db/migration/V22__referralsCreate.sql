CREATE TABLE referrals (
	referral_id SERIAL PRIMARY KEY,
	patient_id INTEGER NOT NULL,
	sender_hospital_id INTEGER NOT NULL,
	recipient_hospital_id INTEGER NOT NULL,
	staff_id INTEGER NOT NULL,
	referral_purpose TEXT,
	request_content TEXT,
	remarks TEXT,
	created_at TIMESTAMP,
	requested_at TIMESTAMP,
	approved_at TIMESTAMP,
	status VARCHAR(20) NOT NULL,
	is_deleted BOOLEAN NOT NULL,
	shared_start_at TIMESTAMP,
	shared_end_at TIMESTAMP,

	CONSTRAINT fk_referrals_patient_id
		FOREIGN KEY (patient_id)
		REFERENCES patients(patient_id),
	CONSTRAINT fk_referrals_sender_hospital_id
		FOREIGN KEY (sender_hospital_id)
		REFERENCES hospital_management(hospital_id),
	CONSTRAINT fk_referrals_recipient_hospital_id
		FOREIGN KEY (recipient_hospital_id)
		REFERENCES hospital_management(hospital_id),
	CONSTRAINT fk_referrals_staff_id
		FOREIGN KEY (staff_id)
		REFERENCES staffs(staff_id)
);

