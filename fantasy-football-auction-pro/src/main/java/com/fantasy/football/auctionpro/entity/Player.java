package com.fantasy.football.auctionpro.entity;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;

/**
 * Player
 * 
 * @author Derek
 *
 */
@Entity
@NamedQueries( {
		@NamedQuery(name = "Player.findAll",          query = "SELECT p FROM Player p"),
		@NamedQuery(name = "Player.findByTeam",       query = "SELECT p FROM Player p where team = :team order by p.pickNumber"),
		@NamedQuery(name = "Player.findByPosition",   query = "SELECT p FROM Player p where position = :position and team is null order by p.rank"),
		@NamedQuery(name = "Player.findAllByPosition",query = "SELECT p FROM Player p where position = :position order by playerData.fantasyPoints desc"),
		@NamedQuery(name = "Player.findByName",       query = "SELECT p FROM Player p where name like :name and team is null order by p.rank"),
		@NamedQuery(name = "Player.count",            query = "SELECT COUNT(p) FROM Player p")
} )
public class Player implements Serializable {

	/** Serial Version UID */
	private static final long serialVersionUID = 3070647459300182117L;

	/** ID */
	@Id
	@Column(name="ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	/** Name */
	@Column(name="NAME", nullable = false, length=30)
	private String name;

	/** Rank */
	@Column(name="RANK", nullable = false)
	private Integer rank = 0;
	
	/** Position */
	@Column(name="POS", nullable = false, length=3)
	private String position;

	/** NFL Team */
	@Column(name="NFLTEAM", nullable = false, length=3)
	private String nflTeam;

	/** Bye Week */
	@Column(name="BYEWK", nullable = false)
	private Integer byeWeek = 0;

	/** Favorite Flag */
	@Column(name="FAV", nullable = false)
	private Boolean favorite = Boolean.FALSE;
	
	/** Price Paid */
	@Column(name="PRICE", nullable = false)
	private Integer price = 0;

	/** Pick Number */
	@Column(name="PICKNUM", nullable = true)
	private Integer pickNumber;
	
	/** Player Data */
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="PLAYERDATAID", nullable = false)
	private PlayerData playerData;
	
	/** Team */
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="TEAMID", nullable = true)
	private Team team;
	
	/**
	 * Constructor
	 */
	public Player() {
	}
	
	/**
	 * Constructor
	 * 
	 * @param name
	 * @param position
	 * @param nflTeam
	 */
	public Player(String name, String position, String nflTeam) {
		this.name = name;
		this.position = position;
		this.nflTeam = nflTeam;
	}
	
	/**
	 * Get ID - Primary Key
	 * 
	 * @return Long
	 */
	public Long getId() {
		return id;
	}

	/**
	 * Set ID - Primary Key
	 * 
	 * @param id
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * Get Name
	 * 
	 * @return String
	 */
	public String getName() {
		return name;
	}

	/**
	 * Get Last Name - Parses Name
	 * 
	 * @return String
	 */
	public String getLastName() {
		if( name.indexOf(' ') == -1 ) {
			return name;
		}
		else {
			return name.split(" ")[1];
		}
	}
	
	/**
	 * Set Name
	 * 
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Get Position
	 * 
	 * @return
	 */
	public String getPosition() {
		return position;
	}

	/**
	 * Set Position
	 * 
	 * @param position
	 */
	public void setPosition(String position) {
		this.position = position;
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

	/**
	 * Get Bye Week
	 * 
	 * @return Integer
	 */
	public Integer getByeWeek() {
		return byeWeek;
	}

	/**
	 * Set Bye Week
	 *
	 * @param byeWeek
	 */
	public void setByeWeek(Integer byeWeek) {
		this.byeWeek = byeWeek;
	}

	/**
	 * Get Price
	 *
	 * @return Integer
	 */
	public Integer getPrice() {
		return price;
	}

	/**
	 * Set Price
	 *
	 * @param price
	 */
	public void setPrice(Integer price) {
		this.price = price;
	}

	/**
	 * Get Rank
	 * 
	 * @return Integer
	 */
	public Integer getRank() {
		return rank;
	}

	/**
	 * Set Rank
	 * 
	 * @param rank
	 */
	public void setRank(Integer rank) {
		this.rank = rank;
	}

	/**
	 * Get Favorite
	 * 
	 * @return
	 */
	public Boolean getFavorite() {
		return favorite;
	}

	/**
	 * Set Favorite
	 * 
	 * @param favorite
	 */
	public void setFavorite(Boolean favorite) {
		this.favorite = favorite;
	}

	/**
	 * Get Pick Number
	 * 
	 * @return Integer
	 */
	public Integer getPickNumber() {
		return pickNumber;
	}

	/**
	 * set Pick Number
	 * 
	 * @param pickNumber
	 */
	public void setPickNumber(Integer pickNumber) {
		this.pickNumber = pickNumber;
	}

	/**
	 * Get Team - Can Be Null
	 * 
	 * @return Team
	 */
	public Team getTeam() {
		return team;
	}

	/**
	 * Set Team - Can Be Null
	 * 
	 * @param team
	 */
	public void setTeam(Team team) {
		this.team = team;
	}
	
	/**
	 * Get Player Data - One to One Not Null
	 * 
	 * @return PlayerData
	 */
	public PlayerData getPlayerData() {
		return playerData;
	}

	/**
	 * Set Player Data - One to One Not Null
	 * 
	 * @param playerData
	 */
	public void setPlayerData(PlayerData playerData) {
		this.playerData = playerData;
	}
	
	@Override
	public String toString() {
		return getLastName() + " " + position + (price > 0 ? " $" + price : "");
	}
	
}
