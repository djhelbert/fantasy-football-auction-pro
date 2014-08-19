package com.fantasy.football.auctionpro.reader;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import com.fantasy.football.auctionpro.Constants;
import com.fantasy.football.auctionpro.entity.Player;
import com.fantasy.football.auctionpro.entity.PlayerData;
import com.fantasy.football.auctionpro.ui.PlayerUtil;

/**
 * RB File Reader
 * 
 * @author Derek
 *
 */
public class RunningBackFileReader extends AbstractFileReader {
	
	/** Rank */
	public static final int RANK = 0;
	
	/** First Name */
	private static final int FIRSTNAME = 1;

	/** Last Name */
	private static final int LASTNAME = 2;
	
	/** Team */
	public static final int TEAM = 3;
	
	/** Rush Yards */
	public static final int RUSHYD = 6;
	
	/** Rush TD */
	public static final int RUSHTD = 9;
	
	/** Receptions */
	public static final int REC = 10;
	
	/** REC YDS */
	public static final int RECYDS = 12;
	
	/** REC TD */
	public static final int RECTD = 18;
	
	/** Fumbles */
	public static final int FL = 20;
	
	/** Players */
	private List<Player> players = new ArrayList<Player>();
	
	/**
	 * Constructor
	 * 
	 * @param path
	 */
	public RunningBackFileReader(String path) throws FileNotFoundException {
		super(path);
	}
	
	@Override
	public void processLine(String data, int row) throws Exception {
		if(row <= 1) {
			return;
		}
		
		String[] values = data.split(getDelimiter());
		
		String name = values[FIRSTNAME] + " " + values[LASTNAME];
		
		Player p = new Player(name.toUpperCase(),Constants.RB,values[TEAM]);
		p.setRank(new Integer(values[RANK]));
		
		PlayerData pd = new PlayerData();
		
		pd.setRushingYards(new Integer(values[RUSHYD]));
		pd.setRushingTd(new Integer(values[RUSHTD]));
		pd.setReception(new Integer(values[REC]));
		pd.setReceivingYards(new Integer(values[RECYDS]));
		pd.setReceptionTd(new Integer(values[RECTD]));
		pd.setFumbleLost(new Integer(values[FL]));
		
		// Set player data
		p.setPlayerData(pd);
		
		// Set fantasy points
		pd.setFantasyPoints(PlayerUtil.getFantasyPoints(p, scoreSystem));
		
		players.add(p);
	}

	/**
	 * Get Players
	 * 
	 * @return List
	 */
	public List<Player> getPlayers() {
		return players;
	}

	/**
	 * Main
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			RunningBackFileReader rfr = new RunningBackFileReader("/data/" + Constants.YEAR + "/runningbacks.csv");
			rfr.processFile();
			
			System.out.println(rfr.getPlayers());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
