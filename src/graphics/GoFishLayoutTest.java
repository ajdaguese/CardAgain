package graphics;

import java.io.IOException;

import client.GameController;
import gameObjects.Hand;

public class GoFishLayoutTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		GameController gc = new GameController();
		GameGraphics gg = new GameGraphics(gc);
		GoFishLayout goFish = new GoFishLayout(gg);
		
		Hand hand = new Hand();
		
	//	goFish.setHand(hand);
		
		for(int i = 0; i < hand.getSize(); i++){
			System.out.println(hand.getCard(i).getSuitAndValue());
		}
		
		try {
			gg.createGameWindow("Fish", 4);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		gg.showWindow();
	}

}
