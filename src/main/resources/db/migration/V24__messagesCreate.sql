CREATE TABLE messages (
	message_id SERIAL PRIMARY KEY,
	sender_hospital_id INTEGER NOT NULL,
	recipient_hospital_id INTEGER,
	staff_id INTEGER NOT NULL,
	subject VARCHAR(200),
	main_text TEXT,
	state SMALLINT DEFAULT 0 NOT NULL,
	send_at TIMESTAMP,
	created_at TIMESTAMP NOT NULL,
	update_at TIMESTAMP NOT NULL,

	CONSTRAINT fk_messages_sender_hospital_id
		FOREIGN KEY (sender_hospital_id)
		REFERENCES hospital_management(hospital_id),
	CONSTRAINT fk_messages_recipient_hospital_id
		FOREIGN KEY (recipient_hospital_id)
		REFERENCES hospital_management(hospital_id),
	CONSTRAINT fk_messages_staff_id
		FOREIGN KEY (staff_id)
		REFERENCES staffs(staff_id)
);
