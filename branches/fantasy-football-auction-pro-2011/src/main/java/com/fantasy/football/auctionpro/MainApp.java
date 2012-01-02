package com.fantasy.football.auctionpro;

import java.awt.Component;

import javax.swing.JFrame;

import com.fantasy.football.auctionpro.entity.Configuration;
import com.fantasy.football.auctionpro.service.ConfigurationService;
import com.fantasy.football.auctionpro.service.FileService;
import com.fantasy.football.auctionpro.service.StartupService;
import com.fantasy.football.auctionpro.service.VbdService;
import com.fantasy.football.auctionpro.service.impl.StartupServiceImpl;
import com.fantasy.football.auctionpro.ui.FootballFrame;
import com.fantasy.football.auctionpro.ui.ProgressPanel;
import com.fantasy.football.auctionpro.ui.Util;

/**
 * Main App
 *
 * @param args
 */
public class MainApp 
{	
	/** Main Component */
	private static Component mainComponent;
	
	/** Main Frame */
	private static JFrame mainFrame;

	/**
     * Main
     * 
     * @param args
     */
	public static void main( String[] args )
    {
        //Create and set up the window.
        JFrame frame = new JFrame("Fantasy Football Action Pro");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    
        //Create and set up the content pane.
        ProgressPanel progressPanel = new ProgressPanel();
        progressPanel.setOpaque(true); //content panes must be opaque
        frame.setContentPane(progressPanel);
        frame.pack();
        
		Util.centerComponent(frame);
		frame.setVisible(true);
		
	    progressPanel.update("Starting",0);
	    progressPanel.update("Services Initialized",25);
	    
		Util.setDerbySystemHome();
		
        ConfigurationService configService = null;
        Configuration        configuration = null;
        StartupService       service       = null;
        
        boolean started = false;
        
        try {
        	configService = ServiceFactory.getConfigurationService();
        	service       = new StartupServiceImpl();
        	started       = service.isStarted();
        }
        catch(Exception err) {
        	err.printStackTrace();
        	System.exit(0);
        }

        if( !started ) {
        	service.initTeams(10);
        	
        	FileService fileService = ServiceFactory.getFileService();
        	
        	try {
        		// init conf
        		configuration = service.initConfiguration();
        		
        		// init playares
        		service.initPlayers(fileService.getAllPlayers(),configService.getByeWeekMap());
            	
        		progressPanel.update("Database Initialized",50);
        		
            	VbdService vbdService = ServiceFactory.getVbdService();
            	vbdService.updateAllPlayerVbd(configuration);
            	
        		progressPanel.update("VBD Data Initialized",75);
			} catch (Exception err) {
				err.printStackTrace();
				System.exit(0);
			}
        }
        else {
        	try {
				configuration = configService.getConfiguration();
			} catch (Exception err) {
				err.printStackTrace();
				System.exit(0);
			}
        	
        	progressPanel.update("Database Initialized",50);
        }
        
        FootballFrame footballFrame = new FootballFrame(configuration == null ? "" : configuration.getLeagueName());
        
        // Set main frame
        mainFrame = footballFrame;
        
        progressPanel.update("Complete",100);
        frame.setVisible(false);
        
        // Show frame
        footballFrame.setVisible(true);
        
        // Set for dialogs
        mainComponent = footballFrame.getContentPane();
    }

	/**
	 * Get Main Component
	 * 
	 * @return Component
	 */
	public static Component getMainComponent() {
		return mainComponent;
	}
	
    /**
     * Get Main Frame
     * 
	 * @return Frame
	 */
	public static JFrame getMainFrame() {
		return mainFrame;
	}
	
}