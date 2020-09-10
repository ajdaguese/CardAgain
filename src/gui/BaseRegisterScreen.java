package gui;

import java.awt.GridBagLayout;
//import java.sql.SQLException;

import javax.swing.JComponent;
import javax.swing.JFrame;
//import javax.swing.JPanel;

//import server.MySQLGameAccess;

public class BaseRegisterScreen {
	
	private static JFrame framCardAgainRegister = new JFrame("Card Again");
	private static SignUp signup = new SignUp();
	private static LogIn login = new LogIn();
	private static JComponent curPanel;
	
	public static void main(String args[]){
		startScreen();
	}

	
	public static void startScreen(){
		framCardAgainRegister.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		login.renderPanel();
		curPanel = login.getGui();
		framCardAgainRegister.add(curPanel);
		
		framCardAgainRegister.pack();
		framCardAgainRegister.setSize(500, 600);
		framCardAgainRegister.setLocationRelativeTo(null);
		framCardAgainRegister.setLayout(new GridBagLayout());
		framCardAgainRegister.setVisible(true);
		//framCardAgainRegister.setResizable(false);
	}


	public void switchToSignup() {
		// TODO Auto-generated method stub
		framCardAgainRegister.remove(curPanel);
		curPanel = signup.getGui();
		signup.renderPanel();
		
		framCardAgainRegister.add(curPanel);
		framCardAgainRegister.validate();
		framCardAgainRegister.repaint();
	}


	public void switchToLogin() {
		// TODO Auto-generated method stub
		framCardAgainRegister.remove(curPanel);
		curPanel = login.getGui();
		login.renderPanel();
		
		framCardAgainRegister.add(curPanel);
		framCardAgainRegister.validate();
		framCardAgainRegister.repaint();
	}
	
	public void close()
	{
		framCardAgainRegister.dispose();
	}
}
