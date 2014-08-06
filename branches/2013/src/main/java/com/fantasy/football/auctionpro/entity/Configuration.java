package com.fantasy.football.auctionpro.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;

/**
 * Configuration
 * 
 * @author dhelbert
 *
 */
@Entity
@NamedQueries({
	@NamedQuery(name = "Configuration.find",  query = "SELECT c FROM Configuration c"),
	@NamedQuery(name = "Configuration.count", query = "SELECT COUNT(c) FROM Configuration c")
})
public class Configuration implements Serializable {

	/** Serial Version UID */
	private static final long serialVersionUID = 4874013167636014569L;

	/**
	 * Constructor
	 */
	public Configuration() {	
	}
	
	/** ID */
	@Id
	@Column(name="CONF_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	/** League Name */
	@Column(name="LEAGUENAME", nullable = false, length=30)
	private String leagueName = "Name";
	
	/** League URL */
	@Column(name="LEAGUEURL", nullable = true, length=80)
	private String leagueUrl = "http://football.fantasysports.yahoo.com/league/name";
	
	/** Max Teams */
	@Column(name="MAXTEAMS", nullable = false)
	private Integer maxTeams = 10;
	
	/** Max Roster Size */
	@Column(name="MAXROSTERSZ", nullable = false)
	private Integer maxRosterSize = 14;
	
	/** Maximum Default Budget */
	@Column(name="MAXBUDGET", nullable = false)
	private Integer maxBudget = 115;

	/** Default Auction Budget */
	@Column(name="DEFBUDGET", nullable = false)
	private Integer defaultBudget = 100;
	
	/** Starting QB */
	@Column(name="STARTQB", nullable = false)
	private Integer startQb = 1;
	
	/** Starting RB */
	@Column(name="STARTRB", nullable = false)
	private Integer startRb = 2;
	
	/** Starting TE */
	@Column(name="STARTTE", nullable = false)
	private Integer startTe = 1;

	/** Starting WR */
	@Column(name="STARTWR", nullable = false)
	private Integer startWr = 2;
	
	/** Starting RB or WR */
	@Column(name="STARTRBWR", nullable = false)
	private Integer startRbWr = 1;

	/** Starting Defenses */
	@Column(name="STARTDEF", nullable = false)
	private Integer startDef = 1;

	/** Starting Kickers */
	@Column(name="STARTK", nullable = false)
	private Integer startK = 1;
	
	/** Draft Date */
	@Column(name="DRAFTDT", nullable = true)
	private Date draftDate = new java.util.Date();
	
	/**  (Total Money) - (Minimum salary for each roster spot) */
	@Column(name="DISPCASH", nullable = false)
	private Integer disposableCash = 0;
	
	/** Available Points */
	@Column(name="AVP", nullable = false)
	private Integer availablePoints = 0;
	
	/** Player Data */
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="SCORESYSID", nullable = false)
	private ScoreSystem scoreSystem;
	
	/**
	 * Get ID
	 * @return Long
	 */
	public Long getId() {
		return id;
	}

	/**
	 * Set ID
	 * 
	 * @param id
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * Set League Name
	 * 
	 * @return String
	 */
	public String getLeagueName() {
		return leagueName;
	}

	/**
	 * Set League Name
	 * 
	 * @param leagueName
	 */
	public void setLeagueName(String leagueName) {
		this.leagueName = leagueName;
	}

	/**
	 * Get League URL
	 *  
	 * @return String
	 */
	public String getLeagueUrl() {
		return leagueUrl;
	}

	/**
	 * Set League URl
	 * 
	 * @param leagueUrl
	 */
	public void setLeagueUrl(String leagueUrl) {
		this.leagueUrl = leagueUrl;
	}

	/**
	 * Get Max Teams
	 * 
	 * @return Integer
	 */
	public Integer getMaxTeams() {
		return maxTeams;
	}

	/**
	 * Set Max Teams
	 * 
	 * @param maxTeams
	 */
	public void setMaxTeams(Integer maxTeams) {
		this.maxTeams = maxTeams;
	}

	/**
	 * Get Max Roster Size
	 * 
	 * @return Integer
	 */
	public Integer getMaxRosterSize() {
		return maxRosterSize;
	}

	/**
	 * Set Max Roster Size
	 * 
	 * @param maxRosterSize
	 */
	public void setMaxRosterSize(Integer maxRosterSize) {
		this.maxRosterSize = maxRosterSize;
	}

	/**
	 * Get Max Budget
	 * 
	 * @return Integer
	 */
	public Integer getMaxBudget() {
		return maxBudget;
	}

	/**
	 * Set Max Budget
	 * 
	 * @param maxBudget
	 */
	public void setMaxBudget(Integer maxBudget) {
		this.maxBudget = maxBudget;
	}

	/**
	 * Get Draft Date
	 * 
	 * @return date
	 */
	public Date getDraftDate() {
		return draftDate;
	}

	/**
	 * Set Draft Date
	 * 
	 * @param draftDate
	 */
	public void setDraftDate(Date draftDate) {
		this.draftDate = draftDate;
	}

	/**
	 * Get Starting Qb
	 * 
	 * @return Integer
	 */
	public Integer getStartQb() {
		return startQb;
	}

	/**
	 * Set Starting Qb
	 * 
	 * @param startQb
	 */
	public void setStartQb(Integer startQb) {
		this.startQb = startQb;
	}

	/**
	 * Get Starting RB
	 * 
	 * @return
	 */
	public Integer getStartRb() {
		return startRb;
	}

	/**
	 * Set Starting RB
	 * 
	 * @param startRb
	 */
	public void setStartRb(Integer startRb) {
		this.startRb = startRb;
	}

	/**
	 * Get Start TE
	 * 
	 * @return
	 */
	public Integer getStartTe() {
		return startTe;
	}

	/**
	 * Set Starting TE
	 * 
	 * @param startTe
	 */
	public void setStartTe(Integer startTe) {
		this.startTe = startTe;
	}

	/**
	 * Get Starting RB or WR
	 * 
	 * @return
	 */
	public Integer getStartRbWr() {
		return startRbWr;
	}

	/**
	 * Set Starting RB or WR
	 * 
	 * @param startRbWr
	 */
	public void setStartRbWr(Integer startRbWr) {
		this.startRbWr = startRbWr;
	}

	/**
	 * Get Starting DEF
	 * 
	 * @return Integer
	 */
	public Integer getStartDef() {
		return startDef;
	}

	/**
	 * Set Starting DEF
	 * 
	 * @param startDef
	 */
	public void setStartDef(Integer startDef) {
		this.startDef = startDef;
	}

	/**
	 * Get Starting K
	 * 
	 * @return
	 */
	public Integer getStartK() {
		return startK;
	}

	/**
	 * Set Starting K
	 * 
	 * @param startK
	 */
	public void setStartK(Integer startK) {
		this.startK = startK;
	}

	/**
	 * Get Starting WR
	 * 
	 * @return Integer
	 */
	public Integer getStartWr() {
		return startWr;
	}

	/**
	 * Set Starting WR
	 * 
	 * @param startWr
	 */
	public void setStartWr(Integer startWr) {
		this.startWr = startWr;
	}

	/**
	 * Get Default Budget
	 * 
	 * @return Integer
	 */
	public Integer getDefaultBudget() {
		return defaultBudget;
	}

	/**
	 * Set Default Budget
	 * 
	 * @param defaultBudget
	 */
	public void setDefaultBudget(Integer defaultBudget) {
		this.defaultBudget = defaultBudget;
	}

	/**
	 * Get PPR Flag
	 * 
	 * @return Boolean 
	 */
	public Boolean getPpr() {
		return scoreSystem.getReceptionPts() > 0 ? Boolean.TRUE : Boolean.FALSE;
	}

	/**
	 * Get Disposable Cash
	 * 
	 * @return
	 */
	public Integer getDisposableCash() {
		return disposableCash;
	}

	/**
	 * Set Disposable Cash
	 * 
	 * @param disposableCash
	 */
	public void setDisposableCash(Integer disposableCash) {
		this.disposableCash = disposableCash;
	}

	/**
	 * Get Available Points
	 * 
	 * @return Integer
	 */
	public Integer getAvailablePoints() {
		return availablePoints;
	}

	/**
	 * Set Available Points
	 * 
	 * @param availablePoints
	 */
	public void setAvailablePoints(Integer availablePoints) {
		this.availablePoints = availablePoints;
	}

	/**
	 * @return the scoreSystem
	 */
	public ScoreSystem getScoreSystem() {
		return scoreSystem;
	}

	/**
	 * @param scoreSystem the scoreSystem to set
	 */
	public void setScoreSystem(ScoreSystem scoreSystem) {
		this.scoreSystem = scoreSystem;
	}

}
