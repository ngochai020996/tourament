/**
 * 
 */
package com.axonactive.training.tourament.player;

import java.net.URI;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonValue;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

/**
 * @author htnguyen
 *
 */
@Stateless
@Path("players")
public class PlayerResource {
	@PersistenceContext
	EntityManager em;
	
	@EJB
	PlayerService playerService;
	
	@Context
	UriInfo uriInfo;
	
	@GET
	@Path("{playerId}")
	@Produces({MediaType.APPLICATION_JSON})
	public Response showPlayer(@PathParam("playerId") Integer playerId) {
		Player result = this.playerService.loadPlayerById(playerId);
		if(Objects.isNull(result)) {
			return Response.status(404).build();
		}
		PlayerDto dto = this.playerService.convertToDto(result);
		return Response.ok(dto).build();
	}
	 
	@GET
	public JsonArray buildJsonPlayer() {
		JsonArrayBuilder playerList = Json.createArrayBuilder();
		List<Player> playersFromDB = em.createQuery("Select p From Player p").getResultList();
		for (Player player : playersFromDB) {
			playerList.add(this.createJsonPlayer(player.getName(), player.getSocialInsurranceNumber(), player.getNumber(), player.getDateOfBirth(),	 player.getGender()));
		}
		return playerList.build();
	}
	
	@POST
	@Consumes({MediaType.APPLICATION_JSON})
	public Response addPlayer(@Valid PlayerDto member) {
		Integer newPlayerId = this.playerService.addPlayer(member.getTeamId(), this.playerService.convertToEntity(member));
		URI location = uriInfo.getAbsolutePathBuilder().path(newPlayerId.toString()).build();
		return Response.created(location).build();
	}
	
	JsonObject createJsonPlayer(String name, String socialnumber, int number, Date dob, Gender gender ) {
		return Json.createObjectBuilder().
				add("name", name).
				add("socialnumber", socialnumber).
				add("number", number).
				add("dob", dob.toString()).
				add("gender", gender.toString()).build();
	}
}
