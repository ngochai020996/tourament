package com.axonactive.training.tourament.player;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

import javax.faces.view.facelets.Facelet;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Converter;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.axonactive.training.tourament.team.Team;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tbl_player")
@NoArgsConstructor @AllArgsConstructor
public @Data class Player implements Serializable {
	

	/**
	 * 
	 * @param name
	 * @param socialInsurranceNumber
	 * @param number
	 * @param dateOfBirth
	 * @param gender
	 */
	
	@Id
	@GeneratedValue(strategy =  GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name="full_name", length = 50, nullable = false)
	private String name;
	
	@Column(name = "social_insurrance_number", length = 10, nullable = false, unique = true)
	private String socialInsurranceNumber;
	
	@Column(name = "number", length = 2, nullable = false)
	private int number;
	
	@Column(name = "dob", nullable = false)
	@Temporal(TemporalType.DATE)
	private Date dateOfBirth;
	
	@Convert(converter = GenderConverter.class)
	@Column(name = "gender")	
	private Gender gender;
	
	@ManyToOne
	private Team team;
	
	public Player(String name, String socialInsurranceNumber, int number, Date dateOfBirth, Gender gender) {
		this.name = name;
		this.socialInsurranceNumber = socialInsurranceNumber;
		this.number = number;
		this.dateOfBirth = dateOfBirth;
		this.gender = gender;
	}
	
	
}
