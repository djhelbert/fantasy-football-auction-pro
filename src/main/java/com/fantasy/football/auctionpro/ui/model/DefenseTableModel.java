package com.fantasy.football.auctionpro.ui.model;

import com.fantasy.football.auctionpro.Constants;
import com.fantasy.football.auctionpro.entity.Player;

/**
 * Owner Table Model
 * 
 * @author dhelbert
 * 
 */
@SuppressWarnings("unchecked")
public class DefenseTableModel extends PlayerTableModel {

	/** Serial Version UID */
	private static final long serialVersionUID = 6173217310603452823L;

	/** Headers */
	private static final String[] headers = {"Favorite","Rank","Name","Team","Bye","Points Allowed","Interceptions", "Fumbles","Touchdowns"};
	
	/**
	 * Constructor
	 */
	public DefenseTableModel() {
		super(Constants.DEF);
	}
	
	/**
	 * Get Count
	 * 
	 * @return int
	 */
	public int getColumnCount() {
		return headers.length;
	}
	
	/**
	 * Get Name
	 * 
	 * @param col
	 */
	public String getColumnName(int col) {
		return headers[col];
	}

	public Object getValueAt(int row, int col) {
		Player p = players.get(row);
		
		if(col == 0) {
			return p.getFavorite();
		}
		else if(col == 1) {
			return p.getRank();
		}
		else if(col == 2) {
			return p.getName();
		}
		else if(col == 3) {
			return p.getNflTeam();
		}
		else if(col == 4) {
			return p.getByeWeek();
		}
		else if(col == 5) {
			return  p.getPlayerData().getPointsAllowed();
		}
		else if(col == 6) {
			return  p.getPlayerData().getDefenseInt();
		}
		else if(col == 7) {
			return p.getPlayerData().getFumbleRecovery();
		}
		else {
			return p.getPlayerData().getDefenseTd();
		}
	}

}
