package com.fantasy.football.auctionpro.reader;

import java.util.ArrayList;
import java.util.List;

import com.fantasy.football.auctionpro.entity.DefenseWeek;

/**
 * Defense Week File Reader
 * 
 * @date Sep 6, 2011
 * @author dhelbert
 */
public class DefenseWeekFileReader extends AbstractFileReader {

	/** Weeks */
	private List<DefenseWeek> weeks = new ArrayList<DefenseWeek>();
	
	/** Team */
	public static final int TEAM = 0;
	
	/** Week */
	public static final int WEEK = 1;
	
	/** Points */
	public static final int POINTS_ALLOWED = 2;
	
	/**
	 * Constructor
	 * 
	 * @param path
	 */
	public DefenseWeekFileReader(String path) {
		super(path);
	}

	@Override
	public void processLine(String data, int row) throws Exception {
		if(row <= 1) {
			return;
		}
		
		String[] values = data.split(getDelimiter());
		
		weeks.add(new DefenseWeek(values[TEAM],new Integer(values[WEEK]),new Integer(values[POINTS_ALLOWED])));
	}

	/**
	 * Get Weeks
	 * 
	 * @return List
	 */
	public List<DefenseWeek> getWeeks() {
		return weeks;
	}

	/**
	 * Main
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			DefenseWeekFileReader dfr = new DefenseWeekFileReader("/data/2010/defenseweek.csv");
			dfr.processFile();
			System.out.println(dfr.getWeeks());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
