package Client;

import java.awt.FlowLayout;
import java.awt.LayoutManager;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Shared.Message;

public class LoginWindow implements ClientUserInterface {
	
	private Socket socket;
	private ObjectOutputStream objectOutput;
	private ObjectInputStream objectInput;
	
	public LoginWindow(Socket sock, ObjectOutputStream output, ObjectInputStream input)  {
		socket = sock;
		objectOutput = output;
		objectInput = input;
	}
	

	@Override
	public void processCommands() {
		// TODO Auto-generated method stub
		
//		boolean validLogin = false;
//		
//		JFrame loginMenu = new JFrame("Login Menu");
//		JPanel panel = new JPanel();
//		LayoutManager layout = new FlowLayout();
//		panel.setLayout(layout);
//		
//		JTextField userName = new JTextField();
//		JTextField userPassword = new JTextField();
//		
//		panel.add(userName);
//		panel.add(userPassword);
//		
//		loginMenu.add(panel);
//		
//		loginMenu.setSize(560, 200);
//		loginMenu.setLocationRelativeTo(null); // Center on screen
//		loginMenu.setVisible(true); // make visible
//		
//		
//		
//		
		
		
	}

}
