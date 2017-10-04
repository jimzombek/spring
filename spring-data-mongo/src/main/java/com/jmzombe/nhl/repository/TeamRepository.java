package com.jmzombe.nhl.repository;

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

import com.jmzombe.nhl.model.Player;
import com.jmzombe.nhl.model.Team;

/**
 * This is the Repository interface for the Team objects.
 */
@Repository
public abstract class TeamRepository implements TeamRepositoryInterface {
	final private MongoTemplate mongoTemplate;
	
	private static Aggregation getAggregation(String propertyName, String propertyValue, Pageable pageable) {
        MatchOperation matchOperation = match(Criteria.where(propertyName).is(propertyValue));
        SkipOperation skipOperation = skip((long) (pageable.getPageNumber() * pageable.getPageSize()));
        LimitOperation limitOperation = limit(pageable.getPageSize());
        SortOperation sortOperation = sort(pageable.getSort());

        return newAggregation(matchOperation, skipOperation, limitOperation, sortOperation);
    }
	
	/**
     * Team constructor.
	 */
	public TeamRepository(@Autowired MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }
	
	/**
     * Get all Teams.
     */
	@Override
	public Page<Team> getAllTeams(Pageable pageable) {
		long total = getCount("position.player.name", "player");
        Aggregation aggregation = getAggregation("position.team.name", "player", pageable);
        List<Team> aggregationResults = mongoTemplate.aggregate(aggregation, Team.class, Team.class).getMappedResults();
        return new PageImpl<>(aggregationResults, pageable, total);
	}
	
	/**
     * Get Team by name.
     */
	@Override
	public Team getTeamByName(String name) {
		return null;
	}
	
	/**
     * Get Teams by division.
     */
	@Override
	public Page<Team> getTeamsByDivision(String division, Pageable pageable) {
		return null;
	}
	
	/**
     * Get Teams by conference.
     */
	@Override
	public Page<Team> getTeamsByConference(String conference, Pageable pageable) {
		return null;
	}
	
	private long getCount(String propertyName, String propertyValue) {
        Query countQuery = new Query(Criteria.where(propertyName).is(propertyValue));
        return mongoTemplate.count(countQuery, Player.class);
    }
    
}

