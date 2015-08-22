package com.fantasy.football.auctionpro.ui;

import java.util.List;

import javax.swing.DefaultListModel;

import com.fantasy.football.auctionpro.entity.Player;

/**
 * Team List Model
 * 
 * @author dhelbert
 *
 */
public class TeamListModel extends DefaultListModel<Player> {

	/** Serial Version UID */
	private static final long serialVersionUID = -6069810961951710014L;
	
	/**
	 * Constructor
	 */
	public TeamListModel() {
		super();
	}

	/**
	 * Update Player
	 * 
	 * @param index
	 * @param player
	 */
	public void setPlayer(int index, Player player) {
		set(index,player);
	}
	
	/**
	 * Remove Player
	 */
	public void removePlayer(int index) {
		remove(index);
	}
	
	/**
	 * Set Team Players
	 * 
	 * @param players
	 */
	public void setPlayers(List<Player> players) {
		clear();
		
		for(Player p : players) {
			addElement(p);
		}
	}

}