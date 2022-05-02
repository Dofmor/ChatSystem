package Client;
import Shared.*;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class Client {
	
	public static String UserType = "";
	public static boolean SocketNotClosed = true;
	
	private static int port = 7777;
	private static InetAddress serverIP;
	private static Socket socket;
	
	
	private static OutputStream outputStream;
	private static ObjectOutputStream objectOutputStream;
	private static InputStream inputStream;
	private static ObjectInputStream objectInputStream;
	
	private static LoginWindow loginWindow;
	private static ChatWindow chatWindow;
	private static ITWindow itWindow;



	public static Boolean Login(String Username, String Password) throws ClassNotFoundException {
		
		try {
			objectOutputStream.writeObject(new Message("login message", Username+'\n'+Password, "","","",""));
			Message NewMessage = (Message) objectInputStream.readObject();
			PrintMessage(NewMessage);
			
			if (NewMessage.getStatus().equals(new String("success"))) {
				return true;	
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		
		return false;
	}
	

	public Client(String ip) throws ClassNotFoundException, IOException {
		
		
		int port = 7777;
		InetAddress serverIP;
		try {
			serverIP = InetAddress.getByName(ip);
			socket = new Socket(serverIP, port);
			outputStream = socket.getOutputStream();
			objectOutputStream = new ObjectOutputStream(outputStream);
			inputStream = socket.getInputStream();
			objectInputStream = new ObjectInputStream(inputStream);
			
			loginWindow = new LoginWindow(socket, objectOutputStream, objectInputStream, this);
			loginWindow.processCommands();
			
//	        Thread thread = new Thread("New Thread") {
//	            public void run(){
//	            	
//					
//	            }
//	        };
//
//	        thread.start();
	         
	         
	        if (SocketNotClosed) {
	        	chatWindow = new ChatWindow(socket, objectOutputStream, objectInputStream,  this);
				chatWindow.processCommands();
	        }

	        if (SocketNotClosed && UserType.equals(new String("IT"))) {
	        	itWindow = new ITWindow(socket, objectOutputStream, objectInputStream,  this);
	        	itWindow.processCommands();
	        }


	        
	        try {
				while (SocketNotClosed) {
					Message NewMessage = (Message) objectInputStream.readObject();
					PrintMessage(NewMessage);
					if (NewMessage.getType().equals(new String("logout message"))) {
						if (NewMessage.getStatus().equals(new String("success"))) {
							System.out.println("Closing");
							SocketNotClosed = false;
							break;
						}
					} else if (NewMessage.getType().equals(new String("conversation data"))) {
						chatWindow.NewConversationMessage(NewMessage);
					} else if (NewMessage.getType().equals(new String("IT command return info"))) {
						itWindow.SetGuiText(NewMessage.getData());
					}
					
					
				}
			} catch (ClassNotFoundException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        
	        
	        
	
		} catch (UnknownHostException e) {
			System.out.println("Could not get ip address");
			return;
		} catch (IOException e) {
			System.out.println("Could not create socket");
			return;
		}
		
	}
	

	
	
	private static void PrintMessage(Message msg) {
		System.out.println("Type: " + msg.getType());
		System.out.println("Status: " + msg.getStatus());
		System.out.println("Data: " + msg.getData());
		System.out.println("-----------------------------------------------------------");
	}



	public void run() {
		// TODO Auto-generated method stub

	}
}