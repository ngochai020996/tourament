package com.axonactive.training.tourament.team;

import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;

import javax.persistence.EntityManager;

import org.hamcrest.core.Is;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.runners.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;

import com.axonactive.training.tourament.player.Gender;
import com.axonactive.training.tourament.player.Player;
import com.axonactive.training.tourament.team.Team;

@RunWith(MockitoJUnitRunner.class)
public class TeamTest {

	@InjectMocks TeamService teamService;
	
	@Mock EntityManager em;
	
	private Player newPlayer;
	
	private Team team;
	
	private Calendar calendar = new GregorianCalendar();

	@Before
	public void prepareData() {
		calendar.set(1998, 0, 25);
		newPlayer = new Player("Doan Van Hau", "125468463", 4, calendar.getTime(), Gender.MALE);
		team = new Team("Axon Active Vietnam");
		team.setId(1);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void givenFullMemberTeamInDBWhenAddNewPlayerThenExcetionThrown() {
		List<Player> playerList = new ArrayList<>();
		for (int i = 0; i < 12; i++) {
			Player player = new Player("Nguyen Van " + i, "12546846" + i, 4+i, calendar.getTime(), Gender.MALE);
			playerList.add(player);
		}
		team.setPlayerList(playerList);

		doAnswer(new Answer<Team>() {
			
			@Override
			public Team answer(InvocationOnMock invocation) throws Throwable {
				return team;
			}
			
		}).when(em).find(Team.class, 1);
		
		this.teamService.addPlayer(team.getId(), newPlayer);
	}
	
	@Test
	public void givenATeamWithoutMembersWhenAddNewValidPlayerThenOneNewRecordCreated() {
//		doAnswer(new Answer<Team>(){
//			@Override
//			public Team answer(InvocationOnMock invocation) throws Throwable{
//				return team;
//			}
//		}).when(em).find(Team.class, 1);
		
		when(em.find(Team.class, 1)).thenReturn(team);
		
		doAnswer(new Answer<Player>() {
			@Override
			public Player answer(InvocationOnMock invocation) throws Throwable{
				Player player = (Player) invocation.getArguments()[0];
				player.setId(1);
				return player;
			}
		}).when(em).persist(newPlayer);
		
		this.teamService.addPlayer(1, newPlayer);
		verify(em, times(1)).persist(newPlayer);
		assertThat(newPlayer.getId(), Is.is(1));
	}
	
	@After
	public void removeTeam() {
		team = null;
	}
}
