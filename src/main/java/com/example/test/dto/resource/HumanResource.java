package com.example.test.dto.resource;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.example.test.domain.*;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;

@SuppressWarnings("serial")
@Data
@JsonInclude(Include.NON_NULL)
public class HumanResource extends GenericResource implements Serializable {
	
	private Integer identifier;
	private String name;
	private List<NgBase> adn;
	private Boolean mutantStatus;
	private Date creationDate;
	
	public HumanResource() {
		
	}
	
	public HumanResource(Human human) {
		this.identifier = human.getId();
		this.name = human.getName();
		this.adn = human.getAdn();
		this.mutantStatus = human.getMutantStatus();
		this.creationDate = human.getCreationDate();
	}
	
	public HumanResource(Integer identifier, String name, List<NgBase> adn, Boolean mutantStatus, Date creationDate) {
		this.identifier = identifier;
		this.name = name;
		this.adn = adn;
		this.mutantStatus = mutantStatus;
		this.creationDate = creationDate;
	}
	
	public HumanResource(String message, Integer code) {
		setMessage(message);
		setCode(code);
	}
	
	/////////////////////////////////////////////////

	public Integer getIdentifier() {
		return identifier;
	}

	public void setIdentifier(Integer identifier) {
		this.identifier = identifier;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<NgBase> getAdn() {
		return adn;
	}

	public void setAdn(List<NgBase> adn) {
		this.adn = adn;
	}

	public Boolean getMutantStatus() {
		return mutantStatus;
	}

	public void setMutantStatus(Boolean mutantStatus) {
		this.mutantStatus = mutantStatus;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}
	
}
