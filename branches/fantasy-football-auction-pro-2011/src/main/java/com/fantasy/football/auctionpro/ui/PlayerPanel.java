package com.fantasy.football.auctionpro.ui;

import java.awt.BorderLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.TableColumn;

import com.fantasy.football.auctionpro.Constants;
import com.fantasy.football.auctionpro.ServiceFactory;
import com.fantasy.football.auctionpro.entity.Player;
import com.fantasy.football.auctionpro.entity.Team;
import com.fantasy.football.auctionpro.service.TeamService;
import com.fantasy.football.auctionpro.ui.model.DefenseTableModel;
import com.fantasy.football.auctionpro.ui.model.KickerTableModel;
import com.fantasy.football.auctionpro.ui.model.PlayerTableModel;
import com.fantasy.football.auctionpro.ui.model.QuarterBackTableModel;
import com.fantasy.football.auctionpro.ui.model.ReceiverTableModel;
import com.fantasy.football.auctionpro.ui.model.RunningBackTableModel;
import com.fantasy.football.auctionpro.ui.model.TightEndTableModel;

/**
 * Player Panel
 * 
 * @author dhelbert
 *
 */
public class PlayerPanel extends JPanel implements MouseListener {

	/** Serial Version UID */
	private static final long serialVersionUID = -731031706592470384L;

	/** Tab Panel */
	private JTabbedPane tabbedPane = new JTabbedPane();
	
	/** Defense Table Model */
	private static DefenseTableModel defenseTableModel = new DefenseTableModel();
	
	/** QB Table Model */
	private static QuarterBackTableModel quarterBackTableModel = new QuarterBackTableModel();
	
	/** Kicker Table Model */
	private static KickerTableModel kickerTableModel = new KickerTableModel();
	
	/** TE Table Model */
	private static TightEndTableModel tightEndTableModel = new TightEndTableModel();
	
	/** Receiver Table Model */
	private static ReceiverTableModel receiverTableModel = new ReceiverTableModel();
	
	/** RB Table Model */
	private static RunningBackTableModel runningBackTableModel = new RunningBackTableModel();
	
	/** Team Service */
	private static TeamService teamService = ServiceFactory.getTeamService();
	
	/** QB Table */
	private JTable quarterBackTable;
	
	/** RB Table */
	private JTable runningBackTable;
	
	/** WR Table */
	private JTable receiverTable;
	
	/** TE Table */
	private JTable tightEndTable;
	
	/** DEF Table */
	private JTable defenseTable;
	
	/** K Table */
	private JTable kickerTable;
	
	/**
	 * Constructor
	 * 
	 */
	public PlayerPanel() {
		setBorder(BorderFactory.createTitledBorder("Available Players"));
		
		quarterBackTable = new JTable(quarterBackTableModel);
		runningBackTable = new JTable(runningBackTableModel);
		receiverTable    = new JTable(receiverTableModel);
		tightEndTable    = new JTable(tightEndTableModel);
		defenseTable     = new JTable(defenseTableModel);
		kickerTable      = new JTable(kickerTableModel);
		
		quarterBackTable.addMouseListener(this);
		runningBackTable.addMouseListener(this);
		receiverTable.addMouseListener(this);
		tightEndTable.addMouseListener(this);
		defenseTable.addMouseListener(this);
		kickerTable.addMouseListener(this);
		
		initTable(defenseTable);
		initTable(quarterBackTable);
		initTable(kickerTable);
		initTable(tightEndTable);
		initTable(receiverTable);
		initTable(runningBackTable);
		
		// Update tables
		refreshAllTables();
		
		tabbedPane.add("Quarterbacks", new JScrollPane(quarterBackTable));
		tabbedPane.add("Running Backs", new JScrollPane(runningBackTable));
		tabbedPane.add("Receivers", new JScrollPane(receiverTable));
		tabbedPane.add("Tight Ends", new JScrollPane(tightEndTable));
		tabbedPane.add("Defenses", new JScrollPane(defenseTable));
		tabbedPane.add("Kickers", new JScrollPane(kickerTable));
		
		setLayout(new BorderLayout());
		add(tabbedPane, BorderLayout.CENTER);
	}

	/**
	 * Select Player
	 * 
	 * @param player
	 * @param pick
	 */
	public void selectPlayer(Player player, boolean pick) {	
		if( Constants.QB.equals(player.getPosition()) ) {
			tabbedPane.setSelectedIndex(0);
			
			int row = quarterBackTableModel.getPlayerRow(player);
			
			if( row >= 0 ) {
				quarterBackTable.setRowSelectionInterval(row,row);
				quarterBackTable.scrollRectToVisible(quarterBackTable.getCellRect(row,0,false));
				
				if(pick) {
					Team favTeam = teamService.getFavoriteTeam();
					pickPlayer(favTeam,quarterBackTableModel,quarterBackTable);
				}
			}
		}
		else if( Constants.RB.equals(player.getPosition()) ) {
			tabbedPane.setSelectedIndex(1);
			
			int row = runningBackTableModel.getPlayerRow(player);
			
			if( row >= 0 ) {
				runningBackTable.setRowSelectionInterval(row,row);
				runningBackTable.scrollRectToVisible(runningBackTable.getCellRect(row,0,false));
				
				if(pick) {
					Team favTeam = teamService.getFavoriteTeam();
					pickPlayer(favTeam,runningBackTableModel,runningBackTable);
				}
			}
		}
		else if( Constants.WR.equals(player.getPosition()) ) {
			tabbedPane.setSelectedIndex(2);
			
			int row = receiverTableModel.getPlayerRow(player);
			
			if( row >= 0 ) {
				receiverTable.setRowSelectionInterval(row,row);
				receiverTable.scrollRectToVisible(receiverTable.getCellRect(row,0,false));
				
				if(pick) {
					Team favTeam = teamService.getFavoriteTeam();
					pickPlayer(favTeam,receiverTableModel,receiverTable);
				}
			}
		}
		else if( Constants.TE.equals(player.getPosition()) ) {
			tabbedPane.setSelectedIndex(3);
			
			int row = tightEndTableModel.getPlayerRow(player);
			
			if( row >= 0 ) {
				tightEndTable.setRowSelectionInterval(row,row);
				tightEndTable.scrollRectToVisible(tightEndTable.getCellRect(row,0,false));
				
				if(pick) {
					Team favTeam = teamService.getFavoriteTeam();
					pickPlayer(favTeam,tightEndTableModel,tightEndTable);
				}
			}
		}
		else if( Constants.DEF.equals(player.getPosition()) ) {
			tabbedPane.setSelectedIndex(4);
			
			int row = defenseTableModel.getPlayerRow(player);
			
			if( row >= 0 ) {
				defenseTable.setRowSelectionInterval(row,row);
				defenseTable.scrollRectToVisible(defenseTable.getCellRect(row,0,false));
				
				if(pick) {
					Team favTeam = teamService.getFavoriteTeam();
					pickPlayer(favTeam,defenseTableModel,defenseTable);
				}
			}
		}
		else if( Constants.K.equals(player.getPosition()) ) {
			tabbedPane.setSelectedIndex(5);
			
			int row = kickerTableModel.getPlayerRow(player);
			
			if( row >= 0 ) {
				kickerTable.setRowSelectionInterval(row,row);
				kickerTable.scrollRectToVisible(kickerTable.getCellRect(row,0,false));
				
				if(pick) {
					Team favTeam = teamService.getFavoriteTeam();
					pickPlayer(favTeam,kickerTableModel,kickerTable);
				}
			}
		}
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
	 * Refresh Table
	 * 
	 * @param position
	 */
	public void refreshTable(String position) {
		if( Constants.QB.equals(position) ) {
			quarterBackTableModel.refresh();
			quarterBackTable.clearSelection();
		}
		else if( Constants.RB.equals(position) ) {
			runningBackTableModel.refresh();
			runningBackTable.clearSelection();
		}
		else if( Constants.WR.equals(position) ) {
			receiverTableModel.refresh();
			receiverTable.clearSelection();
		}
		else if( Constants.TE.equals(position) ) {
			tightEndTableModel.refresh();
			tightEndTable.clearSelection();
		}
		else if( Constants.DEF.equals(position) ) {
			defenseTableModel.refresh();
			defenseTable.clearSelection();
		}
		else if( Constants.K.equals(position) ) {
			kickerTableModel.refresh();
			kickerTable.clearSelection();
		}
	}
	
	/**
	 * Refresh All Tables
	 */
	public void refreshAllTables() {
		// Refresh all models
		quarterBackTableModel.refresh();
		runningBackTableModel.refresh();
		receiverTableModel.refresh();
		tightEndTableModel.refresh();
		defenseTableModel.refresh();
		kickerTableModel.refresh();
		
		// Clear table selections
		quarterBackTable.clearSelection();
		runningBackTable.clearSelection();
		receiverTable.clearSelection();
		tightEndTable.clearSelection();
		defenseTable.clearSelection();
		kickerTable.clearSelection();
	}
	
	/**
	 * Init Table
	 * 
	 * @param table
	 */
	private void initTable(JTable table) {
		table.setColumnSelectionAllowed(false);
		table.setRowSelectionAllowed(true);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		sizeTableColumns(table,0,25);
		sizeTableColumns(table,1,25);
		sizeTableColumns(table,3,25);
		sizeTableColumns(table,4,25);
	}

	/**
	 * Action Performed
	 * 
	 * @param e ActionEvent
	 */
	public void mouseClicked(MouseEvent e) {
		if (e.getClickCount() == 2 && e.getSource() == quarterBackTable && quarterBackTable.getSelectedRow() >= 0 ){
			Team favTeam = teamService.getFavoriteTeam();
			pickPlayer(favTeam,quarterBackTableModel,quarterBackTable);
		}
		else if (e.getClickCount() == 2 && e.getSource() == runningBackTable && runningBackTable.getSelectedRow() >= 0 ){
			Team favTeam = teamService.getFavoriteTeam();
			pickPlayer(favTeam,runningBackTableModel,runningBackTable);
		}
		else if (e.getClickCount() == 2 && e.getSource() == receiverTable && receiverTable.getSelectedRow() >= 0 ){
			Team favTeam = teamService.getFavoriteTeam();
			pickPlayer(favTeam,receiverTableModel,receiverTable);
		}
		else if (e.getClickCount() == 2 && e.getSource() == tightEndTable && tightEndTable.getSelectedRow() >= 0 ){
			Team favTeam = teamService.getFavoriteTeam();
			pickPlayer(favTeam,tightEndTableModel,tightEndTable);
		}
		else if (e.getClickCount() == 2 && e.getSource() == kickerTable && kickerTable.getSelectedRow() >= 0 ){
			Team favTeam = teamService.getFavoriteTeam();
			pickPlayer(favTeam,kickerTableModel,kickerTable);
		}
		else if (e.getClickCount() == 2 && e.getSource() == defenseTable && defenseTable.getSelectedRow() >= 0 ){
			Team favTeam = teamService.getFavoriteTeam();
			pickPlayer(favTeam,defenseTableModel,defenseTable);
		}
	}

	/**
	 * Pick Player
	 * 
	 * @param favTeam
	 * @param playerTableModel
	 * @param table
	 */
	private void pickPlayer(Team favTeam, PlayerTableModel playerTableModel, JTable table) {
		Player pl = playerTableModel.getPlayer(table.getSelectedRow());
		PickDialog pd = new PickDialog(pl,favTeam,playerTableModel);
		pd.setVisible(true);
	}

	/**
	 * Mouse Pressed
	 * 
	 * @param e ActionEvent
	 */
	public void mousePressed(MouseEvent e) {
	}

	/**
	 * Mouse Released
	 * 
	 * @param e ActionEvent
	 */
	public void mouseReleased(MouseEvent e) {
	}

	/**
	 * Mouse Entered
	 * 
	 * @param e ActionEvent
	 */
	public void mouseEntered(MouseEvent e) {
	}

	/**
	 * Mouse Exited
	 * 
	 * @param e ActionEvent
	 */
	public void mouseExited(MouseEvent e) {
	}
}
