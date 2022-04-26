package Client;


import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import Shared.Message;

public class ChatWindow implements ClientUserInterface {

	public  boolean SocketNotClosed = true;

	private static Socket socket;
	private static ObjectOutputStream objectOutput;
	private static ObjectInputStream objectInput;
	

	private static String[] optionsToChoose = {"None of the listed"};
	private static JFrame GuiFrame = new JFrame("Chat Window");;
	private static JButton SendButton = new JButton("Send");
	private static JTextField textField = new JTextField("", 20);
	private static JComboBox ChatChoice = new JComboBox<>(optionsToChoose);
	
	public ChatWindow(Socket sock, ObjectOutputStream output, ObjectInputStream input) throws ClassNotFoundException  {
		socket = sock;
		objectOutput = output;
		objectInput = input;
		
		
        Thread thread = new Thread("New Thread") {
            public void run(){
           	 
           	 try {

        	        while (SocketNotClosed) {
       				Message NewMessage2 = (Message) objectInput.readObject();
       					PrintMessage(NewMessage2);
        	        }
           	 }
           	 
           	catch (IOException e) {
        			e.printStackTrace();
        		} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
           	 
            }
         };

         thread.start();
      
	}
	
	
	@Override
	public void processCommands() {
		// TODO Auto-generated method stub
		
//		
		int GuiSizeX = 1000;
		int GuiSizeY = 700;
		
		GuiFrame.setSize(GuiSizeX,  GuiSizeY);
		GuiFrame.setLocation(100, 150);
		GuiFrame.setResizable(false);
		GuiFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		GuiFrame.setDefaultLookAndFeelDecorated(true);

		textField.setBounds(300-75, 630, 600+75, 25);
		SendButton.setBounds(905,630,75,24); 
		ChatChoice.setBounds(12, 12, 200, 30);
			
		GuiFrame.add(textField);
		GuiFrame.add(SendButton);
		GuiFrame.add(ChatChoice);
		GuiFrame.setLayout(null);
		GuiFrame.setVisible(true);
		   
		
		GuiFrame.addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent e) {
            	SocketNotClosed = false;
            	try {
					objectOutput.writeObject(new Message("logout message", "", "","","",""));
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
                System.exit(0);
            }
        });
	}

	private static void PrintMessage(Message msg) {
		System.out.println("Type: " + msg.getType());
		System.out.println("Status: " + msg.getStatus());
		System.out.println("Data: " + msg.getData());
		System.out.println("-----------------------------------------------------------");
	}
}
