//package com.axonactive.training.tourament.tourament;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import org.junit.After;
//import org.junit.Assert;
//import org.junit.Before;
//import org.junit.Test;
//
//import com.axonactive.training.tourament.match.Match;
//import com.axonactive.training.tourament.table.Group;
//import com.axonactive.training.tourament.team.Team;
//
//public class TouramentTest {
//	private Tourament tourament;
//	private List<Team> teamList = new ArrayList<>();
//	private ArrayList<Group> tableList;
//
//	@Before
//	public void Start() {
//		this.tourament = new Tourament("2018"); 
//		teamList = new ArrayList<>();
//		for (Team team : teamList) {
//			team = new Team("ABC");
//		}
//	}
//
////	@Test (expected = IllegalArgumentException.class) 
////	public void s() {
////		tourament.addTeam(null);
////	}
//	
//	@Test
//	public void givenFileOfTeamListWhenImportIntoTouramentThenGetNumberOfTeamThatRegister() {
//		tourament.importTeams();
//		Assert.assertEquals(9, tourament.getTeamList().size());
//	}
//
//	@Test
//	public void givenAMatchThenAddAResultThenNumberOfResultsIncreaseByOne() {
//		Team teamA = new Team("Team 1"), teamB = new Team("Team 2");
//		Match matchA = new Match(teamA, teamB);
//		int currentResultsOfMatchA = tourament.getMatchResultList().size();
//		Assert.assertEquals(0, tourament.getMatchResultList().size());
//		tourament.updateMatchResult(teamA, matchA, 2, 3);
//		Assert.assertEquals(1, tourament.getMatchResultList().size());
//	}
//	
//	@After
//	public void cleanup () {
//		this.tourament = null;
//	}
//}
