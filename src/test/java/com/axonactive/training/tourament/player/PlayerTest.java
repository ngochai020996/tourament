///**
// * 
// */
//package com.axonactive.training.tourament.player;
//
//import java.util.Calendar;
//import java.util.GregorianCalendar;
//
//import org.junit.After;
//import org.junit.Before;
//import org.junit.Test;
//
//import com.axonactive.training.tourament.team.Team;
//
///**
// * @author htnguyen
// *
// */
//public class PlayerTest {
//
//	Team team;
//	
//	Calendar dob = new GregorianCalendar();
//	
//	@Before
//	public void createTeam() {
//		team = new Team("Axon Active Vietnam");
//	}
//	
//	@Test
//	public void givenNonExistingPlayerInDBWhenAddValidPlayerThenNewRecordInDBIsCreated() {
//		Player player = new Player("Nguyễn Công Phượng", "023658333", 7, dob.getTime() , Gender.MALE);
//		
//	}
//	
//	
//	@After
//	public void removeTeam() {
//		team = null;
//	}
//	
//}
