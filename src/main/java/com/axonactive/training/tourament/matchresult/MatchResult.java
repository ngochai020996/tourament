package com.axonactive.training.tourament.matchresult;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.axonactive.training.tourament.match.Match;

import com.axonactive.training.tourament.team.Team;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "match_result")
public @Data class MatchResult implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "goal", nullable = true, length = 1)
	private int goal;
	
	@ManyToOne
	private Match match;
	
	@ManyToOne
	private Team team;
	
	public MatchResult(int goal, Team team) {
		this.goal = goal;
		this.team = team;
	}
}
