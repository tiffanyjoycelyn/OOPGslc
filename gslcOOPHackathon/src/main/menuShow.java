package main;

import java.util.Scanner;
import connection.Connection;
import repo.UserRepository;
import repo.TeamRepository;

public class menuShow {
	public static void menuSho(Scanner ns, Connection conn) {
		System.out.println("Which table to show? 1. User, 2.Team. ");
		int inputTable =  ns.nextInt();
		System.out.println("Want to filter by condition? 1. Yes, 2. No. ");
		int inputCondition = ns.nextInt();
		
		
		if(inputCondition == 2) {
			switch (inputTable) {
			case 1:
				//user
				UserRepository userRepository = new UserRepository();
				userRepository.read();
				System.out.println("\n");
				break;
			case 2:
				//team
				 TeamRepository teamRepository = new TeamRepository();
				 teamRepository.read();
				 System.out.println("\n");
				break;
			default:
				break;
			}
		}else if(inputCondition ==1) {
			System.out.println("add condition, separate by semicolon. ");
			System.out.println("Example : name;=;kevin");
			String condition = ns.next();ns.nextLine();
			String [] str = condition.split(";", 3);
			
			// index list
			// 0 -> columnName
			// 1 -> condition (=, <=, etc)
			// 2 -> value
			
			//columnName, [kondisi, value], boolJoin, 
			
			String value = "[\"" + str[1] + "\",\"" + str[2] + "\"]" ;
			
			switch (inputTable) {
			case 1:
				//user
				UserRepository userRepository = new UserRepository();
				
				userRepository.find(str[0], value, "true", "team");
				break;
			case 2:
				//team
				TeamRepository teamRepository = new TeamRepository();
				teamRepository.find(str[0], value, "true" , "user");
				
				break;
			default:
				break;
			}
		}
		
	}
}
