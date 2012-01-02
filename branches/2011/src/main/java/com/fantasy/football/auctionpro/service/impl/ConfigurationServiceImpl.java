package com.fantasy.football.auctionpro.service.impl;

import java.util.HashMap;
import java.util.Map;

import com.fantasy.football.auctionpro.DaoFactory;
import com.fantasy.football.auctionpro.dao.ConfigurationDao;
import com.fantasy.football.auctionpro.entity.Configuration;
import com.fantasy.football.auctionpro.entity.ScoreSystem;
import com.fantasy.football.auctionpro.service.ConfigurationService;

/**
 * Configuration Service Implementation
 * 
 * @author dhelbert
 *
 */
public class ConfigurationServiceImpl implements ConfigurationService {
	
	private static final Object[][] ENTRIES =
	{
	  {"MIA",new Integer(5)},
	  {"WAS",new Integer(5)},
	  {"BAL",new Integer(5)},
	  {"CLE",new Integer(5)},
	  {"DAL",new Integer(5)},
	  {"STL",new Integer(5)},
	  {"ARI",new Integer(6)},
	  {"DEN",new Integer(6)},
	  {"SEA",new Integer(6)},
	  {"SDG",new Integer(6)},
	  {"KAN",new Integer(6)},
	  {"TEN",new Integer(6)},
	  {"PHI",new Integer(7)},
	  {"CIN",new Integer(7)},
	  {"BUF",new Integer(7)},
	  {"SFO",new Integer(7)},
	  {"NYG",new Integer(7)},
	  {"NWE",new Integer(7)},
	  {"TAM",new Integer(8)},
	  {"NYJ",new Integer(8)},
	  {"ATL",new Integer(8)},
	  {"OAK",new Integer(8)},
	  {"GNB",new Integer(8)},
	  {"CHI",new Integer(8)},
	  {"CAR",new Integer(9)},
	  {"MIN",new Integer(9)},
	  {"DET",new Integer(9)},
	  {"JAX",new Integer(9)},
	  {"PIT",new Integer(11)},
	  {"IND",new Integer(11)},
	  {"NOR",new Integer(11)},
	  {"HOU",new Integer(11)},
	};
	
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
		byeWeekMap = new HashMap<String,Integer>();
		
		for (int x = 0; x < ENTRIES.length; x++) {
			Object[] entry = ENTRIES[x];
			byeWeekMap.put((String)entry[0], (Integer)entry[1]);
		}
	}
	
	public long count() {
		return configurationDao.count();
	}
	
	public Map<String,Integer> getByeWeekMap() {
		return byeWeekMap;
	}
	
	public Configuration getConfiguration() {
		if( configuration == null ) {
			configuration = configurationDao.getConfiguration();
		}
		
		if(configuration.getScoreSystem() == null) {
			configuration.setScoreSystem(new ScoreSystem());
		}
		
		return configuration;
	}

	public void updateConfiguration(Configuration configuration) {
		// Update
		configurationDao.update(configuration);
		
		// Set
		this.configuration = configuration;
	}
	
}
