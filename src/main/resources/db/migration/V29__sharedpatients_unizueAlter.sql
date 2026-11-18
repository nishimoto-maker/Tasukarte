ALTER TABLE shared_patients
ADD CONSTRAINT uq_shared_patients_referral_id
UNIQUE (referral_id);