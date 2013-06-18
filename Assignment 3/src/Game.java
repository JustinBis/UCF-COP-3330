/* ****************************************
 * Name: Justin Bisignano
 * COP 3330 – Spring 2013 
 * Program 3: Cards - Game Playing Class
 * Date: March 1, 2013 
 * **************************************** */

public class Game {
	
	private static Hand[] hands;
	private static Deck deck;
	
	// Game constants
	private static final int CARDS_PER_HAND = 5;
	private static final int NUM_HANDS = 2; // NOTE: This game can only handle up 10 5-card hands because Deck is limited to 52 cards 
	
	public static void main(String[] args) {
		// First, create a new Deck object
		deck = new Deck();
		
		// Then create the hands by creating an array of Hand objects
		hands = new Hand[NUM_HANDS];
		
		// Next, initialize the cards array in each hand and then deal out cards
		for (int i=0; i<hands.length; i++){
			hands[i] = new Hand(CARDS_PER_HAND);
			for (int j=0; j<CARDS_PER_HAND; j++)
				hands[i].cards[j] = deck.dealCard();
		}

		// Print the hands
		printHands();
		
		// After that, evaluate each hand
		System.out.println("Hand Values:");
		for(int i=0; i<NUM_HANDS; i++)
			System.out.printf("%-17s   ", hands[i].evaluateHand());
		System.out.println();
		
		// Print another blank line
		System.out.println();
		
		// And finally, declare the winner
		declareWinner();
	}
	
	private static void printHands(){
		// Print the header
		for (int i=1; i<NUM_HANDS; i++)
			System.out.printf("%-17s | ", "Hand #"+i+":");
		System.out.printf("%s\n", "Hand #"+NUM_HANDS+":");
		
		// Print the hands
		for (int i=0; i<CARDS_PER_HAND; i++){
			for (int j = 0; j<NUM_HANDS-1; j++){
				System.out.printf("%-17s | ", deck.cardName(hands[j].cards[i]));
			}
			System.out.printf("%s\n", deck.cardName(hands[NUM_HANDS-1].cards[i]));
		}
		
		// Print an extra line
		System.out.println();
	}
	
	private static void declareWinner(){
		int winningHand = 0;
		int winningRank = 0;
		int winningHighPair = 0;
		int winningHighCard = 0;
		boolean tieFlag = false;
		
		
		///////////////////////////////////////////////////////// Make all the reptitive stuff into a function based on i in hands of i
		
		// Loop through each hand and look for the highest ranked hand. If there is a tie, mark it.
		for(int i=0; i<hands.length; i++){
			// Get variables for this hand
			int rank = hands[i].getRank();
			int highCard = hands[i].getHighCard();
			int highPair = hands[i].getHighPair();
			
			// Compare this hand to the currently winning hand, updating the winner if this hand is better
			if(rank > winningRank){
				winningRank = rank;
				winningHand = i;
				winningHighPair = highPair;
				winningHighCard = highCard;
				tieFlag = false;
			}
			// If the ranks are equal, investigate further to determine the winner
			else if(rank == winningRank){
				// If the highPair is higher, make it the new winner
				if(highPair > winningHighPair){
					winningRank = rank;
					winningHand = i;
					winningHighPair = highPair;
					winningHighCard = highCard;
					tieFlag = false;
				}
				// Else if the highPairs are equal but the highCard is higher, make it the new winner
				else if(highPair==winningHighPair && highCard > winningHighCard){
					winningRank = rank;
					winningHand = i;
					winningHighPair = highPair;
					winningHighCard = highCard;
					tieFlag = false;
				}
				// Otherwise, if the hands are exactly tied, flag it.
				else if(highPair == winningHighPair && highCard == winningHighCard)
					tieFlag = true;
			}
		}
		
		if(tieFlag){
			System.out.printf("There was a tie between Hands ");
			// Loop through all the hands again, printing each hand that exactly matches the tied hand
			for(int i=0; i<hands.length; i++){
				if(hands[i].getRank() == winningRank && hands[i].getHighPair() == winningHighPair && hands[i].getHighCard() == winningHighCard)
					System.out.printf("#"+(i+1)+" ");
			}
			System.out.printf("\nSplit the pot!\n");
		}
		
		else
			System.out.println("And the winning hand is...   Hand #"+(winningHand+1)+"!");
	}

}
