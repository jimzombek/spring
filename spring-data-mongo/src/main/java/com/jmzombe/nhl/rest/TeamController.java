package com.jmzombe.nhl.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import com.jmzombe.nhl.model.Team;
import com.jmzombe.nhl.repository.TeamRepository;
import com.jmzombe.nhl.validator.TeamValidator;

import javax.validation.Valid;


/**
 * This class serves as the {@link RestController} for serving up Team resources.
 */
@RestController
public class TeamController {
    private TeamRepository teamRepository;

    public TeamController(@Autowired TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }

    /**
     * Get all Teams in the Team repository.
     *
     * @return all Teams in the Team repository
     */
    @RequestMapping(value = "/teams", method = RequestMethod.GET)
    public Page<Team> getAllTeams(Pageable pageable) {
        return teamRepository.getAllTeams(pageable);
    }
    
    /**
     * Get the Team with the given name.
     *
     * @param name The name of the Team to find
     * @return the Team with the given name
     */
    @RequestMapping(value = "/teams/name", method = RequestMethod.GET)
    public Team getTeamByName(@RequestParam(value = "name") String name) {
        return teamRepository.getTeamByName(name);  
    }

    /**
     * Get the Team with the given key.
     *
     * @param key the key of the Team to find
     * @return the Team with the given key
     */
    //@RequestMapping(value = "/teams/byKey", method = RequestMethod.GET)
    //public Team getByKey(@RequestParam(value = "key") String key) {
    //    return teamRepository.findByKey(key);
    //}

    /**
     * Get all of the Teams that play in the given division.
     *
     * @param division the division to find all participating Teams
     * @return all Teams that play in the specified division
     */
    @RequestMapping(value = "/teams/division", method = RequestMethod.GET)
    public Page<Team> getTeamsByDivsion(@RequestParam(value = "division") String division, Pageable pageable) {
        return teamRepository.getTeamsByDivision(division, pageable);
    }
    
    /**
     * Get all of the Teams that play in the given conference.
     *
     * @param conference the conference to find all participating Teams
     * @return all Teams that play in the specified conference
     */
    @RequestMapping(value = "/teams/conference", method = RequestMethod.GET)
    public Page<Team> getTeamsConference(@RequestParam(value = "conference") String conference, Pageable pageable) {
        return teamRepository.getTeamsByConference(conference, pageable);
    }

    /**
     * Inserts a new Team.
     *
     * @param team the Team to insert
     * @return the inserted Team
     */
    @RequestMapping(value = "/teams", method = RequestMethod.POST)
    public Team createTeam(@Valid @RequestBody Team team) {
        return teamRepository.insert(team);
    }

    /**
     * Updates an existing Team.
     *
     * @param team the Team to update
     * @return the updated Team
     */
    @RequestMapping(value = "/teams", method = RequestMethod.PUT)
    public Team updateTeam(@Valid @RequestBody Team team) {
        return teamRepository.save(team);
    }

    /**
     * Deletes a Team.
     *
     * @param team the Team to delete
     */
    @RequestMapping(value = "/teams", method = RequestMethod.DELETE)
    public void deleteTeam(@Valid @RequestBody Team team) {
        teamRepository.delete(team);
    }

    /**
     * Adds validation for doing CRUD operations on Teams.
     *
     * @param binder the Binder to add validators to
     */
    @InitBinder("team")
    protected void initBinder(WebDataBinder binder) {
        binder.addValidators(new TeamValidator());
    }
}