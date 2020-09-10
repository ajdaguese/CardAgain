package communication.commands.game;

import communication.commands.CommandType;
import communication.commands.iCommand;

public class EndGameCommand implements iCommand {

	private String winnerName = "";
	
	public EndGameCommand(String s)
	{
		winnerName = s;
	}
	
	@Override
	public CommandType getCommandType() {
		return CommandType.ENDGAMECOMMAND;
	}
	
	public String getName()
	{
		return winnerName;
	}

}