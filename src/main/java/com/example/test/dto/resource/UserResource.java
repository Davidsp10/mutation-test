package com.example.test.dto.resource;

import java.io.Serializable;
import java.util.List;

import com.example.test.domain.*;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;

@SuppressWarnings("serial")
@Data
@JsonInclude(Include.NON_NULL)
public class UserResource extends GenericResource implements Serializable {

	private Integer identifier;
	private String nick;
	private List<Human> humans;
	
	public UserResource() {
		
	}
	
	public UserResource(User user) {
		this.identifier = user.getId();
		this.nick = user.getNick();
		this.humans = user.getHumans();	
	}
	
	public UserResource(Integer identifier, String nick, String password, List<Human> humans) {
		this.identifier = identifier;
		this.nick = nick;
		this.humans = humans;
	}
	
	public UserResource(String message, Integer code) {
		setMessage(message);
		setCode(code);
	}

	
	////////////////////////////////////////////////////
	
	public Integer getIdentifier() {
		return identifier;
	}

	public void setIdentifier(Integer identifier) {
		this.identifier = identifier;
	}

	public String getNick() {
		return nick;
	}

	public void setNick(String nick) {
		this.nick = nick;
	}

	public List<Human> getHumans() {
		return humans;
	}

	public void setHumans(List<Human> humans) {
		this.humans = humans;
	}
		
}
