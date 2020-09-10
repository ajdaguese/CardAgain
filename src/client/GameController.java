package client;

import communication.Callback;
import communication.commands.CommandType;
import communication.commands.game.DisplayOpposingCardCommand;
import communication.commands.game.DrawCardCommand;
import communication.commands.game.EndGameCommand;
import communication.commands.game.EndTurnCommand;
import communication.commands.game.RecieveCardCommand;
import communication.commands.game.StartTurnCommand;
import communication.commands.game.war.EndWarCommand;
import communication.commands.game.war.RoundStatusCommand;
import communication.commands.game.war.StartWarCommand;
import gameObjects.Card;
import graphics.GameGraphics;
import graphics.ParentLayout;
import io.socket.emitter.Emitter.Listener;
import utility.GsonHelper;

public class GameController {
	
	
	protected GameGraphics gg;
	protected ParentLayout lay; //This way we can keep track of the layout in the parent, and in the subclass we'll assign this layout to the child class
	private boolean routesRegistered;
	
	public GameController(){
		gg = new GameGraphics(this);
		routesRegistered = false;
	}
	public void start(int playernum){
		//if(!routesRegistered){
		//	registerNetworkRoutes();
	//	}
		//gg.setPlayerInfo(playernum);
		gg.setPlayerInfo("War", playernum);
		gg.showWindow();
	}
	
	protected void registerNetworkRoutes(ParentLayout lay){
		if(routesRegistered)
			return;
		
		Client.cardNet.addOnCmd(CommandType.STARTTURNCOMMAND, new Listener(){

			@Override
			public void call(Object... args) {
				StartTurnCommand stc = utility.GsonHelper.fromJson((String)args[0], StartTurnCommand.class);
				requestDrawFromServer();
			}
		});
		
		Client.cardNet.addOnCmd(CommandType.ENDTURNCOMMAND, new Listener(){

			@Override
			public void call(Object... args) {
				EndTurnCommand etc = utility.GsonHelper.fromJson((String)args[0], EndTurnCommand.class);	
				System.out.println("Your turn is over.");
			}
		});
		
		
		Client.cardNet.addOnCmd(CommandType.ROUNDSTATUSCOMMAND, new Listener(){

			@Override
			public void call(Object... args) {
				//Round Status
				RoundStatusCommand rsc = utility.GsonHelper.fromJson((String)args[0], RoundStatusCommand.class);
				//gg.clearPlaySpace();
				gg.clearPlaySpace("war");
			}
			
		});
		
		/*Client.cardNet.addOnCmd(CommandType.DISPLAYCARDCOMMAND, new Listener(){
			@Override
			public void call(Object... args) {
				//Round Status
				DisplayCardCommand dcc = utility.GsonHelper.fromJson((String)args[0], DisplayCardCommand.class);	
				System.out.println("Other player had a " + dcc.getCard().getSuitAndValue());
				gg.displayCard(dcc.getCard());
			}
		});
		*/
		Client.cardNet.addOnCmd(CommandType.RECIEVECARDCOMMAND, new Listener(){
			@Override
			public void call(Object... args) {
				System.out.println("Recieved a card: " + (String)args[0]);
				String json = (String)args[0];
				System.out.println(json);
				RecieveCardCommand rcc = GsonHelper.fromJson(json, RecieveCardCommand.class);
				Card j = rcc.getCard();
				System.out.println(j.getSuitAndValue());
				gg.recieveCard("War", j);
				gg.startTurn("War");
			}
			
		});
		
		Client.cardNet.addOnCmd(CommandType.DISPLAYOPPOSINGCARD, new Listener(){
			@Override
			public void call(Object... args) {
				DisplayOpposingCardCommand docc = GsonHelper.fromJson((String)args[0], DisplayOpposingCardCommand.class);
				System.out.println("Opponent had a " + docc.getCard().getSuitAndValue());
				gg.displayCard("War", docc.getCard());
			}
			
		});
		
		Client.cardNet.addOnCmd(CommandType.ENDGAMECOMMAND, new Listener(){

			@Override
			public void call(Object... args) {
				//End war
				EndGameCommand egc = utility.GsonHelper.fromJson((String)args[0], EndGameCommand.class);	
				System.out.println(egc.getName());
			}
		});
		
		System.out.println("Successfully registered all Game networking routes");
		routesRegistered = true;
	}
	
	public void requestDrawFromServer(){
			System.out.println("Requesting a card from the server.");
			Client.cardNet.forwardCommand(utility.GsonHelper.toJson(new DrawCardCommand()),CommandType.DRAWCARDCOMMAND.getName(), new Callback(){
			@Override
			public void call(Object... args) {
				
			}
		});
	}
	
	public void turnComplete(){
		Client.cardNet.forwardCommand(GsonHelper.toJson(new EndTurnCommand()), CommandType.ENDTURNCOMMAND.getName());
	}
	
	public void requestOpponentDisplay(Card cardToDisplay){
		Client.cardNet.forwardCommand(GsonHelper.toJson(new DisplayOpposingCardCommand(cardToDisplay)), CommandType.DISPLAYOPPOSINGCARD.getName());
	}
	
}
