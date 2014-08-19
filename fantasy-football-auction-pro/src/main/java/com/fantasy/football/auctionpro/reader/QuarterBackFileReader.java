package com.fantasy.football.auctionpro.reader;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import com.fantasy.football.auctionpro.Constants;
import com.fantasy.football.auctionpro.entity.Player;
import com.fantasy.football.auctionpro.entity.PlayerData;
import com.fantasy.football.auctionpro.ui.PlayerUtil;

/**
 * QB File Reader
 * 
 * @author Derek
 *
 */
public class QuarterBackFileReader extends AbstractFileReader {
	
	/** Rank */
	private static final int RANK = 0;
	
	/** First Name */
	private static final int FIRSTNAME = 1;

	/** Last Name */
	private static final int LASTNAME = 2;
	
	/** Team */
	private static final int TEAM = 3;
	
	/** Passing Yards */
	private static final int PASSYDS = 9;

	/** Passing TD */
	private static final int PASSTD = 12;
	
	/** Passing INT */
	private static final int PASSINT = 13;
	
	/** Rushing Yards */
	private static final int RUSHYDS = 15;

	/** Rushing TD */
	private static final int RUSHTD = 18;
	
	/** Players */
	private List<Player> players = new ArrayList<Player>();
	
	/**
	 * Constructor
	 * 
	 * @param path
	 */
	public QuarterBackFileReader(String path) throws FileNotFoundException {
		super(path);
	}
	
	@Override
	public void processLine(String data, int row) throws Exception {
		if(row <= 1) {
			return;
		}
		
		String[] values = data.split(getDelimiter());
		
		String name = values[FIRSTNAME] + " " + values[LASTNAME];
		
		Player p = new Player(name.toUpperCase(),Constants.QB,values[TEAM]);
		p.setRank(new Integer(values[RANK]));
		
		PlayerData pd = new PlayerData();
		pd.setPassingYards(new Integer(values[PASSYDS]));
		pd.setPassingTd(new Integer(values[PASSTD]));
		pd.setPassingInt(new Integer(values[PASSINT]));
		pd.setRushingTd(new Integer(values[RUSHTD]));
		pd.setRushingYards(new Integer(values[RUSHYDS]));
		
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
	 * Set Players
	 * 
	 * @param players
	 */
	public void setPlayers(List<Player> players) {
		this.players = players;
	}

	/**
	 * Main
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			QuarterBackFileReader rfr = new QuarterBackFileReader("/data/" + Constants.YEAR + "/quarterbacks.csv");
			rfr.processFile();
			
			System.out.println(rfr.getPlayers());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
