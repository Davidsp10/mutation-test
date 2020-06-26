package com.example.test.controller.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import com.example.test.dto.NewHumanRequest;
import com.example.test.domain.User;
import com.example.test.service.UserService;

@Component
public class HumanValidator {
	
	@Autowired private UserService userService;
	
	public void newHumanValidate(NewHumanRequest newHumanRequest) {
		User user = userService.retrieveById(newHumanRequest.getUserId());
		if(user == null) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, USER_NOT_FOUND);
	}
	
	public final static String USER_NOT_FOUND = "Usuario señalado no encontrado";

}
