package ga_tech;

import java.util.Arrays;
import java.util.Scanner;


public class Battleship {

	public static void main(String[] args) {
		
		Scanner input = new Scanner(System.in);  // Create a Scanner object
		System.out.println("Welcome to Battleship!");
		System.out.println("PLAYER 1, ENTER YOUR SHIPS’ COORDINATES.");
		
		int[][] playerCoords1 = new int[5][2];
		int[][] playerCoords2 = new int[5][2];
		
		
		playerCoords1 = Battleship.getPlayerCoords(playerCoords1, input, "PLAYER1");
		playerCoords2 = Battleship.getPlayerCoords(playerCoords2, input, "PLAYER2");
		
		System.out.println(Arrays.deepToString(playerCoords1));
		System.out.println(Arrays.deepToString(playerCoords2));
		
		
	}
	
	
	/**
	 * Returns 2d array of player positions
	 * @param playerArr - 2d array of integers
	 * @param playerName - String ex.) "PLAYER 1"
	 * @return 2d array of integers
	 */
	public static int[][] getPlayerCoords(int[][] playerArr, Scanner inputObj, String playerName){
		
		System.out.println(playerName + "ENTER YOUR SHIPS' COORDINATES.");
		
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
			

			boolean inArray = Battleship.valueInBattleshipArray(playerArr, tempArr);
			
			// Keep looking if coords have already been entered before.
			while (inArray) {
				System.out.println("You already have a ship there. Choose different coordinates.");
				System.out.println("Entire Ship " + (i+1) + " location:");
				firstCoord = inputObj.nextInt();
				secondCoord = inputObj.nextInt();
				
				tempArr[0] = firstCoord;
				tempArr[1] = secondCoord;
				
				inArray = Battleship.valueInBattleshipArray(playerArr, tempArr);
			}
			
			
			
			
		    // Add values to 2D array
			playerArr[i][0] = firstCoord;
			playerArr[i][1] = secondCoord;
			
			System.out.println(Arrays.deepToString(playerArr));
				
			}
		
		
		return playerArr;
	}
	
	
	/**
	 * @param playerArr - 2d array of player positions
	 * @param coord - array of single coordinate on battleship grid
	 * @return Boolean - True if value in array
	 */
	public static boolean valueInBattleshipArray(int[][] playerArr, int[] coord) {
		
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
