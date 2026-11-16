CREATE TABLE password_resets (
	password_reset_id SERIAL PRIMARY KEY,
	staff_id INTEGER NOT NULL,
	reset_token VARCHAR(255) NOT NULL,
	expiration_date TIMESTAMP NOT NULL,

	CONSTRAINT fk_password_resets_staff_id
		FOREIGN KEY (staff_id)
		REFERENCES staffs(staff_id)
);
