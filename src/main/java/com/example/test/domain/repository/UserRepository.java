package com.example.test.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.test.domain.*;

public interface UserRepository extends JpaRepository<User, Integer> {

	User findByNick(String nick);
	
	User findByPassword(String password);
}
