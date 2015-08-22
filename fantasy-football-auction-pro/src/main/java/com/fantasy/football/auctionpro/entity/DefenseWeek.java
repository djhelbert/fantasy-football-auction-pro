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
 * Defense Data
 * 
 * @author dhelbert
 */
@Entity
@NamedQueries( {
	@NamedQuery(name = "DefenseWeek.findByTeam", query = "SELECT dw FROM DefenseWeek dw where dw.nflTeam = :nflTeam order by dw.week")
} )
public class DefenseWeek implements Serializable {

	/** Serial Version UID */
	private static final long serialVersionUID = 3995319983424894767L;
	
	/** ID */
	@Id
	@Column(name="ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	/** NFL Team */
	@Column(name="NFLTEAM", nullable = false, length=3)
	private String nflTeam;
	
	@Column(name="WEEK")
	private Integer week;
	
	@Column(name="PTSALLOWED")
	private Integer pointsAllowed;

	/**
	 * Constructor
	 */
	public DefenseWeek() {	
	}
	
	/**
	 * Constructor
	 *
	 * @param nflTeam
	 * @param week
	 * @param pointsAllowed
	 */
	public DefenseWeek(String nflTeam, Integer week, Integer pointsAllowed) {
		this.nflTeam       = nflTeam;
		this.week          = week;
		this.pointsAllowed = pointsAllowed;
	}
	
	/**
	 * Get Id
	 * 
	 * @return long
	 */
	public Long getId() {
		return id;
	}

	/**
	 * Set Id
	 * 
	 * @param id
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * Get Week
	 * 
	 * @return Integer
	 */
	public Integer getWeek() {
		return week;
	}

	/**
	 * Set Week
	 * 
	 * @param week
	 */
	public void setWeek(Integer week) {
		this.week = week;
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
	 * @param pointsAllowed
	 */
	public void setPointsAllowed(Integer pointsAllowed) {
		this.pointsAllowed = pointsAllowed;
	}

	/**
	 * Get NFL Team
	 * 
	 * @return String
	 */
	public String getNflTeam() {
		return nflTeam;
	}

	/**
	 * Set NFL Team
	 * 
	 * @param nflTeam
	 */
	public void setNflTeam(String nflTeam) {
		this.nflTeam = nflTeam;
	}
	
	@Override
	public String toString() {
		String tmp = "TM:" + nflTeam + " WK: " + week + " PA: " + pointsAllowed;
		
		return tmp;
	}
}
