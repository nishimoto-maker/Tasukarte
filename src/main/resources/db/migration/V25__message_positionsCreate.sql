CREATE TABLE message_positions (
	recipient_position_id SERIAL PRIMARY KEY,
	message_id INTEGER NOT NULL,
	position_id INTEGER NOT NULL,

	CONSTRAINT fk_message_positions_message_id
		FOREIGN KEY (message_id)
		REFERENCES messages(message_id),
	CONSTRAINT fk_message_positions_position_id
		FOREIGN KEY (position_id)
		REFERENCES hospital_positions(position_id)
);
