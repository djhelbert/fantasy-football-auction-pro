package com.fantasy.football.auctionpro.ui;

import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.fantasy.football.auctionpro.entity.ScoreSystem;

/**
 * Score System Panel
 * 
 * @author dhelbert
 */
public final class ScoreSystemPanel extends JPanel {

	/** Serial Version UID */
	private static final long serialVersionUID = 8393177102087443500L;

	/** Passing TD Points */
	private JFormattedTextField passingTdField = new JFormattedTextField(new Integer(6));
	
	/** Rush TD Points */
	private JFormattedTextField rushingTdField = new JFormattedTextField(new Integer(6));
	
	/** REC TD Points */
	private JFormattedTextField receptionTdField = new JFormattedTextField(new Integer(6));
	
	/** Return TD Points */
	private JFormattedTextField returnTdField = new JFormattedTextField(new Integer(6));
		
	/** Reception Points */
	private JFormattedTextField receptionField = new JFormattedTextField(new Integer(6));
	
	/** Passing INT Points */
	private JFormattedTextField passingIntField  = new JFormattedTextField(new Integer(6));
	
	/** Fumbles Lost Points */
	private JFormattedTextField fumbleLostField = new JFormattedTextField(new Integer(6));
	
	/** Pass Yards Per Point */
	private JFormattedTextField passingYardsPpt = new JFormattedTextField(new Integer(30));
	
	/** Rush Yards Per Point */
	private JFormattedTextField rushingYardsPpt = new JFormattedTextField(new Integer(10));
	
	/** Rec Yards Per Point */
	private JFormattedTextField receivingYardsPpt = new JFormattedTextField(new Integer(15));
	
	/** Extra Points Points */
	private JFormattedTextField extraPointField = new JFormattedTextField(new Integer(1));
	
	/** FG 0-19 Points */
	private JFormattedTextField fieldGoalZeroNineteenField = new JFormattedTextField(new Integer(3));
	
	/** FG 20-29 Points */
	private JFormattedTextField fieldGoalTwentyTwentyNineField = new JFormattedTextField(new Integer(3));

	/** FG 30-39 Points */
	private JFormattedTextField fieldGoalThirtyThirtyNineField = new JFormattedTextField(new Integer(3));
	
	/** FG 40-49 Points */
	private JFormattedTextField fieldGoalFortyFortyNineField = new JFormattedTextField(new Integer(4));
	
	/** FG 50+ Points */
	private JFormattedTextField fieldGoalFiftyField = new JFormattedTextField(new Integer(5));
	
	/** DEF TD Points */
	private JFormattedTextField defTdField = new JFormattedTextField(new Integer(6));
	
	/** Sack Points */
	private JFormattedTextField sackField = new JFormattedTextField(new Integer(1));
	
	/** Defense INT Points */
	private JFormattedTextField defIntField = new JFormattedTextField(new Integer(2));
	
	/** Sack Points */
	private JFormattedTextField safetyField = new JFormattedTextField(new Integer(2));

	/** Block Points */
	private JFormattedTextField blockField = new JFormattedTextField(new Integer(2));
	
	/** Fumbles Recovery Points */
	private JFormattedTextField fumbleRecField = new JFormattedTextField(new Integer(2));
	
	/** Points Allowed Zero Field */
	private JFormattedTextField pointsAllowedZeroField = new JFormattedTextField(new Integer(10));
	
	/** Points Allowed One Six Field */
	private JFormattedTextField pointsAllowedOneSixField = new JFormattedTextField(new Integer(7));
	
	/** Points Allowed Seven Thirteen Field */
	private JFormattedTextField pointsAllowedSevenThirteenField = new JFormattedTextField(new Integer(4));
	
	/** Points Allowed Fourteen Twenty Field */
	private JFormattedTextField pointsAllowedFourteenTwentyField = new JFormattedTextField(new Integer(1));
	
	/** Points Allowed Twenty One Twenty Seven Field */
	private JFormattedTextField pointsAllowedTwentyOneTwentySevenField = new JFormattedTextField(new Integer(0));
	
	/** Points Allowed Twenty Eight Thirty Four Field */
	private JFormattedTextField pointsAllowedTwentyEightThirtyFourField = new JFormattedTextField(new Integer(-1));
	
	/** Points Allowed Thirty Five Field */
	private JFormattedTextField pointsAllowedThirtyFiveField = new JFormattedTextField(new Integer(-4));
	
	/** Score System */
	private ScoreSystem scoreSystem;
	
	/**
	 * Constructor
	 *
	 */
	public ScoreSystemPanel() {
		setLayout(new GridLayout(29,2,2,2) );
		setBorder(BorderFactory.createTitledBorder("Scoring System"));
		
		add( new JLabel("Pass TD Points",JLabel.RIGHT) );
		add( passingTdField );
		add( new JLabel("Rush TD Points",JLabel.RIGHT) );
		add( rushingTdField );
		add( new JLabel("Rec TD Points",JLabel.RIGHT) );
		add( receptionTdField );
		add( new JLabel("Return TD Points",JLabel.RIGHT) );
		add( returnTdField );
		add( new JLabel("Points Per Reception",JLabel.RIGHT) );
		add( receptionField );
		add( new JLabel("Pass INT Points",JLabel.RIGHT) );
		add( passingIntField  );
		add( new JLabel("Fumble Lost Points",JLabel.RIGHT) );
		add( fumbleLostField );
		add( new JLabel("Pass Yards Per Point",JLabel.RIGHT) );
		add( passingYardsPpt );
		add( new JLabel("Rush Yards Per Point",JLabel.RIGHT) );
		add( rushingYardsPpt );
		add( new JLabel("Rec Yards per Point",JLabel.RIGHT) );
		add( receivingYardsPpt );
		
		add( new JLabel("XP Points",JLabel.RIGHT) );
		add( extraPointField );
		add( new JLabel("FGM 0-19 Points",JLabel.RIGHT) );
		add( fieldGoalZeroNineteenField );
		add( new JLabel("FGM 20-29 Points",JLabel.RIGHT) );
		add( fieldGoalTwentyTwentyNineField );
		add( new JLabel("FGM 30-39 Points",JLabel.RIGHT) );
		add( fieldGoalThirtyThirtyNineField );
		add( new JLabel("FGM 40-49 Points",JLabel.RIGHT) );
		add( fieldGoalFortyFortyNineField );
		add( new JLabel("FGM 50+ Points",JLabel.RIGHT) );
		add( fieldGoalFiftyField );
		
		add( new JLabel("DEF TD Points",JLabel.RIGHT) );
		add( defTdField );
		add( new JLabel("Sack Points",JLabel.RIGHT) );
		add( sackField );
		add( new JLabel("DEF INT Points",JLabel.RIGHT) );
		add( defIntField );
		add( new JLabel("Safety Points",JLabel.RIGHT) );
		add( safetyField );
		add( new JLabel("Block Points",JLabel.RIGHT) );
		add( blockField );
		add( new JLabel("Fumble Recovery Points",JLabel.RIGHT) );
		add( fumbleRecField );
		
		add( new JLabel("Points Allowed 0",JLabel.RIGHT) );
		add( pointsAllowedZeroField );
		add( new JLabel("Points Allowed 1-6",JLabel.RIGHT) );
		add( pointsAllowedOneSixField );
		add( new JLabel("Points Allowed 7-13",JLabel.RIGHT) );
		add( pointsAllowedSevenThirteenField );
		add( new JLabel("Points Allowed 14-20",JLabel.RIGHT) );
		add( pointsAllowedFourteenTwentyField );
		add( new JLabel("Points Allowed 21-27",JLabel.RIGHT) );
		add( pointsAllowedTwentyOneTwentySevenField );
		add( new JLabel("Points Allowed 28-34",JLabel.RIGHT) );
		add( pointsAllowedTwentyEightThirtyFourField );
		add( new JLabel("Points Allowed 35+",JLabel.RIGHT) );
		add( pointsAllowedThirtyFiveField );
	}
	
	/**
	 * Set Score System
	 * 
	 * @param scoreSystem
	 */
	public ScoreSystem getScoreSystem() {
		scoreSystem.setExtraPointPts((Integer)extraPointField.getValue());
		scoreSystem.setFieldGoalFiftyPts((Integer)fieldGoalFiftyField.getValue());
		scoreSystem.setFieldGoalFortyFortyNinePts((Integer)fieldGoalFortyFortyNineField.getValue());
		scoreSystem.setFieldGoalThirtyThirtyNinePts((Integer)fieldGoalThirtyThirtyNineField.getValue());
		scoreSystem.setFieldGoalZeroNineteenPts((Integer)fieldGoalZeroNineteenField.getValue());
		
		scoreSystem.setFumbleLostPts((Integer)fumbleLostField.getValue());
		scoreSystem.setPassingIntPts((Integer)passingIntField.getValue());
		scoreSystem.setPassingTdPts((Integer)passingTdField.getValue());
		scoreSystem.setPassingYardsPpt((Integer)passingYardsPpt.getValue());
		scoreSystem.setReceivingYardsPpt((Integer)receivingYardsPpt.getValue());
		scoreSystem.setReceptionPts((Integer)receptionField.getValue());
		scoreSystem.setReturnTdPts((Integer)returnTdField.getValue());
		scoreSystem.setRushingTdPts((Integer)rushingTdField.getValue());
		scoreSystem.setRushingYardsPpt((Integer)rushingYardsPpt.getValue());
		
		scoreSystem.setBlockPts((Integer)blockField.getValue());
		scoreSystem.setDefIntPts((Integer)defIntField.getValue());
		scoreSystem.setDefTdPts((Integer)defTdField.getValue());
		scoreSystem.setFumbleRecPts((Integer)fumbleRecField.getValue());
		scoreSystem.setSackPts((Integer)sackField.getValue());
		scoreSystem.setSafetyPts((Integer)safetyField.getValue());
		
		scoreSystem.setPointsAllowedFourteenTwenty((Integer)pointsAllowedFourteenTwentyField.getValue());
		scoreSystem.setPointsAllowedOneSix((Integer)pointsAllowedOneSixField.getValue());
		scoreSystem.setPointsAllowedSevenThirteen((Integer)pointsAllowedSevenThirteenField.getValue());
		scoreSystem.setPointsAllowedThirtyFive((Integer)pointsAllowedThirtyFiveField.getValue());
		scoreSystem.setPointsAllowedTwentyEightThirtyFour((Integer)pointsAllowedTwentyEightThirtyFourField.getValue());
		scoreSystem.setPointsAllowedTwentyOneTwentySeven((Integer)pointsAllowedTwentyOneTwentySevenField.getValue());
		scoreSystem.setPointsAllowedZero((Integer)pointsAllowedZeroField.getValue());
		
		return scoreSystem;
	}
	
	/**
	 * Set Score System
	 * 
	 * @param scoreSystem
	 */
	public void setScoreSystem(ScoreSystem scoreSystem) {
		this.scoreSystem = scoreSystem;
		
		extraPointField.setValue(scoreSystem.getExtraPointPts());
		fieldGoalFiftyField.setValue(scoreSystem.getFieldGoalFiftyPts());
		fieldGoalFortyFortyNineField.setValue(scoreSystem.getFieldGoalFortyFortyNinePts());
		fieldGoalThirtyThirtyNineField.setValue(scoreSystem.getFieldGoalThirtyThirtyNinePts());
		fieldGoalZeroNineteenField.setValue(scoreSystem.getFieldGoalZeroNineteenPts());
		
		fumbleLostField.setValue(scoreSystem.getFumbleLostPts());
		passingIntField.setValue(scoreSystem.getPassingIntPts());
		passingTdField.setValue(scoreSystem.getPassingTdPts());
		passingYardsPpt.setValue(scoreSystem.getPassingYardsPpt());
		receivingYardsPpt.setValue(scoreSystem.getReceivingYardsPpt());
		receptionField.setValue(scoreSystem.getReceptionPts());
		returnTdField.setValue(scoreSystem.getReturnTdPts());
		rushingTdField.setValue(scoreSystem.getRushingTdPts());
		rushingYardsPpt.setValue(scoreSystem.getRushingYardsPpt());
		
		blockField.setValue(scoreSystem.getBlockPts());
		defIntField.setValue(scoreSystem.getDefIntPts());
		defTdField.setValue(scoreSystem.getDefTdPts());
		fumbleRecField.setValue(scoreSystem.getFumbleRecPts());
		sackField.setValue(scoreSystem.getSackPts());
		safetyField.setValue(scoreSystem.getSafetyPts());
		
		pointsAllowedFourteenTwentyField.setValue(scoreSystem.getPointsAllowedFourteenTwenty());
		pointsAllowedOneSixField.setValue(scoreSystem.getPointsAllowedOneSix());
		pointsAllowedSevenThirteenField.setValue(scoreSystem.getPointsAllowedSevenThirteen());
		pointsAllowedThirtyFiveField.setValue(scoreSystem.getPointsAllowedThirtyFive());
		pointsAllowedTwentyEightThirtyFourField.setValue(scoreSystem.getPointsAllowedTwentyEightThirtyFour());
		pointsAllowedTwentyOneTwentySevenField.setValue(scoreSystem.getPointsAllowedTwentyOneTwentySeven());
		pointsAllowedZeroField.setValue(scoreSystem.getPointsAllowedZero());
	}
}
