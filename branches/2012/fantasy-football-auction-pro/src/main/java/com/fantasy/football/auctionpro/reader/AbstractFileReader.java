package com.fantasy.football.auctionpro.reader;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import com.fantasy.football.auctionpro.entity.ScoreSystem;

/**
 * File Reader
 * 
 * @author dhelbert
 * 
 */
public abstract class AbstractFileReader {

	/** Path */
	private String path;

	/** File Delimiter */
	private String delimiter = ",";

	/** Score System */
	protected ScoreSystem scoreSystem = new ScoreSystem();
	
	/**
	 * Constructor
	 * 
	 * @param path
	 */
	public AbstractFileReader(String path) {
		this.path = path;
	}

	/**
	 * Constructor
	 * 
	 * @param path
	 * @param delimiter
	 */
	public AbstractFileReader(String path, String delimiter) {
		this(path);
		this.delimiter = delimiter;
	}

	/**
	 * Get Delimiter
	 * 
	 * @return
	 */
	public String getDelimiter() {
		return delimiter;
	}

	/**
	 * Set Delimiter
	 * 
	 * @param delimiter
	 */
	public void setDelimiter(String delimiter) {
		this.delimiter = delimiter;
	}

	/**
	 * Process File
	 * 
	 * @throws Exception
	 */
	public void processFile() throws Exception {
		InputStream is = ClassLoader.class.getResourceAsStream(path);
		BufferedReader br = new BufferedReader(new InputStreamReader(is));

		String sCurrentLine;

		int cnt = 1;

		while ((sCurrentLine = br.readLine()) != null) {
			processLine(sCurrentLine, cnt++);
		}

		br.close();
	}

	/**
	 * Process Line
	 * 
	 * @param data
	 */
	public abstract void processLine(String data, int row) throws Exception;

	/**
	 * @return the scoreSystem
	 */
	public ScoreSystem getScoreSystem() {
		return scoreSystem;
	}

	/**
	 * @param scoreSystem the scoreSystem to set
	 */
	public void setScoreSystem(ScoreSystem scoreSystem) {
		this.scoreSystem = scoreSystem;
	}

}
