package com.example.jwt.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.example.jwt.dto.LoginDto;
import com.example.jwt.dto.SignupDto;
import com.example.jwt.entity.Signup;
import com.example.jwt.repository.SignupRepository;
import com.example.jwt.util.ApiResponse;
import com.example.jwt.util.JWT;
@Service
public class SignupService {
	@Autowired
	SignupRepository signupRepository;
	@Autowired
	JWT jwt;

	public ApiResponse signup(SignupDto signupDto) {
		ApiResponse apiResponse = new ApiResponse();
		Signup obj = new Signup();
		obj.setName(signupDto.getName());
		obj.setGender(signupDto.getGender());
		obj.setMobileNumber(signupDto.getMobileNumber());
		obj.setEmail(signupDto.getEmail());
		obj.setPassword(signupDto.getPassword());
		//obj = signupRepository.save(obj);
		
		if(obj.getPassword() == null || obj.getPassword().isEmpty()) {
			//apiResponse.setStatus(HttpStatus.OK.value());
			//apiResponse.setData(obj);
			apiResponse.setStatus(HttpStatus.BAD_REQUEST.value());
			apiResponse.setError("Bad request");
		}else {
			//apiResponse.setStatus(HttpStatus.OK.value());
			//apiResponse.setError("Bad request");
			apiResponse.setStatus(HttpStatus.OK.value());
			apiResponse.setData(obj);
			signupRepository.save(obj);
	        String token = jwt.generated(obj);
	        apiResponse.setToken(token);

		}
		return apiResponse;
		
		
		
	}
//
//	public apiResponse login(LoginDto loginDto) {
//		ApiResponse apiResponse = new ApiResponse();
//		Signup signup =	signupRepository.findbyemailandpassword(loginDto.getEmail(),loginDto.getPassword());
//		
//		if(signup.getEmail() == null && signup.getPassword()== null ) {
//			apiResponse.setStatus(HttpStatus.BAD_REQUEST.value());
//			apiResponse.setError("Bad Request");
//			}else {
//				apiResponse.setStatus(HttpStatus.OK.value());
//				apiResponse.setData(signup);
//				signupRepository.save(signup);
//		        String token = jwt.generated(signup);
//		        apiResponse.setToken(token);
//			}
//		return apiResponse;

	public ApiResponse login(LoginDto loginDto) {
		ApiResponse apiResponse = new ApiResponse();
		Signup signUp = signupRepository.findbyemailandpassword(loginDto.getEmail(), loginDto.getPassword());
		
		if(signUp != null) {
			apiResponse.setStatus(HttpStatus.OK.value());
			apiResponse.setData(signUp);
			String token= jwt.generated(signUp);
			apiResponse.setToken(token);
		}else {
			apiResponse.setStatus(HttpStatus.BAD_REQUEST.value());
			apiResponse.setError("Bad request");
		}
		return apiResponse;
	}
	

}
