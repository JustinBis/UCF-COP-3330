/* ****************************************
 * Name: Justin Bisignano
 * COP 3330 – Spring 2013 
 * Program 3: Cards - Hand of Cards Handler
 * Date: March 1, 2013 
 * **************************************** */

import java.util.Arrays;

public class Hand {

	public int[] cards;
	private int highCard = 0;
	private int highPair = 0;
	private int highestNumOfAKind = 0;
	private boolean flush = false;
	private boolean straight = false;
	private boolean fullHouse = false;
	private boolean twoPair = false;
	
	
	// Rank of the poker hand according to standard poker rules
	private int handRank = 0;
	
	public Hand(int cardsPerHand){
		cards = new int[cardsPerHand];
	}
	
	public String evaluateHand(){
		// Check each hand possibility from greatest value to least.
		checkFlush();
		checkStraight();
		checkNumOfAKind();
		
		if(flush && straight){
			if(isRoyal()){
				handRank = 10;
				return "R. Straight Flush"; // Had to be shortened to fit in 17 character spacing
			}
			else{
				handRank = 9;
				return "Straight Flush";
			}
		}
		
		else if(highestNumOfAKind == 4){
			handRank = 8;
			return "Four of a Kind";
		}
		
		else if((highestNumOfAKind == 3) && fullHouse){
			handRank = 7;
			return "Full House";
		}
		
		else if(flush){
			handRank = 6;
			return "Flush";
		}
		
		else if(straight){
			handRank = 5;
			return "Straight";
		}
		
		else if(highestNumOfAKind == 3){
			handRank = 4;
			return "Three of a Kind";
		}
		
		else if(highestNumOfAKind == 2){
			if(twoPair){
				handRank = 3;
				return "Two Pairs";
			}
			else{
				handRank = 2;
				return "Pair";
			}
		}
		
		else{
			handRank = 1;
			return "High Card";
		}
	}
		
	/**
	 * Checks to see if the cards array is a straight.
	 */
	private void checkStraight(){
		// First, create a new array of just card values
		int[] temp = new int[cards.length];
		for(int i=0; i<cards.length; i++)
			temp[i] = cards[i]%13;
		
		// Now, sort the temp array
		Arrays.sort(temp);
		
		// Now is a good time to declare the high card, since the hand is already just card values and sorted
		highCard = temp[temp.length-1]; 
		
		// Loop through each card index, starting with 1, and break if the card isn't next in a straight.
		// If the loop doesn't break and has iterated through all possibilities, the hand is a straight.
		for(int i = 1; i<temp.length; i++){
			if(temp[i]%13 == (temp[i-1]%13)+1){
				if(i == temp.length-1)
					straight = true;
			}
			else break;
		}
		// If there is an Ace (12) as the highest card, check for the possibility of an A,2,3,4,5 straight
		if(highCard == 12){
			if(temp[0]==0 && temp[1] == 1 && temp[2] == 2 && temp[3] == 3)
				straight = true;
		}
	}
	
	private void checkFlush(){
		for(int i = 1; i<cards.length; i++){
			if(cards[i]/13 == (cards[i-1]/13)){
				if(i == cards.length-1)
					flush = true;
			}
			else break;
		}	
	}
	
	private void checkNumOfAKind(){
		// Create a frequency array and fill it
		int[] freq = new int[13];
		for(int i=0; i<cards.length; i++)
			freq[cards[i]%13]++;
		
		// Look for the maximum number of a single kind of card and set highestNumOfAKind to that number and note it as the highPair
		for(int i=0; i<freq.length; i++)
			if(freq[i]>highestNumOfAKind){
				highestNumOfAKind = freq[i];
				highPair = i;
			}
		
		// If highestNumOfAKind is 3, look for a pair for full house as well. No need to update highPair, since having one set of three means there can't be any other pairs of the same kind
		if(highestNumOfAKind == 3)
			for(int i=0; i<freq.length; i++)
				if(freq[i] == 2)
					fullHouse = true;
		
		// If highestNumOfAKind is 2, look for another pair for a two pair hand. Update the highPair if the found one is higher
		if(highestNumOfAKind == 2)
			for(int i=0; i<freq.length; i++)
				if(freq[i] == 2 && i != highPair){
					twoPair = true;
					if(i>highPair)
						highPair = i;
				}
	}
	
	/**
	 * 
	 * @return Returns true if the cards are all royal cards
	 */
	private boolean isRoyal(){
		// Loop through each card, breaking if any card is not royal
		for(int i=0; i<cards.length; i++){
			if(cards[i]%13 > 10){
				if(i == cards.length-1)
					return true;
			}
			else break;
		}
		// If this didn't return during the for loop, the hand isn't royal 
		return false;
	}
	
	/**
	 * 
	 * @return Returns the rank of the hand
	 */
	public int getRank(){
		return handRank;
	}
	
	/**
	 * 
	 * @return Returns the number value of the highest card to be used as a comparison to break ties
	 */
	public int getHighCard(){
		return highCard;
	}
	
	/**
	 * 
	 * @return Returns the number value of the highest pair or triplet to be used as a comparison to break ties involving two pairs or full houses
	 */
	public int getHighPair(){
		return highPair;
	}
}
