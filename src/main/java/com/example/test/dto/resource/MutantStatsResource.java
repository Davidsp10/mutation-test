package com.example.test.dto.resource;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;

@SuppressWarnings("serial")
@Data
@JsonInclude(Include.NON_NULL)
public class MutantStatsResource extends GenericResource implements Serializable {

	private Integer mutants;
	
	private Integer notMutants;
	
	private Double ratio;
	
	public MutantStatsResource() {
	
	}

	public Integer getMutants() {
		return mutants;
	}

	public void setMutants(Integer mutants) {
		this.mutants = mutants;
	}

	public Integer getNotMutants() {
		return notMutants;
	}

	public void setNotMutants(Integer notMutants) {
		this.notMutants = notMutants;
	}

	public Double getRatio() {
		return ratio;
	}

	public void setRatio(Double ratio) {
		this.ratio = ratio;
	}
	
}
