package com.fantasy.football.auctionpro.ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.KeyStroke;
import javax.swing.border.EtchedBorder;

import com.fantasy.football.auctionpro.DaoFactory;
import com.fantasy.football.auctionpro.MainApp;
import com.fantasy.football.auctionpro.ServiceFactory;
import com.fantasy.football.auctionpro.dao.PlayerDao;
import com.fantasy.football.auctionpro.entity.Player;
import com.fantasy.football.auctionpro.service.TeamService;

/**
 * Football Frame
 * 
 * @author dhelbert
 *
 */
public class FootballFrame extends JFrame implements ActionListener {

	/** Serial Version UID */
	private static final long serialVersionUID = -4709717588289536006L;

	/** Tab Pane */
	private static JTabbedPane tabbedPane = new JTabbedPane();

	/** Add Player Item */
	private static JMenuItem addPlayerItem = new JMenuItem("Add Player");
	
	/** New Item */
	private static JMenuItem newItem = new JMenuItem("New");
	
	/** Exit Item */
	private static JMenuItem exitItem = new JMenuItem("Exit");
	
	/** About Item */
	private static JMenuItem aboutItem = new JMenuItem("About...");

	/** License Item */
	private static JMenuItem licenseItem = new JMenuItem("License");

	/** Help Item */
	private static JMenuItem helpItem = new JMenuItem("Help");
	
    /** Tool Bar */
    private static JToolBar toolBar = new JToolBar();
    
    /** Search Button */
    private static JButton searchButton = new JButton("Search");
    
    /** Search Button */
    private static JTextField searchField = new JTextField(20);
    
	/** Owner Panel */
	private static TeamOwnerPanel teamOwnerPanel = new TeamOwnerPanel();
	
	/** Team Panel */
	private static TeamPanel teamPanel = new TeamPanel();
	
	/** Team Panel */
	private static ConfigurationPanel configurationPanel = new ConfigurationPanel();
	
	/** Favorite Team Panel */
	private static FavoriteTeamPanel favoriteTeamPanel = new FavoriteTeamPanel();
	
	/** Player DAO */
	private PlayerDao playerDao = DaoFactory.getPlayerDao();
	
	/** Team Service */
	private TeamService teamService = ServiceFactory.getTeamService();
	
	/**
	 * Constructor
	 * 
	 * @param leagueName
	 */
	public FootballFrame(String leagueName) {
		super("Fantasy Football Auction Pro - " + leagueName );
		
		// Initialize tool bar
		initToolBar();
		
		// Initialize Frame
		initFrame();
		
		// Center
		Util.centerComponent(this);
	}
	
	/**
	 * Initialize Frame
	 */
	private void initFrame() {
		setSize(1200,800);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		tabbedPane.add("Fantasy Teams", teamPanel);
		tabbedPane.add("Team Owners", teamOwnerPanel);
		tabbedPane.add("League Configuration", configurationPanel);
		
		exitItem.addActionListener(this);
		exitItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, ActionEvent.CTRL_MASK));
		exitItem.setIcon(Util.getImageIcon("exit.png"));
		
		newItem.addActionListener(this);
		newItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.CTRL_MASK));
		newItem.setIcon(Util.getImageIcon("file.png"));
		
		JMenuBar menuBar = new JMenuBar();
		
		JMenu menu = new JMenu("File");
		menu.add(newItem);
		menu.addSeparator();
		menu.add(exitItem);
		menuBar.add(menu);
		
		helpItem.addActionListener(this);
		aboutItem.addActionListener(this);
		licenseItem.addActionListener(this);
		addPlayerItem.addActionListener(this);
		
		addPlayerItem.setIcon(Util.getImageIcon("add.png"));
		aboutItem.setIcon(Util.getImageIcon("about.png"));
		helpItem.setIcon(Util.getImageIcon("help.png"));
		licenseItem.setIcon(Util.getImageIcon("business.png"));

		JMenu playerMenu = new JMenu("Player");
		playerMenu.add(addPlayerItem);
		menuBar.add(playerMenu);
		
		JMenu helpMenu = new JMenu("Help");
		helpMenu.add(helpItem);
		helpMenu.add(licenseItem);
		helpMenu.addSeparator();
		helpMenu.add(aboutItem);
		menuBar.add(helpMenu);
		
		setJMenuBar(menuBar);

		getContentPane().setLayout(new BorderLayout());
		getContentPane().add(tabbedPane, BorderLayout.CENTER);
		getContentPane().add(toolBar, BorderLayout.PAGE_START);
	}
	
	/**
	 * Initialize Tool Bar
	 */
    public void initToolBar() {
    	toolBar.setLayout(new GridLayout(1,2,2,0));
    	
    	searchButton.setIcon(Util.getImageIcon("search.png"));
    	
    	toolBar.add(favoriteTeamPanel);
    	
    	JPanel rightPanel = new JPanel();
    	rightPanel.setLayout(new FlowLayout(FlowLayout.RIGHT,2,0));
    	
    	rightPanel.add(searchField);
    	rightPanel.add(searchButton);
        rightPanel.setOpaque(false);
        
    	toolBar.add(rightPanel);
    	
        searchButton.addActionListener(this);
        searchField.addActionListener(this);
    }
    
	/**
	 * Action Performed
	 * 
	 * @param e Action Event
	 */
	public void actionPerformed(ActionEvent e) {
		if( e.getSource() == exitItem ) {
			// Close entity manager
			playerDao.close();
			
			// Exit
			System.exit(0);
		}
		else if( e.getSource() == helpItem ) {
			helpAction();
		}
		else if( e.getSource() == aboutItem ) {
			aboutAction();
		}
		else if( e.getSource() == licenseItem ) {
			licenseAction();
		}
		else if( e.getSource() == addPlayerItem ) {
			addPlayerAction();
		}
		else if( e.getSource() == newItem ) {
			if( JOptionPane.showConfirmDialog(MainApp.getMainComponent(), "Are you sure you want to delete the fantasy league?", "Confirmation", JOptionPane.OK_CANCEL_OPTION) == 0) {
				// Clear all teams
				teamService.clearLeague();
				
				// Refresh all panels
				teamPanel.refresh();
				
				// Refresh player panel
				TeamPanel.getPlayerPanel().refreshAllTables();
				
				// Refresh team owner panel
				teamOwnerPanel.refresh();
			}
		}
		else if( e.getSource() == searchButton || e.getSource() == searchField ) {
			searchAction();
		}
	}

	/**
	 * Help Action
	 */
	private void helpAction() {
		Map<String,URL> entries = new HashMap<String,URL>();
		
		entries.put("Add Missing Player", Util.getHelpURL("add_missing_player.html"));
		entries.put("Add Player to Team", Util.getHelpURL("add_player_team.html"));
		entries.put("Add New Team", Util.getHelpURL("add_new_team.html"));
		entries.put("Introduction", Util.getHelpURL("intro.html"));
		entries.put("New League", Util.getHelpURL("new_league.html"));
		entries.put("Remove Team", Util.getHelpURL("rem_team.html"));
		entries.put("Remove Player from Team", Util.getHelpURL("rem_team_player.html"));
		entries.put("Player Search", Util.getHelpURL("search_player.html"));
		entries.put("Print Team", Util.getHelpURL("print_team.html"));
		entries.put("Update League", Util.getHelpURL("update_league.html"));
		entries.put("Update Player Price", Util.getHelpURL("update_price.html"));
		entries.put("Update Scoring System", Util.getHelpURL("update_scoring_system.html"));
		
		HelpDialog hd = new HelpDialog(entries,"Introduction");
		hd.setVisible(true);
	}
	
	/**
	 * Add Player Action
	 */
	private void addPlayerAction() {
		AddPlayerDialog ad = new AddPlayerDialog();
		ad.setVisible(true);
	}

	/**
	 * Pick Action
	 */
	private void pickAction() {
		List<Player> results = playerDao.getPlayersByName("%" + searchField.getText().toUpperCase() + "%");
		
		if( results != null && results.size() > 0 ) {
			tabbedPane.setSelectedIndex(0);
			
			TeamPanel.getPlayerPanel().selectPlayer(results.get(0),true);
		}
		else {
			JOptionPane.showMessageDialog(MainApp.getMainComponent(),"No player with matching name found.","Information",JOptionPane.INFORMATION_MESSAGE);
		}
	}
	
	/**
	 * Search Action
	 */
	private void searchAction() {
		List<Player> results = playerDao.getPlayersByName("%" + searchField.getText().toUpperCase() + "%");
		
		if( results != null && results.size() > 0 ) {
			tabbedPane.setSelectedIndex(0);
			
			TeamPanel.getPlayerPanel().selectPlayer(results.get(0),false);
		}
		else {
			JOptionPane.showMessageDialog(MainApp.getMainComponent(),"No player with matching name found.","Information",JOptionPane.INFORMATION_MESSAGE);
		}
	}
	
	/**
	 * About Action
	 * 
	 */
	private void aboutAction() {
		Util.showInfo(MainApp.getMainComponent(), "Fantasy Football Auction Pro Copyright © 2012", "About");
	}
	
	/**
	 * License Action
	 * 
	 */
	private void licenseAction() {
		try {
			String text = Util.getFileText("gpl.txt");
			
			JPanel panel = new JPanel();
			panel.setLayout(new BorderLayout(5, 5));
			
			JTextArea textArea = new JTextArea(text, 25, 80);
			textArea.setEditable(false);
			textArea.setFont(new Font("courier", Font.PLAIN, 12));
			
			JScrollPane spane = new JScrollPane(textArea);
			panel.setBorder(new EtchedBorder());
			panel.add(BorderLayout.CENTER, spane);
			
			Util.showInfo(this, panel, "License");
		} catch (Exception err) {
			err.printStackTrace();
		}
	}

	/**
	 * Get Team Owner Panel
	 * 
	 * @return TeamOwnerPanel
	 */
	public static TeamOwnerPanel getTeamOwnerPanel() {
		return teamOwnerPanel;
	}

	/**
	 * Get Team Panel
	 * 
	 * @return TeamPanel
	 */
	public static TeamPanel getTeamPanel() {
		return teamPanel;
	}

	/**
	 * Get Configuration Panel
	 * 
	 * @return ConfigurationPanel
	 */
	public static ConfigurationPanel getConfigurationPanel() {
		return configurationPanel;
	}
	
	/**
	 * Get Favorite Team Panel
	 * 
	 * @return FavoriteTeamPanel
	 */
	public static FavoriteTeamPanel getFavoriteTeamPanel() {
		return favoriteTeamPanel;
	}
	
}
