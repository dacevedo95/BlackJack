package BlackJackGUI;

/**
 * 
 *Initializes a Casino BlackJack game 
 *
 * @author David Acevedo
 *
 */

public class Blackjack {
	
	/**
	 * Starts a new BlackJack game 
	 * @param args
	 */

	public static void main(String[] args) {
		int numberOfPlayers = 0;
		String[] names;
		Player[] players;
		
		numberOfPlayers = GIO.readInt("How many players are there?");
		while (numberOfPlayers <= 0 || numberOfPlayers > 3) {
			numberOfPlayers = GIO.readInt("You must have at least 1, but no more than 3 players.");
		}
		int newValue = numberOfPlayers + 1;
		names = new String[newValue];
		names[0] = "Dealer";
		boolean[] dealer = new boolean[newValue];
		dealer[0] = true;
		
		for (int counter = 1; counter <= numberOfPlayers; counter++) {
			names[counter] = GIO.readString("What is your name Player " + counter);
		}
		players = new Player[newValue];
		for (int i = 0; i <= numberOfPlayers; i++) {
			players[i] = new Player(names[i], dealer[i]);
		}
		BlackjackWindow windowObject = new BlackjackWindow(players);
		windowObject.redraw();
		playRound(players, windowObject);
		boolean again = GIO.readBoolean("Do you want to play another round?");
		while (again == true) {
			playRound(players, windowObject);
			again = GIO.readBoolean("Do you want to play another round?");
		}
	}
	
	/**
	 * When called, the function initializes a round of BlackJack to be played
	 * 
	 * @param players   List of Players participating in the round
	 * @param window    Game window to be displayed
	 */

	public static void playRound(Player[] players, BlackjackWindow window) {
		Deck abc = new Deck();
		abc.shuffle();
		for (int i = 1; i < players.length; i++) {
			players[i].startRound(abc, window);
		} 
		players[0].startRound(abc, window);
		for (int j = 1; j < players.length; j++) {
			players[j].playRound(abc, window);
		}
		players[0].playRound(abc, window);
		for (int k = 1; k < players.length; k++) {
			players[k].finishRound(players[0].getHand().getScore(), window);
		}
		players[0].finishRound(players[0].getHand().getScore(), window);
	}
}