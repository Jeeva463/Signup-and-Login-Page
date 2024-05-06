package com.example.jwt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.jwt.dto.LoginDto;
import com.example.jwt.dto.SignupDto;
import com.example.jwt.service.SignupService;
import com.example.jwt.util.ApiResponse;

@RestController
@RequestMapping("/api/signup")
public class SignupController {
	@Autowired
	SignupService signupService;
	 @PostMapping("/register")
	public ResponseEntity <ApiResponse> signup(@RequestBody SignupDto signupDto){
		ApiResponse apiResponse = signupService.signup(signupDto);
		return ResponseEntity.status(apiResponse.getStatus()).body(apiResponse);
	}
	 @PostMapping("/login")
	 public ResponseEntity<Object> login (@RequestBody LoginDto loginDto){
		 ApiResponse apiResponse =  signupService.login(loginDto);
		return ResponseEntity.status(apiResponse.getStatus()).body(apiResponse);
	 }
}
