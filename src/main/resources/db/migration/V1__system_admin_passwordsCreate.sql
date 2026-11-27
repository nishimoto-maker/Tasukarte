CREATE TABLE system_admins (
    admin_id SERIAL PRIMARY KEY,
    admin_name VARCHAR(255) NOT NULL,
    admin_name_kana VARCHAR(255) NOT NULL,
    admin_mail VARCHAR(255) NOT NULL
);
