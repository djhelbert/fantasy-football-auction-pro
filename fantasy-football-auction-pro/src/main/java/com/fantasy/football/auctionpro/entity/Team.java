package com.fantasy.football.auctionpro.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;

/**
 * Team
 * 
 * @author Derek
 *
 */
@Entity
@NamedQueries({
	@NamedQuery(name = "Team.findAll", query = "SELECT t FROM Team t"),
	@NamedQuery(name = "Team.findFavorite", query = "SELECT t FROM Team t where t.favorite = true"),
	@NamedQuery(name = "Team.count", query = "SELECT COUNT(t) FROM Team t")
})
public class Team implements Serializable {

	private static final long serialVersionUID = 4447664554287833821L;

	/** ID */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id = null;

	/** Team Name */
	@Column(nullable = false, length=30)
	private String name;

	/** Team Owner Name */
	@Column(nullable = true, length=40)
	private String owner;

	/** Email Address */
	@Column(nullable = true, length=40)
	private String email;
	
	/** Phone Number */
	@Column(nullable = true, length=12)
	private String phone;
	
	/** Budget */
	@Column(nullable = false)
	private Integer budget = 0;
	
	/** Favorite */
	@Column(name="FAV", nullable = false)
	private Boolean favorite = Boolean.FALSE;
	
	/** Team Players */
	@OneToMany(mappedBy="team", fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
	@OrderBy(value = "pickNumber")
	@JoinColumn(name="TEAMID", referencedColumnName="ID")
	private List<Player> teamPlayers = new ArrayList<Player>();
	
	/**
	 * Constructor
	 */
	public Team() {
	}

	/**
	 * Constructor
	 * 
	 * @param name
	 * @param owner
	 */
	public Team(String name,String owner) {
		this.name  = name;
		this.owner = owner;
	}

	/**
	 * Constructor
	 * 
	 * @param name
	 * @param owner
	 */
	public Team(String name,String owner,int budget) {
		this.name   = name;
		this.owner  = owner;
		this.budget = budget;
	}
	
	/**
	 * Get ID
	 * 
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
	public void setId(final Long id) {
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
	 * Set Name
	 * 
	 * @param name
	 */
	public void setName(final String name) {
		this.name = name;
	}

	/**
	 * Get Owner
	 * 
	 * @return String
	 */
	public String getOwner() {
		return owner;
	}

	/**
	 * Set Owner
	 * 
	 * @param owner
	 */
	public void setOwner(String owner) {
		this.owner = owner;
	}

	/**
	 * Get Balance
	 * 
	 * @return Integer
	 */
	public Integer getBalance() {
		return new Integer(getBudget()-getSpent());
	}
	
	/**
	 * Get Amount Spent
	 * 
	 * @return Integer
	 */
	public Integer getSpent() {
		int temp = 0;
		
		for(Player p : teamPlayers) {
			temp += p.getPrice().intValue();
		}
		
		return new Integer(temp);
	}
	
	/**
	 * Get Team Budget
	 * 
	 * @return Integer
	 */
	public Integer getBudget() {
		return budget;
	}

	/**
	 * Set Team Budget
	 * 
	 * @param budget
	 */
	public void setBudget(Integer budget) {
		this.budget = budget;
	}

	/**
	 * Get Email
	 * 
	 * @return String
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Set Email
	 * 
	 * @param email
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * Get Phone
	 * 
	 * @return String
	 */
	public String getPhone() {
		return phone;
	}

	/**
	 * Set Phone Number
	 * 
	 * @param phone
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}

	/**
	 * Get Favorite
	 * 
	 * @return Boolean
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
	 * Get Team Players
	 * 
	 * @return
	 */
	public List<Player> getTeamPlayers() {
		return teamPlayers;
	}

	/**
	 * Set Team Players
	 * 
	 * @param teamPlayers
	 */
	public void setTeamPlayers(List<Player> teamPlayers) {
		this.teamPlayers = teamPlayers;
	}
	
	@Override
	public String toString() {
		return name + " (" + owner + ")";
	}
}
