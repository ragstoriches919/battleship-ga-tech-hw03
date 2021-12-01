package ga_tech;

import java.util.Arrays;
import java.util.Scanner;


public class Battleship {

	public static void main(String[] args) {
		
		Scanner input = new Scanner(System.in);  // Create a Scanner object
		System.out.println("Welcome to Battleship!");
		System.out.println("PLAYER 1, ENTER YOUR SHIPS’ COORDINATES.");
		
		int[][] player1Coords = new int[5][2];
		int[][] player2Coords = new int[5][2];
		
//		System.out.println(Arrays.deepToString(player1Coords));
		Battleship.getPlayerCoords(player1Coords, input);
		
//		input.next();
		
	}
	
	
	/**
	 * Returns 2d array of player positions
	 * @param playerArr - 2d array of integers 
	 * @return 2d array of integers
	 */
	public static int[][] getPlayerCoords(int[][] playerArr, Scanner inputObj){
		
//		Loop through and add coordinates
		for (int i = 0; i < playerArr.length; i++) {
			
			System.out.println("Entire Ship " + (i+1) + " location:");

			int firstCoord = inputObj.nextInt();
			int secondCoord = inputObj.nextInt();
			int[] tempArr = new int[]{firstCoord, secondCoord};
			
			while ( (firstCoord >4 || firstCoord < 0) || (secondCoord >4 || secondCoord < 0) || Battleship.valueInBattleshipArray(playerArr, tempArr) ){
				
				System.out.println("Invalid coordinates. Choose different coordinates.");
				System.out.println("Entire Ship " + (i+1) + " location:");
				firstCoord = inputObj.nextInt();
				secondCoord = inputObj.nextInt();
				
				 
				
				System.out.println("Entire Ship " + (i+1) + " location:");
//				System.out.println(Battleship.valueInBattleshipArray(playerArr, tempArr))   ;
			
			}
			
//		    Add values to 2D array
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
			
			if (playerArr[i] == coord) {
				return true;
			}
		}
		return false;
		
		
	}

}
