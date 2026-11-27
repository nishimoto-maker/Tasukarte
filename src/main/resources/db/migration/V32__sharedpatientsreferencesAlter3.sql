ALTER TABLE shared_patients
ADD CONSTRAINT fk_shared_patients_referral_id
FOREIGN KEY (referral_id)
REFERENCES referrals(referral_id);