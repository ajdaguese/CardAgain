package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import communication.Callback;
import communication.commands.LoginCommand;
import dataModels.User;
import utility.GsonHelper;

public class LogIn extends JFrame {
	
	JPanel logInPanel = new JPanel();
	BaseRegisterScreen baseRegister = new BaseRegisterScreen();
	BaseScreen Base = new BaseScreen();
	
	JButton LOGIN, SIGNUPROUTE;
	JLabel usernameLabel, passwordLabel;
	JTextField usernameText, passwordText;
	GridBagConstraints constraints;
	Font mainFont = new Font("Serif San", Font.PLAIN, 20);
	
	public void renderPanel(){
		logInPanel.setLayout(new GridBagLayout());
		
		constraints = new GridBagConstraints();
		constraints.anchor = GridBagConstraints.WEST;
		constraints.insets = new Insets(10,10,10,10);
		
	
		//Information for the Log In panel
		
		SIGNUPROUTE = new JButton("Need to Sign Up? Sign Up");
		SIGNUPROUTE.setFont(mainFont);
		SIGNUPROUTE.setForeground(new Color(0xffffff));
		SIGNUPROUTE.setBackground(new Color(0x2e2e2e));
		constraints.gridx = 1;
		constraints.gridy = 0;
		logInPanel.add(SIGNUPROUTE, constraints);
		
		usernameLabel = new JLabel("Username:");
		usernameLabel.setFont(mainFont);
		constraints.gridx = 0;
		constraints.gridy = 1;
		logInPanel.add(usernameLabel, constraints);
		
		usernameText = new JTextField(15);
		//DEBUG PURPOSES - fast login
		usernameText.setText("a");
		constraints.gridx = 1;
		logInPanel.add(usernameText, constraints);
		
		
		passwordLabel = new JLabel("Password:");
		passwordLabel.setFont(mainFont);
		constraints.gridx = 0;
		constraints.gridy = 2;
		logInPanel.add(passwordLabel, constraints);

		passwordText = new JTextField(15);
		//DEBUG PURPOSES - fast login
		passwordText.setText("a");
		constraints.gridx = 1;
		logInPanel.add(passwordText, constraints);
	
		
		LOGIN = new JButton("Login");
		LOGIN.setFont(mainFont);
		LOGIN.setForeground(new Color(0xffffff));
		LOGIN.setBackground(new Color(0x2e2e2e));
		//DEBUG PURPOSES - fast login
		LOGIN.setSelected(true);
		
		constraints.gridx = 1;
		constraints.gridy = 4;
		logInPanel.add(LOGIN, constraints);
		// end of information for the sign up panel
		
		//information for the log in panel
		
		logInPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Card Agains Log In"));
		((TitledBorder) logInPanel.getBorder()).setTitleFont(mainFont);
		logInPanel.setPreferredSize(new Dimension(450, 550));

		//add(logInPanel);
		pack();
		//setResizable(true);
		//setLocationRelativeTo(null);
		//this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		SIGNUPROUTE.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				baseRegister.switchToSignup();
			}
		});
		
		LOGIN.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				loginRequirement();
			}
		});
		
		//DEBUG PURPOSES - Fast Login
		loginRequirement();
		
	}
	
	private void loginRequirement(){
		
		
		String user = getUsername();
		String pass = getPassword();
		
		final String uArg[] = {user, ""};
		
		//For testing purpose
		int gameWins = 8;
		int gamePlayed = 19;
		
		JLabel firstWarning = new JLabel("(Must enter first name)");
		JLabel lastWarning = new JLabel("(Must enter Last name)");
		JLabel userWarning = new JLabel("(Must enter Username)");
		JLabel passWarning = new JLabel("(Must enter Password)");
		
		int count = 0;
		
		if(user.equals("")){
			userWarning.setFont(mainFont);
			userWarning.setForeground(new Color(0xff0000));
			constraints.gridx = 3;
			constraints.gridy = 3;
			logInPanel.add(userWarning, constraints);
			count++;
		}
		if(pass.equals("")){
			passWarning.setFont(mainFont);
			passWarning.setForeground(new Color(0xff0000));
			constraints.gridx = 3;
			constraints.gridy = 4;
			logInPanel.add(passWarning, constraints);
			count++;
		}
		if(count == 0){
			try {
				User authenticatedUser = null;
				//create a network registration request and send it.
				LoginCommand lc = new LoginCommand(getUsername(), getPassword());
				
				client.Client.cardNet.emitCommand(lc, new Callback(){
					public void call(Object... args){
						String status = GsonHelper.gson.fromJson((String)args[0],String.class);
						System.out.println(status);
						if(status.equals("success")){
							//user was successfuly logged in
							System.out.println((String)args[1]);
							//deserialize user
							User objUser = GsonHelper.gson.fromJson((String)args[1], User.class);
							//track user
							client.Client.authenticatedUser = objUser;
							//switch to main screen
							Base.main(uArg);
							//kill this window
							baseRegister.close();
						}
					}
				});
				//sqlGameAccess.readUsersPassDataBase(user);
				//System.out.println("You have stored user information");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		// action listener for buttons
		
	}
	
	private void printUserInfo(){
		String user = getUsername();
		String pass = getPassword();
		
		System.out.println(user);
		System.out.println(pass);
	}
	
	private String getUsername(){
		return usernameText.getText();
	}
	
	private String getPassword(){
		return passwordText.getText();
	}
	
	/*
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		LogIn frame = new LogIn();
		frame.setSize(500, 600);
		frame.setVisible(true);
		
	}
	*/
	public JPanel getGui() {
		// TODO Auto-generated method stub
		return logInPanel;
	}
	
}
