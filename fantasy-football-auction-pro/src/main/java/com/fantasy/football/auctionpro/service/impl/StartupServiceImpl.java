package com.fantasy.football.auctionpro.service.impl;

import java.util.List;
import java.util.Map;

import com.fantasy.football.auctionpro.DaoFactory;
import com.fantasy.football.auctionpro.dao.ConfigurationDao;
import com.fantasy.football.auctionpro.dao.PlayerDao;
import com.fantasy.football.auctionpro.dao.TeamDao;
import com.fantasy.football.auctionpro.entity.Configuration;
import com.fantasy.football.auctionpro.entity.Player;
import com.fantasy.football.auctionpro.entity.ScoreSystem;
import com.fantasy.football.auctionpro.entity.Team;
import com.fantasy.football.auctionpro.service.StartupService;

/**
 * Startup Service Implementation
 * 
 * @author dhelbert
 * 
 */
public class StartupServiceImpl implements StartupService {

	/** Team DAO */
	private TeamDao teamDao = DaoFactory.getTeamDao();

	/** Player DAO */
	private PlayerDao playerDao = DaoFactory.getPlayerDao();

	/** Configuration DAO */
	private ConfigurationDao configurationDao = DaoFactory.getConfigurationDao();
	
	/**
	 * Init Players
	 * 
	 * @param players
	 * @param byeWeekMap
	 */
	public void initPlayers(List<Player> players, Map<String,Integer> byeWeekMap) {
		Integer wk = null;
		
		for (Player p : players) {
			wk = byeWeekMap.get(p.getNflTeam());
			
			if(wk == null) {
				wk = 0;
			}
			
			p.setByeWeek(wk);
			playerDao.create(p);
		}
	}

	/**
	 * Init Teams
	 * 
	 * @param size
	 */
	public void initTeams(int size) {
		Team team = null;
		
		for (int i=0; i<size; i++) {
			team = new Team("Name " + (i+1),"Owner",100);
			
			// Set one favorite team
			if(i == 0) {
				team.setFavorite(Boolean.TRUE);
			}
			
			teamDao.create(team);
		}
	}

	/**
	 * Initialize League Configuration - Only Happens Once
	 * 
	 * @return configuration
	 */
	public Configuration initConfiguration() {
		Configuration configuration = new Configuration();
		configuration.setScoreSystem(new ScoreSystem());
		
		configurationDao.create(configuration);
		return configuration;
	}
	
	/**
	 * Is Started
	 * 
	 * @return boolean
	 */
	public boolean isStarted() {
		if( configurationDao.count() > 0 ) {
			return true;
		}
		else {
			return false;
		}
	}
	
}
