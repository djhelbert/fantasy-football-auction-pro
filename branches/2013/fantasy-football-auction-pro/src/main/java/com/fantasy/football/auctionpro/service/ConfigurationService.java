package com.fantasy.football.auctionpro.service;

import java.util.Map;

import com.fantasy.football.auctionpro.entity.Configuration;

/**
 * Configuration Service
 * 
 * @author Derek
 *
 */
public interface ConfigurationService {

	/**
	 * Get Bye Week Map
	 * 
	 * @return Map<String,Integer>
	 */
	public Map<String,Integer> getByeWeekMap();
	
	/**
	 * Count
	 * 
	 * @return long
	 */
	public long count();
	
	/**
	 * Get Configuration
	 * 
	 * @return Configuration
	 */
	public Configuration getConfiguration();
	
	/**
	 * Update Configuration
	 * 
	 * @param configuration
	 */
	public void updateConfiguration(Configuration configuration);
}
