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
		 if(readTeam.isEmpty()) {
			 System.out.println("no data yet!"); return;
		 }
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
		String teamName = null;
		ArrayList<String> teamsName = new ArrayList<>();
		ArrayList<String> teamsNIM = new ArrayList<>();
		ArrayList<Integer> teamsindex = new ArrayList<>();
		List<String[]> readUser = conn.readCSV("user.csv");
		
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
	}else if(readTeam.isEmpty()) {
		System.out.println("Sorry no data yet.");
	}else {
		int flagfound = 0;
		if((columnName.toLowerCase()).contentEquals("teamname")) {
			int i = 0;int indexDataFound = 0; int  j =1;
			for(String[] row : readTeam){
	            for(String rowValue : row){
	            	if(i == 1) { // i untuk cek column mana
	            		if(rowValue.contentEquals(columnFilterString)) {
	            			indexDataFound = j;
	            			flagfound = 1;
	            			
	            		}
	            	}
	            	i++;
	            }
	            i = 0;j++;
	        }
			if(flagfound == 0) {
				System.out.println("sorry " + columnName+" "+columnFiltercondition+" "+columnFilterString + " does not exist.");
				return;
			}
			teamName = columnFilterString;
			int k = 0; int l = 1;
			for(String[] row : readTeam){
	            for(String rowValue : row){
	            	if(l == indexDataFound && k == 0) { // i untuk cek column mana
	            		teamId = rowValue;
	            	}
	            	k++;
	            }
	            k = 0;l++;
	        }
			
			if(joinTableBoolean && (buffStringTableNameJoin.toLowerCase()).equals("user")) {
				int m = 0; int n = 1;
				for(String[] row1 : readUser){
		            for(String rowValue1 : row1){
		            	if(m == 2) {
		            		if(rowValue1.contentEquals(teamId)) {
		            			teamsindex.add(n);
		            		}
		            	}
		            	m++;
		            }
		            m = 0;n++;
		        }
			}
			System.out.print("\n"+columnName + " "+ columnFiltercondition + " " + columnFilterString + " found !");
			System.out.println(" with teamID :" + teamId);
			System.out.println();
			for (int o = 0; o < teamsindex.size(); o++) {
				int p = 0; int q = 1;
				for(String[] row : readUser){
		            for(String rowValue : row){
		            	if(q == teamsindex.get(o) && p == 1) { // i untuk cek column mana
		            		teamsName.add(rowValue);
		            	}
		            	p++;
		            }
		            p = 0;q++;
		        }
				int a = 0; int b = 1;
				for(String[] row : readUser){
		            for(String rowValue : row){
		            	if(b == teamsindex.get(o) && a == 0) { // i untuk cek column mana
		            		teamsNIM.add(rowValue);
		            	}
		            	a++;
		            }
		            a = 0;b++;
		        }
			}
			if(teamsName.size() == 3) {
				System.out.println("-- Team "+teamName + "is FULL !");
			}
			System.out.println("\n"+teamsName.size()+" Members on "+teamName );
			if(teamsName.size()!= 0) {
				System.out.println("Lists member on " + teamName +" with teamID " + columnFilterString +": ");
				for (int m = 0; m < teamsName.size(); m++) {
					// print nama yg ada di team tersebut
					System.out.print((m+1) + ". ");
					System.out.print(teamsNIM.get(m)+ " - ");
					System.out.println(teamsName.get(m));
				}
			}else {
				System.out.println("Sorry no member on " + teamName + "yet.");
			}
			
		}else if((columnName.toLowerCase().contentEquals("teamid"))) {
			int i = 0;int indexDataFound = 0; int  j =1;
			for(String[] row : readTeam){
	            for(String rowValue : row){
	            	if(i == 0) { // i untuk cek column mana
	            		if(rowValue.contentEquals(columnFilterString)) {
	            			indexDataFound = j;
	            			flagfound = 1;
	            			
	            		}
	            	}
	            	i++;
	            }
	            i = 0;j++;
	        }
			teamId = columnFilterString;
			int k = 0; int l = 1;
			for(String[] row : readTeam){
	            for(String rowValue : row){
	            	if(l == indexDataFound && k == 1) { // i untuk cek column mana
	            		teamName = rowValue;
	            	}
	            	k++;
	            }
	            k = 0;l++;
	        }
			
			if(joinTableBoolean && (buffStringTableNameJoin.toLowerCase()).equals("user")) {
				int m = 0; int n = 1;
				for(String[] row1 : readUser){
		            for(String rowValue1 : row1){
		            	if(m == 2) {
		            		if(rowValue1.contentEquals(columnFilterString)) {
		            			teamsindex.add(n);
		            		}
		            	}
		            	m++;
		            }
		            m = 0;n++;
		        }
			}
			if(flagfound == 0) {
				System.out.println("sorry " + columnName+" "+columnFiltercondition+" "+columnFilterString + " does not exist.");
				return;
			}
			System.out.print("\n"+columnName + " "+ columnFiltercondition + " " + columnFilterString + " found !");
			System.out.println(" with teamName :" + teamName);
			System.out.println();
			for (int o = 0; o < teamsindex.size(); o++) {
				int p = 0; int q = 1;
				for(String[] row : readUser){
		            for(String rowValue : row){
		            	if(q == teamsindex.get(o) && p == 1) { // i untuk cek column mana
		            		teamsName.add(rowValue);
		            	}
		            	p++;
		            }
		            p = 0;q++;
		        }
				int a = 0; int b = 1;
				for(String[] row : readUser){
		            for(String rowValue : row){
		            	if(b == teamsindex.get(o) && a == 0) { // i untuk cek column mana
		            		teamsNIM.add(rowValue);
		            	}
		            	a++;
		            }
		            a = 0;b++;
		        }
			}
			if(teamsName.size() == 3) {
				System.out.println("-- Team "+teamName + "is FULL !");
			}
			System.out.println("\n"+teamsName.size()+" Members on "+teamName );
			if(teamsName.size()!= 0) {
				System.out.println("Lists member on " + teamName +" with teamID " + columnFilterString +": ");
				for (int m = 0; m < teamsName.size(); m++) {
					// print nama yg ada di team tersebut
					System.out.print((m+1) + ". ");
					System.out.print(teamsNIM.get(m)+ " - ");
					System.out.println(teamsName.get(m));
				}
			}else {
				System.out.println("Sorry no member on " + teamName + "yet.");
			}
			
		} 
	}
}
		
		
	}

