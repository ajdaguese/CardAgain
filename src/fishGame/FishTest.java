package fishGame;

import java.util.ArrayList;

import gameObjects.Card;
import gameObjects.Deck;
import gameObjects.Player;

public class FishTest {

	public static void main(String[] args)
	{
		Fish test = new Fish();
		
		Deck testDeck = new Deck();
		
		Player p1 = new Player("p1");
		Player p2 = new Player("p2");
		Player p3 = new Player("p3");
		
		ArrayList<Player> playerList = new ArrayList<Player>();
		
		playerList.add(p1);
		playerList.add(p2);
		playerList.add(p3);
		
		
		p1.playerHand.takeCard(new Card("Hearts", 4, true));
		p2.playerHand.takeCard(new Card("Diamonds", 4, true));
		p2.playerHand.takeCard(new Card("Spades", 4, true));
		p2.playerHand.takeCard(new Card("Clubs", 4, true));
		
		int result = test.pickCardValue(p1, 0);
		
		test.checkForCardValue(p2, p1, result, testDeck);
		
		p1.playerHand.takeCard(new Card("Hearts", 5, true));
		
		result = test.pickCardValue(p1, 4);
		
		test.checkForCardValue(p2, p1, result, testDeck);
		
		test.checkForPairsInHand(p1, 4);
		test.checkForPairsInHand(p1, 4);
		
		test.checkForWinner(playerList);
		
		p3.playerHand.takeCard(new Card("Hearts", 6, true));
		p3.playerHand.takeCard(new Card("Diamonds", 6, true));
		p3.playerHand.takeCard(new Card("Spades", 6, true));
		p3.playerHand.takeCard(new Card("Clubs", 6, true));
		
		test.checkForPairsInHand(p3, 6);
		test.checkForPairsInHand(p3, 6);
		test.checkForWinner(playerList);
		
		p3.playerHand.takeCard(new Card("Hearts", 7, true));
		p3.playerHand.takeCard(new Card("Diamonds", 7, true));
		p3.playerHand.takeCard(new Card("Spades", 7, true));
		p3.playerHand.takeCard(new Card("Clubs", 7, true));
		
		test.checkForPairsInHand(p3, 7);
		test.checkForPairsInHand(p3, 7);
		
		test.checkForWinner(playerList);
		
	}
}