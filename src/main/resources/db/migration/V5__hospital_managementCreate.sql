CREATE TABLE hospital_management (
    hospital_id SERIAL PRIMARY KEY,
    hospital_name VARCHAR(255) NOT NULL,
    address VARCHAR(255) NOT NULL,
    post_code VARCHAR(10) NOT NULL,
    phone_number VARCHAR(20) NOT NULL,
    hospital_derector_name VARCHAR(100) NOT NULL,
    established_date DATE NOT NULL,
    hospital_mail VARCHAR(255) NOT NULL,
    is_deleted BOOLEAN NOT NULL
);