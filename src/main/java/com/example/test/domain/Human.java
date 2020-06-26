package com.example.test.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Entity
@Data
@Table(name = "humans")
@SuppressWarnings("serial")

public class Human implements Serializable{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false, unique = true)
	private Integer id;
	
	@Column(name = "name", nullable = false, unique = true)
	private String name;

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "human_id")
	private List<NgBase> adn;
	
	@Column(name = "mutant", nullable = false)
	private Boolean mutantStatus;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "creation_date", nullable = false, updatable = false)
	private Date creationDate;
	
	@JsonIgnoreProperties(value = { "humans", "hibernateLazyInitializer", "handler"}, allowSetters = true)
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "user_id")
	private User user;
	
	///////////////////////////////////////////////////////////////////
	
//	public void addBase(NgBase tempBase) {
//		if(adn == null) {
//			adn = new ArrayList<>();
//		}
//		
//		adn.add(tempBase);
//	}
	
//	public Human() {
//		adn = new ArrayList<>();
//	}

	//////////////////////////////////////////////////////////////////
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
}
