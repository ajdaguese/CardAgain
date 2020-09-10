package fishGame;

import java.util.ArrayList;

import gameObjects.*;

public class Fish {

	// Rough Draft of how Fish is going to work
	public static void main(String args[])
	{
		// Create players
		Player p1 = new Player("player1");
		Player p2 = new Player("player2");
		Player p3 = new Player("player3");
		Player p4 = new Player("player4");
		
		// Create and Shuffle Deck
		Deck drawPile = new Deck();
		drawPile.shuffle();
		
		// Deal Cards
		dealCards(p1, drawPile);
		dealCards(p2, drawPile);
		dealCards(p3, drawPile);
		dealCards(p4, drawPile);
		
		// Keeps track of which players turn it is
		Player currentPlayer;
		
		ArrayList<Player> playerList = new ArrayList<Player>();
		playerList.add(p1);
		playerList.add(p2);
		playerList.add(p3);
		playerList.add(p4);
		
		int count = 0;
		int t;
		int askingValue;
		
		// Game is over when there are no cards in the draw pile or in people's hands
		while(drawPile.size() != 0 && !p1.playerHand.isEmpty() && !p2.playerHand.isEmpty() && !p3.playerHand.isEmpty() && !p4.playerHand.isEmpty())
		{
			
			// There are 4 players so take the loop count modded by 4 to get which the correct players turn
			t = count % 4;
			currentPlayer = playerList.get(t);
			
			// Player has their turn
		
			// Player chooses a card to ask for
			askingValue = pickCardValue(currentPlayer, 0);
		
			// Player chooses someone to ask
			if(t < 3)
			{
				checkForCardValue(playerList.get(t + 1), playerList.get(t), askingValue, drawPile);
			}
			else
			{
				checkForCardValue(playerList.get(0), playerList.get(t), askingValue, drawPile);
			}
		
			// Lay down all pairs/books
			// Since it is possible a player gets all 4 cards in one turn therefore having 2 pairs,
			// we run the method twice to check.
			checkForPairsInHand(currentPlayer, askingValue);
			checkForPairsInHand(currentPlayer, askingValue);
			
			// Next player turn
			count++;
		}
		
		checkForWinner(playerList);
		
		
	}
	
	// Deals 5 cards to each player - WORKS
	public static void dealCards(Player player, Deck dp)
	{
		for(int i = 0; i < 5; i++)
		{
			player.playerHand.takeCard(dp.draw());
		}
	}
	
	// Gets the card value that the player wants to ask for in their hand - WORKS
	public static int pickCardValue(Player player, int pos)
	{
		int result = player.playerHand.getCard(pos).getValue();
		System.out.println(player.getName() + " picks a card value of " + result);
		return result;
	}
	
	// Checks the checkPlayers hand for card value, if they have any cards of that value,
	// recievePlayer takes those cards. otherwise recievePlayer draws from pile - WORKS
	public static void checkForCardValue(Player checkPlayer, Player recievePlayer, int value, Deck deck)
	{
		// If they the card, take all of that kind
		ArrayList<Card> takenCards = checkPlayer.playerHand.containsCardValue(value, 0);
		
		// If not, go fish / draw a card from the main deck
		if(takenCards.size() == 0)
		{
			Card drawCard = deck.draw();
			recievePlayer.playerHand.takeCard(drawCard);
			System.out.println(recievePlayer.getName() + " drew the " + drawCard.getSuitAndValue());
		}
		else
		{
			for(int i = 0; i < takenCards.size(); i++)
			{
				recievePlayer.playerHand.takeCard(takenCards.get(i));
				System.out.println(recievePlayer.getName() + " has taken the " + takenCards.get(i).getSuitAndValue() + " from " + checkPlayer.getName());
			}
		}
	}
	
	// Checks the given players hand for any pairs to lay down - WORKS
	public static void checkForPairsInHand(Player player, int value)
	{
		// Check for pairs of 2
		ArrayList<Card> book = player.playerHand.containsCardValue(value, 1);
		
		// Adds the pair to their group of books / pairs
		if(book.size() == 2)
		{
			player.playerBooks.add(book.get(0));
			player.playerBooks.add(book.get(1));
			
			System.out.println(player.getName() + " has laid down the pair " + book.get(0).getSuitAndValue()
					+ " and " + book.get(1).getSuitAndValue() + " to create a book");
		}
	}
	
	// Goes through the given playeeList to determine who has the most Books - WORKS
	public static void checkForWinner(ArrayList<Player> playerList)
	{
		int bookCount;
		ArrayList<Player> winners = new ArrayList<Player>();
		Player winner;
		
		bookCount = playerList.get(0).playerBooks.size() / 2;
		winners.add(playerList.get(0));
		
		for(int i = 1; i < playerList.size(); i++)
		{
			if((playerList.get(i).playerBooks.size() / 2) > bookCount)
			{
				winners.removeAll(winners);
				bookCount = playerList.get(i).playerBooks.size() / 2;
				winners.add(playerList.get(i));
			}
			else if(playerList.get(i).playerBooks.size() / 2 == bookCount)
			{
				winners.add(playerList.get(i));
			}
		}
		
		displayWinner(winners, bookCount);
	}
	
	// Prints a message that shows which player won the game with how many books they have - WORKS
	public static void displayWinner(ArrayList<Player> winners, int bookCount)
	{
		if(winners.size() == 1)
		{
			System.out.println(winners.get(0).getName() + " wins with " + bookCount + " books/pairs!");
		}
		else if(winners.size() > 1)
		{
			for(int i = 0; i < winners.size(); i++)
			{
				System.out.println(winners.get(i).getName() + " tied with " + bookCount + " books/pairs!");
			}
		}
	}
	
	
	
	
}