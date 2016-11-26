package BlackJackGUI;

public class Card {
	
	/**
	 * List of card suits using integers to assign to a specific suit
	 */
	
	public static final int SPADES   = 0;
	public static final int HEARTS   = 1;
	public static final int CLUBS    = 2;
	public static final int DIAMONDS = 3;

	/**
	 * List of card faces using integers to assign to each specific face
	 */
	
	public static final int ACE      = 1;
	public static final int TWO      = 2;
	public static final int THREE    = 3;
	public static final int FOUR     = 4;
	public static final int FIVE     = 5;
	public static final int SIX      = 6;
	public static final int SEVEN    = 7;
	public static final int EIGHT    = 8;
	public static final int NINE     = 9;
	public static final int TEN      = 10;
	public static final int JACK     = 11;
	public static final int QUEEN    = 12;
	public static final int KING     = 13;

	/**
	 * Card suit for a given Card object
	 */
	
	private final int suit;
	
	/**
	 * Card face for a given Card object
	 */
	
	private final int face;
	
	/**
	 * Tracks whether the given Card object is face down or is face up
	 */
	
	private boolean faceUp;
	
	/**
	 * A constructor for the Card class that takes in a Card's Suit and Face
	 * and checks whether it is a valid card. If the card does not exist, 
	 * and error message will be printed to the screen
	 * 
	 * @param cardSuit     Suit of a card
	 * @param cardFace     Face of a card
	 */
	
	public Card(int cardSuit, int cardFace) {
		if (cardSuit < 0 || cardSuit > 3) {
			System.out.println("Suit does not exist.");
		} if (cardFace < 1 || cardFace > 13) {
			System.out.println("Card doesn't exist.");
		}
		suit = cardSuit;
		face = cardFace;
	}
	
	/**
	 * Method that gets the Suit of a card	 * 
	 * @return    returns the Suit of a Card object
	 */

	public int getSuit() {
		return suit;
	}
	
	/**
	 * Method that gets the Face of a card	 * 
	 * @return    returns the Face of a Card object
	 */
	
	public int getFace() {
		return face;
	}
	
	/**
	 * This method gets the mathimatical value of a card object given its Face
	 * @return    Mathematical value of a card
	 */
	
	public int getValue() {
		if (face >= 2 && face <= 10)
			return face;
		else if (face == 1)
			return 11;
		else
			return 10;
	}
	
	/**
	 * Method that sets faceUp to true, indicating that the card has been shown
	 * to the players and dealer
	 */
	
	public void turnFaceUp() {
		faceUp = true;
	}
	
	/**
	 * Method that sets faceUp to false, indicating that the card has been placed
	 * face down and hidden from the players
	 */
	
	public void turnFaceDown() {
		faceUp = false;
	}
	
	/**
	 * Method that returns either True or False depending on if the card is facing
	 * up or down
	 * 
	 * @return     Boolean value whether card is facing up
	 */
	
	public boolean isFaceUp() {
		return faceUp;
	}
}