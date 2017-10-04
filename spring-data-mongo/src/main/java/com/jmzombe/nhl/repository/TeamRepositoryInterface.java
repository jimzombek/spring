package com.jmzombe.nhl.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.jmzombe.nhl.model.Team;

@Repository
public interface TeamRepositoryInterface extends PagingAndSortingRepository<Team, String>, MongoRepository<Team, String> {
	public Page<Team> getAllTeams(Pageable pagable);
	public Team getTeamByName(String name);
	public Page<Team> getTeamsByDivision(String division, Pageable pageable);
	public Page<Team> getTeamsByConference(String conference, Pageable pageable);
}

//  /teams
//  /teams/name
//  /teams/division
//  /teams/conference

