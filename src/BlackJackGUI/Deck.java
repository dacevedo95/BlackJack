package BlackJackGUI;

public class Deck {
	
	/**
	 * An array of cards that forms a deck
	 */
	
	private Card[] deck;
	
	/**
	 * Number of cards that have been used in the deck
	 */
	
	private int cardsUsed;
	
	/**
	 * Tracks the card being used
	 */
	
	private int count = 0;
	
	
	
	/**
	 * Constructor that initializes a deck of cards to an array of size 52
	 */
	
	public Deck() {
		deck = new Card[52];
		int cardsCreated = 0;
		for (int suit = 0; suit <= 3; suit++) {
			for (int face = 1; face <= 13; face++) {
				deck[cardsCreated] = new Card(suit, face);
				cardsCreated++;
			}
		}
		cardsUsed = 0;
	}
	
	/**
	 * Method that returns a True or False value based on if the deck has no 
	 * more cards remaining 
	 * 
	 * @return     Boolean value whether the deck is empty
	 */

	public boolean isEmpty() {
		if (52 - cardsUsed == 0) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * Takes the deck and randomizes the card positions in the deck, imitating 
	 * the shuffling of a deck
	 */
	
	public void shuffle() {
		if (isEmpty() == true) {
			System.out.println("There are no cards left to shuffle.");
		} else {		
			for (int i = deck.length-1; i > 0; i--){
				int randomNum = (int)(Math.random()*(i+1));
				Card a = deck[i];
				deck[i] = deck[randomNum];
				deck[randomNum] = a;
			}
			cardsUsed = 0;
		}
	}
	
	/**
	 * Method that draws a card from the 'deck' array
	 * @return     Drawn card
	 */
	
	public Card drawCard() {
		Card drawnCard = deck[count];
		deck[count] = null;
		count++;
		cardsUsed++;
		return drawnCard;
	}
	
	/**
	 * Method that retrieves the size of the deck
	 * 
	 * @return   Number of cards left in the deck
	 */
	
	public int getSize() {
		return (52 - cardsUsed);
	}
}