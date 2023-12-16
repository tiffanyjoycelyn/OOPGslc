package repo;

import java.util.ArrayList;




import java.util.List;
import java.util.Scanner;

import connection.Connection;
import model.Model;
import model.User;

public class UserRepository implements Repository{
    
    Connection conn = Connection.getConnectionInstance();

	@Override
	public void read() {
		List<String[]> readUser = conn.readCSV("user.csv");
		
        for(String[] row : readUser){
            for(String rowValue : row){
                System.out.print(rowValue + " ");
            }
            System.out.println();
        }
		
	}
	
	@Override
	public void insert(String str) {
		String[] str1 = str.split(",", 3);
		 List<Model> users = new ArrayList<>();
		 	users.add(new User(str1[0], str1[1], Integer.parseInt(str1[2])));
	        conn.writeCSV(users, "user.csv");
		
	}
	@Override
	public void find(String name, String condition, String joinBool, String joinTableName) {
		String columnName = name;
		String buffColumnFilter = condition;
	    String[] arrOfBuff = buffColumnFilter.split(",");
	   	String columnFiltercondition = arrOfBuff[0].substring(2, (arrOfBuff[0].length()-1));
	   	String columnFilterString = arrOfBuff[1].substring(1, (arrOfBuff[1].indexOf("]")-1));
		String buffBooleanJoinTable = joinBool;
		Boolean joinTableBoolean;
		String teamId = null;
		String teamName = null;
		
		if((buffBooleanJoinTable.toLowerCase()).equals("true")) {
			joinTableBoolean = true;
		}else {
			joinTableBoolean = false;
		}
		
		String buffStringTableNameJoin = joinTableName;
		
		
	List<String[]> readUser = conn.readCSV("user.csv");
	if(buffColumnFilter.isBlank() && columnName.isBlank() && buffBooleanJoinTable.isBlank() && buffStringTableNameJoin.isBlank()) {
		for(String[] row : readUser){
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
		int i = 0; int indexDataFound = 0;
		int flagfound = 0;
		if((columnName.toLowerCase()).contentEquals("name")) {
			if(joinTableBoolean) {
				int x = 0;
			 	Integer j = 1;
			 	
			    for(String[] row : readUser){
			    	for(String rowValue : row){
			    		if(x == 1  && rowValue.contentEquals(columnFilterString)) {
			    			indexDataFound = j;
			    			flagfound = 1;
			            }
			            x++;
			       }
			       x = 0; j++;
			    }
				
			}
			if(flagfound == 0) {
				System.out.println("sorry " + columnName+" "+columnFiltercondition+" "+columnFilterString + " does not exist.");
				return;
			}
			
			for(String[] row : readUser){
	            for(String rowValue : row){
	            	if(i == 1) { // i untuk cek column mana
	            		if(rowValue.contentEquals(columnFilterString)) {
	            			
	            			if(joinTableBoolean && (buffStringTableNameJoin.toLowerCase()).equals("team")) {
	            				//check ada di teamID berapa
	            		        int j = 0;int k = 1;
	            				for(String[] row1 : readUser){
	            		            for(String rowValue1 : row1){
	            		            	if(k == indexDataFound && j == 2) {
	            		            		teamId = rowValue1;
	            		            	}
	            		                j++;
	            		            }
	            		            
	            		            j = 0; k++;
	            		        }
	            				
	            				//check idteam ada di index keberapa
	            				List<String[]> readTeam = conn.readCSV("teams.csv");
	            				int l = 0;int m = 1; int indexDataIDFound = 0;
	            				for(String[] row2 : readTeam){
	            		            for(String rowValue2 : row2){
	            		            	if(l == 0 && rowValue2.contentEquals(teamId)) {
	            		            		indexDataIDFound = m;
	            		            	}
	            		                l++;
	            		            }
	            		            l = 0; m++;
	            		        }
	            				
	            				// check teamname bedasarkan index
	            				int n = 0;int o = 1;
	            				for(String[] row2 : readTeam){
	            		            for(String rowValue2 : row2){
	            		            	if(o == indexDataIDFound && n == 1) {
	            		            		teamName = rowValue2;
	            		            	}
	            		                n++;
	            		            }
	            		            n = 0; o++;
	            		        }
	            				
	            				
	            			}
	            			System.out.println(columnName+" "+columnFiltercondition+" "+columnFilterString + " found ! on team id :" +teamId +" on team named : " +teamName);
	            		}
	            	}
	            	i++;
	            }
	            i = 0;
	        }	
			
		}else if((columnName.toLowerCase().contentEquals("nim"))) {
			
			if(joinTableBoolean) {
				int x = 0;
			 	Integer j = 1;
			 	
			    for(String[] row : readUser){
			    	for(String rowValue : row){
			    		if(x == 0  && rowValue.contentEquals(columnFilterString)) {
			    			indexDataFound = j;
			    			flagfound = 1;
			            }
			            x++;
			       }
			       x = 0; j++;
			    }
				
			}
			if(flagfound == 0) {
				System.out.println("sorry " + columnName+" "+columnFiltercondition+" "+columnFilterString + " does not exist.");
				return;
			}
			
			for(String[] row : readUser){
	            for(String rowValue : row){
	            	if(i == 0) { // i untuk cek column mana
	            		if(rowValue.contentEquals(columnFilterString)) {
	            			
	            			if(joinTableBoolean && (buffStringTableNameJoin.toLowerCase()).equals("team")) {
	            				//check ada di teamID berapa
	            		        int j = 0;int k = 1;
	            				for(String[] row1 : readUser){
	            		            for(String rowValue1 : row1){
	            		            	if(k == indexDataFound && j == 2) {
	            		            		teamId = rowValue1;
	            		            	}
	            		                j++;
	            		            }
	            		            
	            		            j = 0; k++;
	            		        }
	            				
	            				//check idteam ada di index keberapa
	            				List<String[]> readTeam = conn.readCSV("teams.csv");
	            				int l = 0;int m = 1; int indexDataIDFound = 0;
	            				for(String[] row2 : readTeam){
	            		            for(String rowValue2 : row2){
	            		            	if(l == 0 && rowValue2.contentEquals(teamId)) {
	            		            		indexDataIDFound = m;
	            		            	}
	            		                l++;
	            		            }
	            		            l = 0; m++;
	            		        }
	            				
	            				// check teamname bedasarkan index
	            				int n = 0;int o = 1;
	            				for(String[] row2 : readTeam){
	            		            for(String rowValue2 : row2){
	            		            	if(o == indexDataIDFound && n == 1) {
	            		            		teamName = rowValue2;
	            		            	}
	            		                n++;
	            		            }
	            		            n = 0; o++;
	            		        }
	            				
	            				
	            			}
	              			System.out.println(columnName+" "+columnFiltercondition+" "+columnFilterString + " found ! on team id :" +teamId +" on team named : " +teamName);
	            		}
	            	}
	            	i++;
	            }
	            i = 0;
	        }	
		}else if((columnName.toLowerCase().contentEquals("teamid"))) {
			
			if(joinTableBoolean) {
				int x = 0;
			 	Integer j = 1;
			 	
			    for(String[] row : readUser){
			    	for(String rowValue : row){
			    		if(x == 2  && rowValue.contentEquals(columnFilterString)) {
			    			indexDataFound = j;
			    			flagfound = 1;
			            }
			            x++;
			       }
			       x = 0; j++;
			    }
				
			}
			if(flagfound == 0) {
				System.out.println("sorry " + columnName+" "+columnFiltercondition+" "+columnFilterString + " does not exist.");
				return;
			}
			for(String[] row : readUser){
	            for(String rowValue : row){
	            	if(i == 2) { // i untuk cek column mana
	            		if(rowValue.contentEquals(columnFilterString)) {
	            			
	            			if(joinTableBoolean && (buffStringTableNameJoin.toLowerCase()).equals("team")) {
	            				//check ada di teamID berapa
	            		        int j = 0;int k = 1;
	            				for(String[] row1 : readUser){
	            		            for(String rowValue1 : row1){
	            		            	if(k == indexDataFound && j == 2) {
	            		            		teamId = rowValue1;
	            		            	}
	            		                j++;
	            		            }
	            		            
	            		            j = 0; k++;
	            		        }
	            				
	            				//check idteam ada di index keberapa
	            				List<String[]> readTeam = conn.readCSV("teams.csv");
	            				int l = 0;int m = 1; int indexDataIDFound = 0;
	            				for(String[] row2 : readTeam){
	            		            for(String rowValue2 : row2){
	            		            	if(l == 0 && rowValue2.contentEquals(teamId)) {
	            		            		indexDataIDFound = m;
	            		            	}
	            		                l++;
	            		            }
	            		            l = 0; m++;
	            		        }
	            				
	            				// check teamname bedasarkan index
	            				int n = 0;int o = 1;
	            				for(String[] row2 : readTeam){
	            		            for(String rowValue2 : row2){
	            		            	if(o == indexDataIDFound && n == 1) {
	            		            		teamName = rowValue2;
	            		            	}
	            		                n++;
	            		            }
	            		            n = 0; o++;
	            		        }
	            				
	            				
	            			}
	              			System.out.println(columnName+" "+columnFiltercondition+" "+columnFilterString + " found ! on team id :" +teamId +" on team named : " +teamName);
	            		}
	            	}
	            	i++;
	            }
	            i = 0;
	        }	
			if(flagfound == 0) {
				System.out.println("sorry " + columnName+" "+columnFiltercondition+" "+columnFilterString + " does not exist.");
			}
	}
}
	}
	}
