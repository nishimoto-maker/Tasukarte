package com.tasukarte.auth.dto;

//Getter自動生成
import lombok.Getter;
//Getter自動生成
import lombok.Setter;


@Getter
@Setter
public class LoginRequest {
	
	//スタッフのID
	private String staffId;
	
	//スタッフの所属病院ID
	private String hospitalId;
	
	private String password;
}
