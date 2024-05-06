package com.example.jwt.dto;

import java.util.UUID;

import lombok.Data;

@Data
public class SignupDto {
	private UUID id;
	private String name;
	private String gender;
	private String email;
	private String mobileNumber;
	private String password;
	

}
