package com.fantasy.football.auctionpro.ui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.swing.JDialog;
import javax.swing.JEditorPane;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkListener;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;

import com.fantasy.football.auctionpro.MainApp;

/**
 * Help Frame
 * 
 * @author dhelbert
 *
 */
public class HelpDialog extends JDialog implements TreeSelectionListener, HyperlinkListener {

	/** Serial Version UID */
	private static final long serialVersionUID = 7217796584898452233L;

	/** Tree */
	private JTree tree;
	
	/** Editor Pane */
	private JEditorPane pane = new JEditorPane();
	
	/** Entries */
	private Map<String,URL> entries;
	
	/** Text Field */
	private JTextField textField = new JTextField(" ");
	
	/**
	 * Constructor
	 * 
	 * @param entries
	 * @param defaultKey
	 */
	public HelpDialog(Map<String,URL> entries, String defaultKey) {
		super(MainApp.getMainFrame(),"Help",true);
		
		this.entries = entries;
		
		init();
		
		setSize(800, 600);
		
		if( defaultKey != null ) {
			if( entries.get(defaultKey) != null ) {
				try {
					pane.setPage(entries.get(defaultKey));
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
		Util.centerComponent(this);
	}
	
	/**
	 * Init
	 */
	private void init() {
		Container content = getContentPane();
		
		content.setLayout(new BorderLayout(2,2));
		
		DefaultMutableTreeNode root = new DefaultMutableTreeNode("Topics");
		
		tree = new JTree(root);
		
		DefaultMutableTreeNode child;
	    
		List<String> list = new ArrayList<String>(entries.keySet());
		
		Collections.sort(list);
		
	    for(String key : list) {
	    	child = new DefaultMutableTreeNode(key);
	    	root.add(child);
	    }
	    
	    tree.expandRow(0);
	    tree.addTreeSelectionListener(this);
	    content.add(new JScrollPane(tree), BorderLayout.WEST);
	    
	    pane.setEditable(false);
	    pane.addHyperlinkListener(this);
	    
	    content.add(new JScrollPane(pane),BorderLayout.CENTER);
	    
	    textField.setEditable(false);
	    
	    content.add(textField,BorderLayout.SOUTH);
	}

	/**
	 * Value Changed
	 * 
	 * @param e TreeSelectionEvent
	 */
	public void valueChanged(TreeSelectionEvent e) {
		String key = tree.getLastSelectedPathComponent().toString();
		
		try {
			if( entries.get(key) != null ) {
				pane.setPage(entries.get(key));
			}
		} catch (IOException err) {
			err.printStackTrace();
		}
	}

	/**
	 * Hyper Link Update
	 * 
	 * @param e HyperlinkEvent
	 */
	public void hyperlinkUpdate(HyperlinkEvent e) {
		if( e.getEventType() == HyperlinkEvent.EventType.EXITED ) {
			textField.setText(" ");
		}
		else if( e.getEventType() == HyperlinkEvent.EventType.ENTERED ) {
			textField.setText(e.getURL().toString());
		}
		else if (e.getEventType() == HyperlinkEvent.EventType.ACTIVATED) {
	        try {
	          pane.setPage(e.getURL());
	          textField.setText(e.getURL().toExternalForm());
	        } 
	        catch(IOException err) {
	          err.printStackTrace();
	        }
	      }
	}

}
