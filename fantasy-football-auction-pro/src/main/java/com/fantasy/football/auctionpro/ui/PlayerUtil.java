package com.fantasy.football.auctionpro.ui;

import java.util.List;

import com.fantasy.football.auctionpro.DaoFactory;
import com.fantasy.football.auctionpro.dao.DefenseWeekDao;
import com.fantasy.football.auctionpro.entity.DefenseWeek;
import com.fantasy.football.auctionpro.entity.Player;
import com.fantasy.football.auctionpro.entity.PlayerData;
import com.fantasy.football.auctionpro.entity.ScoreSystem;

/**
 * Player Util
 * 
 * @author dhelbert
 *
 */
public class PlayerUtil {

	/**
	 * Get Fantasy Points - Defense
	 * 
	 * @param p
	 * @param ppr
	 * 
	 * @return int
	 */
	public static int getDefenseFantasyPoints(Player p, ScoreSystem ss) {
		int points = 0;
		
		PlayerData pd = p.getPlayerData();
		
		points += pd.getSack()           * ss.getSackPts();
		points += pd.getSafety()         * ss.getSafetyPts();
		points += pd.getDefenseTd()      * ss.getDefTdPts();
		points += pd.getDefenseInt()     * ss.getDefIntPts();
		points += pd.getBlockedKick()    * ss.getBlockPts();
		points += pd.getFumbleRecovery() * ss.getFumbleRecPts();
		
		DefenseWeekDao defenseWeekDao = DaoFactory.getDefenseWeekDao();
		
		List<DefenseWeek> weeks = defenseWeekDao.getDefenseWeeksByTeam(p.getNflTeam());
		
		for(DefenseWeek week : weeks) {
			if( week.getPointsAllowed() == 0 ) {
				points += ss.getPointsAllowedZero();
			}
			else if( week.getPointsAllowed() >= 1 && week.getPointsAllowed() <= 6 ) {
				points += ss.getPointsAllowedOneSix();
			}
			else if( week.getPointsAllowed() >= 7 && week.getPointsAllowed() <= 13 ) {
				points += ss.getPointsAllowedSevenThirteen();
			}
			else if( week.getPointsAllowed() >= 14 && week.getPointsAllowed() <= 20 ) {
				points += ss.getPointsAllowedFourteenTwenty();
			}
			else if( week.getPointsAllowed() >= 21 && week.getPointsAllowed() <= 27 ) {
				points += ss.getPointsAllowedTwentyOneTwentySeven();
			}
			else if( week.getPointsAllowed() >= 28 && week.getPointsAllowed() <= 34 ) {
				points += ss.getPointsAllowedTwentyEightThirtyFour();
			}
			else if( week.getPointsAllowed() >= 35 ) {
				points += ss.getPointsAllowedThirtyFive();
			}
		}
		
		return points;
	}
	
	/**
	 * Get Fantasy Points - Defense not supported
	 * 
	 * @param p
	 * @param ppr
	 * 
	 * @return int
	 */
	public static int getFantasyPoints(Player p, ScoreSystem ss) {
		int points = 0;
		
		PlayerData pd = p.getPlayerData();
		
		points += pd.getReception();

		points += Math.round(pd.getPassingYards().floatValue() / ss.getPassingYardsPpt());
		points += Math.round(pd.getReceivingYards().floatValue() / ss.getReceivingYardsPpt());
		points += Math.round(pd.getRushingYards().floatValue() / ss.getRushingYardsPpt());
		
		points += ((pd.getReceptionTd() * ss.getReceptionTdPts()) + (pd.getPassingTd() * ss.getPassingTdPts()) + (pd.getRushingTd() * ss.getRushingTdPts()) + (pd.getReturnTd() * ss.getReturnTdPts()));
		
		points += pd.getFumbleLost() * ss.getFumbleLostPts();
		points += pd.getPassingInt() * ss.getPassingIntPts();
		
		points += ((pd.getFieldGoalTwentyTwentyNine() * ss.getFieldGoalTwentyTwentyNinePts()) + (pd.getFieldGoalZeroNineteen() * ss.getFieldGoalZeroNineteenPts()) + (pd.getFieldGoalThirtyThirtyNine() * ss.getFieldGoalThirtyThirtyNinePts()));
		points += pd.getFieldGoalFortyFortyNine() * ss.getFieldGoalFortyFortyNinePts();
		points += pd.getFieldGoalFifty() * ss.getFieldGoalFiftyPts();
		points += pd.getExtraPoint() * ss.getExtraPointPts();
		
		return points;
	}

	/**
	 * Get Position Count
	 * 
	 * @param players
	 * @param position
	 * 
	 * @return int
	 */
	public static int getPositionCount(List<Player> players,String position) {
		int cnt = 0;
		
		if(position == null) {
			return cnt;
		}
		
		for(Player p : players) {
			if(position.equals(p.getPosition())) {
				cnt ++;
			}
		}
		
		return cnt;
	}
	
	/**
	 * Get Bye Week Count
	 * 
	 * @param players
	 * @param week
	 * 
	 * @return int
	 */
	public static int getByeWeekCount(List<Player> players,int week) {
		int cnt = 0;
		
		for(Player p : players) {
			if(p.getByeWeek() == week) {
				cnt ++;
			}
		}
		
		return cnt;
	}
	
}
