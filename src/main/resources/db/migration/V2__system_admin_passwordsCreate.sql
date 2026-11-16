CREATE TABLE system_admin_passwords (
    admin_id INTEGER PRIMARY KEY,
    password_hash VARCHAR(255) NOT NULL,

    CONSTRAINT fk_system_admin_passwords_admin_id
	FOREIGN KEY (admin_id)
	REFERENCES system_admins(admin_id)
);
