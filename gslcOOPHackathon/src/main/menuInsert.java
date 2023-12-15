package main;

import java.nio.file.FileSystemNotFoundException;
import java.util.List;
import java.util.Scanner;

import connection.Connection;
import repo.UserRepository;
import repo.TeamRepository;

public class menuInsert {
	protected static void menuIns (Scanner ns, Connection conn) {
		int inputTable = 0;
 		do {
 			System.out.println("Which table to insert? 1. User, 2. Team.");
 			inputTable = ns.nextInt();
 		}while(inputTable >2 || inputTable < 1);
		
		
		String name;
		String nim;
		String team;
		
		if(inputTable == 1) {
			System.out.print("add name: ");
			name = ns.next();ns.nextLine();
			System.out.print("add nim: ");
			nim = ns.next();ns.nextLine();
			System.out.print("add team: ");
			team = ns.next();ns.nextLine();
			
			if(checkTeamName(conn, team) == false) {
				System.out.println("\nERROR:  No team named " + team + " in the lists");
				return;
			}
			
			
			if(memberCheck(conn, team) == 0) {
				System.out.println("\nERROR:  Team Full");
				return;
			}else {
				
				try {
					UserRepository userRepository = new UserRepository();
					team = Integer.toString(checkTeamId(conn, team));
					
					userRepository.insert(name + "," + nim + "," + team);
					System.out.println("\nUser Created");
					
				} catch (FileSystemNotFoundException e) {
					// TODO: handle exception
				}
				
			}
			
			
		}else {
			System.out.println("add name: ");
			team = ns.next();ns.nextLine();
			Integer id = checkLastID(conn);
			
			String param = Integer.toString(id)+ ","+ team;
//			System.out.println(param);
//			System.out.println(id);
			try {
				TeamRepository teamRepository = new TeamRepository();
				teamRepository.insert(param);
				
				System.out.println("\nTeam Created");
			} catch (FileSystemNotFoundException e) {
				// TODO: handle exception
			}
			
			
			
		}
		
	}
	
	protected static int checkLastID (Connection conn) {
		int teamID = 0;
        List<String[]> readTeam = conn.readCSV("teams.csv");
        
		for(String [] row: readTeam) {
			teamID += 1;
		}
		
		return teamID;
	}
	
	protected static Integer checkTeamId(Connection conn, String team) {
		String teamId1 = null;
        int i = 0;
        Integer j = 1;
        
        List<String[]> readTeam = conn.readCSV("teams.csv");
        for(String[] row : readTeam){
            for(String rowValue : row){
            	if(i == 1  && rowValue.contentEquals(team)) {
            		teamId1 = j.toString();
            	}i++;
            }
            i = 0;
            j++;
        }
		return Integer.parseInt(teamId1);
	}
	
	protected static boolean checkTeamName(Connection conn, String team) {
		boolean found = false;
        int i = 0;
        Integer j = 1;
        
        List<String[]> readTeam = conn.readCSV("teams.csv");
        for(String[] row : readTeam){
            for(String rowValue : row){
            	if(i == 1  && rowValue.contentEquals(team)) {
            		found = true;
            		break;
            	}i++;
            }
            i = 0;
            j++;
        }
		return found;
	}
	
	protected static int memberCheck(Connection conn, String team) {
		String teamId = null;
        int i = 0;
        Integer j = 1;
        
        List<String[]> readTeam = conn.readCSV("teams.csv");
        for(String[] row : readTeam){
            for(String rowValue : row){
            	if(i == 1  && rowValue.contentEquals(team)) {
            		teamId = j.toString();
            	}i++;
            }
            i = 0;
            j++;
        }
        
//        System.out.println(teamId);
        
        List<String[]> readUser = conn.readCSV("user.csv");
        int count = 0;
        for(String[] row : readUser){
            for(String rowValue : row){
            	if(i == 2  && rowValue.contentEquals(teamId)) {
            		count++;
            	}
//                System.out.print(rowValue + " ");
                i++;
            }
            i = 0;
        }
        
//        System.out.println(count);
    	
		
		//place holder
		if(count > 2) {
			return 0;
		}return 1;
	}
}
