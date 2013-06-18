/* ****************************************
 * Name: Justin Bisignano
 * COP 3330 – Spring 2013 
 * Program 3: Cards - Deck Handler
 * Date: March 1, 2013 
 * **************************************** */

import java.util.Random;

public class Deck {
	
	private static final int NUM_CARDS = 52;
	private static final Random randomNumbers = new Random();
	private int[] deck;
	
	// the index of the current position in the deck
	private int cardIndex = 0;

	// Creates a new shuffled deck of cards 
	public Deck() {
		// Initialize a new deck of cards
		deck = new int[NUM_CARDS];
		
		// Populate the deck in order
		for(int i = 0; i < deck.length; i++)
			deck[i] = i;
		
		// Shuffle the deck
		int temp, swapIndex;
		for(int i = 0; i < deck.length; i++){
			// Select a random index from 0 to NUM_CARDS-1
			swapIndex = randomNumbers.nextInt(NUM_CARDS);
			
			// Swap the numbers
			temp = deck[i];
			deck[i] = deck[swapIndex];
			deck[swapIndex] = temp;
		}
	}
	
	/**
	 * 
	 * @return Returns the int representation of the current card and increments cardIndex.
	 * If there are no more cards to be dealt, this returns -1
	 */
	public int dealCard(){
		if(cardIndex < deck.length)
			return deck[cardIndex++];
		else
			return -1;
	}
	
	/**
	 * @param card
	 * The int representation of a card to be named.
	 * @return
	 * The the spoken name of the given card, e.g. 0 would return "Two of Clubs"
	 */
	public String cardName(int card){
		// Names are in ranked order from least-value to greatest.
		String[] name = {"Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten", "Jack", "Queen", "King", "Ace"};
		String[] suit = {"Clubs", "Spades", "Diamonds", "Hearts"};
		
		
		return name[card%13]+" of "+suit[card/13];
	}
}
