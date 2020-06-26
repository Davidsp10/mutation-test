package com.example.test.controller;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.example.test.controller.validator.HumanValidator;
import com.example.test.domain.Human;
import com.example.test.dto.MutationRequest;
import com.example.test.dto.NewHumanRequest;
import com.example.test.dto.resource.HumanResource;
import com.example.test.service.HumanService;

@CrossOrigin
@RestController
@RequestMapping("/api/humans")

public class HumanRestController {
	
	@Autowired private HumanValidator humanValidator;
	@Autowired private HumanService humanService;
	
	@GetMapping
	public ResponseEntity<?> list() {
		LOG.info("list Request: {}");

		List<Human> humans = humanService.buildList();
		return new ResponseEntity<>(humans, HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<HumanResource> create(@RequestBody NewHumanRequest request) {
		
		LOG.debug("Create Request {}", request);
		
		request.setCreationDate(new Date());
		request.setAdn(humanService.createAdn());
		
		humanValidator.newHumanValidate(request);
		Human human = null;
		
		try {
			human = humanService.persist(request);
		} catch (Exception e) {
			LOG.error("Error in create human, {}", e.getMessage());
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage(), e);
		}
		
		if(human == null) throw new ResponseStatusException(HttpStatus.NOT_FOUND, HUMAN_NOT_FOUND);
		 
		return new ResponseEntity<HumanResource>(new HumanResource(human), HttpStatus.OK);
		
	}
	
	@PostMapping("/mutation")
	public ResponseEntity<HumanResource> mutationCheck(@RequestBody MutationRequest request) {
		
		LOG.debug("Mutation check: {}", request);
		
		Boolean mutant = humanService.hasMutation(request.getAdn());
		
		if(mutant) {
			return new ResponseEntity<HumanResource>(new HumanResource(HttpStatus.OK.getReasonPhrase(), 
				HttpStatus.OK.value()), HttpStatus.OK);
		} else {
			return new ResponseEntity<HumanResource>(new HumanResource(HttpStatus.FORBIDDEN.getReasonPhrase(), 
					HttpStatus.FORBIDDEN.value()), HttpStatus.FORBIDDEN);
		}
	
	}
		
	private static final Logger LOG = LoggerFactory.getLogger(HumanRestController.class);
	public final static String HUMAN_NOT_FOUND = "Humano no encontrado";

}
