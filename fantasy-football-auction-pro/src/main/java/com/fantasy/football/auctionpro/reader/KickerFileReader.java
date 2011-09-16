package com.fantasy.football.auctionpro.reader;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import com.fantasy.football.auctionpro.Constants;
import com.fantasy.football.auctionpro.entity.Player;
import com.fantasy.football.auctionpro.entity.PlayerData;
import com.fantasy.football.auctionpro.ui.PlayerUtil;

/**
 * Kickers File Reader
 * 
 * @author Derek
 *
 */
public class KickerFileReader extends AbstractFileReader {
	
	/** Rank */
	private static final int RANK = 0;
	
	/** Name */
	private static final int NAME = 1;
	
	/** Team */
	private static final int TEAM = 2;
	
	/** Extra Point */
	private static final int XP = 19;
	
	/** FG 0-19 */
	private static final int FG0_19 = 6;
	
	/** FG 20-29 */
	private static final int FG20_29 = 8;
	
	/** FG 30-39 */
	private static final int FG30_39 = 10;
	
	/** FG 40-49 */
	private static final int FG40_49 = 12;
	
	/** FG 50+ */
	private static final int FG50PLUS = 14;
	
	/** Defenses */
	private List<Player> players = new ArrayList<Player>();
	
	/**
	 * Constructor
	 * 
	 * @param path
	 * @throws FileNotFoundException
	 */
	public KickerFileReader(String path) throws FileNotFoundException {
		super(path);
	}
	
	@Override
	public void processLine(String data, int row) throws Exception {
		if(row <= 1) {
			return;
		}
		
		String[] values = data.split(getDelimiter());
		
		Player p = new Player(values[NAME].toUpperCase(),Constants.K,values[TEAM]);
		p.setRank(new Integer(values[RANK]));
		
		PlayerData pd = new PlayerData();
		
		pd.setFieldGoalZeroNineteen(new Integer(values[FG0_19]));
		pd.setFieldGoalTwentyTwentyNine(new Integer(values[FG20_29]));
		pd.setFieldGoalThirtyThirtyNine(new Integer(values[FG30_39]));
		pd.setFieldGoalFortyFortyNine(new Integer(values[FG40_49]));
		pd.setFieldGoalFifty(new Integer(values[FG50PLUS]));
		pd.setExtraPoint(new Integer(values[XP]));
		
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
			KickerFileReader kfr = new KickerFileReader("/data/2010/kickers.csv");
			kfr.processFile();
			
			System.out.println(kfr.getPlayers());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
