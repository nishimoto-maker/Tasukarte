ALTER TABLE shared_patients
ADD CONSTRAINT fk_shared_patients_share_with_hospital_id
FOREIGN KEY (share_with_hospital_id)
REFERENCES hospitals(hospital_id);
