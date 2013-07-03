package com.fantasy.football.auctionpro.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

/**
 * Player Data
 * 
 * @author Derek
 *
 */
@Entity
@NamedQueries( {
	@NamedQuery(name = "PlayerData.find", query = "SELECT pd FROM PlayerData pd"),
	@NamedQuery(name = "PlayerData.count", query = "SELECT COUNT(pd) FROM PlayerData pd")
} )
public class PlayerData implements Serializable {

	/** Serial Version UID*/
	private static final long serialVersionUID = -5859214516852343762L;

	/** ID - Primary Key */
	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	/** Passing TD */
	@Column(name = "PASSTD", nullable = false)
	private Integer passingTd = 0;
	
	/** Rush TD */
	@Column(name = "RUSHTD", nullable = false)
	private Integer rushingTd = 0;
	
	/** REC TD */
	@Column(name = "RECTD", nullable = false)
	private Integer receptionTd = 0;
	
	/** Return TD */
	@Column(name = "RETTD", nullable = false)
	private Integer returnTd = 0;
	
	/** DEF TD */
	@Column(name = "DEFTD", nullable = false)
	private Integer defenseTd = 0;
	
	/** */
	@Column(name = "REC", nullable = false)
	private Integer reception = 0;
	
	/** Passing INT */
	@Column(name = "PASSINT", nullable = false)
	private Integer passingInt  = 0;
	
	/** DEF INT */
	@Column(name = "DEFINT", nullable = false)
	private Integer defenseInt  = 0;
	
	/** Pass Yards */
	@Column(name = "PASSYD", nullable = false)
	private Integer passingYards = 0;
	
	/** Rush Yards */
	@Column(name = "RUSHYD", nullable = false)
	private Integer rushingYards = 0;
	
	/** Rec Yards */
	@Column(name = "RECYD", nullable = false)
	private Integer receivingYards = 0;
	
	/** Sacks */
	@Column(name = "SACK", nullable = false)
	private Integer sack = 0;
	
	/** Safeties */
	@Column(name = "SAFETY", nullable = false)
	private Integer safety = 0;
	
	/** Points Allowed */
	@Column(name = "PA", nullable = false)
	private Integer pointsAllowed = 0;
	
	/** Fumbles Lost */
	@Column(name = "FUML", nullable = false)
	private Integer fumbleLost = 0;
	
	/** Fumbles Recovered */
	@Column(name = "FUMR", nullable = false)
	private Integer fumbleRecovery = 0;
	
	/** Blocks */
	@Column(name = "BLK", nullable = false)
	private Integer blockedKick = 0;
	
	/** Extra Points */
	@Column(name = "EXP", nullable = false)
	private Integer extraPoint = 0;
	
	/** FG 0-19*/
	@Column(name = "FGZERNIN", nullable = false)
	private Integer fieldGoalZeroNineteen = 0;
	
	/** FG 20-29 */
	@Column(name = "FGTWTWN", nullable = false)
	private Integer fieldGoalTwentyTwentyNine = 0;

	/** FG 30-39 */
	@Column(name = "FGTHTHN", nullable = false)
	private Integer fieldGoalThirtyThirtyNine = 0;
	
	/** FG 40-49 */
	@Column(name = "FGFOFON", nullable = false)
	private Integer fieldGoalFortyFortyNine = 0;
	
	/** FG 50+ */
	@Column(name = "FGFP", nullable = false)
	private Integer fieldGoalFifty = 0;

	/** Fantasy Points */
	@Column(name = "FANPTS", nullable = false)
	private Integer fantasyPoints = 0;
	
	/** Value Based Decision Value */
	@Column(name = "VBD", nullable = false)
	private Integer vbd = 0;
	
	/**
	 * Constructor
	 */
	public PlayerData() {	
	}
	
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
	 * Get Passing Td
	 * 
	 * @retun Integer
	 */
	public Integer getPassingTd() {
		return passingTd;
	}

	/**
	 * Set Passing Td
	 * 
	 * @param passingTd
	 */
	public void setPassingTd(Integer passingTd) {
		this.passingTd = passingTd;
	}

	/**
	 * Get Rushing Td
	 * 
	 * @return Integer
	 */
	public Integer getRushingTd() {
		return rushingTd;
	}

	/**
	 * Set Rushing TD
	 * 
	 * @param rushingTd
	 */
	public void setRushingTd(Integer rushingTd) {
		this.rushingTd = rushingTd;
	}

	/**
	 * Get Reception Td
	 * 
	 * @return Integer
	 */
	public Integer getReceptionTd() {
		return receptionTd;
	}

	/**
	 * Set Reception TD
	 * 
	 * @param receptionTD
	 */
	public void setReceptionTd(Integer receptionTd) {
		this.receptionTd = receptionTd;
	}

	/**
	 * Get Return TD
	 * 
	 * @return Integer
	 */
	public Integer getReturnTd() {
		return returnTd;
	}

	/**
	 * Set Return Td
	 * 
	 * @param returnTD
	 */
	public void setReturnTd(Integer returnTd) {
		this.returnTd = returnTd;
	}

	/**
	 * Get Defense TD
	 * 
	 * @return Integer
	 */
	public Integer getDefenseTd() {
		return defenseTd;
	}

	/**
	 * Set Defense TD
	 * 
	 * @param defenseTd
	 */
	public void setDefenseTd(Integer defenseTd) {
		this.defenseTd = defenseTd;
	}

	/**
	 * Get Reception
	 * 
	 * @return Integer
	 */
	public Integer getReception() {
		return reception;
	}

	/**
	 * Set Reception
	 * 
	 * @param reception
	 */
	public void setReception(Integer reception) {
		this.reception = reception;
	}

	/**
	 * Get Passing Int
	 * 
	 * @return Integer
	 */
	public Integer getPassingInt() {
		return passingInt;
	}

	/**
	 * Set Passing Int
	 * 
	 * @param passingInt
	 */
	public void setPassingInt(Integer passingInt) {
		this.passingInt = passingInt;
	}

	/**
	 * Get Defense Int
	 * 
	 * @return Integer
	 */
	public Integer getDefenseInt() {
		return defenseInt;
	}

	/**
	 * Set Defense Int
	 * 
	 * @parma defenseInt
	 */
	public void setDefenseInt(Integer defenseInt) {
		this.defenseInt = defenseInt;
	}

	/**
	 * Get Passing Yards
	 * 
	 * @return Integer
	 */
	public Integer getPassingYards() {
		return passingYards;
	}

	/**
	 * Set Passing Yards
	 * 
	 * @param passingYards
	 */
	public void setPassingYards(Integer passingYards) {
		this.passingYards = passingYards;
	}

	/**
	 * Get Rushing Yards
	 * 
	 * @return Integer
	 */
	public Integer getRushingYards() {
		return rushingYards;
	}

	/**
	 * Set Rushing Yards
	 * 
	 * @param rushingYards
	 */
	public void setRushingYards(Integer rushingYards) {
		this.rushingYards = rushingYards;
	}

	/**
	 * Get Receiving Yards
	 * 
	 * @return Integer
	 */
	public Integer getReceivingYards() {
		return receivingYards;
	}

	/**
	 * Set Receiving Yards
	 * 
	 * @param receivingYards
	 */
	public void setReceivingYards(Integer receivingYards) {
		this.receivingYards = receivingYards;
	}

	/**
	 * Get Sacks
	 * 
	 * @return Integer
	 */
	public Integer getSack() {
		return sack;
	}

	/**
	 * Set Sacks
	 * 
	 * @param sack
	 */
	public void setSack(Integer sack) {
		this.sack = sack;
	}

	/** 
	 * Get Safety
	 * 
	 * @return Integer
	 */
	public Integer getSafety() {
		return safety;
	}

	/**
	 * Set Safety
	 * 
	 * @param safety
	 */
	public void setSafety(Integer safety) {
		this.safety = safety;
	}

	/**
	 * Get Points Allowed
	 * 
	 * @return Integer
	 */
	public Integer getPointsAllowed() {
		return pointsAllowed;
	}

	/**
	 * Set Points Allowed
	 * 
	 * @parma pointsAllowed
	 */
	public void setPointsAllowed(Integer pointsAllowed) {
		this.pointsAllowed = pointsAllowed;
	}

	/**
	 * Get Fumble Lost
	 * 
	 * @return Integer
	 */
	public Integer getFumbleLost() {
		return fumbleLost;
	}

	/**
	 * Set Fumble Lost
	 * 
	 * @param fumbleLost
	 */
	public void setFumbleLost(Integer fumbleLost) {
		this.fumbleLost = fumbleLost;
	}

	/**
	 * Get Fumble Recovery
	 * 
	 * @return Integer
	 */
	public Integer getFumbleRecovery() {
		return fumbleRecovery;
	}

	/**
	 * Set Fumble Recovery
	 * 
	 * @param fumbleRecovery
	 */
	public void setFumbleRecovery(Integer fumbleRecovery) {
		this.fumbleRecovery = fumbleRecovery;
	}

	/**
	 * Get Blocked Kick
	 * 
	 * @return Integer
	 */
	public Integer getBlockedKick() {
		return blockedKick;
	}

	/**
	 * Set Blocked Kick
	 * 
	 * @param blockedKick
	 */
	public void setBlockedKick(Integer blockedKick) {
		this.blockedKick = blockedKick;
	}

	/**
	 * Get Extra Point
	 * 
	 * @return Integer
	 */
	public Integer getExtraPoint() {
		return extraPoint;
	}

	/**
	 * Set Extra Point
	 * 
	 * @param extraPoint
	 */
	public void setExtraPoint(Integer extraPoint) {
		this.extraPoint = extraPoint;
	}

	/**
	 * Get Field Goal Zero - Nineteen
	 * 
	 * @return Integer
	 */
	public Integer getFieldGoalZeroNineteen() {
		return fieldGoalZeroNineteen;
	}

	/**
	 * Set Field Goal Zero - Nineteen
	 * 
	 * @param fieldGoalZeroNineteen
	 */
	public void setFieldGoalZeroNineteen(Integer fieldGoalZeroNineteen) {
		this.fieldGoalZeroNineteen = fieldGoalZeroNineteen;
	}

	/**
	 * Get Field Goal Twenty - Twenty Nine
	 * 
	 * @return Integer
	 */
	public Integer getFieldGoalTwentyTwentyNine() {
		return fieldGoalTwentyTwentyNine;
	}

	/**
	 * Set Field Goal Twenty - Twenty Nine
	 * 
	 * @param fieldGoalTwentyTwentyNine
	 */
	public void setFieldGoalTwentyTwentyNine(Integer fieldGoalTwentyTwentyNine) {
		this.fieldGoalTwentyTwentyNine = fieldGoalTwentyTwentyNine;
	}

	/**
	 * Get Field Goal Thirty - Thirty Nine
	 * 
	 * @return Integer
	 */
	public Integer getFieldGoalThirtyThirtyNine() {
		return fieldGoalThirtyThirtyNine;
	}

	/**
	 * Set Field Goal Thirty - Thirty Nine
	 * 
	 * @param fieldGoalThirtyThirtyNine
	 */
	public void setFieldGoalThirtyThirtyNine(Integer fieldGoalThirtyThirtyNine) {
		this.fieldGoalThirtyThirtyNine = fieldGoalThirtyThirtyNine;
	}

	/**
	 * Get Field Goal Forty - Forty Nine
	 * 
	 * @return Integer
	 */
	public Integer getFieldGoalFortyFortyNine() {
		return fieldGoalFortyFortyNine;
	}

	/**
	 * Set Field Goal Forty - Forty Nine
	 * 
	 * @param fieldGoalFortyFortyNine
	 */
	public void setFieldGoalFortyFortyNine(Integer fieldGoalFortyFortyNine) {
		this.fieldGoalFortyFortyNine = fieldGoalFortyFortyNine;
	}

	/**
	 * Get Field Goal Fifty +
	 * 
	 * @return Integer
	 */
	public Integer getFieldGoalFifty() {
		return fieldGoalFifty;
	}

	/**
	 * Set Field Goal Fifty +
	 */
	public void setFieldGoalFifty(Integer fieldGoalFifty) {
		this.fieldGoalFifty = fieldGoalFifty;
	}
	
	/**
	 * Get Fantasy Points
	 * 
	 * @return Integer
	 */
	public Integer getFantasyPoints() {
		return fantasyPoints;
	}

	/**
	 * Set Fantasy Points
	 */
	public void setFantasyPoints(Integer fantasyPoints) {
		this.fantasyPoints = fantasyPoints;
	}

	/**
	 * Get Vbd
	 * 
	 * @return Integer
	 */
	public Integer getVbd() {
		return vbd;
	}

	/**
	 * Set Vbd
	 * 
	 * 
	 */
	public void setVbd(Integer vbd) {
		this.vbd = vbd;
	}

	@Override
	public String toString() {
		String tmp = "";
		
		if( passingTd > 0 )
			tmp += "TD " + passingTd;
		if( passingYards > 0)
			tmp += " PASS YDS " + passingYards;
		
		if(rushingYards > 0)
			tmp += (passingYards > 0 ? " " : "") + "RUSH YDS " + rushingYards;
		if( rushingTd > 0 )
			tmp += " TD " + rushingTd;

		if( reception > 0)
			tmp += (passingYards > 0 || rushingYards > 0 ? " " : "") + "REC " + reception;
		if(receivingYards > 0)
			tmp += " YDS " + receivingYards;
		if( receptionTd > 0 )
			tmp += " TD " + receptionTd;
		
		if(fumbleLost > 0)
			tmp += " FL " + fumbleLost;
		
		if( passingInt > 0)
			tmp += " INT " + passingInt;
		
		if(defenseInt > 0)
			tmp += " INT " + defenseInt;
		if(sack > 0)
			tmp += " S " + sack;
		if(safety > 0)
			tmp += " SF " + safety;
		if(pointsAllowed > 0)
			tmp += " PA " + pointsAllowed;
		if(fumbleRecovery > 0)
			tmp += " FR " + fumbleRecovery;
		if(blockedKick > 0)
			tmp += " BLK " + blockedKick;
		if( defenseTd > 0 )
			tmp += " DEF TD " + defenseTd;
		
		if( extraPoint > 0 ) {
			tmp += " XP " + extraPoint;
			tmp += " FG " + (fieldGoalZeroNineteen + fieldGoalTwentyTwentyNine + fieldGoalThirtyThirtyNine + fieldGoalFortyFortyNine + fieldGoalFifty);
		}
		
		return tmp;
	}
}
