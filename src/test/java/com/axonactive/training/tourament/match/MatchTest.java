/**
 * 
 */
package com.axonactive.training.tourament.match;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;

import javax.persistence.EntityManager;

import org.hamcrest.core.Is;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.runners.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;

import com.axonactive.training.tourament.matchresult.MatchResult;
import com.axonactive.training.tourament.team.Team;

/**
 * @author htnguyen
 *
 */
@RunWith(MockitoJUnitRunner.class)
public class MatchTest {
	
	@InjectMocks MatchService matchService;
	
	@Mock EntityManager em;

	private Match match;
	
	private Team a;
	
	private Team b;
	
	Calendar schedule = new GregorianCalendar();
	
	
	@Before
	public void preparation() {
		match = new Match(schedule.getTime(), "Field A01");
		a = new Team("FPT");
		a.setId(1);
		b = new Team("Axon Active Vietnam");
		b.setId(2);
	}
	
	@Test
	public void givenAMatchWhenAddItIntoDBThenNewRecordCreated() {
		
		doAnswer(new Answer<Match>() {

			@Override
			public Match answer(InvocationOnMock invocation) throws Throwable {
				Match match = (Match) invocation.getArguments()[0];
				match.setId(1);
				return match;
			}

		}).when(em).persist(match);
		
		this.matchService.createMatch(match, a, b);
		verify(em, times(1)).persist(match);
		assertThat(match.getId(), Is.is(1));
		
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void whenCreateAMatchOfTwoIndifferentTeamsThenExceptionThrown() {
		
		doAnswer(new Answer<Match>() {

			@Override
			public Match answer(InvocationOnMock invocation) throws Throwable {
				Match match = (Match) invocation.getArguments()[0];
				match.setId(1);
				return match;
			}

		}).when(em).persist(match);
		
		this.matchService.createMatch(match, b, b);
		verify(em, times(1)).persist(match); 
		
	}
	
	@Test
	public void givenFinishedMatchWhenUpdateResultThenTwoResultsUpdateCorrectly() {
		
		
		
	}
	
	
	@After
	public void cleanMatch() {
		match = null;
	}
	
}
