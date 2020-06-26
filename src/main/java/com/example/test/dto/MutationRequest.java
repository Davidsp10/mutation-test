package com.example.test.dto;

import java.io.Serializable;
import java.util.List;

import com.example.test.domain.NgBase;

import lombok.Data;;

@SuppressWarnings("serial")
@Data
public class MutationRequest implements Serializable {
	
	private List<NgBase> adn;

	public List<NgBase> getAdn() {
		return adn;
	}

	public void setAdn(List<NgBase> adn) {
		this.adn = adn;
	}
	
}
