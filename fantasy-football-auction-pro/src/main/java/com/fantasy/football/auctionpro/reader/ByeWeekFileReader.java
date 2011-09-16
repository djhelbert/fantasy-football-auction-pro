package com.fantasy.football.auctionpro.reader;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;

/**
 * Kickers File Reader
 * 
 * @author Derek
 *
 */
public class ByeWeekFileReader extends AbstractFileReader {
	
	/** Week */
	public static int WEEK = 0;
	
	/** Name */
	public static int NAME = 1;
	
	/** Bye Week Map */
	private Map<String,Integer> byeWeekMap = new HashMap<String,Integer>();
	
	/**
	 * Constructor
	 * 
	 * @param path
	 * 
	 * @throws FileNotFoundException
	 */
	public ByeWeekFileReader(String path) throws FileNotFoundException {
		super(path);
	}
	
	@Override
	public void processLine(String data, int row) throws Exception {
		if(row <= 1) {
			return;
		}
		
		String[] values = data.split(getDelimiter());
		
		byeWeekMap.put(values[NAME], new Integer(values[WEEK]));
	}

	/**
	 * Get Bye Week Map
	 * 
	 * @return Map
	 */
	public Map<String, Integer> getByeWeekMap() {
		return byeWeekMap;
	}

	/**
	 * Main
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			ByeWeekFileReader bfr = new ByeWeekFileReader("/data/2010/byeweek.csv");
			bfr.processFile();
			
			System.out.println(bfr.getByeWeekMap());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
