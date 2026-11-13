CREATE TABLE images (
    image_id SERIAL PRIMARY KEY,
    original_file_name VARCHAR(255) NOT NULL,
    stored_file_name VARCHAR(255) NOT NULL,
    file_path VARCHAR(500) NOT NULL,
    display_order INTEGER NOT NULL,
    image_description TEXT,
    medical_record_id INTEGER NOT NULL,

    CONSTRAINT fk_images_medical_record
        FOREIGN KEY (medical_record_id)
        REFERENCES medical_records(medical_record_id)
);