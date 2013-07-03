package com.fantasy.football.auctionpro.dao;

import com.fantasy.football.auctionpro.entity.PlayerData;

/**
 * Player Data DAO
 * 
 * @author Derek
 * 
 */
public class PlayerDataDao extends BaseJpaDao<PlayerData,Long> {

	/**
	 * Constructor
	 */
	public PlayerDataDao() {
		super(PlayerData.class);
	}
	
}
