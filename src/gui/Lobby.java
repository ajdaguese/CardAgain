package gui;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import client.GameController;
import client.GoFishGameController;
import client.WarGameController;
import communication.commands.CommandType;
import communication.commands.game.StartGameCommand;

/**
 * Lobby handles the Lobby panel
 * @author Jacob Richards
 *
 */
public class Lobby {
	
	private JPanel panLobby = new JPanel();
	private Games game;
	private WarGameController wg;
	private GoFishGameController fg;
	
	/**
	 * Adds all the buttons, labels, lines, etc.
	 * to the panel.
	 */
	public void renderPanel(boolean userIsHost)
	{
		
		// Panel
		panLobby.setLayout(null);
		panLobby.setBounds(0, 0, 1280, 720);
		
		// Buttons
		JButton buttSend = new JButton("Send");
		JButton buttLaunch = new JButton("Launch");
		// Labels
		JLabel labServerDets = new JLabel("Server Details:");
		JLabel labHostUser = new JLabel("Host User:");
		JLabel labGameMode = new JLabel("Game Mode:");
		JLabel labNumPlayersCon = new JLabel("Players Connected: ");
		JLabel labTotPlayers = new JLabel("Total Players: ");
		JLabel labPlayersCon = new JLabel("Connected Users: ");
		JLabel labPlayersUnCon = new JLabel("Unconneted Users: ");
		JLabel labChat = new JLabel("Chat: ");
		
		// Text Fields
		JTextField tfChat = new JTextField("Type here to chat");
		
		// Separating lines
		JSeparator sepHorz1 = new JSeparator(SwingConstants.HORIZONTAL);
		JSeparator sepHorz2 = new JSeparator(SwingConstants.HORIZONTAL);
		JSeparator sepVert = new JSeparator(SwingConstants.VERTICAL);
		
		//Button Placement and Size
		buttSend.setBounds(1150,650,75,20);
		buttLaunch.setBounds(1050,650,75,20);
		
		// TextField Placement and Size
		tfChat.setBounds(410,650,740,20);
		
		// Label Placement and Size
		labServerDets.setBounds(10,50,150,15);
		labHostUser.setBounds(10,100,150, 15);
		labGameMode.setBounds(10,150,150,15);
		labNumPlayersCon.setBounds(10,200,150,15);
		labTotPlayers.setBounds(10,250,150,15);
		labPlayersCon.setBounds(10,400,150,15);
		labPlayersUnCon.setBounds(10,550,150,15);
		labChat.setBounds(410,50,150,15);
		
		// Separating Lines size
		sepHorz1.setPreferredSize(new Dimension(100, 20));
		sepHorz1.setBounds(0,350,400,15);
		sepVert.setBounds(400,0,15,720);
		sepHorz2.setBounds(400,640,880,15);
		
		// Adding buttons to frame
		panLobby.add(buttSend);
		if(userIsHost){
			panLobby.add(buttLaunch);
		}
		// Adding labels to frame
		panLobby.add(labServerDets);
		panLobby.add(labHostUser);
		panLobby.add(labGameMode);
		panLobby.add(labNumPlayersCon);
		panLobby.add(labTotPlayers);
		panLobby.add(labPlayersCon);
		panLobby.add(labPlayersUnCon);
		panLobby.add(labChat);
		
		// Adding text fields to frame
		panLobby.add(tfChat);
		
		// Adding Seperation Lines
		panLobby.add(sepHorz1);
		panLobby.add(sepVert);
		panLobby.add(sepHorz2);
		
		//If the user is the host, then don't forget to create the game controller and add the Launch button
		if(userIsHost){
		//	GameController g;
			switch(game)
			{
			case WAR:
				wg = new WarGameController();
				wg.registerNetworkRoutes();
				System.out.println("war");
				break;
			case GOFISH:
				fg = new GoFishGameController();
				fg.registerNetworkRoutes();
				System.out.println("fiszh");
				break;
			}
		//	final WarGameController g = new WarGameController();
	//		g.registerNetworkRoutes();
			//add launching capability
			buttLaunch.addActionListener(new ActionListener(){
				@Override
				public void actionPerformed(ActionEvent arg0) {
					System.out.println("Launching Card Game as Host");
					client.Client.cardNet.forwardCommand(utility.GsonHelper.toJson(new StartGameCommand()),CommandType.STARTGAMECOMMAND.getName());
					switch(game)
					{
					case WAR:
						wg.start(1);
						break;
//						since I was going to use gofish as the default anyway, I just didn't do a case for GOFISH
					default:
						fg.start(1);
						break;
					}
				}
			});
		}
		//TODO Implement displaying the server detals and chat
		
 	}
	/**
	 * Returns the lobby panel
	 * @return the lobby panel
	 */
	public JComponent getGui()
	{
		return panLobby;
	}
	
	public void setGame(Games gameName)
	{
		game = gameName;
	}

}
