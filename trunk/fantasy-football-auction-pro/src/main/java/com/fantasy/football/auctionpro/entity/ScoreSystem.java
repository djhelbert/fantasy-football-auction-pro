package com.fantasy.football.auctionpro.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Score System
 * 
 * @author dhelbert
 */
@Entity
public class ScoreSystem implements Serializable {

	/** Serial Version UID */
	private static final long serialVersionUID = 4667862954027232521L;

	/** ID */
	@Id
	@Column(name="SCORE_SYSTEM_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	/** Passing TD Points */
	@Column(name = "PASSTDPTS", nullable = false)
	private Integer passingTdPts = 6;
	
	/** Rush TD Points */
	@Column(name = "RUSHTDPTS", nullable = false)
	private Integer rushingTdPts = 6;
	
	/** REC TD Points */
	@Column(name = "RECTDPTS", nullable = false)
	private Integer receptionTdPts = 6;
	
	/** Return TD Points */
	@Column(name = "RETTDPTS", nullable = false)
	private Integer returnTdPts = 6;
		
	/** Reception Points */
	@Column(name = "RECPTS", nullable = false)
	private Integer receptionPts = 6;
	
	/** Passing INT Points */
	@Column(name = "PASSINTPTS", nullable = false)
	private Integer passingIntPts  = -2;
	
	/** Fumbles Lost Points */
	@Column(name = "FUMLPTS", nullable = false)
	private Integer fumbleLostPts = -2;
	
	/** Pass Yards Per Point */
	@Column(name = "PASSYDPPT", nullable = false)
	private Integer passingYardsPpt = 30;
	
	/** Rush Yards Per Point */
	@Column(name = "RUSHYDPPT", nullable = false)
	private Integer rushingYardsPpt = 10;
	
	/** Rec Yards Per Point */
	@Column(name = "RECYDPPT", nullable = false)
	private Integer receivingYardsPpt = 15;
	
	/** Extra Points Points */
	@Column(name = "EXPPTS", nullable = false)
	private Integer extraPointPts = 1;
	
	/** FG 0-19 Points */
	@Column(name = "FGZERNINPTS", nullable = false)
	private Integer fieldGoalZeroNineteenPts = 3;
	
	/** FG 20-29 Points */
	@Column(name = "FGTWTWNPTS", nullable = false)
	private Integer fieldGoalTwentyTwentyNinePts = 3;

	/** FG 30-39 Points */
	@Column(name = "FGTHTHNPTS", nullable = false)
	private Integer fieldGoalThirtyThirtyNinePts = 3;
	
	/** FG 40-49 Points */
	@Column(name = "FGFOFONPTS", nullable = false)
	private Integer fieldGoalFortyFortyNinePts = 4;
	
	/** FG 50+ Points */
	@Column(name = "FGFPPTS", nullable = false)
	private Integer fieldGoalFiftyPts = 5;

	/** Rush TD Points */
	@Column(name = "DEFTDPTS", nullable = false)
	private Integer defTdPts = 6;
	
	/** Sack Points */
	@Column(name = "SACKPTS", nullable = false)
	private Integer sackPts = 1;
	
	/** Defense INT Points */
	@Column(name = "DEFINTPTS", nullable = false)
	private Integer defIntPts = 1;
	
	/** Sack Points */
	@Column(name = "SAFETYPTS", nullable = false)
	private Integer safetyPts = 2;

	/** Block Points */
	@Column(name = "BLOCKPTS", nullable = false)
	private Integer blockPts = 2;
	
	/** Fumbles Recovery Points */
	@Column(name = "FUMRECPTS", nullable = false)
	private Integer fumbleRecPts = 2;
	
	/** Points Allowed 0 */
	@Column(name = "PTSALLOWEDZERO", nullable = false)
	private Integer pointsAllowedZero = 10;
	
	/** Points Allowed 1-6 */
	@Column(name = "PTSALLOWEDONESIX", nullable = false)
	private Integer pointsAllowedOneSix = 7;
	
	/** Points Allowed 7-13*/
	@Column(name = "PTSALLOWEDSEVENTHIRTEEN", nullable = false)
	private Integer pointsAllowedSevenThirteen = 4;
	
	/** Points Allowed 14-20*/
	@Column(name = "PTSALLOWEDFOURTEENTWENTY", nullable = false)
	private Integer pointsAllowedFourteenTwenty = 1;
	
	/** Points Allowed 21-27*/
	@Column(name = "PTSALLOWEDTWENTYONETWENTYSEVEN", nullable = false)
	private Integer pointsAllowedTwentyOneTwentySeven = 0;
	
	/** Points Allowed 28-34*/
	@Column(name = "PTSALLOWEDTWENTYEIGTHTHIRTYFOUR", nullable = false)
	private Integer pointsAllowedTwentyEightThirtyFour = -1;
	
	/** Points Allowed 35+*/
	@Column(name = "PTSALLOWEDTHIRTYFIVE", nullable = false)
	private Integer pointsAllowedThirtyFive = -4;
	
	/**
	 * Get Id - Primary Key
	 * 
	 * @return Long
	 */
	public Long getId() {
		return id;
	}

	/**
	 * Set Id - Primary Key
	 * 
	 * @param id
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * Get Passing TD Points
	 * 
	 * @return Integer
	 */
	public Integer getPassingTdPts() {
		return passingTdPts;
	}

	/**
	 * Set Passing TD Points
	 * 
	 * @param passingTdPts
	 */
	public void setPassingTdPts(Integer passingTdPts) {
		this.passingTdPts = passingTdPts;
	}

	/**
	 * Get Rushing TD Points
	 * 
	 * @return Integer
	 */
	public Integer getRushingTdPts() {
		return rushingTdPts;
	}

	/**
	 * Set Rushing TD Points
	 * 
	 * @return rushingTdPts
	 */
	public void setRushingTdPts(Integer rushingTdPts) {
		this.rushingTdPts = rushingTdPts;
	}

	/**
	 * Get Reception TD Points
	 * 
	 * @return Integer
	 */
	public Integer getReceptionTdPts() {
		return receptionTdPts;
	}

	/**
	 * Set Reception TD Points
	 * 
	 * @return receptionTdPts
	 */
	public void setReceptionTdPts(Integer receptionTdPts) {
		this.receptionTdPts = receptionTdPts;
	}

	/**
	 * Get Return TD Points
	 * 
	 * @return Integer
	 */
	public Integer getReturnTdPts() {
		return returnTdPts;
	}

	/**
	 * Set Return TD Points
	 * 
	 * @return receptionTdPts
	 */
	public void setReturnTdPts(Integer returnTdPts) {
		this.returnTdPts = returnTdPts;
	}

	/**
	 * Get Return TD Points
	 * 
	 * @return Integer
	 */
	public Integer getReceptionPts() {
		return receptionPts;
	}

	/**
	 * Set Reception TD Points
	 * 
	 * @return receptionPts
	 */
	public void setReceptionPts(Integer receptionPts) {
		this.receptionPts = receptionPts;
	}

	/**
	 * Get Reception TD Points
	 * 
	 * @return Integer
	 */
	public Integer getPassingIntPts() {
		return passingIntPts;
	}

	/**
	 * Set Passing Interception Points
	 * 
	 * @return passingIntPts
	 */
	public void setPassingIntPts(Integer passingIntPts) {
		this.passingIntPts = passingIntPts;
	}

	/**
	 * Get Fumble Lost Points
	 * 
	 * @return Integer
	 */
	public Integer getFumbleLostPts() {
		return fumbleLostPts;
	}

	/**
	 * Set Fumble Lost Points
	 * 
	 * @return fumbleLostPts
	 */
	public void setFumbleLostPts(Integer fumbleLostPts) {
		this.fumbleLostPts = fumbleLostPts;
	}

	/**
	 * Get Passing Yards Per Point
	 * 
	 * @return Integer
	 */
	public Integer getPassingYardsPpt() {
		return passingYardsPpt;
	}

	/**
	 * Set Passing Yards Per Point
	 * 
	 * @return passingYardsPpt
	 */
	public void setPassingYardsPpt(Integer passingYardsPpt) {
		this.passingYardsPpt = passingYardsPpt;
	}

	/**
	 * Get Rushing Yards Per Point
	 * 
	 * @return Integer
	 */
	public Integer getRushingYardsPpt() {
		return rushingYardsPpt;
	}

	/**
	 * Set Rushing Yards Per Point
	 * 
	 * @return rushingYardsPpt
	 */
	public void setRushingYardsPpt(Integer rushingYardsPpt) {
		this.rushingYardsPpt = rushingYardsPpt;
	}

	/**
	 * Get Receiving Yards Per Point
	 * 
	 * @return rushingYardsPpt
	 */
	public Integer getReceivingYardsPpt() {
		return receivingYardsPpt;
	}

	/**
	 * Set Receiving Yards Per Point
	 * 
	 * @return receivingYardsPpt
	 */
	public void setReceivingYardsPpt(Integer receivingYardsPpt) {
		this.receivingYardsPpt = receivingYardsPpt;
	}

	/**
	 * Get Extra Point Points
	 * 
	 * @return Integer
	 */
	public Integer getExtraPointPts() {
		return extraPointPts;
	}

	/**
	 * Set Extra Point Points
	 * 
	 * @return extraPointPts
	 */
	public void setExtraPointPts(Integer extraPointPts) {
		this.extraPointPts = extraPointPts;
	}

	/**
	 * Get Field Goal Zero Nineteen Points
	 * 
	 * @return Integer
	 */
	public Integer getFieldGoalZeroNineteenPts() {
		return fieldGoalZeroNineteenPts;
	}

	/**
	 * Set Field Goal Zero Nineteen Points
	 * 
	 * @return fieldGoalZeroNineteenPts
	 */
	public void setFieldGoalZeroNineteenPts(Integer fieldGoalZeroNineteenPts) {
		this.fieldGoalZeroNineteenPts = fieldGoalZeroNineteenPts;
	}

	/**
	 * Get Field Goal Twenty Twenty Nine Points
	 * 
	 * @return Integer
	 */
	public Integer getFieldGoalTwentyTwentyNinePts() {
		return fieldGoalTwentyTwentyNinePts;
	}

	/**
	 * Set Field Goal Twenty Twenty Nine Points
	 * 
	 * @return fieldGoalTwentyTwentyNinePts
	 */
	public void setFieldGoalTwentyTwentyNinePts(Integer fieldGoalTwentyTwentyNinePts) {
		this.fieldGoalTwentyTwentyNinePts = fieldGoalTwentyTwentyNinePts;
	}

	/**
	 * Get Field Goal Twenty Twenty Nine Points
	 * 
	 * @return Integer
	 */
	public Integer getFieldGoalThirtyThirtyNinePts() {
		return fieldGoalThirtyThirtyNinePts;
	}

	/**
	 * Set Field Goal Thirty Thirty Nine Points
	 * 
	 * @return fieldGoalThirtyThirtyNinePts
	 */
	public void setFieldGoalThirtyThirtyNinePts(Integer fieldGoalThirtyThirtyNinePts) {
		this.fieldGoalThirtyThirtyNinePts = fieldGoalThirtyThirtyNinePts;
	}

	/**
	 * Get Field Goal Thirty Thirty Nine Points
	 * 
	 * @return Integer
	 */
	public Integer getFieldGoalFortyFortyNinePts() {
		return fieldGoalFortyFortyNinePts;
	}

	/**
	 * Set Field Goal Forty Forty Nine Points
	 * 
	 * @param fieldGoalFortyFortyNinePts
	 */
	public void setFieldGoalFortyFortyNinePts(Integer fieldGoalFortyFortyNinePts) {
		this.fieldGoalFortyFortyNinePts = fieldGoalFortyFortyNinePts;
	}

	/**
	 * Get Field Goal Fifty Plus Points
	 * 
	 * @return Integer
	 */
	public Integer getFieldGoalFiftyPts() {
		return fieldGoalFiftyPts;
	}

	/**
	 * Set Field Goal Fifty Points
	 * 
	 * @param fieldGoalFiftyPts
	 */
	public void setFieldGoalFiftyPts(Integer fieldGoalFiftyPts) {
		this.fieldGoalFiftyPts = fieldGoalFiftyPts;
	}

	/**
	 * Get DEF TD Points
	 * 
	 * @return Integer
	 */
	public Integer getDefTdPts() {
		return defTdPts;
	}

	/**
	 * Set DEF TD Pts
	 * 
	 * @param Integer
	 */
	public void setDefTdPts(Integer defTdPts) {
		this.defTdPts = defTdPts;
	}

	/**
	 * Get Sack Points
	 * 
	 * @return Integer
	 */
	public Integer getSackPts() {
		return sackPts;
	}

	/**
	 * Set Sack Points
	 * 
	 * @param sackPts
	 */
	public void setSackPts(Integer sackPts) {
		this.sackPts = sackPts;
	}

	/**
	 * Get DEF INT Points
	 * 
	 * @return Integer
	 */
	public Integer getDefIntPts() {
		return defIntPts;
	}

	/**
	 * Set DEF INT Points
	 * 
	 * @param defIntPts
	 */
	public void setDefIntPts(Integer defIntPts) {
		this.defIntPts = defIntPts;
	}

	/**
	 * Get Safety Pts
	 * 
	 * @return Integer
	 */
	public Integer getSafetyPts() {
		return safetyPts;
	}

	/**
	 * Set Safety Points
	 * 
	 * @param safetyPts
	 */
	public void setSafetyPts(Integer safetyPts) {
		this.safetyPts = safetyPts;
	}

	/**
	 * Get Block Kick Points
	 * 
	 * @return Integer
	 */
	public Integer getBlockPts() {
		return blockPts;
	}

	/**
	 * Set Block Points
	 * 
	 * @param blockPts
	 */
	public void setBlockPts(Integer blockPts) {
		this.blockPts = blockPts;
	}

	/**
	 * Get Fumble REC Points
	 * 
	 * @return Integer
	 */
	public Integer getFumbleRecPts() {
		return fumbleRecPts;
	}

	/**
	 * Set Fumble REC Points
	 * 
	 * @param fumbleRecPts
	 */
	public void setFumbleRecPts(Integer fumbleRecPts) {
		this.fumbleRecPts = fumbleRecPts;
	}

	public Integer getPointsAllowedZero() {
		return pointsAllowedZero;
	}

	public void setPointsAllowedZero(Integer pointsAllowedZero) {
		this.pointsAllowedZero = pointsAllowedZero;
	}

	public Integer getPointsAllowedOneSix() {
		return pointsAllowedOneSix;
	}

	public void setPointsAllowedOneSix(Integer pointsAllowedOneSix) {
		this.pointsAllowedOneSix = pointsAllowedOneSix;
	}

	public Integer getPointsAllowedSevenThirteen() {
		return pointsAllowedSevenThirteen;
	}

	public void setPointsAllowedSevenThirteen(Integer pointsAllowedSevenThirteen) {
		this.pointsAllowedSevenThirteen = pointsAllowedSevenThirteen;
	}

	public Integer getPointsAllowedFourteenTwenty() {
		return pointsAllowedFourteenTwenty;
	}

	public void setPointsAllowedFourteenTwenty(Integer pointsAllowedFourteenTwenty) {
		this.pointsAllowedFourteenTwenty = pointsAllowedFourteenTwenty;
	}

	public Integer getPointsAllowedTwentyOneTwentySeven() {
		return pointsAllowedTwentyOneTwentySeven;
	}

	public void setPointsAllowedTwentyOneTwentySeven(Integer pointsAllowedTwentyOneTwentySeven) {
		this.pointsAllowedTwentyOneTwentySeven = pointsAllowedTwentyOneTwentySeven;
	}

	public Integer getPointsAllowedTwentyEightThirtyFour() {
		return pointsAllowedTwentyEightThirtyFour;
	}

	public void setPointsAllowedTwentyEightThirtyFour(
			Integer pointsAllowedTwentyEightThirtyFour) {
		this.pointsAllowedTwentyEightThirtyFour = pointsAllowedTwentyEightThirtyFour;
	}

	public Integer getPointsAllowedThirtyFive() {
		return pointsAllowedThirtyFive;
	}

	public void setPointsAllowedThirtyFive(Integer pointsAllowedThirtyFive) {
		this.pointsAllowedThirtyFive = pointsAllowedThirtyFive;
	}
	
}
