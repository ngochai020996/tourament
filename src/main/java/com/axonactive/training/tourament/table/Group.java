package com.axonactive.training.tourament.table;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.axonactive.training.tourament.team.*;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tbl_table")
@NoArgsConstructor
public @Data class Group implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "table_name", nullable = false, unique = true, length = 50)
	private String name;
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "group_id")
	private List<Team> teamList;
	
	
	static final int MINIMUM_NUMBER_OF_PLAYER = 7;
	
	/**
	 * @param name
	 */
	public Group(String name) {
		this.name = name;
		this.teamList = new ArrayList<>();
	}
	
	public void addTeam(Team team) {
		if(Objects.isNull(team)) {
			throw new IllegalArgumentException("The input Team is null");
		}
		
		if(teamList.contains(team)) {
			throw new IllegalArgumentException("The input Team was Exist");
		}
		if( team.getPlayerList().size() < MINIMUM_NUMBER_OF_PLAYER ) {
			throw new IllegalArgumentException("This team doesn't have enough player.");
		}
		this.teamList.add(team);
	}
	
}
