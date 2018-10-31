/**
 * 
 */
package com.axonactive.training.tourament.team;

import static com.axonactive.training.excel.ExcelReader.asDouble;
import static com.axonactive.training.excel.ExcelReader.asString;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.axonactive.training.excel.ExcelReader;
import com.axonactive.training.tourament.player.Gender;
import com.axonactive.training.tourament.player.Player;

/**
 * @author htnguyen
 *
 */
//@Singleton
//@Startup
public class TeamService {

	@PersistenceContext(name = "TournamentDS")
	EntityManager em;
	
	public void addPlayer(Integer teamId, Player newPlayer) {
		
		Team team = em.find(Team.class, teamId);
		
		if(Objects.isNull(team)) {
			throw new IllegalArgumentException("Team doesn't exist.");
		}
		
		if(team.getPlayerList().size() >= team.MAXIMUM_PLAYER_IN_TEAM) {
			throw new IllegalArgumentException("This team is full.");
		}
		
		newPlayer.setTeam(team);
		em.persist(newPlayer);
	}
	
}
