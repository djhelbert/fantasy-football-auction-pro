package com.fantasy.football.auctionpro.ui.model;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;

import com.fantasy.football.auctionpro.MainApp;
import com.fantasy.football.auctionpro.DaoFactory;
import com.fantasy.football.auctionpro.ServiceFactory;
import com.fantasy.football.auctionpro.dao.ConfigurationDao;
import com.fantasy.football.auctionpro.entity.Team;
import com.fantasy.football.auctionpro.service.TeamService;
import com.fantasy.football.auctionpro.ui.FootballFrame;

/**
 * Team Table Model
 * 
 * @author dhelbert
 * 
 */
public class TeamTableModel extends AbstractTableModel {

	/** Serial Version UID */
	private static final long serialVersionUID = 6173217310603452823L;

	/** Headers */
	private static final String[] headers = {"Number","Favorite","Team Name","Team Owner","Budget","Phone Number", "Email Address"};
	
	/** Team List */
	private List<Team> teams = new ArrayList<Team>();
	
	/** Team Service */
	private TeamService teamService = ServiceFactory.getTeamService();
	
	/** Configuration DAO */
	private ConfigurationDao configurationDao = DaoFactory.getConfigurationDao();
	
	/**
	 * Constructor
	 */
	public TeamTableModel() {
	}
	
	/**
	 * Refresh Data
	 */
	public void refresh() {
		teams = teamService.getAllTeams();
		fireTableDataChanged();
	}
	
	/**
	 * Get Team at Row
	 * 
	 * @param row
	 * @return Team
	 */
	public Team getTeam(int row) {
		Team team = teams.get(row);
		return team;
	}
	
	/**
	 * Get Row Count
	 * 
	 * @return int
	 */
	public int getRowCount() {
		return teams.size();
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
		Team team = teams.get(row);
		
		if(col == 0) {
			return new Integer(row+1);
		}
		else if(col == 1) {
			return team.getFavorite();
		}
		else if(col == 2) {
			return team.getName();
		}
		else if(col == 3) {
			return team.getOwner();
		}
		else if(col == 4) {
			return team.getBudget();
		}
		else if(col == 5) {
			return team.getPhone();
		}
		else {
			return team.getEmail();
		}
	}

	/**
	 * Get Column Count
	 * 
	 * @return int
	 */
	public int getColumnCount() {
		return headers.length;
	}

	@Override
	public boolean isCellEditable(int row, int col) {
		if( col == 0) {
			return false;
		}
		else {
			return true;
		}
    }
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public Class getColumnClass(int col) {
		if (col == 1) {
			return Boolean.class;
        }
		else if (col == 4) {
			return Integer.class;
        }
		else {
			return String.class;
		}
    }
	
	@Override
	public void setValueAt(Object value, int row, int col) {
		if( value != null && value.equals(getValueAt(row,col)) ) {
			return;
		}
		
		if( isValueTooLong(value.toString(),col) ) {
			return;
		}
		
		Team team = teams.get(row);
		
		if(col == 1) {			
			team.setFavorite((Boolean)value);
			
			Long currentTeamId = team.getId();
			
			for(Team otherTeam : teams ) {
				if( otherTeam.getFavorite() && !currentTeamId.equals(otherTeam.getId()) ) {
					otherTeam.setFavorite(Boolean.FALSE);
					teamService.updateTeam(otherTeam);
				}
			}
		}
		else if(col == 2) {
			team.setName(value.toString());
		}
		else if(col == 3) {
			team.setOwner(value.toString());
		}
		else if(col == 4) {
			if( configurationDao.getConfiguration().getMaxBudget() <= (Integer)value) {
				JOptionPane.showMessageDialog(MainApp.getMainComponent(),"Budget must be below maximum value.","Error",JOptionPane.ERROR_MESSAGE);
				return;
			}
			
			try {
				team.setBudget(new Integer(value.toString()));
			}
			catch(NumberFormatException err) {
				JOptionPane.showMessageDialog(MainApp.getMainComponent(),"Budget must be a valid integer value.","Error",JOptionPane.ERROR_MESSAGE);
				return;
			}
		}
		else if(col == 5) {
			team.setPhone(value.toString());
		}
		else {
			team.setEmail(value.toString());
		}

		teamService.updateTeam(team);
		
		if( col == 1 || col == 2 || col == 4) {
			FootballFrame.getTeamPanel().refreshTeamPlayerPanel(team);
		}
		
		fireTableDataChanged();
		
		JOptionPane.showMessageDialog(MainApp.getMainComponent(),"League team has been saved.","Information",JOptionPane.INFORMATION_MESSAGE);
	}

	/**
	 * Is Value Too Long
	 * 
	 * @param value
	 * @param col
	 * 
	 * @return boolean
	 */
	private boolean isValueTooLong(String value, int col) {
		if( value == null ) {
			return false;
		}
		
		if(col == 2 && value.length() > 30) {
			JOptionPane.showMessageDialog(MainApp.getMainComponent(),"Name value is longer than maximum of 30.","Error",JOptionPane.ERROR_MESSAGE);
			return true;
		}
		else if(col == 3 && value.length() > 40) {
			JOptionPane.showMessageDialog(MainApp.getMainComponent(),"Owner value is longer than maximum of 40.","Error",JOptionPane.ERROR_MESSAGE);
			return true;
		}
		else if(col == 5 && value.length() > 12) {
			JOptionPane.showMessageDialog(MainApp.getMainComponent(),"Phone value is longer than maximum of 12.","Error",JOptionPane.ERROR_MESSAGE);
			return true;
		}
		else if(col == 6 && value.length() > 40) {
			JOptionPane.showMessageDialog(MainApp.getMainComponent(),"Email value is longer than maximum of 40.","Error",JOptionPane.ERROR_MESSAGE);
			return true;
		}
		
		return false;
	}
}
