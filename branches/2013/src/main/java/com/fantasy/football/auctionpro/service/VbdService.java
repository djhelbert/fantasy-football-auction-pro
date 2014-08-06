package com.fantasy.football.auctionpro.service;

import com.fantasy.football.auctionpro.entity.Configuration;

/**
 * VBD Service
 * 
 * @author Derek
 *
 */
public interface VbdService {

	/**
	 * Update Player VBD
	 * 
	 * @param conf
	 * @param position
	 */
	public int updatePlayerVbd(Configuration conf,String position);
	
	/**
	 * Update All Player VBD
	 * 
	 * @param conf
	 */
	public void updateAllPlayerVbd(Configuration conf);
	
	/**
	 * Update All Player Fantasy Points
	 * 
	 * @param configuration
	 */
	public void updatePlayerFantasyPoints(Configuration configuration);
	
}
