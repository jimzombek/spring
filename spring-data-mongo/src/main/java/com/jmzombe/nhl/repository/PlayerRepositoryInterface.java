package com.jmzombe.nhl.repository;

import com.jmzombe.nhl.model.Player;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

/**
 * This is the Repository interface for the Player objects.
 */
@Repository
public interface PlayerRepositoryInterface extends PagingAndSortingRepository<Player, String>, MongoRepository<Player, String> {
    public Page<Player> getAllPlayers(Pageable pageable);
    public Page<Player> getPlayersByName(String name, Pageable pageable);
	public Page<Player> getPlayersByTeam(String team, Pageable pageable);
	public Page<Player> getPlayersByPosition(String position, Pageable pageable);
	public Page<Player> getPlayersByConference(String conference, Pageable pageable);
	public Page<Player> getPlayersByDivision(String division, Pageable pageable);
}

//   /players
//   /players/name
//   /players/team 
//   /players/position
//   /players/conference
//   /players/division
//   /players -insert
//   /players -update
//   /players -delete