/* ****************************************
 * Name: Justin Bisignano
 * COP 3330 – Spring 2013 
 * Program 4: Tic Tac Toe
 * Date: March 24, 2013 
 * **************************************** */

public class TicTacToeTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// Create the game
		TicTacToe game = new TicTacToe();
		
		// Start the main game loop
		do{
			// Print the board 
			game.printBoard();
				
			// Start the next turn by asking for input so the player can take their turn
			game.takeTurn();
			
			// Check if that move ended the game, and assign winner to true to break the loop if the game has been won.
			//winner = game.isWon();
		}
		while(!game.isWon());
	}
}
