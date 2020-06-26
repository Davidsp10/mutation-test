package com.example.test.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import com.example.test.domain.User;
import com.example.test.domain.Human;
import com.example.test.domain.repository.UserRepository;
import com.example.test.dto.resource.MutantStatsResource;

@Service
public class UserService {

	@Autowired private UserRepository userRepository;
	
	@Transactional(readOnly = true)
	public User retrieveById(Integer id) {
		LOG.info("retrieveById({})", id);
		return userRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, USER_NOT_FOUND));
	}
	
	@Transactional(readOnly = true)
	public User retrieveByNick(String nick) {
		LOG.info("retrieveByNick = {}", nick);
		return userRepository.findByNick(nick);
	}
	
	@Transactional(readOnly = true)
	public User retrieveByPassword(String password) {
		LOG.info("retrieveByPassword = {}", password);
		return userRepository.findByPassword(password);
	}
	
	@Transactional(readOnly = true)
	public MutantStatsResource readStats(User user) {
		
		MutantStatsResource statsInfo = new MutantStatsResource();
		statsInfo.setMutants(calculateMutants(user.getHumans()));
		statsInfo.setNotMutants(calculateNotMutants(user.getHumans()));
		statsInfo.setRatio( (double) (calculateMutants(user.getHumans()) / calculateNotMutants(user.getHumans())) );
		
		return statsInfo;
	}
	
	public Integer calculateMutants(List<Human> humans) { 
		int mutantCounter = 0;
		
		for(int i = 0; i < humans.size(); i++) {
			if(humans.get(i).getMutantStatus()) {
				mutantCounter++;
			}
		}
		
		return mutantCounter;
	}
	
	public Integer calculateNotMutants(List<Human> humans) {
		int notMutantCounter = 0;
		
		for(int i = 0; i < humans.size(); i++) {
			if(!humans.get(i).getMutantStatus()) {
				notMutantCounter++;
			}
		}
		
		return notMutantCounter;
	}	
	
	private static final Logger LOG = LoggerFactory.getLogger(UserService.class);
	public final static String USER_NOT_FOUND = "Usuario no encontrado";
	
}
