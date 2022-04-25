import Client.*;
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

class ClientDriver {
	
	private static boolean SocketNotClosed = true;
	
	private static int port = 7777;
	private static InetAddress serverIP;
	private static Socket socket;
	
	
	private static OutputStream outputStream;
	private static ObjectOutputStream objectOutputStream;
	private static InputStream inputStream;
	private static ObjectInputStream objectInputStream;
	
	
	public static Boolean Login(String Username, String Password) throws ClassNotFoundException {
		
				
		try {
			objectOutputStream.writeObject(new Message("login", Username+'\n'+Password, "","","",""));
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
	
	
	public static void main(String[] args) throws ClassNotFoundException {
		
		int port = 7777;
		InetAddress serverIP;
		try {
			serverIP = InetAddress.getByName("127.0.0.1");
			socket = new Socket(serverIP, port);
			
			outputStream = socket.getOutputStream();
			objectOutputStream = new ObjectOutputStream(outputStream);


			inputStream = socket.getInputStream();
			objectInputStream = new ObjectInputStream(inputStream);
			
			while (SocketNotClosed) {
				
				JTextField Username = new JTextField();
				JTextField Password = new JPasswordField();
				Object[] Message = {"Username:", Username,"Password:", Password};
				int optionChoosen = JOptionPane.showConfirmDialog(null, Message, "Login", JOptionPane.OK_CANCEL_OPTION);
				if (optionChoosen == JOptionPane.OK_OPTION) {
					if (Login(Username.getText(), Password.getText()) == true) {
						break;
					}else {
						
						JFrame jFrame = new JFrame();
				        String msg  = "Login Failed!";
				        JOptionPane.showMessageDialog(jFrame, msg);
						continue;
					}
		
				} else {
					break;
				}
			}
			
			ClientGui clientGui = new ClientGui();
			clientGui.processCommands();

			
	         Thread thread = new Thread("New Thread") {
	             public void run(){
	            	 
	            	 try {

	         	        while (SocketNotClosed) {
	        				Message NewMessage2 = (Message) objectInputStream.readObject();
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
	       

		} catch (UnknownHostException e) {

			System.out.println("Could not get ip address");
			return;
		} catch (IOException e) {

			System.out.println("Could not create socket");
			return;
		} finally {
			 SocketNotClosed = false;
		     try {
				objectOutputStream.writeObject(new Message("logout message", "", "","","",""));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	private static void PrintMessage(Message msg) {
		System.out.println("Type: " + msg.getType());
		System.out.println("Status: " + msg.getStatus());
		System.out.println("Data: " + msg.getData());
		System.out.println("-----------------------------------------------------------");
	}
}


