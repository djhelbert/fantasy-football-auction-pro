package com.fantasy.football.auctionpro.service;

import java.util.List;

import com.fantasy.football.auctionpro.entity.Player;

/**
 * File Service
 * 
 * @author Derek
 *
 */
public interface FileService {
	
	/**
	 * Get All Players
	 * 
	 * @return List<Player>
	 * 
	 * @throws Exception
	 */
	public List<Player> getAllPlayers() throws Exception;
}
