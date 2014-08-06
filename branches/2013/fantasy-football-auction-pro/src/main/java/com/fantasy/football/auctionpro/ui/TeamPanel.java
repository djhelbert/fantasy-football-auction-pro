package com.fantasy.football.auctionpro.ui;

import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

import com.fantasy.football.auctionpro.DaoFactory;
import com.fantasy.football.auctionpro.dao.TeamDao;
import com.fantasy.football.auctionpro.entity.Team;

/**
 * Team Panel
 * 
 * @author dhelbert
 *
 */
public class TeamPanel extends JPanel {

	/** Serial Version UID */
	private static final long serialVersionUID = 257994647465984078L;
	
	/** Team Player Panels */
	private List<TeamPlayerPanel> teamPlayerPanels = new ArrayList<TeamPlayerPanel>(12);
	
	/** Grid Panel */
	private JPanel gridPanel = new JPanel();

	/** Player Panel */
	private static PlayerPanel playerPanel = new PlayerPanel();
	
	/** Team DAO */
	private TeamDao teamDao = DaoFactory.getTeamDao();
	
	/**
	 * Constructor
	 */
	public TeamPanel() {
		setLayout(new GridLayout(2,1));
		add(gridPanel);
		add(playerPanel);
		
		// Update list panels
		List<Team> teams = teamDao.getAllTeams();
		
		gridPanel.setLayout(new GridLayout(1,teams.size()));
		
		teamPlayerPanels.clear();
		
		// Give each team a panel
		for(Team team : teams) {
			TeamPlayerPanel tpp = new TeamPlayerPanel(team);
			teamPlayerPanels.add(tpp);
			gridPanel.add(tpp);
		}
	}
	
	/**
	 * Get Player Panel
	 * 
	 * @return PlayerPanel
	 */
	public static PlayerPanel getPlayerPanel() {
		return playerPanel;
	}
	
	/**
	 * Refresh Team Player Panel
	 * 
	 * @param id Team ID
	 * 
	 * @return TeamPlayerPanel
	 */
	public void refreshTeamPlayerPanel(Team team) {
		// Update favorite
		FootballFrame.getFavoriteTeamPanel().refresh();
		
		for(TeamPlayerPanel tpp : teamPlayerPanels ) {
			if(tpp.getTeamPlayerPanelTeam() != null && tpp.getTeamPlayerPanelTeam().getId().equals(team.getId())) {
				// Update panels
				tpp.setTeam(team);
			}
		}
	}
	
	/**
	 * Refresh Data
	 */
	public void refresh() {
		// Refresh player tables
		playerPanel.refreshAllTables();
		
		// Update list panels
		List<Team> teams = teamDao.getAllTeams();
		
		gridPanel.setLayout(new GridLayout(1,teams.size()));
		gridPanel.removeAll();
		
		teamPlayerPanels.clear();
		
		// Give each team a panel
		for(Team team : teams) {
			TeamPlayerPanel tpp = new TeamPlayerPanel(team);
			teamPlayerPanels.add(tpp);
			gridPanel.add(tpp);
		}
		
		FootballFrame.getFavoriteTeamPanel().refresh();
	}
}
