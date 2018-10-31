/**
 * 
 */
package com.axonactive.training.tourament.match;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.axonactive.training.tourament.matchresult.MatchResult;
import com.axonactive.training.tourament.team.Team;

/**
 * @author htnguyen
 *
 */

public class MatchService {

	@PersistenceContext
	EntityManager em;

	public void createMatch(Match match, Team a, Team b) {
		
		if(a.equals(b)) {
			throw new IllegalArgumentException("Cannot create a match with two teams that are the same.");
		}
		
		match.getMatchResults().add(new MatchResult(0, a));
		match.getMatchResults().add(new MatchResult(0, b));
		
		em.persist(match);
	}

}
