package com.fantasy.football.auctionpro.service.impl;

import java.util.List;

import com.fantasy.football.auctionpro.DaoFactory;
import com.fantasy.football.auctionpro.dao.PlayerDao;
import com.fantasy.football.auctionpro.dao.TeamDao;
import com.fantasy.football.auctionpro.entity.Player;
import com.fantasy.football.auctionpro.entity.Team;
import com.fantasy.football.auctionpro.service.TeamService;

/**
 * Team Service Implementation
 * 
 * @author dhelbert
 *
 */
public class TeamServiceImpl implements TeamService {

	/** Team DAO */
	private TeamDao teamDao = DaoFactory.getTeamDao();
	
	/** Player DAO */
	private PlayerDao playerDao = DaoFactory.getPlayerDao();

	public Player addTeamPlayer(Team team, Player player, Integer amount, int picknum) {		
		player.setPrice(amount);
		player.setTeam(team);
		player.setPickNumber(picknum);
		
		team.getTeamPlayers().add(player);
		
		playerDao.update(player);
		return player;
	}

	public Player removeTeamPlayer(Player player) {		
		player.getTeam().getTeamPlayers().remove(player);
		player.setPrice(0);
		player.setTeam(null);
		playerDao.update(player);
		return player;
	}

	public Team getFavoriteTeam() {
		return teamDao.getFavoriteTeam();
	}
	
	public List<Team> getAllTeams() {
		return teamDao.getAllTeams();
	}

	public void clearLeague() {
		List<Team> teams = getAllTeams();
		
		for(Team t : teams) {
			removeAllTeamPlayers(t);
		}
		
		for(Team t : teams) {
			deleteTeam(t);
		}
	}
	
	public void removeAllTeamPlayers(Team team) {
		Team t = teamDao.read(team.getId());
		
		for(Player p : t.getTeamPlayers()) {
			p.setTeam(null);
			p.setPrice(0);
			p.setPickNumber(0);
			playerDao.update(p);
		}
		
		team.getTeamPlayers().clear();
	}

	public Team getTeam(Long id) {
		return teamDao.read(id);
	}

	public void createTeam(Team team) {
		teamDao.create(team);
	}

	public Team updateTeam(Team team) {
		return teamDao.update(team);
	}

	public void deleteTeam(Team team) {
		teamDao.delete(team);
	}
}
