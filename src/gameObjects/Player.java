package gameObjects;

import java.util.UUID;

public class Player {
	
	private String name = "";
	public Hand playerHand;
	public DiscardPile playerBooks;
	public UUID playerID;

	public Player(String s)
	{
		name = s;
		playerHand = new Hand();
		playerBooks = new DiscardPile();
	}
	
	public Player(String s, UUID id)
	{
		name = s;
		playerHand = new Hand();
		playerBooks = new DiscardPile();
	}
	
	public String getName()
	{
		return name;
	}
	
}
