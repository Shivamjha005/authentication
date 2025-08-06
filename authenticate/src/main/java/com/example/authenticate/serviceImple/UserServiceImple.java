package com.example.authenticate.serviceImple;

import com.example.authenticate.dto.JwtResponse;
import com.example.authenticate.dto.UserLoginDto;
import com.example.authenticate.dto.UserSignupDto;
import com.example.authenticate.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.example.authenticate.entity.User;
import com.example.authenticate.repository.UserRepository;
import com.example.authenticate.services.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImple implements UserService {
	String message;
	
	@Autowired
	private UserRepository userRepository;
	
	private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

	@Autowired
	private JwtUtils jwtUtils;

	@Override
	public JwtResponse signup(UserSignupDto userDto) {
		User user = new User();
		user.setUsername(userDto.getUsername());
		user.setPassword(passwordEncoder.encode(userDto.getPassword()));
		user.setTryCnt(0);
		user.setLock(false);
		user.setOtpRequired(false);
		userRepository.save(user);
		// TODO Auto-generated method stub
		String accessToken = jwtUtils.generateAccessToken(user.getUsername());
		String refreshToken = jwtUtils.generateRefreshToken(user.getUsername());
		return new JwtResponse(accessToken, refreshToken, "Signup successfull");
	}

	@Override
	public JwtResponse login(UserLoginDto userLoginDto) {

//		String message = "";
		// TODO Auto-generated method stub
		User user = userRepository.findByUsername(userLoginDto.getUsername());
		String accessToken = "";//jwtUtils.generateAccessToken(user.getUsername());
		String refreshToken = "";//jwtUtils.generateRefreshToken(user.getUsername());

		if(user == null) return new JwtResponse(accessToken, refreshToken, "Login failed no user found");
		if(user.isLock()) {
			if(!user.isOtpRequired()) return new JwtResponse(accessToken, refreshToken, "Login failed Account is locked Contact admin");//return "Account is locked Contact Admin";
			if(userLoginDto.getOtp() == null || !userLoginDto.getOtp().equals("123456") ) {
				//return "otp is in correct";
				return new JwtResponse(accessToken, refreshToken,"Login failed otp incorrect");
			}
			
			user.setTryCnt(0);
			user.setLock(false);
			user.setOtpRequired(false);
			userRepository.save(user);
			 accessToken = jwtUtils.generateAccessToken(user.getUsername());
			refreshToken = jwtUtils.generateRefreshToken(user.getUsername());

			return new JwtResponse(accessToken, refreshToken, "login Successfully with otp");
		}

		
		if(!passwordEncoder.matches(userLoginDto.getPassword(), user.getPassword())) {
			user.setTryCnt(user.getTryCnt() + 1);
			if(user.getTryCnt() >= 3) {
				user.setLock(true);
				user.setOtpRequired(true);
			}
			userRepository.save(user);
			return new JwtResponse(accessToken, refreshToken, "Login failed: Incorrect password");
		}

		user.setTryCnt(0);
		userRepository.save(user);
		accessToken = jwtUtils.generateAccessToken(user.getUsername());
		refreshToken = jwtUtils.generateRefreshToken(user.getUsername());
		return new JwtResponse(accessToken, refreshToken, "Login successful");
		
//		return null;
	}

}
