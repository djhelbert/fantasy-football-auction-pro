package com.fantasy.football.auctionpro.dao;

import java.util.List;

import javax.persistence.Query;

import com.fantasy.football.auctionpro.entity.Team;

/**
 * Team DAO
 * 
 * @author Derek
 * 
 */
public class TeamDao extends BaseJpaDao<Team,Long> {

	/**
	 * Constructor
	 */
	public TeamDao() {
		super(Team.class);
	}
	
	/**
	 * Team Count
	 * 
	 * @return
	 */
	public long count() {
		Query q = getEntityManager().createNamedQuery("Team.count");
		return (Long) q.getSingleResult();
	}

	/**
	 * Get All Teams
	 * 
	 * @return List
	 */
	@SuppressWarnings("unchecked")
	public List<Team> getAllTeams() {
		Query query = getEntityManager().createNamedQuery("Team.findAll");
		return query.getResultList();
	} 

	/**
	 * Get Favorite team
	 * 
	 * @param position
	 * 
	 * @return List
	 */
	public Team getFavoriteTeam() {
		try {
			Query query = getEntityManager().createNamedQuery("Team.findFavorite");
			return (Team)query.getSingleResult();
		}
		catch( javax.persistence.NoResultException err) {
			return null;
		}
	} 
}
