package graphics;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import communication.commands.ChatCommand;
import communication.commands.CommandType;
import io.socket.emitter.Emitter.Listener;

public class ChatPanel extends JPanel implements ActionListener{
	private JTextArea txt;
	private JScrollPane sp;
	private JTextField typeHere;
	private JButton sendChat;
	
	public ChatPanel(){
		txt = new JTextArea();
		sp = new JScrollPane(txt);
		sendChat = new JButton();
		typeHere = new JTextField();
		
		//add handlers to send chat messages.
		sendChat.addActionListener(this);		
		typeHere.addActionListener(this);
		
		//adds chat to the box when it's received
		client.Client.cardNet.addOnCmd(CommandType.CHAT, new Listener(){
			@Override
			public void call(Object... args) {
				ChatCommand cmd = utility.GsonHelper.fromJson(args[0],ChatCommand.class);
				txt.append(cmd.getUser() + ": " + cmd.getMessage() + "\n");
			}	
		});
		
		//add components to this panel
		this.add(sp,BorderLayout.CENTER);
		this.add(typeHere, BorderLayout.SOUTH);
		this.add(sendChat, BorderLayout.SOUTH);
	}
	
	//sends a chat and clears the chatbox when the button is clicked or the Enter key is pressed.
	@Override
	public void actionPerformed(ActionEvent arg0) {
		String message = txt.getText();
		if(message.length() > 0){
			ChatCommand cc = new ChatCommand(client.Client.authenticatedUser.getUsername(),message);
			client.Client.cardNet.forwardCommand(utility.GsonHelper.toJson(cc), CommandType.CHAT.getName());
		}
		txt.setText("");
	}
}
