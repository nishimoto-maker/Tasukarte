CREATE TABLE hospital_bed_type (
	hospital_id INTEGER NOT NULL,
	bed_type_id INTEGER NOT NULL,
	authorized_bed_count INTEGER NOT NULL DEFAULT 0,
	operational_bed_count INTEGER NOT NULL DEFAULT 0,

	CONSTRAINT pk_hospital_bed_type
        PRIMARY KEY (hospital_id, bed_type_id),

	CONSTRAINT fk_hospital_bed_type_hospital_id
		FOREIGN KEY (hospital_id)
		REFERENCES hospital_management(hospital_id),

	CONSTRAINT fk_hospital_bed_type_bed_type_id
		FOREIGN KEY (bed_type_id)
		REFERENCES bed_types(bed_type_id)
);