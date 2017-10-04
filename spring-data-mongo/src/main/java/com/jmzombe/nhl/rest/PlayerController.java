package com.jmzombe.nhl.rest;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import com.jmzombe.nhl.model.Player;
import com.jmzombe.nhl.repository.PlayerRepository;
import com.jmzombe.nhl.validator.PlayerValidator;

import javax.validation.Valid;

/**
 * This class serves as the {@link RestController} for serving up Player resources and provides
 * endpoints to retrieve Players by various properties or aspects that may apply to a subset of
 * all Players in the data store.
 */
@RestController
public class PlayerController {
    private PlayerRepository playerRepository;

    public PlayerController(@Autowired PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    /**
     * Get all Players in the players repository.
     *
     * @return a Collection of all Players in the players repository
     */
    @RequestMapping(value = "/players", method = RequestMethod.GET)
    public Page<Player> getAllPlayers(Pageable pageable) {
        return playerRepository.getAllPlayers(pageable);
    }
    
    /**
     * Get Players for the specified name.
     *
     * @param name The name of the {@link Player} 
     * @return all Players associated with the specified name.
     */
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", dataType = "string", paramType = "query",
                    value = "Results page you want to retrieve (0..N)"),
            @ApiImplicitParam(name = "size", dataType = "string", paramType = "query",
                    value = "Number of records per page."),
            @ApiImplicitParam(name = "sort", allowMultiple = true, dataType = "string", paramType = "query",
                    value = "Sorting criteria in the format: property(,asc|desc). " +
                            "Default sort order is ascending. " +
                            "Multiple sort criteria are supported.")
    })
    @RequestMapping(value = "/players/name", method = RequestMethod.GET)
    public Page<Player> getPlayersByName(@RequestParam(value = "name") String name, Pageable pageable) {
        return playerRepository.getPlayersByName(name, pageable);
    }
    
    /**
     * Get all Players for the specified {@link Team}.
     *
     * @param teamName The name of the Team to retrieve all associated Players
     * @return all Players associated with the given {@link Team}.
     */
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", dataType = "string", paramType = "query",
                    value = "Results page you want to retrieve (0..N)"),
            @ApiImplicitParam(name = "size", dataType = "string", paramType = "query",
                    value = "Number of records per page."),
            @ApiImplicitParam(name = "sort", allowMultiple = true, dataType = "string", paramType = "query",
                    value = "Sorting criteria in the format: property(,asc|desc). " +
                            "Default sort order is ascending. " +
                            "Multiple sort criteria are supported.")
    })
    @RequestMapping(value = "/players/team", method = RequestMethod.GET)
    public Page<Player> getPlayersByTeam(@RequestParam(value = "team") String team, Pageable pageable) {
        return playerRepository.getPlayersByTeam(team, pageable);
    }

    /**
     * Get all Players that play the specified position.
     *
     * @param positionName The name of the position to retrieve all Players that have that position
     * @return all Players with the specified position
     */
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", dataType = "string", paramType = "query",
                    value = "Results page you want to retrieve (0..N)"),
            @ApiImplicitParam(name = "size", dataType = "string", paramType = "query",
                    value = "Number of records per page."),
            @ApiImplicitParam(name = "sort", allowMultiple = true, dataType = "string", paramType = "query",
                    value = "Sorting criteria in the format: property(,asc|desc). " +
                            "Default sort order is ascending. " +
                            "Multiple sort criteria are supported.")
    })
    @RequestMapping(value = "/players/position", method = RequestMethod.GET)
    public Page<Player> getPlayersByPosition(@RequestParam(value = "position") String position, Pageable pageable) {
        return playerRepository.getPlayersByPosition(position, pageable);
    }

    /**
     * Get all Players that play in the specified conference.
     *
     * @param conference the conference name to find all associated Players
     * @return all Players that play in the given conference
     */
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", dataType = "string", paramType = "query",
                    value = "Results page you want to retrieve (0..N)"),
            @ApiImplicitParam(name = "size", dataType = "string", paramType = "query",
                    value = "Number of records per page."),
            @ApiImplicitParam(name = "sort", allowMultiple = true, dataType = "string", paramType = "query",
                    value = "Sorting criteria in the format: property(,asc|desc). " +
                            "Default sort order is ascending. " +
                            "Multiple sort criteria are supported.")
    })
    @RequestMapping(value = "/players/conference", method = RequestMethod.GET)
    public Page<Player> getPlayersByConference(@RequestParam(value = "conference") String conference, Pageable pageable) {
        return playerRepository.getPlayersByConference(conference, pageable);
    }

    /**
     * Get all Players that play in the specified division.
     *
     * @param division the division name to find all associated Players
     * @return all Players that play in the given division
     */
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", dataType = "string", paramType = "query",
                    value = "Results page you want to retrieve (0..N)"),
            @ApiImplicitParam(name = "size", dataType = "string", paramType = "query",
                    value = "Number of records per page."),
            @ApiImplicitParam(name = "sort", allowMultiple = true, dataType = "string", paramType = "query",
                    value = "Sorting criteria in the format: property(,asc|desc). " +
                            "Default sort order is ascending. " +
                            "Multiple sort criteria are supported.")
    })
    @RequestMapping(value = "/players/division", method = RequestMethod.GET)
    public Page<Player> getPlayersByDivsion(@RequestParam(value = "division") String division, Pageable pageable) {
        return playerRepository.getPlayersByDivision(division, pageable);
    }

    /**
     * Persists a new Player.
     *
     * @param player the Player to insert
     * @return the inserted Player
     */
    @RequestMapping(value = "/players", method = RequestMethod.POST)
    public Player createPlayer(@Valid @RequestBody Player player) {
        return playerRepository.insert(player);
    }

    /**
     * Updates an existing Player.
     *
     * @param player the Player to update
     * @return the updated Player
     */
    @RequestMapping(value = "/players", method = RequestMethod.PUT)
    public Player updatePlayer(@Valid @RequestBody Player player) {
        return playerRepository.save(player);
    }

    /**
     * Deletes an Player.
     *
     * @param player the Player to delete
     */
    @RequestMapping(value = "/players", method = RequestMethod.DELETE)
    public void deletePlayer(@Valid @RequestBody Player player) {
        playerRepository.delete(player);
    }

    /**
     * Adds validation for doing CRUD operations on Players.
     *
     * @param binder the Binder to add validators to Players
     */
    @InitBinder("player")
    protected void initBinder(WebDataBinder binder) {
        binder.addValidators(new PlayerValidator());
    }
}