package com.fantasy.football.auctionpro.ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.fantasy.football.auctionpro.MainApp;
import com.fantasy.football.auctionpro.ServiceFactory;
import com.fantasy.football.auctionpro.entity.Configuration;
import com.fantasy.football.auctionpro.service.ConfigurationService;
import com.fantasy.football.auctionpro.service.VbdService;

/**
 * Configuration Panel
 * 
 * @author dhelbert
 *
 */
public class ConfigurationPanel extends JPanel implements ActionListener {
	
	/** Serial Version UID */
	private static final long serialVersionUID = 8000918573746657159L;
	
	/** Name Field */
	private static JTextField nameField = new JTextField(30);
	
	/** URL Field */
	private static JTextField urlField = new JTextField(80);
	
	/** Max Teams Box */
	private static JComboBox maxTeamsComboBox = new JComboBox(new Integer[] {8,9,10,11,12});
	
	/** Max Roster Size Box */
	private static JComboBox maxRosterSizeComboBox = new JComboBox(new Integer[] {7,8,9,10,11,12,13,14,15,16});
	
	/** Max Budget Field Box */
	private static JFormattedTextField maxBudgetField = new JFormattedTextField(new Integer(0));

	/** Default Budget Field Box */
	private static JFormattedTextField defBudgetField = new JFormattedTextField(new Integer(0));
	
	/** Save Button */
	private static JButton saveButton = new JButton("Save");

	/** Cancel Button */
	private static JButton cancelButton = new JButton("Cancel");
	
	/** Tool Bar Panel */
	private static JPanel toolBarPanel = new JPanel();

	/** Field Panel */
	private static JPanel leaguePanel = new JPanel();

	/** Position Panel */
	private static JPanel positionPanel = new JPanel();

	/** Bye Week Panel */
	private static JPanel byeWeekPanel = new JPanel();
	
	/** Configuration Panel */
	private static JPanel configPanel = new JPanel();

	/** Center Panel */
	private static JPanel centerPanel = new JPanel();
	
	/** Score System Panel */
	private static ScoreSystemPanel scoreSystemPanel = new ScoreSystemPanel();
	
	/** Start QB Box */
	private static JComboBox startQbComboBox = new JComboBox(new Integer[] {1,2});

	/** Start RB Box */
	private static JComboBox startRbComboBox = new JComboBox(new Integer[] {1,2,3,4});
	
	/** Start TE Box */
	private static JComboBox startTeComboBox = new JComboBox(new Integer[] {0,1,2});
	
	/** Start WR Box */
	private static JComboBox startRbWrComboBox = new JComboBox(new Integer[] {0,1,2});
	
	/** Start WR Box */
	private static JComboBox startWrComboBox = new JComboBox(new Integer[] {0,1,2,3,4,5});
	
	/** Start K Box */
	private static JComboBox startKComboBox = new JComboBox(new Integer[] {0,1,2});
	
	/** Start DEF Box */
	private static JComboBox startDefComboBox = new JComboBox(new Integer[] {0,1,2});
	
	/** Draft Date */
	private static JFormattedTextField draftDateField = new JFormattedTextField(new SimpleDateFormat("MMM d yyyy H:mm aa"));

	/** Configuration DAO */
	private ConfigurationService configurationService = ServiceFactory.getConfigurationService();
	
	/** Configuration */
	private static Configuration configuration;
	
	/**
	 * Constructor
	 *
	 */
	public ConfigurationPanel() {
		initToolBarPanel();
		initFieldPanel();
		initPositionPanel();
		initByeWeekPanel();
		
		configPanel.setLayout(new GridLayout(3,1,0,0));
		configPanel.add(leaguePanel);
		configPanel.add(positionPanel);
		configPanel.add(byeWeekPanel);
		
		centerPanel.setLayout(new GridLayout(1,2,0,0));
		centerPanel.add(configPanel);
		centerPanel.add(scoreSystemPanel);
		
		setLayout(new BorderLayout());
		add(toolBarPanel, BorderLayout.NORTH);
		add(centerPanel, BorderLayout.CENTER);
		
		try {
			if(configurationService.count() > 0) {
				configuration = configurationService.getConfiguration();
				initSettings();
			}
		}
		catch(Exception err) {
			err.printStackTrace();
		}
	}

	/**
	 * Init By Week Panel
	 */
	private void initByeWeekPanel() {
		String wk4  = "";
		String wk5  = "";
		String wk6  = "";
		String wk7  = "";
		String wk8  = "";
		String wk9  = "";
		String wk10 = "";
		String wk11 = "";
		
		try {
			Map<String,Integer> map = configurationService.getByeWeekMap();
			
			Integer week = null;
			
			for(String key : map.keySet()) {
				week = map.get(key);
				
				if(week == 4) {
					wk4 += key + " ";
				}
				else if(week == 5) {
					wk5 += key + " ";
				}
				else if(week == 6) {
					wk6 += key + " ";
				}
				else if(week == 7) {
					wk7 += key + " ";
				}
				else if(week == 8) {
					wk8 += key + " ";
				}
				else if(week == 9) {
					wk9 += key + " ";
				}
				else if(week == 10) {
					wk10 += key + " ";
				}
				else if(week == 11) {
					wk11 += key + " ";
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		byeWeekPanel.setBorder(BorderFactory.createTitledBorder("Bye Weeks"));
		byeWeekPanel.setLayout(new GridLayout(8,2,2,2));

		byeWeekPanel.add(new JLabel("Week 4",JLabel.RIGHT));
		byeWeekPanel.add(new JLabel(wk4,JLabel.RIGHT));
		
		byeWeekPanel.add(new JLabel("Week 5",JLabel.RIGHT));
		byeWeekPanel.add(new JLabel(wk5,JLabel.RIGHT));
		
		byeWeekPanel.add(new JLabel("Week 6",JLabel.RIGHT));
		byeWeekPanel.add(new JLabel(wk6,JLabel.RIGHT));
		
		byeWeekPanel.add(new JLabel("Week 7",JLabel.RIGHT));
		byeWeekPanel.add(new JLabel(wk7,JLabel.RIGHT));
		
		byeWeekPanel.add(new JLabel("Week 8",JLabel.RIGHT));
		byeWeekPanel.add(new JLabel(wk8,JLabel.RIGHT));
		
		byeWeekPanel.add(new JLabel("Week 9",JLabel.RIGHT));
		byeWeekPanel.add(new JLabel(wk9,JLabel.RIGHT));
		
		byeWeekPanel.add(new JLabel("Week 10",JLabel.RIGHT));
		byeWeekPanel.add(new JLabel(wk10,JLabel.RIGHT));
		
		byeWeekPanel.add(new JLabel("Week 11",JLabel.RIGHT));
		byeWeekPanel.add(new JLabel(wk11,JLabel.RIGHT));
	}
	
	/**
	 * Init Settings
	 */
	private void initSettings() {
		nameField.setText(configuration.getLeagueName());
		urlField.setText(configuration.getLeagueUrl());
		maxTeamsComboBox.setSelectedItem(configuration.getMaxTeams());
		maxRosterSizeComboBox.setSelectedItem(configuration.getMaxRosterSize());
		maxBudgetField.setText(configuration.getMaxBudget().toString());
		defBudgetField.setText(configuration.getDefaultBudget().toString());
		draftDateField.setValue(configuration.getDraftDate());
		
		startQbComboBox.setSelectedItem(configuration.getStartQb());
		startRbComboBox.setSelectedItem(configuration.getStartRb());
		startRbWrComboBox.setSelectedItem(configuration.getStartRbWr());
		startTeComboBox.setSelectedItem(configuration.getStartTe());
		startWrComboBox.setSelectedItem(configuration.getStartWr());
		startKComboBox.setSelectedItem(configuration.getStartK());
		startDefComboBox.setSelectedItem(configuration.getStartDef());
		
		scoreSystemPanel.setScoreSystem(configuration.getScoreSystem());
	}
	
	/**
	 * Init Field Panel
	 * 
	 */
	private void initPositionPanel() {
		positionPanel.setBorder(BorderFactory.createTitledBorder("Starting Position Configuration"));
		positionPanel.setLayout(new GridLayout(7,2,5,2));
		
		positionPanel.add(new JLabel("Starting Quarterbacks",JLabel.RIGHT));
		positionPanel.add(startQbComboBox);
		
		positionPanel.add(new JLabel("Starting Running Backs",JLabel.RIGHT));
		positionPanel.add(startRbComboBox);
		
		positionPanel.add(new JLabel("Starting Running Backs or Wide Receivers",JLabel.RIGHT));
		positionPanel.add(startRbWrComboBox);
		
		positionPanel.add(new JLabel("Starting Wide Receivers",JLabel.RIGHT));
		positionPanel.add(startWrComboBox);
		
		positionPanel.add(new JLabel("Starting Tight Ends",JLabel.RIGHT));
		positionPanel.add(startTeComboBox);
		
		positionPanel.add(new JLabel("Starting Kickers",JLabel.RIGHT));
		positionPanel.add(startKComboBox);
		
		positionPanel.add(new JLabel("Starting Defenses",JLabel.RIGHT));
		positionPanel.add(startDefComboBox);
		
		startQbComboBox.addActionListener(this);
		startRbComboBox.addActionListener(this);
		startRbWrComboBox.addActionListener(this);
		startWrComboBox.addActionListener(this);
		startTeComboBox.addActionListener(this);
		startKComboBox.addActionListener(this);
		startDefComboBox.addActionListener(this);
	}
	
	/**
	 * Init Field Panel
	 * 
	 */
	private void initFieldPanel() {
		leaguePanel.setBorder(BorderFactory.createTitledBorder("League Configuration"));
		
		leaguePanel.setLayout(new GridLayout(7,2,5,2));
		
		leaguePanel.add(new JLabel("Fantasy League Name",JLabel.RIGHT));
		leaguePanel.add(nameField);
		
		leaguePanel.add(new JLabel("Fantasy League URL",JLabel.RIGHT));
		leaguePanel.add(urlField);
		
		leaguePanel.add(new JLabel("Maximum Number Teams",JLabel.RIGHT));
		leaguePanel.add(maxTeamsComboBox);
		
		leaguePanel.add(new JLabel("Maximum Roster Size",JLabel.RIGHT));
		leaguePanel.add(maxRosterSizeComboBox);
		
		leaguePanel.add(new JLabel("Maximum Team Auction Budget",JLabel.RIGHT));
		leaguePanel.add(maxBudgetField);
		
		leaguePanel.add(new JLabel("Default Team Auction Budget",JLabel.RIGHT));
		leaguePanel.add(defBudgetField);
		
		draftDateField.setToolTipText("Sep 1 2013 11:30 AM");
		
		leaguePanel.add(new JLabel("Auction Draft Date",JLabel.RIGHT));
		leaguePanel.add(draftDateField);
	}
	
	/**
	 * Init Tool Bar Panel
	 * 
	 */
	private void initToolBarPanel() {
		saveButton.setIcon(Util.getImageIcon("save.png"));
		saveButton.addActionListener(this);

		cancelButton.setIcon(Util.getImageIcon("delete.png"));
		cancelButton.addActionListener(this);
		
		toolBarPanel.setBorder(BorderFactory.createEtchedBorder());
		toolBarPanel.setLayout(new FlowLayout(FlowLayout.LEFT,2,2));
		toolBarPanel.add(saveButton);
		toolBarPanel.add(cancelButton);
	}

	/**
	 * Action Performed
	 * 
	 * @param e ActionEvent
	 */
	public void actionPerformed(ActionEvent e) {
		if( e.getSource() == cancelButton ) {
			initSettings();
		}
		else if( e.getSource() == saveButton ) {
			if(nameField.getText().length() > 30) {
				JOptionPane.showMessageDialog(MainApp.getMainComponent(),"League name is longer than maximum of 30.","Error",JOptionPane.ERROR_MESSAGE);
				return;
			}
			
			if(urlField.getText().length() > 80) {
				JOptionPane.showMessageDialog(MainApp.getMainComponent(),"League URL is longer than maximum of 80.","Error",JOptionPane.ERROR_MESSAGE);
				return;
			}
			
			MainApp.getMainFrame().setTitle("Fantasy Football Auction Pro - " + nameField.getText());
			
			configuration.setLeagueName(nameField.getText());
			configuration.setLeagueUrl(urlField.getText());
			configuration.setMaxBudget(new Integer(maxBudgetField.getText()));
			configuration.setDefaultBudget(new Integer(defBudgetField.getText()));
			configuration.setMaxRosterSize((Integer)maxRosterSizeComboBox.getSelectedItem());
			configuration.setMaxTeams((Integer)maxTeamsComboBox.getSelectedItem());
			configuration.setDraftDate((Date)draftDateField.getValue());
			
			configuration.setStartQb((Integer)startQbComboBox.getSelectedItem());
			configuration.setStartRb((Integer)startRbComboBox.getSelectedItem());
			configuration.setStartTe((Integer)startTeComboBox.getSelectedItem());
			configuration.setStartRbWr((Integer)startRbWrComboBox.getSelectedItem());
			configuration.setStartK((Integer)startKComboBox.getSelectedItem());
			configuration.setStartDef((Integer)startDefComboBox.getSelectedItem());
			configuration.setStartWr((Integer)startWrComboBox.getSelectedItem());
			
			configuration.setScoreSystem(scoreSystemPanel.getScoreSystem());
			
			// Update database
			configurationService.updateConfiguration(configuration);
			
			// Update VBD numbers
			VbdService vbdService = ServiceFactory.getVbdService();
			vbdService.updatePlayerFantasyPoints(configuration);
			vbdService.updateAllPlayerVbd(configuration);
			
			JOptionPane.showMessageDialog(MainApp.getMainComponent(),"League configuration has been saved.","Information",JOptionPane.INFORMATION_MESSAGE);
		}
	}
	
	/**
	 * Get Configuration
	 * 
	 * @return configuration
	 */
	public Configuration getConfiguration() {
		return configuration;
	}
}
