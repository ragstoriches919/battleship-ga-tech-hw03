package ga_tech;

import java.util.Arrays;
import java.util.Scanner;


public class Battleship {

	public static void main(String[] args) {
		
		Scanner input = new Scanner(System.in);  // Create a Scanner object
		System.out.println("Welcome to Battleship!" + "\n");
		
		//Initial battleship locations that player inputs
		int[][] playerCoords1 = new int[5][2]; 
		int[][] playerCoords2 = new int[5][2];
		
		//Complete player grid with all ships
		char[][] playerGrid1 = new char[5][5];
		char[][] playerGrid2 = new char[5][5];
		
		//Where player has tried attacking
		char[][] playerGridMoves1 = new char[5][5]; 
		char[][] playerGridMoves2 = new char[5][5]; 
		playerGridMoves1 = initializePlayerGridMovesArray(playerGridMoves1);
		playerGridMoves2 = initializePlayerGridMovesArray(playerGridMoves2);
		
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
		char[][] defensePlayerGrid = playerGrid2;
		char[][] currentPlayerMoves = playerGridMoves1;
		int[] attackCoords;
		
		
		boolean gameOver = false;
		
		do {
			
			// First, run the attack sequence
			attackCoords = Battleship.legitAttackCoords(current_attack, current_defense, defensePlayerGrid, currentPlayerMoves, input);
			
			
			// Now, go back and change whose turn it is
			if (current_attack == "Player 1"){
				
				// Attacking player
				playerGridMoves1 = Battleship.updatePlayerMovesArr(attackCoords, playerGridMoves1, playerGrid2, current_attack, current_defense);
				playerGrid2 = Battleship.updatePlayerGridArr(attackCoords, playerGrid2);
				
				// Revert to other player
				current_attack = "Player 2";
				current_defense = "Player 1";
				defensePlayerGrid = playerGrid1;
				currentPlayerMoves = playerGridMoves2;	
			}
			
			else if (current_attack == "Player 2") {
				
				// Attacking player
				playerGridMoves2 = Battleship.updatePlayerMovesArr(attackCoords, playerGridMoves2, playerGrid1, current_attack, current_defense);
				playerGrid1 = Battleship.updatePlayerGridArr(attackCoords, playerGrid1);
				
				// Revert to other player
				current_attack = "Player 1";
				current_defense = "Player 2";
				defensePlayerGrid = playerGrid2;
				currentPlayerMoves = playerGridMoves1;	
			}
			
			gameOver = Battleship.checkGameOver(playerGridMoves1, playerGridMoves2);
			
		} while(!gameOver);
	}
	
	
	public static int[] legitAttackCoords(String playerAttack, String playerDefense, char[][] playerGrid, char[][] playerGridMoves, Scanner inputObj ){
		
		System.out.println(playerAttack + ", enter hit row/column:");
		
		int firstCoord = inputObj.nextInt();
		int secondCoord = inputObj.nextInt();
		int[] legitAttackArr = new int[]{firstCoord, secondCoord};
		
		// Keep looping if coords are outside of range
		while ( (firstCoord >4 || firstCoord < 0) || (secondCoord >4 || secondCoord < 0) ){
			
			System.out.println("Invalid coordinates. Choose different coordinates.");
			System.out.println(playerAttack + ", enter hit row/column:");
			firstCoord = inputObj.nextInt();
			secondCoord = inputObj.nextInt();
		}
		
		// Keep looping if coords have already been fired on spots.
		while (playerGridMoves[firstCoord][secondCoord]=='X' || playerGridMoves[firstCoord][secondCoord]=='O') {
			
			System.out.println("You already fired on this spot. Choose different coordinates.");
			System.out.println(playerAttack + ", enter hit row/column:");
			firstCoord = inputObj.nextInt();
			secondCoord = inputObj.nextInt();			
			
		}
		
		return legitAttackArr;
	}
	
	
	//Update player grid
	public static char[][] updatePlayerMovesArr(int[] legitAttack, char[][] playerMoves, char[][] playerGrid, String playerAttack, String playerDefense){
		
		int row = legitAttack[0];
		int col = legitAttack[1];
		
		// Ship got hit! --update playerGridMoves
		if (playerGrid[row][col]=='@'){
			
			System.out.println(playerAttack.toUpperCase() + " HIT PLAYER " + playerDefense.toUpperCase() +"’s SHIP!");
			playerMoves[row][col] = 'X';
			
		}
		
		
		// Ship missed!
		else if (playerGrid[row][col]=='-') {
			System.out.println(playerAttack.toUpperCase() + " MISSED!");
			playerMoves[row][col] = 'O';
		}
		
		Battleship.printBattleShip(playerMoves);
		System.out.println("\n");
		
		return playerMoves;
		
		
	}
	
	
	public static char[][] updatePlayerGridArr(int[] legitAttack, char[][] playerGrid) {
		
		int row = legitAttack[0];
		int col = legitAttack[1];
		
		// Ship got hit! --update playerGridMoves
		if (playerGrid[row][col]=='@'){
			playerGrid[row][col] = 'X';
		}
		
		// Ship missed!
		else if (playerGrid[row][col]=='-') {
			playerGrid[row][col] = 'O';
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
	
	
	public static char[][] initializePlayerGridMovesArray(char[][] playerMoves){
		
		for (int row = 0; row < 5; row++)
		{
			for (int col = 0; col < 5; col++)
		    {
				playerMoves[row][col] = '-'; //Whatever value you want to set them to
		    }
		}
		
		return playerMoves;
		
	}
	
	public static boolean checkGameOver(char[][] playerMoves1, char[][] playerMoves2) {
		
		int player1Score = 0;
		int player2Score = 0;
		
		for (int row = 0; row < 5; row++)
		{
			for (int col = 0; col < 5; col++)
		    {
				if (playerMoves1[row][col] == 'X')
					player1Score ++;
				if (playerMoves2[row][col] == 'X')
					player2Score ++;	
		    }
		}
		System.out.println("player1=" + player1Score + " Player2="+player2Score);
		
		if (player1Score==5) {
			System.out.println("PLAYER 1 WINS! YOU SUNK ALL OF YOUR OPPONENT’S SHIPS!");
		}
		else if (player2Score == 5) {
			System.out.println("PLAYER 2 WINS! YOU SUNK ALL OF YOUR OPPONENT’S SHIPS!");
		}
		
		
		return (player1Score == 5 || player2Score == 5);	
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
			
			System.out.println("Enter Ship " + (i+1) + " location:");

			int firstCoord = inputObj.nextInt();
			int secondCoord = inputObj.nextInt();
			int[] tempArr = new int[]{firstCoord, secondCoord};
			boolean inArray = Battleship.valueInPlayerArray(playerArr, tempArr);			
			
			// Keep looping if coords are outside of range
			while ( (firstCoord >4 || firstCoord < 0) || (secondCoord >4 || secondCoord < 0) || inArray  ){
				

				
				if ( (firstCoord >4 || firstCoord < 0) || (secondCoord >4 || secondCoord < 0)){
					System.out.println("Invalid coordinates. Choose different coordinates.");
					System.out.println("Enter Ship " + (i+1) + " location:");
					
					firstCoord = inputObj.nextInt();
					secondCoord = inputObj.nextInt();
	
					tempArr[0] = firstCoord;
					tempArr[1] = secondCoord;
					
				}
				
				if( Battleship.valueInPlayerArray(playerArr, tempArr)) {
					System.out.println("You already have a ship there. Choose different coordinates.");
					System.out.println("Enter Ship " + (i+1) + " location:");
					
					firstCoord = inputObj.nextInt();
					secondCoord = inputObj.nextInt();
					
					tempArr[0] = firstCoord;
					tempArr[1] = secondCoord;
					
					inArray = Battleship.valueInPlayerArray(playerArr, tempArr);
					
				}
			}
			
			
		    // Add values to 2D array
			playerArr[i][0] = firstCoord;
			playerArr[i][1] = secondCoord;
			
			System.out.println(Arrays.deepToString(playerArr));
				
			}

		return playerArr;
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