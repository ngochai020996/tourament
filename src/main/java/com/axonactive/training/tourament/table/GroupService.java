/**
 * 
 */
package com.axonactive.training.tourament.table;

import static com.axonactive.training.excel.ExcelReader.asDouble;
import static com.axonactive.training.excel.ExcelReader.asString;

import java.net.URL;
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

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.axonactive.training.excel.ExcelReader;
import com.axonactive.training.tourament.player.Gender;
import com.axonactive.training.tourament.player.Player;
import com.axonactive.training.tourament.team.Team;

/**
 * @author htnguyen
 *
 */

@Singleton
@Startup
public class GroupService {

	@PersistenceContext(name = "TournamentDS")
	EntityManager em;
	List<Group> groupList = new ArrayList<>();
//	final Logger logger = LoggerFactory.getLogger(GroupService.class);
	public void addTeam(Team team, Integer groupId) {
		Group group = em.find(Group.class, groupId);
		
		if(Objects.isNull(group)) {
			throw new IllegalArgumentException("Group doesn't exist!!!");
		}
		
		team.setGroup(group);
		em.persist(team);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
//	public void initGroup() {
//		List<Team> teamList = generateGroupFromFile();
//		Group groupA = new Group("Group 01");
//		Group groupB = new Group("Group 02");
//		List<Team> groupATeams = new ArrayList<>();
//		List<Team> groupBTeams = new ArrayList<>();
//		for (int i = 0; i < teamList.size(); i++) {
//			if(i % 2 == 0)
//				groupATeams.add(teamList.get(i));
//			else
//				groupBTeams.add(teamList.get(i));
//		}
//		em.persist(groupA);
//		em.persist(groupB);
//	}
	
//	public List<Team> generateGroupFromFile() {
//		List<Team> teamList = new ArrayList<>();
//		String filePath = this.getClass().getResourceAsStream("dataset/TeamTestData").toString();
//		int totalSheet = 9;
//        boolean sheetContainsHeaderRow = true;
//        for (int tab = 0; tab < totalSheet; tab++) {
//			String teamName = ExcelReader.loadSheetName(tab, filePath, "teams");
//	        Stream<Player> teamStream = ExcelReader.load(mapper(), sheetContainsHeaderRow, tab, filePath, "teams");
//	        List<Player> players = teamStream.collect(Collectors.toList());
//	        ArrayList<Player> newTeamPlayers = new ArrayList<>();
//	        for (int i = 0; i < players.size(); i++) {
//	        	newTeamPlayers.add(players.get(i));
//	        }
//	        Team newTeam = new Team(teamName, newTeamPlayers, -1);
//	        teamList.add(newTeam);
//		}
//        return teamList;
//	}
//	
//	static Function<Row, Player> mapper() {
//        return (row) -> {
//            Iterator<Cell> cells = row.cellIterator();
//            String fullname = asString(cells.next()),
//            		socialInsuranceNumber = asString(cells.next());
//    		int number = (int) asDouble(cells.next());
//    		Date bod = cells.next().getDateCellValue();
//    		String genderString = asString(cells.next());
//    		Gender gender = Gender.UNKNOWN;
//    		switch (genderString) {
//    			case "MALE":
//    				gender = Gender.MALE;
//    				break;
//    			case "FEMALE":
//    				gender = Gender.FEMALE;
//    				break;
//    			default:
//    				gender = Gender.UNKNOWN;
//    				break;
//    		}
//            return new Player(
//            			fullname,
//            			socialInsuranceNumber,
//            			number,
//            			bod,
//            			gender
//            		);
//        };
//    }
	
	
}
