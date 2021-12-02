package ga_tech;

import java.util.Arrays;
import java.util.Scanner;


public class Battleship {

	public static void main(String[] args) {
		
		Scanner input = new Scanner(System.in);  // Create a Scanner object
		System.out.println("Welcome to Battleship!" + "\n");
		
		
		int[][] playerCoords1 = new int[5][2];
		int[][] playerCoords2 = new int[5][2];
		char[][] playerGrid1 = new char[5][5];
		char[][] playerGrid2 = new char[5][5];
		
		char[][] playerGridMoves1 = new char[5][5];
		char[][] playerGridMoves2 = new char[5][5];
		
		
		//Player 1 setup
		playerCoords1 = Battleship.getPlayerCoords(playerCoords1, input, "PLAYER 1");
		playerGrid1 = Battleship.getPlayerGrid(playerCoords1);
		Battleship.printBattleShip(playerGrid1);
		for (int i = 0; i<100; i++) {
			System.out.println("\n");
		}
		
		//Player 2 setup
		playerCoords2 = Battleship.getPlayerCoords(playerCoords2, input, "PLAYER 2");
		playerGrid2 = Battleship.getPlayerGrid(playerCoords2);
		Battleship.printBattleShip(playerGrid2);
		for (int i = 0; i<100; i++) {
			System.out.println("\n");
		}	
		
		
		//Game start!!
		String current_attack = "Player 1";
		String current_defense = "Player 2";
		Battleship.playerAttackSequence(current_attack, current_defense, playerGrid2, input);
				
		
	}
	
	
	/**
	 * Returns 2d array of player positions
	 * @param playerArr - 2d array of integers
	 * @param playerName - String ex.) "PLAYER 1"
	 * @return 2d array of integers
	 */
	public static int[][] getPlayerCoords(int[][] playerArr, Scanner inputObj, String playerName){
		
		System.out.println(playerName + ", ENTER YOUR SHIPS' COORDINATES.");
		
//		Loop through and add coordinates
		for (int i = 0; i < playerArr.length; i++) {
			
			System.out.println("Entire Ship " + (i+1) + " location:");

			int firstCoord = inputObj.nextInt();
			int secondCoord = inputObj.nextInt();
			int[] tempArr = new int[]{firstCoord, secondCoord};
			
			
			// Keep looping if coords are outside of range
			while ( (firstCoord >4 || firstCoord < 0) || (secondCoord >4 || secondCoord < 0)){
				
				System.out.println("Invalid coordinates. Choose different coordinates.");
				System.out.println("Entire Ship " + (i+1) + " location:");
				firstCoord = inputObj.nextInt();
				secondCoord = inputObj.nextInt();

				tempArr[0] = firstCoord;
				tempArr[1] = secondCoord;
			}
			

			boolean inArray = Battleship.valueInPlayerArray(playerArr, tempArr);
			
			// Keep looking if coords have already been entered before.
			while (inArray) {
				System.out.println("You already have a ship there. Choose different coordinates.");
				System.out.println("Entire Ship " + (i+1) + " location:");
				firstCoord = inputObj.nextInt();
				secondCoord = inputObj.nextInt();
				
				tempArr[0] = firstCoord;
				tempArr[1] = secondCoord;
				
				inArray = Battleship.valueInPlayerArray(playerArr, tempArr);
			}
			
			
		    // Add values to 2D array
			playerArr[i][0] = firstCoord;
			playerArr[i][1] = secondCoord;
			
			System.out.println(Arrays.deepToString(playerArr));
				
			}

		return playerArr;
	}
	
	
	public static char[][] playerAttackSequence(String playerAttack, String playerDefense, char[][] playerGrid, Scanner inputObj){
		
		System.out.println(playerAttack + ", enter hit row/column:");
		
		int firstCoord = inputObj.nextInt();
		int secondCoord = inputObj.nextInt();
//		int[] tempArr = new int[]{firstCoord, secondCoord};
		
		// Keep looping if coords are outside of range
		while ( (firstCoord >4 || firstCoord < 0) || (secondCoord >4 || secondCoord < 0) ){
			
			System.out.println("Invalid coordinates. Choose different coordinates.");
			System.out.println(playerAttack + ", enter hit row/column:");
			firstCoord = inputObj.nextInt();
			secondCoord = inputObj.nextInt();
		}
		
		// Keep looping if coords have already been fired on spots.
		while (playerGrid[firstCoord][secondCoord]=='X' || playerGrid[firstCoord][secondCoord]=='O') {
			
			System.out.println("You already fired on this spot. Choose different coordinates.");
			System.out.println(playerAttack + ", enter hit row/column:");
			firstCoord = inputObj.nextInt();
			secondCoord = inputObj.nextInt();			
			
		}
		
		// Ship got hit!
		if (playerGrid[firstCoord][secondCoord]=='@'){
			
			System.out.println(playerAttack.toUpperCase() + " HIT PLAYER " + playerDefense.toUpperCase() +"’s SHIP!");
			playerGrid[firstCoord][secondCoord] = 'X';
			
		}
		
		// Ship missed!
		else if (playerGrid[firstCoord][secondCoord]=='-') {
			System.out.println(playerAttack.toUpperCase() + " MISSED!");
			playerGrid[firstCoord][secondCoord] = 'O';
		}
		
		return playerGrid;
		
	}
	
	

	
	
	/**
	 * @param playerCoords - Array of player coords [5][2]
	 * @return Array of player board [5][5]
	 */
	public static char[][] getPlayerGrid(int[][] playerCoords){
		
		char[][] playerGrid = new char[5][5];
		int[] tempCoords = new int[2];
		
		System.out.print("  ");
		for (int row = 0; row < 5; row++) {

			for (int column = 0; column < 5; column++) {
				
				tempCoords[0] = row;
				tempCoords[1] = column;
						
				if (Battleship.valueInPlayerArray(playerCoords, tempCoords)) {
					playerGrid[row][column] = '@';}
				else {
					playerGrid[row][column] = '-';}
				}
				
			}
		
		return playerGrid;
	}
		
		
	/**
	 * @param playerArr - 2d array of player positions
	 * @param coord - array of single coordinate on battleship grid
	 * @return Boolean - True if value in array
	 */
	public static boolean valueInPlayerArray(int[][] playerArr, int[] coord) {
		
		for (int i = 0; i < playerArr.length; i++) {
			
			if (playerArr[i][0] == coord[0] && playerArr[i][1] == coord[1] ) {
				
				return true;
			}
		}
		return false;
	}
	
				
 // Use this method to print game boards to the console.
	private static void printBattleShip(char[][] player) {
		System.out.print("  ");
		for (int row = -1; row < 5; row++) {
			if (row > -1) {
				System.out.print(row + " ");
			}
			for (int column = 0; column < 5; column++) {
				if (row == -1) {
					System.out.print(column + " ");
				} else {
					System.out.print(player[row][column] + " ");
				}
			}
			System.out.println("");
		}
	}
	
}