package com.fantasy.football.auctionpro.ui;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.swing.filechooser.FileFilter;

/**
 * Custom File Filter
 * 
 * @author dhelbert
 */
public class CustomFileFilter extends FileFilter {

	/** Map */
	private Map<String,Boolean> map = new HashMap<String,Boolean>();

	/** Description */
	private String desc;

	/**
	 * Constructor
	 * 
	 * @param ext Extension eg. xml html doc
	 * @param desc Description File Description
	 */
	public CustomFileFilter(String ext, String d) {
		super();
		desc = d;
		map.put(ext, new Boolean(true));
	}

	/**
	 * Constructor
	 * 
	 * @param map Extension Map
	 * @param d Description
	 */
	public CustomFileFilter(Map<String,Boolean> map, String d) {
		super();
		this.desc = d;
		this.map = map;
	}

	/**
	 * Accept File
	 * 
	 * @param f File
	 */
	public boolean accept(File f) {
		if( f.isDirectory() ) {
			return true;
		}
		
		String name = f.getName();

		if (name.lastIndexOf('.') != -1) {
			String ext = name.substring(name.lastIndexOf('.'), name.length());

			if (map.get(ext) != null) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

	/**
	 * Get File Description
	 * 
	 * @return String
	 */
	public String getDescription() {
		String ext = "";

		Set<String> set = map.keySet();
		Object[] items = set.toArray();

		for (int i = 0; i < items.length; i++) {
			ext += (i == 0 ? "" : ",") + "*" + items[i].toString();
		}

		return desc + " (" + ext + ")";
	}

}
