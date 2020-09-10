package graphics;

import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JLabel;

import gameObjects.Deck;
import gameObjects.Hand;


public class GoFishLayout {
	
	private GameGraphics gg;
	private Deck deck = new Deck();
	private ArrayList<Hand> hands;
	private Hand hand;
	private CardDictionary cd;
	
	private String goFish = "Fish";
	
	public GoFishLayout(GameGraphics gg){
		this.gg = gg;
		deck.shuffle();
		hands = new ArrayList<Hand>();
		//Makes the hands arraylist the same size as the number of players
		for(int i = 0; i < gg.getGameSize(); i++){
			hands.add(null);
		}
		cd = new CardDictionary();
	}
	
	public ArrayList<GameJPanel> createGoFishMiddle(int numoFPlayers, GameJPanel panel){
		return null;
		
	}
	
	public GameJPanel populateGoFishPlayerPanel(GameJPanel panel, int playerNum) throws IOException{
		int x = 0;
		int y = 0;
		ArrayList<JLabel> pics = new ArrayList<JLabel>();
		JLabel pic;
		setHand(hands.get(playerNum - 1), playerNum);
		for(int i = 0; i < 5; i++){
			if(playerNum == 1 || playerNum == 2){
				x = x + 155;
				pic = gg.drawCard(cd.mapCardtoImageString(hands.get(playerNum - 1).getCard(i)), panel, x, 15);
				gg.addCard(goFish, panel, pic);
				x++;
			}
			if(playerNum == 3 ||playerNum == 4){
				y = y + 120;
				pic = gg.drawCard(cd.mapCardtoImageString(hands.get(playerNum - 1).getCard(i)), panel, 30, y);
				gg.addCard(goFish, panel, pic);
			}
		}
		return panel;
	}
	
	//Temporally
	public void setHand(Hand hand, int playerNum)
	{
		for(int i = 0; i < hand.getSize(); i++)
		{
			if(playerNum == gg.getCurrPlayer())
			{
				hand.getCard(i).setFacedown(false);
			}
			else
			{
				hand.getCard(i).setFacedown(true);
			}
		}
		hands.set(playerNum-1, hand);
	}
		
	/*
	private void setHand(int playerNum){
		for(int i = 1; i < playerNum + 1; i++){
			for(int j = 0; j < hands.size(); j++){
				hands.get(playerNum - 1);
				
			}
		}
	}
	*/

}
