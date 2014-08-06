package com.fantasy.football.auctionpro.service.impl;

import java.util.Map;

import com.fantasy.football.auctionpro.Constants;
import com.fantasy.football.auctionpro.DaoFactory;
import com.fantasy.football.auctionpro.dao.ConfigurationDao;
import com.fantasy.football.auctionpro.entity.Configuration;
import com.fantasy.football.auctionpro.entity.ScoreSystem;
import com.fantasy.football.auctionpro.reader.ByeWeekFileReader;
import com.fantasy.football.auctionpro.service.ConfigurationService;

/**
 * Configuration Service Implementation
 * 
 * @author dhelbert
 *
 */
public class ConfigurationServiceImpl implements ConfigurationService {
	
	/** Configuration DAO */
	private ConfigurationDao configurationDao = DaoFactory.getConfigurationDao();

	/** Bye Week Map */
	private static Map<String,Integer> byeWeekMap;
	
	/** Configuration */
	private Configuration configuration;
	
	/**
	 * Constructor
	 *
	 */
	public ConfigurationServiceImpl() {
		ByeWeekFileReader bfr;
		try {
			bfr = new ByeWeekFileReader("/data/" + Constants.YEAR + "/byeweek.csv");
			
			bfr.processFile();
			
			byeWeekMap = bfr.getByeWeekMap();
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public long count() {
		return configurationDao.count();
	}
	
	@Override
	public Map<String,Integer> getByeWeekMap() {
		return byeWeekMap;
	}
	
	@Override
	public Configuration getConfiguration() {
		if( configuration == null ) {
			configuration = configurationDao.getConfiguration();
		}
		
		if(configuration.getScoreSystem() == null) {
			configuration.setScoreSystem(new ScoreSystem());
		}
		
		return configuration;
	}

	@Override
	public void updateConfiguration(Configuration configuration) {
		// Update
		configurationDao.update(configuration);
		
		// Set
		this.configuration = configuration;
	}
	
}
