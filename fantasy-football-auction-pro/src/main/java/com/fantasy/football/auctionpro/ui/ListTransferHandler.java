package com.fantasy.football.auctionpro.ui;

import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;

import javax.swing.DefaultListModel;
import javax.swing.JComponent;
import javax.swing.JList;
import javax.swing.TransferHandler;

/**
 * List Transfer Handler
 * 
 * @author dhelbert
 *
 */
public class ListTransferHandler extends TransferHandler {

	/**
	 * Serial Version UID
	 */
	private static final long serialVersionUID = -4702387230470575917L;
    
	/**
     * Get Source Actions
     * 
     * @param c
     */
    public int getSourceActions(JComponent c) {
        return MOVE;
    }
    
    /**
     * Import Data
     * 
     * @parm info
     */
    public boolean importData(TransferHandler.TransferSupport info) {
        if (!info.isDrop()) {
            return false;
        }

        JList list = (JList)info.getComponent();
        DefaultListModel listModel = (DefaultListModel)list.getModel();
        JList.DropLocation dl = (JList.DropLocation)info.getDropLocation();
        
        int     index  = dl.getIndex();
        //boolean insert = dl.isInsert();

        Transferable t = info.getTransferable();
        
        String data;
        
        try {
            data = (String)t.getTransferData(DataFlavor.stringFlavor);
        } 
        catch (Exception e) { 
        	return false; 
        }
        
        listModel.add(index++, data);
        
        return true;
    }
    
    /**
     * Can Import
     * 
     * @param info
     */
    public boolean canImport(TransferHandler.TransferSupport info) {
        if (!info.isDataFlavorSupported(DataFlavor.stringFlavor)) {
            return false;
        }
        
        return true;
   }
    
}
