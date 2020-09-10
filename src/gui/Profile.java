package gui;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Profile handles the Profile panel
 * @author Jacob Richards
 *
 */
public class Profile{
	
	private JPanel panProfile = new JPanel();
	private BaseScreen Base = new BaseScreen();
	
	/**
	 * Adds all the buttons, labels, lines, etc.
	 * to the panel.
	 */
	public void renderPanel()
	{
		panProfile.setLayout(null);
		panProfile.setBounds(0, 0, 1280, 720);
		
		// Buttons
		JButton buttMainMenu = new JButton("Main Menu");
		JButton buttServerBrowser = new JButton("Server Browser");
		JButton buttHostServers = new JButton("Host Server");
		JButton buttLeaderBoard = new JButton("Leader Board");
		JButton buttLogout = new JButton("Logout");
		
		// Labels
		JLabel labName = new JLabel("Name: ");
		JLabel labUserName = new JLabel("UserName: ");
		JLabel labEmail = new JLabel("Email: ");
		JLabel labUserType = new JLabel("User Type: ");
		JLabel labGamesPlayed = new JLabel("Games Played: ");
		JLabel labWins = new JLabel("Wins: ");
		JLabel labLosses = new JLabel("Losses: ");
		JLabel labLevelUp = new JLabel("Level Up: ");
		
		// Button Placement and Size
		buttMainMenu.setBounds(0,0,256,50);
		buttServerBrowser.setBounds(256,0,256,50);
		buttHostServers.setBounds(512,0,256,50);
		buttLeaderBoard.setBounds(768,0,256,50);
		buttLogout.setBounds(1020,0,256,50);
		
		// Label Placements and Size
		labName.setBounds(200, 200, 100, 15);
		labUserName.setBounds(200, 250, 100, 15);
		labEmail.setBounds(200, 300, 100, 15);
		labUserType.setBounds(200,350,100,15);
		labGamesPlayed.setBounds(850, 200, 100, 15);
		labWins.setBounds(860, 250, 100, 15);
		labLosses.setBounds(860, 300, 100, 15);
		labLevelUp.setBounds(850,350, 100, 15);
		
		// User Info
		Base.ga.addUserProfileData(Base.username, panProfile);		
		
		// Adding Buttons
		panProfile.add(buttMainMenu);
		panProfile.add(buttServerBrowser);
		panProfile.add(buttHostServers);
		panProfile.add(buttLeaderBoard);
		panProfile.add(buttLogout);
		
		// action listener for buttons
		buttMainMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				Base.switchToMainMenu();
			}
		});
						
		buttLeaderBoard.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				Base.switchToLeaderBoard();
			}
		});
						
		buttServerBrowser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				Base.switchToServerBrowser();
			}
		});
		
		buttLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				Base.logOut();
			}
		});
				
		// Adding Labels
		panProfile.add(labName);
		panProfile.add(labUserName);
		panProfile.add(labEmail);
		panProfile.add(labUserType);
		panProfile.add(labGamesPlayed);
		panProfile.add(labWins);
		panProfile.add(labLosses);
		panProfile.add(labLevelUp);
	}
	/**
	 * Returns the profile panel
	 * @return the profile panel
	 */
	public JComponent getGui(){
		return panProfile;
	}

}
