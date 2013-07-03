package com.fantasy.football.auctionpro.service.impl;

import java.util.List;

import com.fantasy.football.auctionpro.Constants;
import com.fantasy.football.auctionpro.DaoFactory;
import com.fantasy.football.auctionpro.ServiceFactory;
import com.fantasy.football.auctionpro.dao.PlayerDao;
import com.fantasy.football.auctionpro.dao.PlayerDataDao;
import com.fantasy.football.auctionpro.entity.Configuration;
import com.fantasy.football.auctionpro.entity.Player;
import com.fantasy.football.auctionpro.entity.ScoreSystem;
import com.fantasy.football.auctionpro.service.ConfigurationService;
import com.fantasy.football.auctionpro.service.VbdService;
import com.fantasy.football.auctionpro.ui.PlayerUtil;

/**
 * VBD Service Implementation
 * 
 * @author Derek
 *
 */
public class VbdServiceImpl implements VbdService {

	/** Player Dao */
	private PlayerDao playerDao = DaoFactory.getPlayerDao();
	
	/** Player Data Dao*/
	private PlayerDataDao playerDataDao = DaoFactory.getPlayerDataDao();
	
	/** Configuration Service*/
	private ConfigurationService configurationService = ServiceFactory.getConfigurationService();
	
	@Override
	public void updatePlayerFantasyPoints(Configuration configuration) {
		ScoreSystem ss = configuration.getScoreSystem();
		
		List<Player> players = playerDao.getAllPlayersByPosition(Constants.QB);
		
		for(Player p : players) {
			p.getPlayerData().setFantasyPoints(PlayerUtil.getFantasyPoints(p, ss));
			p.getPlayerData().setVbd(0);
		}
		
		players = playerDao.getAllPlayersByPosition(Constants.RB);
		
		for(Player p : players) {
			p.getPlayerData().setFantasyPoints(PlayerUtil.getFantasyPoints(p, ss));
			p.getPlayerData().setVbd(0);
		}
		
		players = playerDao.getAllPlayersByPosition(Constants.WR);
		
		for(Player p : players) {
			p.getPlayerData().setFantasyPoints(PlayerUtil.getFantasyPoints(p, ss));
			p.getPlayerData().setVbd(0);
		}
		
		players = playerDao.getAllPlayersByPosition(Constants.TE);
		
		for(Player p : players) {
			p.getPlayerData().setFantasyPoints(PlayerUtil.getFantasyPoints(p, ss));
			p.getPlayerData().setVbd(0);
		}
		players = playerDao.getAllPlayersByPosition(Constants.K);
		
		for(Player p : players) {
			p.getPlayerData().setFantasyPoints(PlayerUtil.getFantasyPoints(p, ss));
			p.getPlayerData().setVbd(0);
		}
	}
	
	@Override
	public int updatePlayerVbd(Configuration configuration, String position) {
		int slot = configuration.getMaxTeams();
		
		if( Constants.QB.equals(position) ) {
			slot *= configuration.getStartQb();
		}
		else if( Constants.RB.equals(position) ) {
			slot *= (configuration.getStartRb() + configuration.getStartRbWr());
		}
		else if( Constants.WR.equals(position) ) {
			slot *= (configuration.getStartWr() + configuration.getStartRbWr());
		}
		else if( Constants.TE.equals(position) ) {
			slot *= configuration.getStartTe();
		}
		else if( Constants.DEF.equals(position) ) {
			slot *= configuration.getStartDef();
		}
		else if( Constants.K.equals(position) ) {
			slot *= configuration.getStartK();
		}
		else {
			throw new IllegalArgumentException("Position should not be null.");
		}
		
		List<Player> players = playerDao.getAllPlayersByPosition(position);
		
		int fantasyPoints = players.get(slot).getPlayerData().getFantasyPoints();
		int currentPoints = 0;
		int totalPoints = 0;
		
		for(int i=0; i<slot; i++) {
			currentPoints =  players.get(i).getPlayerData().getFantasyPoints();
			
			players.get(i).getPlayerData().setVbd(currentPoints - fantasyPoints);
			
			totalPoints += currentPoints - fantasyPoints;
			
			playerDataDao.update(players.get(i).getPlayerData());
			
			System.out.println(players.get(i).getName() + " vbd= " + (currentPoints - fantasyPoints));
		}
		
		return totalPoints;
	}
	
	@Override
	public void updateAllPlayerVbd(Configuration configuration) {
		int grandTotal = updatePlayerVbd(configuration,Constants.QB) + updatePlayerVbd(configuration,Constants.RB) + updatePlayerVbd(configuration,Constants.WR) + updatePlayerVbd(configuration,Constants.TE) + updatePlayerVbd(configuration,Constants.K);
		
		configuration.setAvailablePoints(grandTotal);
		
		int dispInc = configuration.getMaxTeams() * configuration.getDefaultBudget();
		
		dispInc -= configuration.getMaxTeams() * configuration.getMaxRosterSize();

		configuration.setDisposableCash(dispInc);
		
		configurationService.updateConfiguration(configuration);
	}
}
