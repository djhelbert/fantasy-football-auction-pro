package com.fantasy.football.auctionpro.ui.model;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;

import com.fantasy.football.auctionpro.MainApp;
import com.fantasy.football.auctionpro.DaoFactory;
import com.fantasy.football.auctionpro.dao.PlayerDao;
import com.fantasy.football.auctionpro.entity.Player;
import com.fantasy.football.auctionpro.ui.FootballFrame;

/**
 * Roster Table Model
 * 
 * @author Derek
 *
 */
public class RosterTableModel extends AbstractTableModel {

	/**
	 * Serial Version UID
	 */
	private static final long serialVersionUID = -4455798567894917108L;
	
	/** Headers */
	private static final String[] headers = {"Rank","Player Name","Team","Position","Price","Pick Number","Bye Week"};
	
	/** Player DAO */
	protected PlayerDao playerDao = DaoFactory.getPlayerDao();
	
	/** Players */
	private List<Player> players = new ArrayList<Player>();
	
	/**
	 * Constructor
	 * 
	 */
	public RosterTableModel() {
	}

	/**
	 * Clear players
	 * 
	 * @param players
	 */
	public void clearPlayers() {
		players = new ArrayList<Player>();
		fireTableDataChanged();
	}
	
	/**
	 * Set players
	 * 
	 * @param players
	 */
	public void setPlayers(List<Player> players) {
		this.players = players;
		fireTableDataChanged();
	}
	
	@Override
	public String getColumnName(int col) {
		return headers[col];
	}
	
	/**
	 * Get Row Count
	 * 
	 * @return int
	 */
	public int getRowCount() {
		return players.size();
	}

	/**
	 * Get Column Count
	 * 
	 * @return int
	 */
	public int getColumnCount() {
		return headers.length;
	}

	/**
	 * Get Player
	 * @param row
	 * @return Player
	 */
	public Player getPlayer(int row) {
		Player p = players.get(row);
		return p;
	}

	/**
	 * Remove Player
	 * 
	 * @param row
	 */
	public void removePlayer(int row) {
		players.remove(row);
		fireTableDataChanged();
	}
	
	/**
	 * Get Value At
	 * 
	 * @param row
	 * @param col
	 * 
	 * @return Object
	 */
	public Object getValueAt(int row, int col) {
		Player p = players.get(row);
		
		if(col == 0) {
			return p.getRank();
		}
		else if(col == 1) {
			return p.getName();
		}
		else if(col == 2) {
			return p.getNflTeam();
		}
		else if(col == 3) {
			return p.getPosition();
		}
		else if(col == 4) {
			return p.getPrice();
		}
		else if(col == 5) {
			return p.getPickNumber();
		}
		else {
			return p.getByeWeek();
		}
	}

	@Override
	public void setValueAt(Object value, int row, int col) {
		Player p = players.get(row);
		
		if(col == 4) {
			p.setPrice((Integer)value);
		}
		else if(col == 5) {
			p.setPickNumber((Integer)value);
		}
		
		// Update player
		playerDao.update(p);
		
		// Refresh team player panel
		FootballFrame.getTeamPanel().refreshTeamPlayerPanel(p.getTeam());
		
		// Fire cell update
		fireTableCellUpdated(row, col);
		
		JOptionPane.showMessageDialog(MainApp.getMainComponent(),"Player has been updated.","Information",JOptionPane.INFORMATION_MESSAGE);
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public Class getColumnClass(int col) {
		if (col > 3) {
			return Integer.class;
        }
		else {
			return String.class;
		}
    }
	
	@Override
	public boolean isCellEditable(int row, int col) {
        if (col >= 3) {
            return true;
        } else {
            return false;
        }
    }
}
