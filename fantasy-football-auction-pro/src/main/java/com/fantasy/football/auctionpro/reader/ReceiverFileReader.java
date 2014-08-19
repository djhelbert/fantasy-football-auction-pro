package com.fantasy.football.auctionpro.reader;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import com.fantasy.football.auctionpro.Constants;
import com.fantasy.football.auctionpro.entity.Player;
import com.fantasy.football.auctionpro.entity.PlayerData;
import com.fantasy.football.auctionpro.ui.PlayerUtil;

/**
 * Receiver File Reader
 * 
 * @author Derek
 *
 */
public class ReceiverFileReader extends AbstractFileReader {
	
	/** Rank */
	private static final int RANK = 0;
	
	/** First Name */
	private static final int FIRSTNAME = 1;

	/** Last Name */
	private static final int LASTNAME = 2;
	
	/** Team */
	private static final int TEAM = 3;
	
	/** Position */
	private String position = null;
	
	/** Players */
	private List<Player> players = new ArrayList<Player>();
	
	/**
	 * Constructor
	 * 
	 * @param path
	 * @throws FileNotFoundException
	 */
	public ReceiverFileReader(String path,String position) throws FileNotFoundException {
		super(path);
		
		this.position = position;
	}
	
	@Override
	public void processLine(String data, int row) throws Exception {
		if(row <= 1) {
			return;
		}
		
		String[] values = data.split(getDelimiter());
		
		String name = values[FIRSTNAME] + " " + values[LASTNAME];
		
		Player p = new Player(name.toUpperCase(),position,values[TEAM]);
		p.setRank(new Integer(values[RANK]));
		
		PlayerData pd = new PlayerData();
		pd.setReception(new Integer(values[5]));
		pd.setReceivingYards(new Integer(values[7]));
		pd.setReceptionTd(new Integer(values[13]));
		pd.setRushingYards(new Integer(values[15]));
		pd.setRushingTd(new Integer(values[17]));
		pd.setFumbleLost(new Integer(values[19]));
		pd.setReturnTd(new Integer(values[20]));
		
		// Set player data
		p.setPlayerData(pd);
		
		// Set fantasy points
		pd.setFantasyPoints(PlayerUtil.getFantasyPoints(p, scoreSystem));
		
		players.add(p);
	}

	/**
	 * Get Position
	 * 
	 * @return
	 */
	public String getPosition() {
		return position;
	}

	/**
	 * Set Position
	 * 
	 * @param position
	 */
	public void setPosition(String position) {
		this.position = position;
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
			ReceiverFileReader rfr = new ReceiverFileReader("/data/" + Constants.YEAR + "/receivers.csv",Constants.WR);
			rfr.processFile();
			
			System.out.println(rfr.getPlayers());
			
			ReceiverFileReader tefr = new ReceiverFileReader("/data/" + Constants.YEAR + "/tightends.csv",Constants.TE);
			tefr.processFile();
			
			System.out.println(tefr.getPlayers());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
