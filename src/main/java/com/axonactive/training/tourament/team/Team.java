package com.axonactive.training.tourament.team;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.axonactive.training.tourament.matchresult.MatchResult;
import com.axonactive.training.tourament.player.Player;
import com.axonactive.training.tourament.table.Group;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tbl_team")
@NamedQueries({
		@NamedQuery(name = "Team.FindAll", query = "Select t FROM Team t")
})
@NoArgsConstructor
@EqualsAndHashCode (exclude = "playerList")
public @Data class Team implements Serializable {
	
	public static final int MAXIMUM_PLAYER_IN_TEAM = 12;
	public static final int MINIMUM_PLAYER_IN_TEAM = 7;
	public static final String QUERY_SHOW_TEAM_LIST = "Team.FindAll";
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "team_name", length = 50, nullable = false, unique = true)
	private String name;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "team")
	private List<Player> playerList;
	
	@ManyToOne
	private Group group;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "team")
	private List<MatchResult> matchResults;
	/**
	 * 
	 * @param name
	 */
	public Team(String name) {
		this.name = name;
		this.playerList  = new ArrayList<>();
	}
	
	public Team(String name, List<Player> players, int score) {
		this.name = name;
		this.playerList  = players;
	}
	
	public boolean checkPlayer(Player player) {
		//Do something
		return true;
	}
	
}
