CREATE TABLE staff_passwords (
	staff_id INTEGER PRIMARY KEY,
	password_hash VARCHAR(255) NOT NULL,

	CONSTRAINT fk_staff_passwords_staff_id
		FOREIGN KEY (staff_id)
		REFERENCES staffs(staff_id)
);
