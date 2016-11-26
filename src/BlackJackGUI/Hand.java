package BlackJackGUI;

public class Hand {
	
	/**
	 * Tracks the mathematical score of the players hand
	 */
	
	private int handScore;
	
	/**
	 * Number of cards the player has
	 */
	
	private int numberOfCards;
	
	/**
	 * Number of aces the player has in his hand
	 */
	
	private int aceCount;
	
	/**
	 * An array of cards that the player has in his hand
	 */
	
	private Card[] playerHand;
	
	/**
	 * Card that has been dealt to the player when they want to hit
	 */
	
	private Card hit;
	
	/**
	 * Constructor that initializes variables handScore, numberOfCards, and 
	 * playerHand
	 */
	
	public Hand() {
		handScore = 0;
		numberOfCards = 0;
		playerHand = new Card[12];
	}
	
	/**
	 * Method that gets number of cards in the players hand
	 * 
	 * @return   Number of cards the player has
	 */
	
	public int getNumberOfCards() {
		return numberOfCards; 
	}
	
	/**
	 * Method that returns a Card at a given index in the players Hand of cards
	 * 
	 * @param index    Index in a Players hand 
	 * @return         Returns the card at the given index in the players hand
	 */

	public Card getCard(int index) {
		return playerHand[index];
	}
	
	/**
	 * Method that adds a card to the players list of cards
	 * 
	 * @param newcard   Card to be inserted into the players hand
	 */

	public void addCard(Card newcard){
		playerHand[numberOfCards++] = newcard;
		handScore += newcard.getValue();
	}
	
	/**
	 * Method that gets the score of the players hand
	 * 
	 * @return   Mathematical score of all cards that a Player has
	 */

	public int getScore() {
		while (aceCount > 0 && handScore > 21) {
			handScore -= 10;
			aceCount--;
		}
		return handScore;
	}
	
	/**
	 * Resets the players hand to no cards
	 */

	public void discardAll() {
		playerHand = new Card[11];
		numberOfCards = 0;
		handScore = 0;
	}
	
	/**
	 * Method that draws a card and adds it into the players hand and then 
	 * updates the BlackJackWindow to show the card
	 * 
	 * @param deck      Deck of cards to draw from
	 * @param b         BlackJack Window to be updated
	 */
	
	public void hitProcess(Deck deck, BlackjackWindow b) {
		hit = deck.drawCard();
		hit.turnFaceUp();
		addCard(hit);
		b.redraw();
		if (hit.getValue() == 11) {
			aceCount++;
		}
	}
	
	/**
	 * Method finds the number of aces in the players hand
	 * 
	 * @return    Number of aces
	 */
	
	public int numberOfAces() {
		for (int i = 0; i < 2; i++) {
			if (playerHand[i].getValue() == 11) {
				aceCount++;
			}
		}
		return aceCount;
	}
	
	/**
	 * Method that gets a card at a given index and flips the card over
	 * 
	 * @param index      Position of card to be flipped over
	 */
	
	public void flipCardFaceUp(int index) {
		playerHand[index].turnFaceUp();
	}
}