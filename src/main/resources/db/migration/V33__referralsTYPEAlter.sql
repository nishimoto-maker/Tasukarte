ALTER TABLE referrals
ALTER COLUMN shared_start_at TYPE DATE
USING shared_start_at::DATE;