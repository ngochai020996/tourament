/**
 * 
 */
package com.axonactive.training.tourament.player;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.Map.Entry;

import javax.ejb.Stateless;
import javax.faces.convert.DateTimeConverter;
import javax.json.JsonArray;
import javax.json.JsonNumber;
import javax.json.JsonObject;
import javax.json.JsonString;
import javax.json.JsonValue;
import javax.json.JsonValue.ValueType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.axonactive.training.tourament.team.Team;

/**
 * @author htnguyen
 *
 */
//@Singleton
//@Startup
@Stateless
public class PlayerService {

	@PersistenceContext
	EntityManager em;

	public Integer addPlayer(Integer teamId, Player newPlayer) {
		Team team = em.find(Team.class, teamId);
		if (Objects.isNull(team)) {
			throw new IllegalArgumentException("Team doesn't exist.");
		}
		if (team.getPlayerList().size() >= team.MAXIMUM_PLAYER_IN_TEAM) {
			throw new IllegalArgumentException("This team is full.");
		}
		newPlayer.setTeam(team);
		em.persist(newPlayer);
		return newPlayer.getId();
	}

	public Player convertToEntity(PlayerDto member) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Date dob = new Date();
		try {
			dob = formatter.parse(member.getDateOfBirth());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Player player = new Player(member.getName(),
								   member.getSocialInsurranceNumber(),
								   member.getNumber(),
								   dob,
								   member.getGender());
		return player;
	}

	public Player loadPlayerById(Integer id) {
		return this.em.find(Player.class, id);
	}

	public PlayerDto convertToDto(Player player) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String dobString = formatter.format(player.getDateOfBirth());
		PlayerDto dto = new PlayerDto(player.getName(),
									  player.getSocialInsurranceNumber(),
									  player.getNumber(),
									  dobString,
									  player.getGender(),
									  player.getTeam().getId());
		return dto;
	}
	
}
