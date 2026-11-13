CREATE TABLE message_files (
	attachment_file_id SERIAL PRIMARY KEY,
	message_id INTEGER NOT NULL,
	file_name VARCHAR(255) NOT NULL,
	saved_file_name VARCHAR(255) NOT NULL,
	file_path VARCHAR(500) NOT NULL,
	file_size BIGINT NOT NULL,
	created_at TIMESTAMP NOT NULL,

	CONSTRAINT fk_message_files_message_id
		FOREIGN KEY (message_id)
		REFERENCES messages(message_id)
);
