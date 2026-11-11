package com.tasukarte.auth.dto;

//Getter自動生成
//import lombok.Getter;
//Getter自動生成
//import lombok.Setter;


//@Getter
//@Setter
public class LoginRequest {
	
	//スタッフのID
	private Integer staffId;
	
	//password
	private String password;

	public Integer getStaffId() {
		return staffId;
	}

	public void setStaffId(Integer staffId) {
		this.staffId = staffId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	
}
