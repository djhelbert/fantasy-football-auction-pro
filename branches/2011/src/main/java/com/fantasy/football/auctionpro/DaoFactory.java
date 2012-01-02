package com.fantasy.football.auctionpro;

import com.fantasy.football.auctionpro.dao.ConfigurationDao;
import com.fantasy.football.auctionpro.dao.DefenseWeekDao;
import com.fantasy.football.auctionpro.dao.PlayerDao;
import com.fantasy.football.auctionpro.dao.PlayerDataDao;
import com.fantasy.football.auctionpro.dao.TeamDao;

/**
 * DAO Factory
 * 
 * @author Derek
 *
 */
public class DaoFactory {

	/** Configuration Dao */
	private static ConfigurationDao configurationDao;
	
	/** Player Dao*/
	private static PlayerDao playerDao;
	
	/** Player Data Dao */
	private static PlayerDataDao playerDataDao;
	
	/** Team Dao*/
	private static TeamDao teamDao;

	/** Defense Week Dao */
	private static DefenseWeekDao defenseWeekDao;
	
	/**
	 * Get Configuration DAO
	 * 
	 * @return ConfigurationDao
	 */
	public static ConfigurationDao getConfigurationDao() {
		if( configurationDao == null ) {
			configurationDao = new ConfigurationDao();
		}
		
		return configurationDao;
	}

	/**
	 * Get Player DAO
	 * 
	 * @return PlayerDao
	 */
	public static PlayerDao getPlayerDao() {
		if( playerDao == null ) {
			playerDao = new PlayerDao();
		}
		
		return playerDao;
	}

	/**
	 * Get Player Data DAO
	 * 
	 * @return PlayerDataDao
	 */
	public static PlayerDataDao getPlayerDataDao() {
		if( playerDataDao == null ) {
			playerDataDao = new PlayerDataDao();
		}
		
		return playerDataDao;
	}

	/**
	 * Get Team DAO
	 * 
	 * @return TeamDao
	 */
	public static TeamDao getTeamDao() {
		if( teamDao == null ) {
			teamDao = new TeamDao();
		}
		
		return teamDao;
	}

	/**
	 * Get Defense Week DAO
	 * 
	 * @return DefenseWeekDao
	 */
	public static DefenseWeekDao getDefenseWeekDao() {
		if( defenseWeekDao == null ) {
			defenseWeekDao = new DefenseWeekDao();
		}
		
		return defenseWeekDao;
	}
}
