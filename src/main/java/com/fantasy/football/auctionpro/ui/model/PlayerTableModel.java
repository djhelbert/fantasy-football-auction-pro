package com.fantasy.football.auctionpro.ui.model;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import com.fantasy.football.auctionpro.DaoFactory;
import com.fantasy.football.auctionpro.dao.PlayerDao;
import com.fantasy.football.auctionpro.entity.Player;

/**
 * Player Table Model
 * 
 * @author Derek
 *
 */
@SuppressWarnings("serial")
public abstract class PlayerTableModel extends AbstractTableModel {
	
	/** Player DAO */
	protected PlayerDao playerDao = DaoFactory.getPlayerDao();
	
	/** Player List */
	protected List<Player> players = new ArrayList<Player>();
	
	/** Position */
	protected String position;
	
	/**
	 * Constructor
	 * 
	 * @param position
	 */
	public PlayerTableModel(String position) {
		this.position = position;
	}
	
	/**
	 * Refresh Data
	 */
	public void refresh() {
		players = playerDao.getPlayersByPosition(position);
		fireTableDataChanged();
	}

	/**
	 * Get Player at Row
	 * 
	 * @param row
	 * 
	 * @return Player
	 */
	public Player getPlayer(int row) {
		Player p = players.get(row);
		return p;
	}
	
	/**
	 * Get Player Id at Row
	 * 
	 * @param row
	 * 
	 * @return Integer
	 */
	public Long getPlayerId(int row) {
		Player p = players.get(row);
		return p.getId();
	}
	
	/**
	 * Get Player Row
	 * @param p
	 * @return int
	 */
	public int getPlayerRow(Player p) {
		for(int i=0; i<players.size(); i++ ) {
			if( p.getId().equals(players.get(i).getId()) ) {
				return i;
			}
		}
		
		return -1;
	}
	
	/**
	 * Get Count
	 * 
	 * @return int
	 */
	public int getRowCount() {
		return players.size();
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public Class getColumnClass(int col) {
		if (col == 0) {
			return Boolean.class;
        }
		else {
			return String.class;
		}
    }
	
	@Override
	public void setValueAt(Object value, int row, int col) {
		Player p = players.get(row);
		
		p.setFavorite((Boolean)value);
		
		playerDao.update(p);
		
		fireTableCellUpdated(row, col);
	}
	
	@Override
	public boolean isCellEditable(int row, int col) {
        if (col < 1) {
            return true;
        } else {
            return false;
        }
    }
	
}
