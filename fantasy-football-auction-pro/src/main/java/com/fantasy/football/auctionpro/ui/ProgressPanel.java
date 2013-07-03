package com.fantasy.football.auctionpro.ui;

import java.awt.BorderLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 * Progress Panel
 * 
 * @author dhelbert
 *
 */
public class ProgressPanel extends JPanel {

    /** Serial Version UID */
	private static final long serialVersionUID = 286270638044383453L;

	/** Progress Bar */
	private JProgressBar progressBar;
    
	/** Task Output */
    private JTextArea taskOutput;

    /** Label */
    private JLabel label = new JLabel("Progress");
    
    /**
     * Constructor 
     */
    public ProgressPanel() {
        super(new BorderLayout());

        progressBar = new JProgressBar(0, 100);
        progressBar.setValue(0);
        progressBar.setStringPainted(true);

        taskOutput = new JTextArea(5,40);
        taskOutput.setMargin(new Insets(5,5,5,5));
        taskOutput.setEditable(false);

        label.setIcon(Util.getImageIcon("football.png"));
        
        JPanel panel = new JPanel();
        panel.add(label);
        panel.add(progressBar);

        add(panel, BorderLayout.PAGE_START);
        
        add(new JScrollPane(taskOutput), BorderLayout.CENTER);
        
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

    }

    /**
     * Update
     * 
     * @param task
     * @param percent
     */
    public void update(String task, int percent) {
    	taskOutput.append(task + "\n");
    	progressBar.setValue(percent);
    	label.setText(task);
    }
}
