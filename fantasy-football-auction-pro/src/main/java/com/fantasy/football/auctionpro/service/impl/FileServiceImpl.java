package com.fantasy.football.auctionpro.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.fantasy.football.auctionpro.Constants;
import com.fantasy.football.auctionpro.entity.Player;
import com.fantasy.football.auctionpro.reader.DefenseFileReader;
import com.fantasy.football.auctionpro.reader.KickerFileReader;
import com.fantasy.football.auctionpro.reader.QuarterBackFileReader;
import com.fantasy.football.auctionpro.reader.ReceiverFileReader;
import com.fantasy.football.auctionpro.reader.RunningBackFileReader;
import com.fantasy.football.auctionpro.service.FileService;

/**
 * File Service Implementation
 * 
 * @author Derek
 *
 */
public class FileServiceImpl implements FileService {
	
	@Override
	public List<Player> getAllPlayers() throws Exception {
		List<Player> list = new ArrayList<Player>();
		
		DefenseFileReader  dfr = new DefenseFileReader("/data/" + Constants.YEAR + "/defense.csv");
		dfr.processFile();
		
		list.addAll(dfr.getPlayers());
		
		KickerFileReader kfr = new KickerFileReader("/data/" + Constants.YEAR + "/kickers.csv");
		kfr.processFile();
		
		list.addAll(kfr.getPlayers());
		
		ReceiverFileReader rfr = new ReceiverFileReader("/data/" + Constants.YEAR + "/receivers.csv",Constants.WR);
		rfr.processFile();
		
		list.addAll(rfr.getPlayers());
		
		ReceiverFileReader tfr = new ReceiverFileReader("/data/" + Constants.YEAR + "/tightends.csv",Constants.TE);
		tfr.processFile();
		
		list.addAll(tfr.getPlayers());
		
		RunningBackFileReader rbfr = new RunningBackFileReader("/data/" + Constants.YEAR + "/runningbacks.csv");
		rbfr.processFile();
		
		list.addAll(rbfr.getPlayers());

		QuarterBackFileReader qbfr = new QuarterBackFileReader("/data/" + Constants.YEAR + "/quarterbacks.csv");
		qbfr.processFile();
		
		list.addAll(qbfr.getPlayers());
		
		return list;
	}

}
