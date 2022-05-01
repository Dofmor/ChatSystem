package Client;

import java.awt.FlowLayout;
import java.awt.HeadlessException;
import java.awt.LayoutManager;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import Shared.Message;

public class LoginWindow implements ClientUserInterface {
	
	private static Client client;
	
	private static Socket socket;
	private static ObjectOutputStream objectOutput;
	private static ObjectInputStream objectInput;
	
	public static Boolean Login(String Username, String Password) throws ClassNotFoundException {
		
		
		try {
			objectOutput.writeObject(new Message("login message", Username+'\n'+Password, "","","",""));
			Message NewMessage = (Message) objectInput.readObject();
			
			if (NewMessage.getStatus().equals(new String("success"))) {
				Client.UserType = NewMessage.getData();
				return true;	
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		
		return false;
	}
	
	
	public LoginWindow(Socket sock, ObjectOutputStream output, ObjectInputStream input, Client Client) throws ClassNotFoundException  {
		socket = sock;
		objectOutput = output;
		objectInput = input;
		client = Client;
				
		while (client.SocketNotClosed) {
			
			JTextField Username = new JTextField();
			JTextField Password = new JPasswordField();
			Object[] Message = {"Username:", Username,"Password:", Password};
			int optionChoosen = JOptionPane.showConfirmDialog(null, Message, "Login", JOptionPane.OK_CANCEL_OPTION);
			if (optionChoosen == JOptionPane.OK_OPTION) {
				String Username1 = Username.getText();
				String Password1 = Password.getText();
				
				if (true) {
					if (Username1.equals(new String(""))) {
						Username1 = " ";
					}
					if (Password1.equals(new String(""))) {
						Password1 = " ";
					}
				}
				
				if (Login(Username1,Password1) == true) {
					break;
				}else {
					
					JFrame jFrame = new JFrame();
			        String msg  = "Login Failed!";
			        JOptionPane.showMessageDialog(jFrame, msg);
					continue;
				}
	
			} else if (optionChoosen == JOptionPane.CANCEL_OPTION) {
				client.SocketNotClosed = false;
				break;
			}
		}
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
