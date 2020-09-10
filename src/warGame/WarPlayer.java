package warGame;

import java.util.UUID;

import gameObjects.DiscardPile;

public class WarPlayer {
	
	private String name = "";
	public WarHand playerHand;
	public DiscardPile playerDiscPile;
	public boolean hasPlayed;
	public UUID playerID;

	public WarPlayer(String s)
	{
		name = s;
		playerHand = new WarHand();
		playerDiscPile = new DiscardPile();
		hasPlayed = false;
	}
	
	public WarPlayer(String s, UUID id)
	{
		name = s;
		playerHand = new WarHand();
		playerDiscPile = new DiscardPile();
		hasPlayed = false;
		playerID = id;
	}
	
	public String getName()
	{
		return name;
	}
}