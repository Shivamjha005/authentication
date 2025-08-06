package com.example.authenticate.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.authenticate.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

	User findByUsername(String username);
		
}
