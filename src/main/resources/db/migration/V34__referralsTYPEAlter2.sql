ALTER TABLE referrals
ALTER COLUMN shared_end_at TYPE DATE
USING shared_end_at::DATE;