package main;

//import java.util.ArrayList;

import connection.Connection;
import repo.TeamRepository;

//import java.util.List;

public class Main {
	
    public static void main(String[] args) {
        Connection conn = Connection.getConnectionInstance();
        Menu.mainMenu(conn);
        
//        TeamRepository tr = new TeamRepository();
//        
//        tr.find("yoyoyoyo");
        
    
    }
}
    
    
  
