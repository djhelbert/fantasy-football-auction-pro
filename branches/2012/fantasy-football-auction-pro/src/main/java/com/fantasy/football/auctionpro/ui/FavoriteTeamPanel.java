package com.fantasy.football.auctionpro.ui;

import java.awt.FlowLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

import com.fantasy.football.auctionpro.Constants;
import com.fantasy.football.auctionpro.ServiceFactory;
import com.fantasy.football.auctionpro.entity.Team;
import com.fantasy.football.auctionpro.service.TeamService;

/**
 * Favorite Team Panel
 * 
 * @author dhelbert
 *
 */
public class FavoriteTeamPanel extends JPanel {

	/** Serial Version UID */
	private static final long serialVersionUID = 8906910176528769983L;

	/** Team Service */
	private TeamService teamService = ServiceFactory.getTeamService();
	
	/** Text Label */
	private JLabel textLabel;
	
	/**
	 * Constructor
	 */
	public FavoriteTeamPanel() {
		textLabel = new JLabel(Util.getImageIcon("favorite.png"));
		
		setOpaque(false);
		
		setLayout(new FlowLayout(FlowLayout.LEFT));
		add(textLabel);
		
		refresh();
	}
	
	/**
	 * Refresh
	 */
	public void refresh() {
		Team t = teamService.getFavoriteTeam();
		
		if( t != null ) {
			String temp = t.getName();
			
			int qb = PlayerUtil.getPositionCount(t.getTeamPlayers(), Constants.QB);
			int wr = PlayerUtil.getPositionCount(t.getTeamPlayers(), Constants.WR);
			int rb = PlayerUtil.getPositionCount(t.getTeamPlayers(), Constants.RB);
			int te = PlayerUtil.getPositionCount(t.getTeamPlayers(), Constants.TE);
			int k  = PlayerUtil.getPositionCount(t.getTeamPlayers(), Constants.K);
			int df = PlayerUtil.getPositionCount(t.getTeamPlayers(), Constants.DEF);
			
			textLabel.setText(temp + " QB " + qb + " RB " + rb + " WR " + wr + " TE " + te + " DEF " + df + " K " + k);
			textLabel.setVisible(true);
		}
		else {
			textLabel.setVisible(false);
		}
	}
}
