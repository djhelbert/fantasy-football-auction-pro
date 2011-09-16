package com.fantasy.football.auctionpro.ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.MessageFormat;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.TableColumn;

import com.fantasy.football.auctionpro.MainApp;
import com.fantasy.football.auctionpro.DaoFactory;
import com.fantasy.football.auctionpro.ServiceFactory;
import com.fantasy.football.auctionpro.dao.PlayerDao;
import com.fantasy.football.auctionpro.entity.Configuration;
import com.fantasy.football.auctionpro.entity.Player;
import com.fantasy.football.auctionpro.entity.Team;
import com.fantasy.football.auctionpro.service.ConfigurationService;
import com.fantasy.football.auctionpro.service.TeamService;
import com.fantasy.football.auctionpro.ui.model.RosterTableModel;
import com.fantasy.football.auctionpro.ui.model.TeamTableModel;

/**
 * Owner Panel
 * 
 * @author dhelbert
 *
 */
public class TeamOwnerPanel extends JPanel implements ActionListener, ListSelectionListener {

	/** Serial Version UID */
	private static final long serialVersionUID = 8249632306823480331L;
	
	/** Max Teams Supported */
	private static final int MAX_TEAMS = 12;
	
	/** Tool Bar Panel */
	private static JPanel toolBarPanel = new JPanel();
	
	/** Table Panel */
	private static JPanel tablePanel = new JPanel();
	
	/** Add Button */
	private static JButton addButton = new JButton("Add Team");
	
	/** Delete Button */
	private static JButton deleteButton = new JButton("Delete Team");

	/** Remove Button */
	private static JButton removeButton = new JButton("Remove Player");
	
	/** Delete Button */
	private static JButton printButton = new JButton("Print Roster");

	/** Roster Table Model */
	private static RosterTableModel rosterTableModel = new RosterTableModel();

	/** Owner Table Model */
	private static TeamTableModel teamTableModel = new TeamTableModel();

	/** Player DAO */
	private PlayerDao playerDao = DaoFactory.getPlayerDao();
	
	/** Team Service */
	private TeamService teamService = ServiceFactory.getTeamService();

	/** Configuration Service */
	private ConfigurationService configurationService = ServiceFactory.getConfigurationService();
	
	/** Owner Table */
	private static JTable teamTable;

	/** Roster Table */
	private static JTable rosterTable;
	
	/**
	 * Constructor
	 */
	public TeamOwnerPanel() {
		// Setup roster table
		rosterTable = new JTable(rosterTableModel);
		rosterTable.getSelectionModel().addListSelectionListener(this);
		sizeTableColumns(rosterTable,0,25);
		sizeTableColumns(rosterTable,2,25);
		sizeTableColumns(rosterTable,3,25);
		
		// Setup owner table
		teamTable = new JTable(teamTableModel);
		teamTable.setColumnSelectionAllowed(false);
		teamTable.setRowSelectionAllowed(true);
		teamTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		teamTable.getSelectionModel().addListSelectionListener(this);
		
		sizeTableColumns(teamTable,0,25);
		sizeTableColumns(teamTable,1,25);
		sizeTableColumns(teamTable,4,25);
		
		// Update table model data
		refresh();
		
		Configuration conf = configurationService.getConfiguration();
		
		if( teamTableModel.getRowCount() >= MAX_TEAMS ||  teamTableModel.getRowCount() >= conf.getMaxTeams() ) {
			addButton.setEnabled(false);
		}
		else {
			addButton.setEnabled(true);
		}
		
		deleteButton.setEnabled(false);
		printButton.setEnabled(false);
		removeButton.setEnabled(false);
		
		addButton.setIcon(Util.getImageIcon("add.png"));
		deleteButton.setIcon(Util.getImageIcon("minus.png"));
		removeButton.setIcon(Util.getImageIcon("minus.png"));
		printButton.setIcon(Util.getImageIcon("print.png"));
		
		initToolBarPanel();
		initTablePanel();
		
		setLayout(new BorderLayout());
		add(toolBarPanel, BorderLayout.NORTH);
		add(tablePanel, BorderLayout.CENTER);
	}
	
	/**
	 * Size Table Columns
	 * 
	 * @param table
	 * @param col
	 * @param size
	 */
	private void sizeTableColumns(JTable table, int col, int size) {
		TableColumn column = table.getColumnModel().getColumn(col);
		column.setPreferredWidth(size);
	}
	
	/**
	 * Init Tool Bar Panel
	 * 
	 */
	private void initTablePanel() {
		JScrollPane scroller1 = new JScrollPane(teamTable);
		JScrollPane scroller2 = new JScrollPane(rosterTable);
		
		scroller1.setBorder(BorderFactory.createTitledBorder("Teams"));
		scroller2.setBorder(BorderFactory.createTitledBorder("Team Roster"));
		
		tablePanel.setLayout(new GridLayout(2,1,2,2));
		tablePanel.add(scroller1);
		tablePanel.add(scroller2);
	}
	
	/**
	 * Init Tool Bar Panel
	 * 
	 */
	private void initToolBarPanel() {
		toolBarPanel.setBorder(BorderFactory.createEtchedBorder());
		toolBarPanel.setLayout(new FlowLayout(FlowLayout.LEFT,5,2));
		toolBarPanel.add(addButton);
		toolBarPanel.add(deleteButton);
		toolBarPanel.add(printButton);
		toolBarPanel.add(removeButton);
		
		addButton.addActionListener(this);
		deleteButton.addActionListener(this);
		printButton.addActionListener(this);
		removeButton.addActionListener(this);
	}

	/**
	 * Add Team
	 */
	private void addTeamAction() {
		// Create team
		teamService.createTeam(new Team("Name","Owner",configurationService.getConfiguration().getDefaultBudget()));
		
		// Refresh model
		teamTableModel.refresh();
		
		// Refresh other panel
		FootballFrame.getTeamPanel().refresh();
	}

	/**
	 * Delete Owner and Refresh Team Panel
	 * 
	 * @param id
	 */
	private void deleteOwnerAction(int row) {
		Team team = teamTableModel.getTeam(row);
		teamService.removeAllTeamPlayers(team);
		teamService.deleteTeam(team);
		
		// Clear table selection
		teamTable.clearSelection();
		
		// Delete team
		teamTableModel.refresh();
		
		// Clear other table
		rosterTableModel.clearPlayers();
		
		// Refresh other panel
		FootballFrame.getTeamPanel().refresh();
	}
	
	/**
	 * Remove Player
	 * 
	 * @param row
	 */
	private void removePlayerAction(int row) {
		Player tp = rosterTableModel.getPlayer(row);
		
		if( tp != null ) {
			Team tt = tp.getTeam();
			
			teamService.removeTeamPlayer(tp);
			rosterTableModel.removePlayer(row);
			
			FootballFrame.getTeamPanel().refreshTeamPlayerPanel(tt);
			
			TeamPanel.getPlayerPanel().refreshTable(tp.getPosition());
			
			JOptionPane.showMessageDialog(MainApp.getMainComponent(),"Team player has been removed from the roster.","Information",JOptionPane.INFORMATION_MESSAGE);
		}
	}
	
	/**
	 * Clear
	 * 
	 */
	public void clear() {
		teamTable.clearSelection();
		rosterTableModel.clearPlayers();
		printButton.setEnabled(false);
		deleteButton.setEnabled(false);
	}
	
	/**
	 * Action Performed
	 * 
	 * @param e ActionEvent
	 */
	public void actionPerformed(ActionEvent e) {
		if( e.getSource() == addButton ) {
			addTeamAction();
			
			if( teamTableModel.getRowCount() >= MAX_TEAMS || teamTableModel.getRowCount() >= configurationService.getConfiguration().getMaxTeams() ) {
				addButton.setEnabled(false);
			}
			else {
				addButton.setEnabled(true);
			}
		}
		else if(e.getSource() == removeButton) {
			int row = rosterTable.getSelectedRow();
			
			removePlayerAction(row);
		}
		else if(e.getSource() == deleteButton) {
			if( JOptionPane.showConfirmDialog(MainApp.getMainComponent(), "Are you sure you want to delete the fantasy team?", "Confirmation", JOptionPane.OK_CANCEL_OPTION) == 0) {				
				int row = teamTable.getSelectedRow();
				deleteOwnerAction(row);
			}
		}
		else {
			printRosterTable();
		}
	}

	/**
	 * printRosterTable
	 */
	private void printRosterTable() {
		String headerTxt = teamTableModel.getValueAt(teamTable.getSelectedRow(),2).toString();
		headerTxt += " (";
		headerTxt += teamTableModel.getValueAt(teamTable.getSelectedRow(),3).toString();
		headerTxt += ") ";
		
		String footerTxt = "";
		
		if( teamTableModel.getValueAt(teamTable.getSelectedRow(),5) != null ) {
			footerTxt += "Phone: " + teamTableModel.getValueAt(teamTable.getSelectedRow(),5).toString();
		}
		
		if( teamTableModel.getValueAt(teamTable.getSelectedRow(),6) != null ) {
			footerTxt += " Email: ";
			footerTxt += teamTableModel.getValueAt(teamTable.getSelectedRow(),6).toString();
		}
		
		MessageFormat header = new MessageFormat(headerTxt);
		MessageFormat footer = new MessageFormat(footerTxt);
		
		try {
			rosterTable.print(JTable.PrintMode.FIT_WIDTH, header, footer);
		} 
		catch (java.awt.print.PrinterException err) {
		    err.printStackTrace();
		}
	}

	/**
	 * Value Changed
	 * 
	 * @param e ListSelectionEvent
	 */
	public void valueChanged(ListSelectionEvent e) {
		if( e.getSource() == teamTable.getSelectionModel() ) {
			int index = teamTable.getSelectedRow();
			
			if( index < 0 ) {
				deleteButton.setEnabled(false);
				printButton.setEnabled(false);
			}
			else {
				rosterTableModel.clearPlayers();
				
				// Update roster table
				rosterTableModel.setPlayers(playerDao.getPlayersByTeam(teamTableModel.getTeam(index)));
				
				deleteButton.setEnabled(true);
				printButton.setEnabled(true);
			}
			
			if( teamTableModel.getRowCount() >= MAX_TEAMS || teamTableModel.getRowCount() >= configurationService.getConfiguration().getMaxTeams() ) {
				addButton.setEnabled(false);
			}
			else {
				addButton.setEnabled(true);
			}
		}
		else {
			int index = rosterTable.getSelectedRow();
			
			if( index < 0 ) {
				removeButton.setEnabled(false);
			}
			else {
				removeButton.setEnabled(true);
			}
		}
	}
	
	/**
	 * Refresh Selected Team
	 * 
	 * @param team
	 */
	public void refresh(Team team) {
		int row = teamTable.getSelectedRow();
		
		if( row != -1 && team.getId().equals(teamTableModel.getTeam(row).getId())) {
			teamTable.clearSelection();
			rosterTable.clearSelection();
			teamTableModel.refresh();
			rosterTableModel.clearPlayers();
			printButton.setEnabled(false);
			deleteButton.setEnabled(false);
			
			if( teamTableModel.getRowCount() >= MAX_TEAMS || teamTableModel.getRowCount() >= configurationService.getConfiguration().getMaxTeams() ) {
				addButton.setEnabled(false);
			}
			else {
				addButton.setEnabled(true);
			}
		}
	}
	
	/**
	 * Refresh Team Table, Remove Selections, and Clear Roster Table
	 */
	public void refresh() {
		teamTable.clearSelection();
		rosterTable.clearSelection();
		teamTableModel.refresh();
		rosterTableModel.clearPlayers();
		printButton.setEnabled(false);
		deleteButton.setEnabled(false);
		
		if( teamTableModel.getRowCount() >= MAX_TEAMS || teamTableModel.getRowCount() >= configurationService.getConfiguration().getMaxTeams() ) {
			addButton.setEnabled(false);
		}
		else {
			addButton.setEnabled(true);
		}
	}
}
