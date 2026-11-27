CREATE TABLE hospital_medical_departments (
	hospital_id INTEGER NOT NULL,
	medical_department_id INTEGER NOT NULL,
	
	CONSTRAINT pk_hospital_medical_departments
        PRIMARY KEY (hospital_id, medical_department_id),

	CONSTRAINT fk_hospital_medical_departments_hospital_id
		FOREIGN KEY (hospital_id)
		REFERENCES hospital_management(hospital_id),

	CONSTRAINT fk_hospital_medical_departments_medical_department_id
		FOREIGN KEY (medical_department_id)
		REFERENCES medical_departments(medical_department_id)
			
);
