package repo;

import java.util.ArrayList;


import java.util.List;
import java.util.Scanner;

import connection.Connection;
import model.Model;
import model.Team;

public class TeamRepository implements Repository {

	 Connection conn = Connection.getConnectionInstance();

	 @Override
	public void read() {
		 List<String[]> readTeam = conn.readCSV("teams.csv");
		 
	        for(String[] row : readTeam){
	            for(String rowValue : row){
	                System.out.print(rowValue + " ");
	            }
	            System.out.println();
	        }
}

	 @Override
	public void insert(String str) {
        List<Model> teams = new ArrayList<>();
        String[] str1 = str.split(",", 2);
        teams.add(new Team(Integer.parseInt(str1[0]) + 1, str1[1]));
        conn.writeCSV(teams, "teams.csv");
	}
	 
	@Override
	public void find(String Name, String condition, String joinBool, String joinTableName) {
		//format input = columnName, buffColumnFilter, buffBoleeanJoinTable
		String columnName = Name; 
		String buffColumnFilter = condition;
	    String[] arrOfBuff = buffColumnFilter.split(",");
	   	String columnFiltercondition = arrOfBuff[0].substring(2, (arrOfBuff[0].length()-1));
	   	String columnFilterString = arrOfBuff[1].substring(1, (arrOfBuff[1].indexOf("]")-1));
	   	String teamId = null;
	   	Boolean joinTableBoolean; 
		String buffBooleanJoinTable = joinBool;
		String buffStringTableNameJoin = joinTableName;
		
		if((buffBooleanJoinTable.toLowerCase()).equals("true")) {
			joinTableBoolean = true;
		}else {
			joinTableBoolean = false;
		}
		
	List<String[]> readTeam = conn.readCSV("teams.csv");
	if(buffColumnFilter.isBlank() && columnName.isBlank() && buffBooleanJoinTable.isBlank() && buffStringTableNameJoin.isBlank()) {
		for(String[] row : readTeam){
            for(String rowValue : row){
                System.out.print(rowValue + " ");
            }
            System.out.println();
        }	
	}else if(buffColumnFilter.isBlank() && columnName.isBlank()) {
		System.out.println("error...");
	}else if(columnName.isBlank() && !(buffColumnFilter.isBlank())) {
		System.out.println("error...");
	}else if(buffColumnFilter.isBlank() && !(columnName.isBlank())) {
		System.out.println("error...");
	}else if(buffBooleanJoinTable.isBlank() && !(buffStringTableNameJoin.isBlank())) {
		System.out.println("error...");
	}else if(buffStringTableNameJoin.isBlank() && !(buffBooleanJoinTable.isBlank())) {
		System.out.println("error...");
	}else {
		int i = 0;
		if((columnName.toLowerCase()).contentEquals("teamname")) {
			for(String[] row : readTeam){
	            for(String rowValue : row){
	            	System.out.println(rowValue);
	            	System.out.println(columnFilterString);
	            	if(i == 1) { // i untuk cek column mana
	            		if(rowValue.contentEquals(columnFilterString)) {
	            			System.out.println(columnName + " "+ columnFiltercondition + " " + columnFilterString + " found !");
	            		}
	            	}
	            	i++;
	            }
	            System.out.println();
	            i = 0;
	        }	
		}else if((columnName.toLowerCase().contentEquals("teamid"))) {
			for(String[] row : readTeam){
	            for(String rowValue : row){
	            	if(i == 0) { // i untuk cek column mana
	            		if(rowValue.contentEquals(columnFilterString)) {
	            			System.out.println(columnName + " "+ columnFiltercondition + " " + columnFilterString + " found !");
	            		}
	            	}
	            	i++;
	            }
	            i = 0;
	        }	
		} 
	}
}
		
		
	}

