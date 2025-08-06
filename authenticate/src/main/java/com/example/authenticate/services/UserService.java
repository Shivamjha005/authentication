package com.example.authenticate.services;

import com.example.authenticate.dto.JwtResponse;
import com.example.authenticate.dto.UserLoginDto;
import com.example.authenticate.dto.UserSignupDto;
import com.example.authenticate.entity.User;
import org.springframework.stereotype.Service;


public interface UserService {

	JwtResponse signup(UserSignupDto userSignupDto);
	
	JwtResponse login(UserLoginDto userLoginDto);
}
