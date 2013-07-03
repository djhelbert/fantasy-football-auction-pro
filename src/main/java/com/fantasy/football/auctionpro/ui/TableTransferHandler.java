package com.fantasy.football.auctionpro.ui;

import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;

import javax.swing.JComponent;
import javax.swing.JTable;
import javax.swing.TransferHandler;

import com.fantasy.football.auctionpro.ui.model.PlayerTableModel;

/**
 * Table Transfer Handler
 * 
 * @author dhelbert
 *
 */
public class TableTransferHandler extends TransferHandler {

	/**
	 * Serial Version UID
	 */
	private static final long serialVersionUID = -1531087392384820156L;

	/**
	 * Get Source Actions
	 * 
	 */
	public int getSourceActions(JComponent c) {
		//return COPY_OR_MOVE;
	    return MOVE;
	}

	/**
	 * Create Transferable
	 * 
	 * @param c
	 */
	public Transferable createTransferable(JComponent c) {
		JTable table = (JTable)c;
		PlayerTableModel model = (PlayerTableModel)table.getModel();
		Long id = model.getPlayerId(table.getSelectedRow());
		
	    return new StringSelection(id.toString());
	}

	/**
	 * Export Done
	 * 
	 * @param comp
	 * @param tran
	 * @param action
	 */
	public void exportDone(JComponent comp, Transferable tran, int action) {
	    if (action == MOVE) {
			JTable table = (JTable)comp;
			PlayerTableModel model = (PlayerTableModel)table.getModel();
			model.refresh();
	    }
	}
}
