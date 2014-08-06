package com.fantasy.football.auctionpro.dao;

import java.util.List;

import javax.persistence.Query;

import com.fantasy.football.auctionpro.entity.Player;
import com.fantasy.football.auctionpro.entity.Team;

/**
 * Player DAO
 * 
 * @author Derek
 * 
 */
public class PlayerDao extends BaseJpaDao<Player,Long> {

	/**
	 * Constructor
	 */
	public PlayerDao() {
		super(Player.class);
	}
	
	/**
	 * Count Players
	 * 
	 * @return long
	 */
	public long count() {
		Query q = getEntityManager().createNamedQuery("Player.count");
		return (Long) q.getSingleResult();
	}

	/**
	 * Get Players By Position With NULL team
	 * 
	 * @param position
	 * 
	 * @return List
	 */
	@SuppressWarnings("unchecked")
	public List<Player> getPlayersByPosition(String position) {
		Query query = getEntityManager().createNamedQuery("Player.findByPosition");
		return query.setParameter("position", position).getResultList();
	} 

	/**
	 * Get All Players By Position
	 * 
	 * @param position
	 * @return List
	 */
	@SuppressWarnings("unchecked")
	public List<Player> getAllPlayersByPosition(String position) {
		Query query = getEntityManager().createNamedQuery("Player.findAllByPosition");
		return query.setParameter("position", position).getResultList();
	} 
	
	/**
	 * Get Players By Name
	 * 
	 * @param name
	 * @return List
	 */
	@SuppressWarnings("unchecked")
	public List<Player> getPlayersByName(String name) {
		Query query = getEntityManager().createNamedQuery("Player.findByName");
		return query.setParameter("name", name).getResultList();
	} 

	/**
	 * Get Players By Team
	 * 
	 * @param team
	 * 
	 * @return List
	 */
	@SuppressWarnings("unchecked")
	public List<Player> getPlayersByTeam(Team team) {
		Query query = getEntityManager().createNamedQuery("Player.findByTeam");
		return query.setParameter("team", team).getResultList();
	}

}
