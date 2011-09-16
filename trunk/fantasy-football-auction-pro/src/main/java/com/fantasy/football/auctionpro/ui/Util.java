package com.fantasy.football.auctionpro.ui;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Toolkit;
import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

/**
 * Utility Class
 * 
 * @author dhelbert
 *
 */
public class Util {

	/**
	 * Set Derby System Home
	 * 
	 */
	public static void setDerbySystemHome() {
	    String userHomeDir = System.getProperty("user.home") + System.getProperty("file.separator");
	    System.setProperty("derby.system.home", userHomeDir);
	}
	
	/**
	 * Method to center compoenent on screen.
	 * 
	 * @param comp Component
	 */
	public static void centerComponent(Component comp) {
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		Dimension size = comp.getSize();
		screenSize.height = screenSize.height / 2;
		screenSize.width = screenSize.width / 2;
		size.height = size.height / 2;
		size.width = size.width / 2;
		int y = screenSize.height - size.height;
		int x = screenSize.width - size.width;
		comp.setLocation(x, y);
	}
	
	/**
	 * Get Image Icon
	 * 
	 * @param name
	 * @return
	 */
	public static ImageIcon getImageIcon(String name) {
		String imageResPath = "images/" + name;
		ImageIcon icon = null;

		ClassLoader cload = Util.class.getClassLoader();
		URL iconURL = cload.getResource(imageResPath);

		if (iconURL != null) {
			icon = new ImageIcon(iconURL, name);
		}

		return icon;
	}
	
	/**
	 * Get File Text
	 * 
	 * @param name
	 * @return String
	 * @throws Exception
	 */
	public static String getFileText(String name) throws Exception {
		String text = "";

		ClassLoader cload = Util.class.getClassLoader();
		InputStream is = cload.getResourceAsStream(name);
		InputStreamReader isr = new InputStreamReader(is);
		BufferedReader br = new BufferedReader(isr);

		String temp = br.readLine();

		while (temp != null) {
			text += temp + "\n";
			temp = br.readLine();
		}

		br.close();

		return text;
	}
	
	/**
	 * Get User Home
	 * 
	 * @return String
	 */
	public static String getUserHome() {
		return System.getProperty("user.home");
	}
	
	/**
	 * Get Text Value - Return NULL if canceled.
	 * 
	 * @param comp
	 * @param message
	 * @param title
	 * 
	 * @return String
	 */
	public static String getValue(Component comp, String message, String title) {
		return JOptionPane.showInputDialog(comp, message, title, JOptionPane.QUESTION_MESSAGE);
	}

	/**
	 * Show File Dialog
	 * 
	 * @param comp       Component
	 * @param dialogType JFileChooser.OPEN_DIALOG, JFileChooser.SAVE_DIALOG, JFileChooser.CUSTOM_DIALOG
	 * 
	 * @return File
	 */
	public static File getSelectedFile(Component comp, int dialogType) {
		return getSelectedFile(comp, dialogType, null);
	}

	/**
	 * Show File Dialog
	 * 
	 * @param comp       Component
	 * @param dialogType JFileChooser.OPEN_DIALOG, JFileChooser.SAVE_DIALOG, JFileChooser.CUSTOM_DIALOG
	 * @param cff        Custom File Filter
	 * 
	 * @return File
	 */
	public static File getSelectedFile(Component comp, int dialogType, CustomFileFilter cff) {
		JFileChooser chooser = new JFileChooser();
		chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		chooser.setDialogType(dialogType);
		chooser.setMultiSelectionEnabled(false);

		if (cff != null)
			chooser.setFileFilter(cff);

		int returnVal = 0;

		if (dialogType == JFileChooser.OPEN_DIALOG)
			returnVal = chooser.showOpenDialog(comp);
		else
			returnVal = chooser.showSaveDialog(comp);

		if (returnVal == JFileChooser.APPROVE_OPTION) {
			return chooser.getSelectedFile();
		} else {
			return null;
		}
	}
	
	/**
	 * Constraint Utility Method
	 * 
	 * @param gbc GridBagConstraints
	 * @param wx weight x
	 * @param wy weight y
	 * @param gx grid x
	 * @param gy grid y
	 * @param gw grid width x
	 * @param gh grid width y
	 * @param fill Fill
	 * @param anch Anchor
	 */
	public static final void setConstraints(GridBagConstraints gbc, int wx,
			int wy, int gx, int gy, int gw, int gh, int fill, int anch) {
		gbc.weightx = wx;
		gbc.weighty = wy;
		gbc.gridx = gx;
		gbc.gridy = gy;
		gbc.gridwidth = gw;
		gbc.gridheight = gh;
		gbc.fill = fill;
		gbc.anchor = anch;
		gbc.insets = new Insets(5, 5, 5, 5);
	}
	
	/**
	 * Show Error Dialog
	 * 
	 * @param c Component
	 * @param message Message String
	 * @param title Dialog Title
	 */
	public static void showInfo(Component c, String message, String title) {
		JLabel label = new JLabel(message);
		JOptionPane.showMessageDialog(c, label, title,
				JOptionPane.INFORMATION_MESSAGE);
	}
	
	/**
	 * Show Plain Dialog
	 * 
	 * @param c     Component
	 * @param m     Component
	 * @param title Dialog Title
	 */
	public static void showInfo(Component c, Component m, String title) {
		JOptionPane.showMessageDialog(c, m, title, JOptionPane.PLAIN_MESSAGE);
	}	
}
