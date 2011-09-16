package com.fantasy.football.auctionpro.dao;

import javax.persistence.Query;

import com.fantasy.football.auctionpro.entity.Configuration;

/**
 * Configuration DAO
 * 
 * @author Derek
 * 
 */
public class ConfigurationDao extends BaseJpaDao<Configuration,Long> {

	/**
	 * Constructor
	 * 
	 * @param pType
	 */
	public ConfigurationDao() {
		super(Configuration.class);
	}
	
	/**
	 * Count
	 * 
	 * @return
	 */
	public long count() {
		Query q = getEntityManager().createNamedQuery("Configuration.count");
		return (Long) q.getSingleResult();
	}
	
	/**
	 * Get Players By Position
	 * 
	 * @param position
	 * @return List
	 */
	public Configuration getConfiguration() {
		Query query = getEntityManager().createNamedQuery("Configuration.find");
		return (Configuration) query.getSingleResult();
	} 
	
}
