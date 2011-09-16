package com.fantasy.football.auctionpro.service;

import java.util.List;
import java.util.Map;

import com.fantasy.football.auctionpro.entity.Configuration;
import com.fantasy.football.auctionpro.entity.Player;

/**
 * Startup Service
 * 
 * @author Derek
 *
 */
public interface StartupService {

	/**
	 * Init Players
	 * 
	 * @param players
	 * @param byeWeekMap
	 */
	public void initPlayers(List<Player> players, Map<String,Integer> byeWeekMap);
	
	/**
	 * Init Teams
	 * 
	 * @param size
	 */
	public void initTeams(int size);
	
	/**
	 * Init Configuration
	 * 
	 * @return configuration
	 */
	public Configuration initConfiguration();
	
	/**
	 * Is Started
	 * 
	 * @return boolean
	 */
	public boolean isStarted();
	
}
