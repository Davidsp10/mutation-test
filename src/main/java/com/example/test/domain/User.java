package com.example.test.domain;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Entity
@Data
@Table(name = "users")
@SuppressWarnings("serial")

public class User implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false, unique = true)
	private Integer id;
	
	@Column(name = "nick", nullable = false, unique = true)
	private String nick;
	
	@Column(name = "password", nullable = false, unique = true)
	private String password;
	
	@JsonIgnoreProperties(value = { "user", "hibernateLazyInitializer", "handler"}, allowSetters = true)
	@OneToMany(fetch = FetchType.LAZY, mappedBy="user", cascade = CascadeType.ALL)
	private List<Human> humans;
	
	////////////////////////////////////////////////////////////////////
	
//	public void addHuman(Human tempHuman) {
//		if(humans == null) {
//			humans = new ArrayList<>();
//		}
//		
//		humans.add(tempHuman);
//	}
	
//	public User() {
//		this.humans = new ArrayList<>();
//	}
	
	////////////////////////////////////////////////////////////////////

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNick() {
		return nick;
	}

	public void setNick(String nick) {
		this.nick = nick;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<Human> getHumans() {
		return humans;
	}

	public void setHumans(List<Human> humans) {
		this.humans = humans;
	}
	
}
