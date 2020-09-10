package client;

import communication.commands.CommandType;
import communication.commands.game.war.EndWarCommand;
import communication.commands.game.war.StartWarCommand;
import graphics.WarLayout;
import io.socket.emitter.Emitter.Listener;

public class WarGameController extends GameController
{
	private boolean warRoutesRegistered;
	private WarLayout wl;
	public WarGameController()
	{
		super();
		wl = new WarLayout(super.gg);
		warRoutesRegistered = false;
	}
	public void start(int playerNum)
	{
		registerNetworkRoutes();
		super.start(playerNum);
	}
	public void registerNetworkRoutes()
	{
		super.registerNetworkRoutes(wl);
		Client.cardNet.addOnCmd(CommandType.ENDWARCOMMAND, new Listener(){

			@Override
			public void call(Object... args) {
				//End war
				EndWarCommand ewc = utility.GsonHelper.fromJson((String)args[0], EndWarCommand.class);	
				//gg.setInWar(false);
				wl.setInWar(false);
			}
		});
		
		Client.cardNet.addOnCmd(CommandType.STARTWARCOMMAND, new Listener(){

			@Override
			public void call(Object... args) {
				StartWarCommand swc = utility.GsonHelper.fromJson((String)args[0], StartWarCommand.class);	
				//gg.setInWar(true);
				wl.setInWar(true);
			}
		});
		
	}
	
}








/*
*/