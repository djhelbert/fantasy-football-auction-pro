package com.fantasy.football.auctionpro.ui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.fantasy.football.auctionpro.MainApp;
import com.fantasy.football.auctionpro.Constants;
import com.fantasy.football.auctionpro.DaoFactory;
import com.fantasy.football.auctionpro.ServiceFactory;
import com.fantasy.football.auctionpro.dao.PlayerDao;
import com.fantasy.football.auctionpro.entity.Player;
import com.fantasy.football.auctionpro.entity.PlayerData;
import com.fantasy.football.auctionpro.service.ConfigurationService;

/**
 * Add Player Dialog
 * 
 * @author Derek
 *
 */
public class AddPlayerDialog extends JDialog implements ActionListener {

	/** Serial Version UID */
	private static final long serialVersionUID = -9188357441399615478L;

	/** OK Button */
	private JButton okButton = new JButton("OK");
	
	/** Cancel Button */
	private JButton cancelButton = new JButton("CANCEL");
	
	/** Button Panel */
	private JPanel buttonPanel = new JPanel();
	
	/** Add Panel */
	private JPanel addPlayerPanel = new JPanel();
	
	/** Position Box */
	private JComboBox<String> positionBox = new JComboBox<String>(new String[] {Constants.QB,Constants.RB,Constants.WR,Constants.TE,Constants.K});
	
	/** Name Field */
	private JTextField nameField = new JTextField(30);
	
	/** New Player */
	private Player player = new Player();
	
	/** Player DAO */
	protected PlayerDao playerDao = DaoFactory.getPlayerDao();

	/** Configuration Service */
	private ConfigurationService configurationService = ServiceFactory.getConfigurationService();
	
	/** Team Box */
	private JComboBox<String> teamBox;
	
	/**
	 * Add Player Dialog
	 * 
	 * @param frame
	 */
	public AddPlayerDialog() {
		super(MainApp.getMainFrame(),"Add Team Player",true);
		
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		List<String> teams = new ArrayList<String>();
		
		try {
			Map<String,Integer> map = configurationService.getByeWeekMap();
			
			List<String> tmList = new ArrayList<String>(map.keySet()); 
			Collections.sort(tmList);
			
			for(String team : tmList) {
				teams.add(team);
			}
			
			teamBox = new JComboBox<String>(teams.toArray(new String[0]));
		} 
		catch (Exception e) {
			e.printStackTrace();
		    setVisible(false); 
		    dispose(); 
		}
		
		initAddPlayerPanel();
		initButtonPanel();
		initContentPanel();
		
		pack();
		
		Util.centerComponent(this);
	}

	/**
	 * Init Player Panel
	 */
	private void initAddPlayerPanel() {		
		addPlayerPanel.setBorder(BorderFactory.createTitledBorder("New Player"));
		addPlayerPanel.setLayout(new GridLayout(3,2,10,10));
		
		addPlayerPanel.add(new JLabel("Name"));
		addPlayerPanel.add(nameField);

		addPlayerPanel.add(new JLabel("Position"));
		addPlayerPanel.add(positionBox);

		addPlayerPanel.add(new JLabel("Team"));
		addPlayerPanel.add(teamBox);
	}
	
	/**
	 * Init Button Panel
	 */
	private void initButtonPanel() {
		buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
		buttonPanel.add(okButton);
		buttonPanel.add(cancelButton);
		
		okButton.addActionListener(this);
		cancelButton.addActionListener(this);
		
		okButton.setIcon(Util.getImageIcon("check.png"));
		cancelButton.setIcon(Util.getImageIcon("delete.png"));
	}
	
	/**
	 * Init Add Panel
	 */
	private void initContentPanel() {
		Container cont = getContentPane();
		
		cont.setLayout(new BorderLayout(5,5));
		cont.add(addPlayerPanel,BorderLayout.CENTER);
		cont.add(buttonPanel,BorderLayout.SOUTH);
	}
	
	/**
	 * Action Performed
	 * 
	 * @param e ActionEvent
	 */
	public void actionPerformed(ActionEvent e) {
		if( e.getSource() == okButton ) {
			if(nameField.getText().length() <= 0) {
				JOptionPane.showMessageDialog(MainApp.getMainComponent(),"Name is a required value.","Error",JOptionPane.ERROR_MESSAGE);
				return;
			}
			if(nameField.getText().length() > 30) {
				JOptionPane.showMessageDialog(MainApp.getMainComponent(),"Name length is longer than maxiumum of 30.","Error",JOptionPane.ERROR_MESSAGE);
				return;
			}
			
			player.setName(nameField.getText().toUpperCase());
			player.setNflTeam(teamBox.getSelectedItem().toString());
			player.setPosition(positionBox.getSelectedItem().toString());
			player.setPlayerData(new PlayerData());
			
			List<Player> posList = playerDao.getAllPlayersByPosition(player.getPosition());
			
			player.setRank(posList.size()+1);
			
			try {
				Map<String,Integer> map = configurationService.getByeWeekMap();
				
				player.setByeWeek(map.get(player.getNflTeam()));
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			
			// Create player
			playerDao.create(player);
			
			// Refresh player panel
			TeamPanel.getPlayerPanel().refreshTable(player.getPosition());
			
			// Show dialog
			JOptionPane.showMessageDialog(MainApp.getMainComponent(),"New player has been added.","Information",JOptionPane.INFORMATION_MESSAGE);
			
			// Close
		    setVisible(false); 
		    dispose(); 
		}
		else {
			// Close
		    setVisible(false); 
		    dispose(); 
		}
	}
}
