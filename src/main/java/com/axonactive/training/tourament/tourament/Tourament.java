package com.axonactive.training.tourament.tourament;
import java.util.ArrayList;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;

import com.axonactive.training.tourament.match.Match;
import com.axonactive.training.tourament.matchresult.MatchResult;
import com.axonactive.training.tourament.table.Group;
import com.axonactive.training.tourament.team.*;
import com.axonactive.training.tourament.player.Gender;
import com.axonactive.training.tourament.player.Player;
import lombok.Data;
import java.util.stream.Stream;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import java.util.stream.Collectors;
import com.axonactive.training.excel.ExcelReader;
import static com.axonactive.training.excel.ExcelReader.asString;
import static com.axonactive.training.excel.ExcelReader.asDouble;

public @Data class Tourament {
	
	public static final int MINIMUN_TEAM_NUMBER_IN_ONE_TABLE = 4;
	
	private String name;
	
	private ArrayList<Team> teamList;
	
	private ArrayList<Match> matchList;
	
	private ArrayList<MatchResult> matchResultList;
	
	private ArrayList<Group> tableList;
	
	public Tourament(String name) {
		this.name = name;
		this.teamList = new ArrayList<>();
		this.matchList =  new ArrayList<>();
		this.matchResultList = new ArrayList<>();
		this.tableList = new ArrayList<>();
	}
	
	/**
	 * 
	 */
	
	public void addTeam(Team team) {
		if(Objects.isNull(team)) {
			throw new IllegalArgumentException("The input Team is null");
		}
;		
		if(teamList.contains(team)) {
			throw new IllegalArgumentException("The input Team was Exist");
		}
		
//		if(teamList.size() < 7) {
//			String message = String.format("The input Team isn't enough Player. Current Player: %s. Require Player: %s",
//												teamList.size(), Team.MINIMUM_PLAYER_IN_TEAM);
//			throw new IllegalArgumentException(message);
//		}
		
		this.teamList.add(team);
	}
	                                                                                                                                                                                                                                                                                                      
	public void arrangeTeamIntoTable() {
		
		if(teamList.size() <= MINIMUN_TEAM_NUMBER_IN_ONE_TABLE) {
			throw new IllegalArgumentException("Team number isn't enough to arrange");
		}
		else {
			
			int teamSize = teamList.size();
			
			int tableSize = teamSize / MINIMUN_TEAM_NUMBER_IN_ONE_TABLE;
			
			int surplusTeam = teamSize %  MINIMUN_TEAM_NUMBER_IN_ONE_TABLE;
			
			for(int i = 0; i < tableSize; i++) {
				char TableName = (char)(i + 65);
				Group tempTable = new Group(Character.toString(TableName));
				tableList.add(tempTable);
			}
			
			for(int i = 0; i < teamSize; i++) {
				tableList.get(i/MINIMUN_TEAM_NUMBER_IN_ONE_TABLE).addTeam(teamList.get(i));
			}
			
			for(int i = 0; i < surplusTeam - 1; i++) {
				tableList.get(i).addTeam(teamList.get(i));
			}
		}
	}
	
	public void scheduleTableRound() {
		for(Group table : tableList) {
			int teamNumber = table.getTeamList().size();
			int matchNumber = teamNumber * (teamNumber - 1) / 2;
			
			for(int i = 0; i < matchNumber; i++) {
				Match match = new Match(/*table.getTeamList().get(i % MINIMUN_TEAM_NUMBER_IN_ONE_TABLE), 
										table.getTeamList().get((i+1) % MINIMUN_TEAM_NUMBER_IN_ONE_TABLE)*/);
				
				matchList.add(match);
			}
		}
	}
	
	public void updateMatchResult(Team team, Match match, int goal, int score) {
		MatchResult matchResult = new MatchResult(goal, team);
		matchResultList.add(matchResult);
	}
	
	static Function<Row, Player> mapper() {
        return (row) -> {
            Iterator<Cell> cells = row.cellIterator();
            String fullname = asString(cells.next()),
            		socialInsuranceNumber = asString(cells.next());
    		int number = (int) asDouble(cells.next());
    		Date bod = cells.next().getDateCellValue();
    		String genderString = asString(cells.next());
    		Gender gender = Gender.UNKNOWN;
    		switch (genderString) {
    			case "MALE":
    				gender = Gender.MALE;
    				break;
    			case "FEMALE":
    				gender = Gender.FEMALE;
    				break;
    			default:
    				gender = Gender.UNKNOWN;
    				break;
    		}
            return new Player(
            			fullname,
            			socialInsuranceNumber,
            			number,
            			bod,
            			gender
            		);
        };
    }

	public int importTeams() {
		// TODO Auto-generated method stub
		int totalSheet = 9; // first tab
        boolean sheetContainsHeaderRow = true;
        for (int tab = 0; tab < totalSheet; tab++) {
			String teamName = ExcelReader.loadSheetName(tab, "resources/dataset/TeamTestData", "teams");
	        Stream<Player> teamStream = ExcelReader.load(mapper(), sheetContainsHeaderRow, tab, "resources/dataset/TeamTestData", "teams");
	        List<Player> players = teamStream.collect(Collectors.toList());
	        ArrayList<Player> newTeamPlayers = new ArrayList<>();
	        for (int i = 0; i < players.size(); i++) {
	        	newTeamPlayers.add(players.get(i));
		        System.out.println(String.format("Name: %s. Social Insurance Number: %s. Number: %d. Birthday: %s. Gender: %s. ", players.get(i).getName(), players.get(i).getSocialInsurranceNumber(), players.get(i).getNumber(), players.get(i).getDateOfBirth().toString(), players.get(i).getGender().toString()));
	        }
	        Team newTeam = new Team(teamName, newTeamPlayers,-1);
	        this.addTeam(newTeam);
		}
        return this.getTeamList().size();
	}
}
