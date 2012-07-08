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
 * File Service Impl
 * 
 * @author Derek
 *
 */
public class FileServiceImpl implements FileService {
	
	/**
	 * Get All Players from Files
	 * 
	 * @throws Exception
	 */
	public List<Player> getAllPlayers() throws Exception {
		List<Player> list = new ArrayList<Player>();
		
		DefenseFileReader  dfr = new DefenseFileReader("/data/2011/defense.csv");
		dfr.processFile();
		
		list.addAll(dfr.getPlayers());
		
		KickerFileReader kfr = new KickerFileReader("/data/2011/kickers.csv");
		kfr.processFile();
		
		list.addAll(kfr.getPlayers());
		
		ReceiverFileReader rfr = new ReceiverFileReader("/data/2011/receivers.csv",Constants.WR);
		rfr.processFile();
		
		list.addAll(rfr.getPlayers());
		
		ReceiverFileReader tfr = new ReceiverFileReader("/data/2011/tightends.csv",Constants.TE);
		tfr.processFile();
		
		list.addAll(tfr.getPlayers());
		
		RunningBackFileReader rbfr = new RunningBackFileReader("/data/2011/runningbacks.csv");
		rbfr.processFile();
		
		list.addAll(rbfr.getPlayers());

		QuarterBackFileReader qbfr = new QuarterBackFileReader("/data/2011/quarterbacks.csv");
		qbfr.processFile();
		
		list.addAll(qbfr.getPlayers());
		
		return list;
	}

	/**
	 * Main
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		FileService serv = new FileServiceImpl();
		try {
			System.out.println(serv.getAllPlayers());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
