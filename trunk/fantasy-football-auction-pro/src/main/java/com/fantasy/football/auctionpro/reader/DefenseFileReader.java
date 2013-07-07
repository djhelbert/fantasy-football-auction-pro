package com.fantasy.football.auctionpro.reader;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import com.fantasy.football.auctionpro.Constants;
import com.fantasy.football.auctionpro.entity.Player;
import com.fantasy.football.auctionpro.entity.PlayerData;
import com.fantasy.football.auctionpro.ui.PlayerUtil;

/**
 * Defense File Reader
 * 
 * @author Derek
 *
 */
public class DefenseFileReader extends AbstractFileReader {
	
	/** Rank*/
	public static int RANK = 0;
	
	/** Name */
	public static int NAME = 1;
	
	/** Team */
	public static int TEAM = 2;
	
	/** PTS */
	public static int POINTS = 9;
	
	/** Sack */
	public static int SACK = 10;
	
	/** Safety */
	public static int SAFETY = 11;
	
	/** DEF INT */
	public static int DEF_INT = 12;
	
	/** Fumbles */
	public static int FR = 13;

	/** DEF TD */
	public static int DEF_TD = 14;
	
	/** Blocked */
	public static int BLOCKED_KICK = 15;
	
	/** Defenses */
	private List<Player> players = new ArrayList<Player>();
	
	/**
	 * Constructor
	 * 
	 * @param path
	 * @throws FileNotFoundException
	 */
	public DefenseFileReader(String path) throws FileNotFoundException {
		super(path);
	}
	
	@Override
	public void processLine(String data, int row) throws Exception {
		if(row <= 1) {
			return;
		}
		
		String[] values = data.split(getDelimiter());
		
		Player pl = new Player(values[NAME].toUpperCase(),Constants.DEF,values[TEAM]);
		pl.setRank(new Integer(values[RANK]));
		
		PlayerData pd = new PlayerData();
		pd.setSack(new Integer(values[SACK]));
		pd.setSafety(new Integer(values[SAFETY]));
		pd.setBlockedKick(new Integer(values[BLOCKED_KICK]));
		pd.setDefenseInt(new Integer(values[DEF_INT]));
		pd.setDefenseTd(new Integer(values[DEF_TD]));
		pd.setPointsAllowed(new Integer(values[POINTS]));
		pd.setFumbleRecovery(new Integer(values[FR]));
		
		// Set player data
		pl.setPlayerData(pd);
		
		// Set fantasy points
		pd.setFantasyPoints( PlayerUtil.getDefenseFantasyPoints(pl, scoreSystem) );
		
		players.add(pl);
	}
	
	/**
	 * Get Defenses
	 * 
	 * @return
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
			DefenseFileReader dfr = new DefenseFileReader("/data/" + Constants.YEAR + "/defense.csv");
			dfr.processFile();
			System.out.println(dfr.getPlayers());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
