package com.jmzombe.nhl.model;

import javax.validation.Valid;

import org.springframework.data.mongodb.core.mapping.Document;

/**
 * This is the model object that represents a Player.
 */
//The @Document annotation identifies a domain object that is going to be persisted to MongoDB
@Document
public class Player extends Entity {
	// Spring MVC will validate a @Valid object after binding so-long as an 
    // Validator has been configured. Note:The @Valid annotation is part of 
    // standard JSR-303 Bean Validation API, and is not a Spring-specific construct.
    @Valid
    private Team team;
    @Valid
    private String position;
    @Valid
    private int jerseyNumber;
     

    public Player() {
    }

    public Player(String name, Team team, String position, int jerseyNumber) {
        this.name = name;
        this.team = team;
        this.position = position;
        this.jerseyNumber = jerseyNumber;
    }
    
	public Team getTeam() {
		return team;
	}

	public void setTeam(Team team) {
		this.team = team;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public int getJerseyNumber() {
		return jerseyNumber;
	}

	public void setJerseyNumber(int jerseyNumber) {
		this.jerseyNumber = jerseyNumber;
	}
    
}