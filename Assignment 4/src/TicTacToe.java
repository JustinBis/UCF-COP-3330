/* ****************************************
 * Name: Justin Bisignano
 * COP 3330 – Spring 2013 
 * Program 4: Tic Tac Toe
 * Date: March 24, 2013 
 * **************************************** */

import java.util.Scanner;
import java.util.Random;

public class TicTacToe {
	
	// constant to set the size of the board
	private final int BOARD_SIZE = 3;
	// number of empty spots remaining in the game
	private int numMovesLeft = BOARD_SIZE*BOARD_SIZE;
	// turn flag, FALSE for X and TRUE for O. 
	private boolean turn = false;
	private boolean computerPlayer = false;
	private Random randomNums;
	private Scanner input;
	// enumerator constants for each box in the board
	private enum Box {
	    X, O, EMPTY
	}
	private Box[][] board = new Box[BOARD_SIZE][BOARD_SIZE];
	
	public TicTacToe(){
		// Set the game to all empty spots when the game is created.
		for(int i = 0; i<BOARD_SIZE; i++)
			for(int k = 0; k<BOARD_SIZE; k++)
				board[i][k] = Box.EMPTY;
		
		// Initialize the scanner object so we can read in input for the duration of the game
		input = new Scanner(System.in);
		
		// Ask if the player wants the computer to play against them
		System.out.println("Do you have one player or two? If it's just you, the computer will play as Player 2.");
		System.out.print("Enter the number of players, 1 or 2: "); 
		int numPlayers = input.nextInt();
		
		// If the player said they are alone (1 player), have the computer play against them and initilize the randomNums variable for use later
		if(numPlayers == 1){
			computerPlayer = true;
			randomNums = new Random();
		}
	}
	
		
	public void printBoard(){
		String spacing = " --- --- --- ";
		// We need at least 2*BOARD_SIZE + 1 lines to print the board with spacing
		for(int line = 1; line<=BOARD_SIZE+BOARD_SIZE+1; line++)
			// print spacing every odd line
			if(line%2 == 1)
				System.out.println(spacing);
			// if we're on an even line, print the board values and their formatting
			else if(line%2 == 0){
				for(int i = 0; i<BOARD_SIZE; i++){
					System.out.print("| ");
					
					if(board[(line-1)/2][i] == Box.EMPTY)
						System.out.print(" ");
					else
						System.out.print(board[(line-1)/2][i]);
					
					System.out.print(" ");
				}
				
				System.out.printf("|\n");
			}
	}
	
	public void takeTurn(){
		// First, check if it is the computer's turn. If it is, skip this function and use takeComputerTurn() instead.
		if(computerPlayer && turn){
				takeComputerTurn();
				return; // exit the function
		}
		
		int row = 0, column = 0;
		
		// X always goes first, as indicated by turn being false
		if(!turn)
			System.out.println("\nIt's X's turn!");
		else
			System.out.println("\nIt's O's turn!");
		
		// Ask for their location and confirm that it's an allowed move
		do{
			System.out.printf("Please enter the location you want to mark as a number between 1 and %d\n", BOARD_SIZE);
		
			// Get their desired row, subtracting 1 to translate from human counting to computer counting
			System.out.print("Row: ");
			if(input.hasNextInt())
				row = input.nextInt()-1;
		
			// Get their desired column, again subtracting 1 so that it correctly indexes the array
			System.out.print("Column: ");
			if(input.hasNextInt())
				column = input.nextInt()-1;
		}
		while (!isValid(row, column));
		
		// Make the move. If turn is true, this assigns O, otherwise this assigns X.
		board[row][column] = turn ? Box.O : Box.X;		
		
		// Switch the turn
		turn = !turn;
		
		// Decrement the number of spaces remaining
		numMovesLeft--;
	}
	
	private void takeComputerTurn(){
		System.out.println("\nIt's the computer's turn!");
		
		// TODO: Improve the game's logic so that the computer isn't just making random moves but is instead playing strategically
		int index = 0;
		// An array that tracks the available move locations
		int[] locations = new int[BOARD_SIZE*BOARD_SIZE];
		
		// Locate all the spots available to move
		for(int i = 0; i<BOARD_SIZE; i++)
			for(int k = 0; k<BOARD_SIZE; k++)
				if(board[i][k] == Box.EMPTY){
					locations[index] = i*BOARD_SIZE + k; // For example, row 0 column 2 would be known as spot #3
					index++;
				}
		
		// Now that we have all the remaining locations, choose one randomly.
		// Note: this is better than just choosing spots blindly, because we only choose from available spots now. 
		int spot = locations[randomNums.nextInt(index)];
		board[spot/BOARD_SIZE][spot%BOARD_SIZE] = Box.O; // The computer is always O.
		
		// Switch the turn
		turn = !turn;
		
		// Decrement the number of spaces remaining
		numMovesLeft--;
	}
	
	/**
	 * Checks to see if the given move is valid and prints an error if it isn't
	 * @param row on the game board to mark
	 * @param column on the game board to mark
	 * @return true if the spot is empty, false if it is not
	 */
	public boolean isValid(int row, int column){
		// First, make sure that we aren't going out of bounds
		if(row>=BOARD_SIZE || row<0 || column>=BOARD_SIZE || column<0){
			System.out.println("You are trying to place a marker out of bounds. Please enter a valid move.");
			return false;
		}
		
		// If the spot at row and column is empty, the move is valid
		if(board[row][column] == Box.EMPTY)
			return true;
		else
			System.out.println("There is already a marker there. Please enter a valid move");
		
		// If we get here, then it by default isn't a valid move
		return false;
	}
	
	/**
	 * Checks to see if the game has been won, and if it has, prints out the winner. 
	 * @return false if the game has not been won, true if it has been won.
	 */
	public boolean isWon(){	
		// Check all of the winning possibilities. Currently only works for a 3x3 grid. Thanks MyProgrammingLab for saving my answer, so I didn't have to write this out again.
		if(
				   (board[0][0] == board[0][1] && board[0][1] == board[0][2] && board[0][0] == board[0][2] && board[0][0] != Box.EMPTY) // Across Row 1
				|| (board[1][0] == board[1][1] && board[1][1] == board[1][2] && board[1][0] == board[1][2] && board[1][0] != Box.EMPTY) // Across Row 2
				|| (board[2][0] == board[2][1] && board[2][1] == board[2][2] && board[2][0] == board[2][2] && board[2][0] != Box.EMPTY) // Across Row 3
				
				|| (board[0][0] == board[1][0] && board[1][0] == board[2][0] && board[2][0] == board[0][0] && board[0][0] != Box.EMPTY) // Down Column 1
				|| (board[0][1] == board[1][1] && board[1][1] == board[2][1] && board[2][1] == board[0][1] && board[0][1] != Box.EMPTY) // Down Column 2
				|| (board[0][2] == board[1][2] && board[1][2] == board[2][2] && board[2][2] == board[0][2] && board[0][2] != Box.EMPTY) // Down Column 3
				
				|| (board[0][0] == board[1][1] && board[1][1] == board[2][2] && board[2][2] == board[0][0] && board[1][1] != Box.EMPTY) // Diagonal from 0,0 to 2,2
				|| (board[0][2] == board[1][1] && board[1][1] == board[2][0] && board[2][0] == board[0][2] && board[1][1] != Box.EMPTY))// Diagonal from 0,2 to 2,0
		{
			// The game has been won. Print out the board one final time.
			printBoard();
			
			// Print out the winner
			System.out.print("That's three in a row! The winner is ");
			
			// Because the winning move was made last turn, the winner was the last person to move.  Because turn is switched at the end of a turn, that makes X the winner if turn is true, otherwise it's O.
			if(turn)
				System.out.println("X!");
			else
				System.out.println("O!");
			
			// Now that the game is over, close the scanner
			input.close();
			
			return true;
		}
		
		// Check if the game is a tie, as signaled by there being no moves left to make
		if(numMovesLeft == 0){
			System.out.println("The game is a tie! Better luck next time!");
			return true;
		}
		
		return false;
	}

}
