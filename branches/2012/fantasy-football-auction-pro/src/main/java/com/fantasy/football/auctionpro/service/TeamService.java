package com.fantasy.football.auctionpro.service;

import java.util.List;

import com.fantasy.football.auctionpro.entity.Player;
import com.fantasy.football.auctionpro.entity.Team;

/**
 * Team Service
 * 
 * @author dhelbert
 *
 */
public interface TeamService {

	/**
	 * Add Team Player
	 * 
	 * @param team
	 * @param player
	 * @param amount
	 * @param picknum
	 */
	public Player addTeamPlayer(Team team, Player player, Integer amount, int picknum);
	
	/**
	 * Clear League - Removes all players from all teams and deletes them
	 * 
	 */
	public void clearLeague();
	
	/**
	 * Remove Team Player
	 * 
	 * @param player
	 */
	public Player removeTeamPlayer(Player player);

	/**
	 * Remove All Team Players
	 * 
	 * @param team
	 */
	public void removeAllTeamPlayers(Team team);
	
	/**
	 * Get All Teams
	 * 
	 * @return List
	 */
	public List<Team> getAllTeams();
	
	/**
	 * Get Team
	 * 
	 * @param  id
	 * @return Team
	 */
	public Team getTeam(Long id);
	
	/**
	 * Create Team
	 * 
	 * @param team
	 */
	public void createTeam(Team team);
	
	/**
	 * Update Team
	 * 
	 * @param team
	 */
	public Team updateTeam(Team team);
	
	/**
	 * Delete Team
	 * 
	 * @param team
	 */
	public void deleteTeam(Team team);
	
	/**
	 * Gwt Favorite Team
	 * 
	 * @return Team
	 */
	public Team getFavoriteTeam();
}
