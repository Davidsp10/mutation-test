package com.example.test.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.example.test.controller.validator.UserValidator;
import com.example.test.domain.User;
import com.example.test.dto.LoginRequest;
import com.example.test.dto.resource.MutantStatsResource;
import com.example.test.dto.resource.UserResource;
import com.example.test.service.UserService;


@CrossOrigin
@RestController
@RequestMapping("/api/users")
public class UserRestController {
	
	@Autowired private UserValidator userValidator;
	@Autowired private UserService userService;

	@GetMapping("/{id}")
	public ResponseEntity<UserResource> read(@PathVariable Integer id) {
		LOG.info("Read Id: {}", id);

		User user = userService.retrieveById(id);
		if(user == null) throw new ResponseStatusException(HttpStatus.NOT_FOUND, USER_NOT_FOUND);

		return new ResponseEntity<UserResource>(new UserResource(user), HttpStatus.OK);
	}
	
	@PostMapping("/login")
	public ResponseEntity<UserResource> userLogin(@RequestBody LoginRequest request) {
		LOG.debug("Login request: {}", request);
		
		userValidator.loginValidate(request);
		
		return new ResponseEntity<UserResource>(new UserResource(HttpStatus.OK.getReasonPhrase(), 
				HttpStatus.OK.value()), HttpStatus.OK);
	}
	
	@GetMapping("/stats/{id}")
	public ResponseEntity<MutantStatsResource> stats(@PathVariable Integer id) {
	
		LOG.info("Read Id: {}", id);

		User user = userService.retrieveById(id);
		MutantStatsResource statsInfo = userService.readStats(user);
		
		if(statsInfo == null) throw new ResponseStatusException(HttpStatus.NOT_FOUND, USER_NOT_FOUND);

		return new ResponseEntity<MutantStatsResource>(statsInfo, HttpStatus.OK);

	}
	
	private static final Logger LOG = LoggerFactory.getLogger(UserRestController.class);
	public final static String USER_NOT_FOUND = "Usuario no encontrado";
}
