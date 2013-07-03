package com.fantasy.football.auctionpro.ui.model;

import com.fantasy.football.auctionpro.Constants;
import com.fantasy.football.auctionpro.entity.Player;

/**
 * Tight End Table Model
 * 
 * @author dhelbert
 *
 */
@SuppressWarnings("unchecked")
public class TightEndTableModel extends PlayerTableModel {

	/** Serial Version UID */
	private static final long serialVersionUID = 621561030639590680L;

	/**
	 * Constructor
	 * 
	 */
	public TightEndTableModel() {
		super(Constants.TE);
	}
	
	/**
	 * Get Column Count
	 * 
	 * @return int
	 */
	public int getColumnCount() {
		return 9;
	}
	
	/**
	 * Get Column Name
	 * 
	 * @return String
	 */
	public String getColumnName(int col) {
		if(col == 0) {
			return "Favorite";
		}
		else if(col == 1) {
			return "Rank";
		}
		else if(col == 2) {
			return "Player Name";
		}
		else if(col == 3) {
			return "Team";
		}
		else if(col == 4) {
			return "Bye";
		}
		else if(col == 5) {
			return "Receptions";
		}
		else if(col == 6) {
			return "Yards";
		}
		else if(col == 7) {
			return "Touchdowns";
		}
		else {
			return "Fumbles";
		}
	}

	/**
	 * Get Value
	 * 
	 * @param row
	 * @param col
	 * 
	 * @return Object
	 */
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
			return p.getPlayerData().getReception();
		}
		else if(col == 6) {
			return p.getPlayerData().getReceivingYards();
		}
		else if(col == 7) {
			return p.getPlayerData().getReceptionTd();
		}
		else {
			return p.getPlayerData().getFumbleLost();
		}
	}
}
