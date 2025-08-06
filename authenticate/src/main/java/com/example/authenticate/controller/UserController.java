package com.example.authenticate.controller;

import com.example.authenticate.dto.JwtResponse;
import com.example.authenticate.dto.UserLoginDto;
import com.example.authenticate.dto.UserSignupDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.authenticate.entity.User;
import com.example.authenticate.services.UserService;

@RestController
@RequestMapping("/api")
public class UserController {

	@Autowired
	private UserService userService;
	
	@PostMapping("/signup")
	public ResponseEntity<JwtResponse>signup(@RequestBody UserSignupDto signupDto) {
		JwtResponse response = userService.signup(signupDto);
		return ResponseEntity.ok(response);
	}
	
	@PostMapping("/login")
	public ResponseEntity<JwtResponse> login(@RequestBody UserLoginDto userLoginDto) {
		JwtResponse response = userService.login(userLoginDto);
		return ResponseEntity.ok(response);
	}
}
