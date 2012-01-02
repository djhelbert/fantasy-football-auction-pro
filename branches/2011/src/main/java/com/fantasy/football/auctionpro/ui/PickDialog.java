package com.fantasy.football.auctionpro.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import com.fantasy.football.auctionpro.MainApp;
import com.fantasy.football.auctionpro.Constants;
import com.fantasy.football.auctionpro.DaoFactory;
import com.fantasy.football.auctionpro.ServiceFactory;
import com.fantasy.football.auctionpro.dao.PlayerDao;
import com.fantasy.football.auctionpro.entity.Configuration;
import com.fantasy.football.auctionpro.entity.Player;
import com.fantasy.football.auctionpro.entity.Team;
import com.fantasy.football.auctionpro.service.ConfigurationService;
import com.fantasy.football.auctionpro.service.TeamService;
import com.fantasy.football.auctionpro.ui.model.PlayerTableModel;

/**
 * Pick Panel
 * 
 * @author dhelbert
 *
 */
public class PickDialog extends JDialog implements ActionListener {

	/** Serial Version UID */
	private static final long serialVersionUID = 5779305917596669078L;
	
	/** Alert Panel */
	private JPanel alertPanel = new JPanel();
	
	/** Player Panel */
	private JPanel playerPanel = new JPanel();

	/** Button Panel */
	private JPanel buttonPanel = new JPanel();

	/** Button Panel */
	private JPanel upDownPanel = new JPanel();

	/** OK Button */
	private JButton upButton = new JButton();
	
	/** OK Button */
	private JButton downButton = new JButton();
	
	/** OK Button */
	private JButton okButton = new JButton("OK");
	
	/** Cancel Button */
	private JButton cancelButton = new JButton("CANCEL");
	
	/** Amount Box */
	private JComboBox amountBox = new JComboBox();

	/** Team Box */
	private JComboBox teamBox = new JComboBox();
	
	/** Player DAO */
	private PlayerDao playerDao = DaoFactory.getPlayerDao();

	/** Team Service */
	private TeamService teamService = ServiceFactory.getTeamService();

	/** Configuration Service */
	private ConfigurationService configurationService = ServiceFactory.getConfigurationService();

	/** Player Table Model */
	private PlayerTableModel playerTableModel;
	
	/** Configuration */
	private Configuration configuration;
	
	/** Player */
	private Player player;
	
	/** Default Team */
	private Team team;
	
	/** Team Balance */
	private int teamBalance = 0;
	
	/**
	 * Constructor
	 * 
	 * @param configuration
	 * @param player
	 * @param team
	 * @param teamDao
	 */
	public PickDialog(Player player, Team team, PlayerTableModel playerTableModel) {
		super(MainApp.getMainFrame(),"Add Team Player",true);
		
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		this.configuration    = configurationService.getConfiguration();
		
		this.player           = player;
		this.team             = team;
		this.playerTableModel = playerTableModel;
		
		if( team != null ) {
			teamBalance = team.getBudget() - team.getSpent() - (configuration.getMaxRosterSize() - team.getTeamPlayers().size());
			
			System.out.println(teamBalance);
			
			if( team != null && teamBalance >= amountBox.getSelectedIndex()) {
				amountBox.setForeground(Color.BLACK);
			}
			else {
				amountBox.setForeground(Color.RED);
			}
		}
		
		// Init panels
		initAlertPanel();
		initPlayerPanel();
		initButtonPanel();
		
		// Init container
		initPickPanel();
		pack();

		Util.centerComponent(this);
	}
	
	/**
	 * Init Button Panel
	 */
	private void initButtonPanel() {
		buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER,5,5));
		buttonPanel.add(okButton);
		buttonPanel.add(cancelButton);
		
		okButton.addActionListener(this);
		cancelButton.addActionListener(this);
		
		okButton.setIcon(Util.getImageIcon("check.png"));
		cancelButton.setIcon(Util.getImageIcon("delete.png"));
	}
	
	/**
	 * Init Pick Panel
	 */
	private void initPickPanel() {
		Container cont = getContentPane();
		
		cont.setLayout(new BorderLayout(5,5));
		cont.add(alertPanel,BorderLayout.NORTH);
		cont.add(playerPanel,BorderLayout.CENTER);
		cont.add(buttonPanel,BorderLayout.SOUTH);
	}

	/**
	 * Init Player Panel
	 */
	private void initPlayerPanel() {
		int max = configuration.getMaxBudget() - configuration.getMaxRosterSize();
		
		Integer[] values = new Integer[max];
		
		for(int i = 1; i <= max; i++) {
			values[i-1] = i;
		}
		
		amountBox = new JComboBox(values);
		amountBox.addActionListener(this);
		
		Object[] teams = teamService.getAllTeams().toArray();
		
		teamBox = new JComboBox(teams);
		
		playerPanel.setBorder(BorderFactory.createTitledBorder(getLabelString(player.getPosition())));
		playerPanel.setLayout(new GridLayout(8,2,2,2));
		
		playerPanel.add(new JLabel("Player Name"));
		playerPanel.add(new JLabel(player.getName()));

		playerPanel.add(new JLabel("2010 Player Statistics"));
		playerPanel.add(new JLabel(player.getPlayerData().toString()));
		
		playerPanel.add(new JLabel("Bye Week"));
		playerPanel.add(new JLabel(player.getByeWeek().toString()));

		playerPanel.add(new JLabel("Player Rank"));
		playerPanel.add(new JLabel(player.getRank().toString()));
		
		int estValue = 1 + Math.round(configuration.getDisposableCash().floatValue() / configuration.getAvailablePoints().floatValue() * player.getPlayerData().getVbd().floatValue());
		
		playerPanel.add(new JLabel("VBD Player Value"));
		playerPanel.add(new JLabel( "$" + estValue));
		
		playerPanel.add(new JLabel("Team"));
		playerPanel.add(teamBox);
		
		playerPanel.add(new JLabel("Amount"));
		playerPanel.add(amountBox);
		
		upDownPanel.setLayout(new FlowLayout(FlowLayout.CENTER,2,2) );
		
		upButton.setIcon(Util.getImageIcon("up.png"));
		upButton.addActionListener(this);
		downButton.setIcon(Util.getImageIcon("down.png"));
		downButton.addActionListener(this);
		
		upDownPanel.add(upButton);
		upDownPanel.add(downButton);
		
		playerPanel.add(new JLabel(""));
		playerPanel.add(upDownPanel);
	}
	
	/**
	 * Get Label String
	 * 
	 * @param position
	 * 
	 * @return String
	 */
	private String getLabelString(String position) {
		if(Constants.QB.equals(position)) {
			return "Quarterback";
		}
		else if(Constants.RB.equals(position)) {
			return "Running Back";
		}
		else if(Constants.TE.equals(position)) {
			return "Tight End";
		}
		else if(Constants.K.equals(position)) {
			return "Kicker";
		}
		else if(Constants.WR.equals(position)) {
			return "Wide Receiver";
		}
		else {
			return "Defense";
		}
	}
	
	/**
	 * Init Pick Panel
	 */
	private void initAlertPanel() {
		if( team != null ) {
			List<String> alerts = new ArrayList<String>();
			
			ImageIcon icon = Util.getImageIcon("warning.png");
			
			if( configuration.getMaxRosterSize() <= team.getTeamPlayers().size() ) {
				alerts.add("Team roster is already full.");
			}
			
			int slots = 0;
			
			if( Constants.QB.equals(player.getPosition()) ) {
				slots = configuration.getStartQb();
			}
			else if( Constants.RB.equals(player.getPosition()) ) {
				slots = configuration.getStartRb();
			}
			else if( Constants.WR.equals(player.getPosition()) ) {
				slots = configuration.getStartWr();
			}
			else if( Constants.TE.equals(player.getPosition()) ) {
				slots = configuration.getStartTe();
			}
			else if( Constants.DEF.equals(player.getPosition()) ) {
				slots = configuration.getStartDef();
			}
			else if( Constants.K.equals(player.getPosition()) ) {
				slots = configuration.getStartK();
			}
			
			if( Constants.RB.equals(player.getPosition()) || Constants.WR.equals(player.getPosition())) {
				slots += configuration.getStartRbWr();
			}
			
			int posCnt = PlayerUtil.getPositionCount(team.getTeamPlayers(), player.getPosition());
			
			if( posCnt < slots ) {
				alerts.add(player.getPosition() + " is an open roster position.");
			}
			
			int byeWeekCnt = PlayerUtil.getByeWeekCount(team.getTeamPlayers(), player.getByeWeek());
			
			if( byeWeekCnt > 0) {
				alerts.add("Team has " + byeWeekCnt + " players with the same bye week." );
			}
			
			int balance = team.getBudget() - team.getSpent() - (configuration.getMaxRosterSize() - team.getTeamPlayers().size() - 1);
			
			if( balance > 0 ) {
				alerts.add("Up to $" + balance + " can be bid on this player");
			}
			
			alertPanel.setLayout(new GridLayout(alerts.size(),1,5,5));
			alertPanel.setBorder(BorderFactory.createTitledBorder(team.getName() + " Roster Alerts"));
			
			JLabel alertLabel = null;
			
			for(String warning : alerts ) {
				alertLabel = new JLabel(warning,icon,JLabel.LEFT);
				alertPanel.add(alertLabel);
			}
		}
	}

	/**
	 * Action Performed
	 * 
	 * @param e ActionEvent
	 */
	public void actionPerformed(ActionEvent e) {
		if( e.getSource() == upButton ) {
			if(amountBox.getSelectedIndex() < amountBox.getModel().getSize()) {
				amountBox.setSelectedIndex(amountBox.getSelectedIndex()+1);
				
				if( team != null && teamBalance >= amountBox.getSelectedIndex()) {
					amountBox.setForeground(Color.BLACK);
				}
				else {
					amountBox.setForeground(Color.RED);
				}
			}
		}
		else if( e.getSource() == downButton ) {
			if(amountBox.getSelectedIndex() > 0) {
				amountBox.setSelectedIndex(amountBox.getSelectedIndex()-1);
				
				if( team != null && teamBalance >= amountBox.getSelectedIndex()) {
					amountBox.setForeground(Color.BLACK);
				}
				else {
					amountBox.setForeground(Color.RED);
				}
			}
		}
		else if( e.getSource() == amountBox ) {
			if( team != null && teamBalance >= amountBox.getSelectedIndex()) {
				amountBox.setForeground(Color.BLACK);
			}
			else {
				amountBox.setForeground(Color.RED);
			}
		}
		else if( e.getSource() == okButton ) {
			Team         selectedTeam  = (Team)teamBox.getSelectedItem();
			List<Player> teamPlayers   = playerDao.getPlayersByTeam(selectedTeam);
			
			// Calculate spent
			int spent = 0;
			
			for(Player pp : teamPlayers) {
				spent += pp.getPrice();
			}
			
			// Calculate available balance
			int balance = selectedTeam.getBudget() - spent - (configuration.getMaxRosterSize() - teamPlayers.size() - 1);
			
			if( configuration.getMaxRosterSize() <= teamPlayers.size() ) {
				JOptionPane.showMessageDialog(playerPanel, "Team roster is already full.", "Error", JOptionPane.ERROR_MESSAGE);
			}
			else if( balance < (Integer)amountBox.getSelectedItem() ) {
				JOptionPane.showMessageDialog(playerPanel, "Team balance is not high enough to add player.", "Error", JOptionPane.ERROR_MESSAGE);
			}
			else {
				// Update Team
				player = teamService.addTeamPlayer(selectedTeam,player,(Integer)amountBox.getSelectedItem(),teamPlayers.size()+1);
				
				// Refresh player table
				playerTableModel.refresh();
				
				// Refresh owner table
				FootballFrame.getTeamOwnerPanel().refresh(selectedTeam);
				
				// Refresh team player panel
				FootballFrame.getTeamPanel().refreshTeamPlayerPanel(selectedTeam);
				
				// Close
			    setVisible(false); 
			    dispose(); 
			}
		}
		else if( e.getSource() == cancelButton ) {
			// Close
		    setVisible(false); 
		    dispose(); 
		}
	}
	
}
