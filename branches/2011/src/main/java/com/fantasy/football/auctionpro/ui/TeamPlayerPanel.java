package com.fantasy.football.auctionpro.ui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;

import com.fantasy.football.auctionpro.MainApp;
import com.fantasy.football.auctionpro.DaoFactory;
import com.fantasy.football.auctionpro.ServiceFactory;
import com.fantasy.football.auctionpro.dao.PlayerDao;
import com.fantasy.football.auctionpro.entity.Configuration;
import com.fantasy.football.auctionpro.entity.Player;
import com.fantasy.football.auctionpro.entity.Team;
import com.fantasy.football.auctionpro.service.ConfigurationService;
import com.fantasy.football.auctionpro.service.TeamService;

/**
 * Team Player Panel
 * 
 * @author dhelbert
 *
 */
public class TeamPlayerPanel extends JPanel implements MouseListener, ActionListener {

	/** Serial Version UID */
	private static final long serialVersionUID = -977823806057818126L;

	/** Player List */
	private JList playerList;
	
	/** Team List Model */
	private TeamListModel teamListModel = new TeamListModel();
	
	/** Name Label */
	private JLabel nameLabel = new JLabel(" ");
	
	/** Amount Label */
	private JLabel amountLabel = new JLabel(" ");
	
	/** Configuration Service */
	private ConfigurationService configurationService = ServiceFactory.getConfigurationService();
	
	/** Team Service */
	private TeamService teamService = ServiceFactory.getTeamService();
	
	/** Player DAO */
	private PlayerDao playerDao = DaoFactory.getPlayerDao();
	
	/** Pop Up Menu */
	private JPopupMenu popupMenu;
	
	/** Remove Item */
	private JMenuItem removeItem;
	
	/** Update Item */
	private JMenuItem updateItem;
	
	/** Team */
	private Team team;
	
	/**
	 * Constructor
	 */
	public TeamPlayerPanel(Team team) {
		this();
		setTeam(team);
	}
	
	/**
	 * Constructor
	 * 
	 */
	public TeamPlayerPanel() {
		playerList = new JList(teamListModel);
		playerList.setCellRenderer(new CustomCellRenderer());
		playerList.setVisibleRowCount(18);
		playerList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		playerList.addMouseListener(this);
		
		popupMenu = new JPopupMenu();
		
		removeItem = new JMenuItem("Remove");
		updateItem = new JMenuItem("Update");
		
		removeItem.setIcon(Util.getImageIcon("minus.png"));
		updateItem.setIcon(Util.getImageIcon("undo.png"));
		
		popupMenu.add(removeItem);
		popupMenu.add(new JPopupMenu.Separator());
		popupMenu.add(updateItem);
		
		removeItem.addActionListener(this);
		updateItem.addActionListener(this);
		
		// Set layout
		setLayout(new BorderLayout());
		add(nameLabel, BorderLayout.NORTH);
		add(new JScrollPane(playerList), BorderLayout.CENTER);
		add(amountLabel, BorderLayout.SOUTH);
		
		setVisible(false);
	}
	
	/**
	 * Refresh Data
	 * 
	 * @param team
	 */
	public void setTeam(Team team) {
		this.team = team;
		
		playerList.clearSelection();
		
		if( team != null ) {
			nameLabel.setText(team.getName());
			amountLabel.setText("[" + team.getTeamPlayers().size() + "/" + configurationService.getConfiguration().getMaxRosterSize() + "] $" + new Integer(team.getBudget() - team.getSpent()).toString());
			teamListModel.setPlayers(team.getTeamPlayers());
			setVisible(true);
		}
		else {
			nameLabel.setText(" ");
			amountLabel.setText(" ");
			teamListModel.clear();
			setVisible(false);
		}
	}

	/**
	 * Get Panel Team
	 * 
	 * @return Team
	 */
	public Team getTeamPlayerPanelTeam() {
		return team;
	}

	/**
	 * Mouse Clicked
	 * 
	 * @param e MouseEvent
	 */
	public void mouseClicked(MouseEvent e) {
       if (SwingUtilities.isRightMouseButton(e) && !playerList.isSelectionEmpty() && playerList.locationToIndex(e.getPoint()) == playerList.getSelectedIndex()) {
    	   popupMenu.show(playerList, e.getX(), e.getY());
       }
	}

	/**
	 * Mouse Pressed
	 * 
	 * @param e MouseEvent
	 */
	public void mousePressed(MouseEvent e) {
	}

	/**
	 * Mouse Released
	 * 
	 * @param e MouseEvent
	 */
	public void mouseReleased(MouseEvent e) {
	}

	/**
	 * Mouse Entered
	 * 
	 * @param e MouseEvent
	 */
	public void mouseEntered(MouseEvent e) {
	}

	/**
	 * Mouse Exited
	 * 
	 * @param e MouseEvent
	 */
	public void mouseExited(MouseEvent e) {
	}

	/**
	 * Action Performed
	 * 
	 * @param e ActionEvent
	 */
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == removeItem) {
			removeAction(playerList.getSelectedIndex(), (Player) playerList.getSelectedValue());
		}
		else if(e.getSource() == updateItem) {
			updateAction(playerList.getSelectedIndex(), (Player) playerList.getSelectedValue());
		}
	}

	/**
	 * Update Action
	 * 
	 * @param index
	 * @param player
	 */
	private void updateAction(int index, Player player) {
		Configuration configuration = configurationService.getConfiguration();
		
		int max = configuration.getMaxBudget() - configuration.getMaxRosterSize();
		
		Integer[] values = new Integer[max];
		
		for(int i = 1; i <= max; i++) {
			values[i-1] = i;
		}
		
		Integer price = (Integer)JOptionPane.showInputDialog( MainApp.getMainFrame(),"Select new auction price amount.",
		                    "Update Amount",JOptionPane.PLAIN_MESSAGE,null,values,player.getPrice());
		
		if( price == null ) {
			return;
		}
		else {
			// Update price
			player.setPrice(price);
			
			// Update model
			playerDao.update(player);
			
			// Clear team owner panel
			FootballFrame.getTeamOwnerPanel().refresh(team);
			
			// Refresh team player panel
			FootballFrame.getTeamPanel().refreshTeamPlayerPanel(team);
		}
	}
	
	/**
	 * Remove Action
	 * 
	 * @param index
	 * @param player
	 */
	private void removeAction(int index, Player player) {
		// Do remove
		teamService.removeTeamPlayer(player);
		
		// Update player table
		TeamPanel.getPlayerPanel().refreshTable(player.getPosition());
		
		// Clear team owner panel
		FootballFrame.getTeamOwnerPanel().refresh(team);
		
		// Refresh team player panel
		FootballFrame.getTeamPanel().refreshTeamPlayerPanel(team);
	}
	
	/**
	 * Custom Cell Renderer
	 * 
	 * @author dhelbert
	 *
	 */
	class CustomCellRenderer extends DefaultListCellRenderer {
		
	    /** Serial Version UID */
		private static final long serialVersionUID = -677845173070061240L;

		/**
		 * Get List Cell Renderer Component
		 * 
		 * @param list
		 * @param value
		 * @param index
		 * @param selected
		 * @param hasFocus
		 */
		public Component getListCellRendererComponent(JList list, Object value, int index, boolean selected, boolean hasFocus)
	    {
	        super.getListCellRendererComponent(list, value, index, selected, hasFocus);

	        Font font = getFont();
	        
	        setFont(new Font(font.getName(),Font.PLAIN,12));
	        
			return this;
	    }
	}
}
