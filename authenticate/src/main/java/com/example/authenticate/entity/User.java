package com.example.authenticate.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "users")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(unique = true)
	private String username;
	
	private String password;
	
	private int tryCnt;
	
	private boolean lock;
	
	private boolean otpRequired;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getTryCnt() {
		return tryCnt;
	}

	public void setTryCnt(int tryCnt) {
		this.tryCnt = tryCnt;
	}

	public boolean isLock() {
		return lock;
	}

	public void setLock(boolean lock) {
		this.lock = lock;
	}

	public boolean isOtpRequired() {
		return otpRequired;
	}

	public void setOtpRequired(boolean otpRequired) {
		this.otpRequired = otpRequired;
	}
	
	
	
	
	
}
