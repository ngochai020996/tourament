package com.axonactive.training.tourament.table;

import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;

import java.awt.List;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.persistence.EntityManager;
import javax.persistence.Table;

import org.hamcrest.CoreMatchers;
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
public class TableTest {

	@InjectMocks
	GroupService groupService;

	@Mock
	EntityManager em;

	private Group group;
	private Calendar calendar;

	@Before
	public void initializeTableInstance() {
		group = new Group("Group A");
		group.setId(1);
		calendar = new GregorianCalendar();
	}

	@Test
	public void givenAGroupWhenAddATeamThenNumberOfTeamInThatGroupIncreasedByOne() {
		Team team = new Team("Axcon Active Vietnam");

		when(em.find(Group.class, 1)).thenReturn(group);

		doAnswer(new Answer<Team>() {
			@Override
			public Team answer(InvocationOnMock invo) throws Throwable {
				Team team = (Team) invo.getArguments()[0];
				team.setId(1);
				return team;
			}
		}).when(em).persist(team);

		this.groupService.addTeam(team, 1);
		verify(em, times(1)).persist(team);
		assertThat(team.getId(), Is.is(1));

	}
	
	

	@After
	public void removeTable() {
		group = null;
	}
}