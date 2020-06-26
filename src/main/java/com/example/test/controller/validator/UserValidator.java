package com.example.test.controller.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import com.example.test.domain.User;
import com.example.test.dto.LoginRequest;
import com.example.test.service.UserService;

@Component
public class UserValidator {
	
@Autowired private UserService userService;
	
	public void loginValidate(LoginRequest loginRequest) {
		
		User nUser = userService.retrieveByNick(loginRequest.getNick());
		if(nUser == null) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, USER_NOT_FOUND);
		
		User pUser = userService.retrieveByPassword(loginRequest.getPassword());
		if(pUser == null) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, INCORRECT_PASSWORD);
		
		if(pUser != null && nUser.getId() != pUser.getId()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El nick de usuario o contraseña es incorrecta!");
		}
		
	}
	
	public final static String USER_NOT_FOUND = "Usuario señalado no encontrado";
	public final static String INCORRECT_PASSWORD = "Contraseña incorrecta";
}
