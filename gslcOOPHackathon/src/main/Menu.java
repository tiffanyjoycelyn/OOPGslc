package main;

import java.util.Scanner;
import connection.Connection;

public class Menu {
	
	public static void mainMenu(Connection conn) {
		Scanner ns = new Scanner(System.in);
		
		int inputMain = 0;
		
		while(inputMain != 4) {
			System.out.print("\n1. MenuUtama\n2. Insert Data\n3. Show\n4. Exit\n>> ");
			inputMain = ns.nextInt();
			
			switch (inputMain) {
			case 2:
				menuInsert.menuIns(ns, conn);
				break;
			case 3:
				menuShow.menuSho(ns, conn);
				break;

			default:
				break;
			}
		}
		
		ns.close();
		
	}
	
	
	

	
	
	
	
	
	
}
