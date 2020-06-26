package com.example.test.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

//import javax.validation.constraints.NotNull;

import com.example.test.domain.NgBase;

import lombok.Data;


@SuppressWarnings("serial")
@Data
public class NewHumanRequest implements Serializable {
	
	private String name;
	
	private List<NgBase> adn;
	
	private Date creationDate;
	
	//@NotNull
	private Integer userId;
	
	
	///////////////////////////////////////////////////
	

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
	
	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	
}
