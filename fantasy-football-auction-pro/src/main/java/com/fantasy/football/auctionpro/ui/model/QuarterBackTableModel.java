package com.fantasy.football.auctionpro.ui.model;

import com.fantasy.football.auctionpro.Constants;
import com.fantasy.football.auctionpro.entity.Player;

/**
 * QB Table Model
 * 
 * @author dhelbert
 * 
 */
@SuppressWarnings("unchecked")
public class QuarterBackTableModel extends PlayerTableModel {

	/** Serial Version UID */
	private static final long serialVersionUID = 6173217310603452823L;

	/** Headers */
	private static String[] headers = {"Favorite","Rank","Player Name","Team","Bye","Passing TD","Int","Passing Yards","Rushing TD","Rushing Yards"};
	
	/**
	 * Constructor
	 */
	public QuarterBackTableModel() {
		super(Constants.QB);
	}
	
	/**
	 * Get Count
	 * 
	 * @return int
	 */
	public int getColumnCount() {
		return headers.length;
	}
	
	@Override
	public String getColumnName(int col) {
		return headers[col];
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
			return p.getPlayerData().getPassingTd();
		}
		else if(col == 6) {
			return p.getPlayerData().getPassingInt();
		}
		else if(col == 7) {
			return p.getPlayerData().getPassingYards();
		}
		else if(col == 8) {
			return p.getPlayerData().getRushingTd();
		}
		else {
			return p.getPlayerData().getRushingYards();
		}
	}

}
