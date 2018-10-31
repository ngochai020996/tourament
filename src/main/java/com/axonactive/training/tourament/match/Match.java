package com.axonactive.training.tourament.match;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.axonactive.training.tourament.team.Team;

import lombok.Data;
import lombok.NoArgsConstructor;

import com.axonactive.training.tourament.matchresult.MatchResult;

@Entity
@Table(name = "match")
@NoArgsConstructor
public @Data class Match implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "schedule", nullable = false)
	@Temporal(TemporalType.DATE)
	private Date schedule;
	
	@Column(name = "place", nullable = false, length = 100)
	private String place;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "match")
	private List<MatchResult> matchResults;
	
	public Match(Date started, String field) {
		this.schedule = started;
		this.place = field;
		this.matchResults = new ArrayList<>();
	}
	
	/**
	 * 
	 * @param schedule
	 * @param place
	 * @param team1
	 * @param team2
	 */
	
//	public static boolean canCreate(Match match, ArrayList<Match> listMatch) {
//		int Day = match.schedule.getDate();
//		int Month = match.schedule.getMonth();
//		int Year = match.schedule.getYear();
//		
//		for(Match tempMatch : listMatch) {
//			int tempDay = tempMatch.schedule.getDate();
//			int tempMonth = tempMatch.schedule.getMonth();
//			int tempYear = tempMatch.schedule.getYear();
//			
//			if(Year == tempYear && Month == tempMonth && Day == tempDay && (isExistTeamOnMatch(tempMatch, match.team1) || isExistTeamOnMatch(tempMatch, match.team2))) {
//				return false;
//			}
//		}
//		
//		return true;
//	}
//	
//	public static boolean isExistTeamOnMatch(Match match, Team team) {
//		return team == match.team1 || team == match.team2;			 
//	}

	
}
