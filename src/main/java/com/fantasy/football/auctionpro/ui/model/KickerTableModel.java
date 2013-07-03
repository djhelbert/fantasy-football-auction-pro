package com.fantasy.football.auctionpro.ui.model;

import com.fantasy.football.auctionpro.Constants;
import com.fantasy.football.auctionpro.entity.Player;


/**
 * Kicker Table Model
 * 
 * @author dhelbert
 * 
 */
@SuppressWarnings("unchecked")
public class KickerTableModel extends PlayerTableModel {

	/** Serial Version UID */
	private static final long serialVersionUID = 6173217310603452823L;

	/** Headers */
	private static final String headers[] = {"Favorite","Rank","Player Name","Team","Bye","Extra Points","FGM 0-19","FGM 20-29","FGM 30-39","FGM 40-49","FGM 50+"};
	
	/**
	 * Constructor
	 */
	public KickerTableModel() {
		super(Constants.K);
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
	 * Get Column Name
	 * 
	 * @return String
	 */
	public String getColumnName(int col) {
		return headers[col];
	}

	/**
	 * Get Value At
	 * 
	 * @param row
	 * @param col
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
			return p.getPlayerData().getExtraPoint();
		}
		else if(col == 6) {
			return p.getPlayerData().getFieldGoalZeroNineteen();
		}
		else if(col == 7) {
			return p.getPlayerData().getFieldGoalTwentyTwentyNine();
		}
		else if(col == 8) {
			return p.getPlayerData().getFieldGoalThirtyThirtyNine();
		}
		else if(col == 9) {
			return p.getPlayerData().getFieldGoalFortyFortyNine();
		}
		else {
			return p.getPlayerData().getFieldGoalFifty();
		}
	}

}
