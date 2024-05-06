package com.example.jwt.util;

import lombok.Data;

@Data
public class ApiResponse {
	
	private Object Data;
	private Object Error;
	private int Status;
	private String token;

}
