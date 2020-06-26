package com.example.test.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import com.example.test.domain.Human;
import com.example.test.domain.NgBase;
import com.example.test.domain.repository.HumanRepository;
import com.example.test.dto.NewHumanRequest;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class HumanService {

	@Autowired
	private HumanRepository humanRepository;
	@Autowired
	private UserService userService;

	@Transactional
	public Human persist(NewHumanRequest humanForm) {
		LOG.info("persist = {}", humanForm);
		Human human = new Human();
		human.setName(humanForm.getName());
		human.setAdn(humanForm.getAdn());
		human.setMutantStatus(hasMutation(humanForm.getAdn()));
		human.setCreationDate(humanForm.getCreationDate());
		human.setUser(userService.retrieveById(humanForm.getUserId()));

		humanRepository.save(human);
		return human;
	}

	@Transactional(readOnly = true)
	public Human retrieveById(Integer id) {
		LOG.info("retrieveById({})", id);
		return humanRepository.findById(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, HUMAN_NOT_FOUND));
	}

	@Transactional(readOnly = true)
	public List<Human> buildList() {
		List<Human> humans = humanRepository.findAll();
		return humans;
	}
	
	public List<NgBase> createAdn(){
		int number = (int)(Math.random()*(6-4+1)+4);
		char[] letters = {'A', 'C', 'G', 'T'};
		
		List<NgBase> dna = new ArrayList<>();
		
		for(int i = 0; i < number; i++) {
			NgBase ngBase = new NgBase();
			String string = "";
 				for(int j = 0; j < number; j++) {
 					string += letters[(int)(Math.random()*4)];
 				}
 			ngBase.setBase(string);
			dna.add(ngBase);
		}
		
		LOG.info("Created DNA({})", dna);
		return dna;
	}
	
	public Boolean hasMutation(List<NgBase> adn) {
	
		boolean vertical = validateVertical(adn);
		if (vertical)
			return true;

		for (int i = 0; adn.size() > i; i++) {
			boolean horizontal = validateHorizontal(adn.get(i).getBase());
			if (horizontal)
				return true;
		}

		boolean diagonal = validateDiagonal(adn);
		if (diagonal)
			return true;

		return false;
	}
	
	/////////////////////////////////////////////////////////
	
	public static boolean validateVertical(List<NgBase> adn) {
		char[][] matrix = createMatrix(adn);
		System.out.println(matrix);
		int letterCounter = 1;

		for (int i = 0; i < adn.size(); i++) {
			for (int j = 0; j < adn.size(); j++) {
				if (j == 0)
					continue;
				if (matrix[j][i] == matrix[j - 1][i])
					letterCounter++;
				else
					letterCounter = 1;
				if (letterCounter == 4)
					return true;
			}
		}
		return false;		
	}

	private static boolean validateHorizontal(String ngBase) {
		int letterCounter = 1;
		char[] ngbChars = ngBase.toCharArray();

		for (int i = 0; i < ngbChars.length; i++) {
			if (i == 0)
				continue;
			if (ngbChars[i] == ngbChars[i - 1])
				letterCounter++;
			else
				letterCounter = 1;
			if (letterCounter == 4)
				return true;
		}
		return false;
	}

	public static boolean validateDiagonal(List<NgBase> adn) {
		char[][] matrix = createMatrix(adn);
	    int letterCounter = 1;

	    for (int i = 0; i < adn.size(); i++) {
	        for (int j = 0; j < adn.size(); j++) {
	            if (j == 0) continue;
	            if (i == 0) continue;
	
	            if (matrix[i - 1][j - 1] == matrix[i][j]) letterCounter++; else letterCounter = 1;

	            if (letterCounter == 4) return true;
	        }
	    }
	    return false;
	}

	private static char[][] createMatrix(List<NgBase> adn) {
		char[][] matrix = new char[adn.size()][adn.size()];
		for (int i = 0; i < adn.size(); i++) {
			for (int j = 0; j < adn.get(0).getBase().length(); j++) {
				matrix[i][j] = adn.get(i).getBase().toCharArray()[j];
			}
		}
		return matrix;
	}

	///////////////////////////////

	private static final Logger LOG = LoggerFactory.getLogger(HumanService.class);
	public final static String HUMAN_NOT_FOUND = "Humano no encontrado";

}
