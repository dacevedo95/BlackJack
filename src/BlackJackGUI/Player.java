package BlackJackGUI;

public class Player {
	
	/**
	 * Name of a the Player 
	 */
	
	private String name;
	
	/**
	 * A Players hand that keeps track of the list of Cards
	 */
	
	private Hand hand;
	
	/**
	 * Checks if the Player is the dealer
	 */
	
	private boolean isDealer;
	
	/**
	 * Checks if the dealer has BlackJack
	 */
	
	private boolean dealerBlackJack;
	
	/**
	 * Checks if the player has BlackJack
	 */
	
	private boolean playerBlackJack;
	
	/**
	 * Constructor that initializes the Player's name and whether the Player is 
	 * the Dealer
	 * 
	 * @param name        Name of the Player
	 * @param isDealer    Boolean value determining if the player is a dealer
	 */
	
	public Player(String name, boolean isDealer){
		this.name = name;
		this.isDealer = isDealer;
		hand = new Hand();
	}
	
	/**
	 * Method that gets the name of the Player
	 * 
	 * @return    Player's name
	 */

	public String getName() {
		return name;
	}
	
	/**
	 * Method that gets the Hand of the Player
	 * 
	 * @return    Player's hand
	 */

	public Hand getHand() {
		return hand;
	}
	
	/**
	 * Method that starts the round and checks to see if the player is 
	 * the dealer. If the player is the dealer, it draws two cards for 
	 * the dealer and checks to see if the dealer has BlackJack.
	 * 
	 * If the Player is not a dealer, it draws two cards from the deck
	 * and checks to see if the player has blackjack. If the player has
	 * blackjack then a message is displayed
	 * 
	 * @param deck      Deck of cards being played
	 * @param window    GUI display window
	 */
	
	public void startRound(Deck deck, BlackjackWindow window) {
		if (isDealer == true) {
			Card first = deck.drawCard();
			first.turnFaceDown();
			hand.addCard(first);
			window.redraw();
			Card second = deck.drawCard();
			second.turnFaceUp();
			hand.addCard(second);
			window.redraw();
			if (hand.getScore() == 21) {
				dealerBlackJack = true;
			} 
		} else {
			Card first = deck.drawCard();
			first.turnFaceUp();
			hand.addCard(first);
			window.redraw();
			Card second = deck.drawCard();
			second.turnFaceUp();
			hand.addCard(second);
			window.redraw();
			if (hand.getScore() == 21) {
				playerBlackJack = true;
				GIO.displayMessage(name + ", you got BlackJack!");
			}
		}
	}
	
	/**
	 * Method that plays out a round. If the player is the dealer, it flips
	 * over his first card and continues to hit until the dealer's hand value 
	 * is over 17.
	 * 
	 * If the player is not the dealer, it will continue to ask the player 
	 * whether he wants to hit or stand. If the player decides to hit and 
	 * his value is over 21, a message is displayed indicating that the 
	 * player has busted. If the player decides to continue hitting, a 
	 * prompt will continue to appear until he stands, gets blackjack, or
	 * busts.
	 * 
	 * @param deck      Deck of cards being played
	 * @param window    BlackJack display window
	 */

	public void playRound(Deck deck, BlackjackWindow window) {
		if (isDealer == true) {
			hand.flipCardFaceUp(0);
			window.redraw();
			while (hand.getScore() < 17) {
				hand.hitProcess(deck, window);
			}
		} else {
			hand.numberOfAces();
			while (hand.getScore() < 21) {
				String decision = GIO.readString(name + ", Do you want to \"Hit\" or \"Stand\"");
				if (decision.equalsIgnoreCase("hit")) {
					hand.hitProcess(deck, window);
				} else if (decision.equalsIgnoreCase("stand")){
					break;
				}
			} 
			if (hand.getScore() > 21) {
				GIO.displayMessage(name + " busts");
			}
		}
	}
	
	/**
	 * Method that is called when the round is over. If the Player is 
	 * the dealer then everything is reset. 
	 * 
	 * If the player is not the dealer, the method compares the player's
	 * hand to that of the dealer and displays a message whether or not
	 * the player has won this round or has lost
	 * 
	 * @param dealerScore     Score of the Dealer
	 * @param window          GUI display window
	 */

	public void finishRound(int dealerScore, BlackjackWindow window) {
		if (isDealer == false) {
			if (hand.getScore() > 21) {
				GIO.displayMessage(name + " loses.");
			} else if (dealerScore <= 21 && dealerScore > hand.getScore()) {
				GIO.displayMessage(name + " loses.");
			} else if (dealerScore > 21 && hand.getScore() <= 21) {
				GIO.displayMessage(name + " wins!");
			} else if ((dealerScore <= 21 && hand.getScore() <= 21) && hand.getScore() > dealerScore) {
				GIO.displayMessage(name + " wins!");
			} else if (hand.getScore() == dealerScore) {
				if (dealerBlackJack == true && playerBlackJack == false) {
					GIO.displayMessage(name + " loses.");
				} else if (dealerBlackJack == false && playerBlackJack == true) {
					GIO.displayMessage(name + " wins!");
				} else {
					GIO.displayMessage(name + " pushes.");
				}
			}
		}
		hand.discardAll();
		window.redraw();
	}
}