package com.jmzombe.nhl.repository;

import com.jmzombe.nhl.model.Player;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.limit;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.match;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.newAggregation;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.skip;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.sort;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.LimitOperation;
import org.springframework.data.mongodb.core.aggregation.MatchOperation;
import org.springframework.data.mongodb.core.aggregation.SkipOperation;
import org.springframework.data.mongodb.core.aggregation.SortOperation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

/**
 * This is the Repository interface for the Player objects.
 */
@Repository
public abstract class PlayerRepository implements PlayerRepositoryInterface {
	final private MongoTemplate mongoTemplate;
   	
	private static Aggregation getAggregation(String propertyName, String propertyValue, Pageable pageable) {
        MatchOperation matchOperation = match(Criteria.where(propertyName).is(propertyValue));
        SkipOperation skipOperation = skip((long) (pageable.getPageNumber() * pageable.getPageSize()));
        LimitOperation limitOperation = limit(pageable.getPageSize());
        SortOperation sortOperation = sort(pageable.getSort());

        return newAggregation(matchOperation, skipOperation, limitOperation, sortOperation);
    }
	
	/**
     * Player constructor.
	 */
	public PlayerRepository(@Autowired MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }
	
	/**
     * Get all Players.
     */
	@Override
    public Page<Player> getAllPlayers(Pageable pageable) {
    	long total = getCount("position.player.name", "player");
        Aggregation aggregation = getAggregation("position.team.name", "player", pageable);
        List<Player> aggregationResults = mongoTemplate.aggregate(aggregation, Player.class, Player.class).getMappedResults();
        return new PageImpl<>(aggregationResults, pageable, total);
    }
    
    /**
     * Get Players by name.
     */
	@Override
	public Page<Player>getPlayersByName(String name, Pageable pageable) {
    	long total = getCount("position.player.name", name);
        Aggregation aggregation = getAggregation("position.team.name", name, pageable);
        List<Player> aggregationResults = mongoTemplate.aggregate(aggregation, Player.class, Player.class).getMappedResults();
        return new PageImpl<>(aggregationResults, pageable, total);
    }
    
    /**
     * Get Players by team.
     */
	@Override
    public Page<Player>getPlayersByTeam(String team, Pageable pageable) {
    	long total = getCount("position.team.name", team);
        Aggregation aggregation = getAggregation("position.team.name", team, pageable);
        List<Player> aggregationResults = mongoTemplate.aggregate(aggregation, Player.class, Player.class).getMappedResults();
        return new PageImpl<>(aggregationResults, pageable, total);
    }
      
    /**
     * Get all Players by position.
     */
    @Override
    public Page<Player> getPlayersByPosition(String position, Pageable pageable) {
    	long total = getCount("position.name", position);
        Aggregation aggregation = getAggregation("position.name", position, pageable);
        List<Player> aggregationResults = mongoTemplate.aggregate(aggregation, Player.class, Player.class).getMappedResults();
        return new PageImpl<>(aggregationResults, pageable, total);
    }
	
    /**
     * Get Players by conference.
     */
    @Override
    public Page<Player> getPlayersByConference(String conference, Pageable pageable) {
    	long total = getCount("position.team.conference", conference);
        Aggregation aggregation = getAggregation("position.team.conference", conference, pageable);
        List<Player> aggregationResults = mongoTemplate.aggregate(aggregation, Player.class, Player.class).getMappedResults();
        return new PageImpl<>(aggregationResults, pageable, total);
    }
	
    /**
     * Get Players by division.
     */
    @Override
    public Page<Player> getPlayersByDivision(String division, Pageable pageable) {
    	long total = getCount("position.team.division", division);
        Aggregation aggregation = getAggregation("position.team.division", division, pageable);
        List<Player> aggregationResults = mongoTemplate.aggregate(aggregation, Player.class, Player.class).getMappedResults();
        return new PageImpl<>(aggregationResults, pageable, total);
    }
    
    private long getCount(String propertyName, String propertyValue) {
        Query countQuery = new Query(Criteria.where(propertyName).is(propertyValue));
        return mongoTemplate.count(countQuery, Player.class);
    }
		
}