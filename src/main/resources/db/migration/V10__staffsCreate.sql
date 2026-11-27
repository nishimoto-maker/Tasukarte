CREATE TABLE staffs (
	staff_id SERIAL PRIMARY KEY,
	hospital_id INTEGER NOT NULL,
	position_id INTEGER NOT NULL,
	role_id INTEGER NOT NULL,
	name_kanji VARCHAR(50) NOT NULL,
	name_kana VARCHAR(50) NOT NULL,
	gender SMALLINT NOT NULL,
	address VARCHAR(255) NOT NULL,
	post_code VARCHAR(7) NOT NULL,
	phone_number VARCHAR(20) NOT NULL,
	email_address VARCHAR(254) NOT NULL,
	is_deleted BOOLEAN NOT NULL,

	CONSTRAINT fk_staffs_hospital_id
		FOREIGN KEY (hospital_id)
		REFERENCES hospital_management(hospital_id),

	CONSTRAINT fk_staffs_position_id
		FOREIGN KEY (position_id)
		REFERENCES hospital_positions(position_id),

	CONSTRAINT fk_staffs_role_id
		FOREIGN KEY (role_id)
		REFERENCES hospital_roles(role_id)
);
