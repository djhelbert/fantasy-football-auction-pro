package com.fantasy.football.auctionpro.dao;

import java.util.List;

import javax.persistence.Query;

import com.fantasy.football.auctionpro.entity.DefenseWeek;

/**
 * Defense Week Dao
 * 
 * @author dhelbert
 */
public class DefenseWeekDao extends BaseJpaDao<DefenseWeek,Long> {

	/**
	 * Constructor
	 */
	public DefenseWeekDao() {
		super(DefenseWeek.class);
	}

	/**
	 * Get Players By Team
	 * 
	 * @param team
	 * 
	 * @return List
	 */
	@SuppressWarnings("unchecked")
	public List<DefenseWeek> getDefenseWeeksByTeam(String nflTeam) {
		Query query = getEntityManager().createNamedQuery("DefenseWeek.findByTeam");
		return query.setParameter("nflTeam", nflTeam).getResultList();
	}
	
}
