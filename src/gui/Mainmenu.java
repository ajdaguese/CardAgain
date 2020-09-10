package gui;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * MainMenu handles the MainMenu panel
 * @author Jacob Richards
 *
 */
public class Mainmenu {
	
	private JPanel panMainMenu = new JPanel();
	private BaseScreen Base = new BaseScreen();
	
	/**
	 * Adds all the buttons, labels, lines, etc.
	 * to the panel.
	 */
	public void renderPanel()
	{
		// The window that is used
		//JFrame framGui= new JFrame("CardAgain");
		
		//Main Menu Panel
		panMainMenu.setLayout(null);
		panMainMenu.setBounds(0,0,1280,720);
		
		// All of the Main Menu buttons
		JButton buttPlayNow = new JButton("Play Now!");
		JButton buttHostServer = new JButton("Host Server");
		JButton buttServerBrowser = new JButton("Server Browser");
		JButton buttLeaderBoards = new JButton("Leaderboards");
		JButton buttProfileSettings = new JButton("Profile Settings");
		JButton buttLogout = new JButton("Logout");
		
		// The labels
		JLabel labMain = new JLabel("Main Menu");
		
		//TODO Change username to variable
		JLabel labUserName = new JLabel(Base.username);
		
		// Where each button is placed on the window

		buttPlayNow.setBounds(320,250,640,50); // x axis, y axis, width, height
		buttHostServer.setBounds(320,310,640,50);
		buttServerBrowser.setBounds(320,370,640,50);
		buttLeaderBoards.setBounds(320,430,640,50);
		buttProfileSettings.setBounds(320,490,640,50);
		buttLogout.setBounds(320,550,640,50);
		
		// Where the label is placed and the size
		labMain.setBounds(50, 50, 375, 100);
		labMain.setFont(new Font("Serif", Font.PLAIN, 75));
		labUserName.setBounds(1025, 50, 150, 100);
		labUserName.setFont(new Font("Arial", Font.PLAIN, 25));
		
		// Panel Placement
		panMainMenu.setBounds(0,0,1280,720);
		
		// adding each button to the frame
		panMainMenu.add(buttPlayNow);
		panMainMenu.add(buttHostServer);
		panMainMenu.add(buttServerBrowser);
		panMainMenu.add(buttLeaderBoards);
		panMainMenu.add(buttProfileSettings);
		panMainMenu.add(buttLogout);
		
		// action listener for buttons
		buttLeaderBoards.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				Base.switchToLeaderBoard();
			}
		});
		
		buttProfileSettings.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				Base.switchToProfile();
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
		buttHostServer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				Base.switchToServerScreen();
			}
		});
		
		
		// adding the label to the frame
		panMainMenu.add(labMain);
		panMainMenu.add(labUserName);
	}
	/**
	 * Returns the mainmenu panel
	 * @return the mainmenu panel
	 */
	public JComponent getGui()
	{
		return panMainMenu;
	}

}
