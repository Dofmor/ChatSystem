package Server;

import Shared.*;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;

public class ServerThread implements Runnable {
	private Boolean SocketOpen = true;
	
	protected Person person = null;
	
	private Socket socket;
	private static Server server;
	private OutputStream outputStream;
	private ObjectOutputStream objectOutputStream;
	private InputStream inputStream;
	private ObjectInputStream objectInputStream;

	ServerThread(Socket socket, Server server) {
		this.socket = socket;
		ServerThread.server = server;
		
		try {
			this.outputStream = socket.getOutputStream();
			this.objectOutputStream = new ObjectOutputStream(outputStream);
			this.inputStream = socket.getInputStream();
			this.objectInputStream = new ObjectInputStream(inputStream);
			
		} catch (Exception e) {
			SocketOpen = false;
			System.out.println("Error:" + socket);
		}

	}

	@Override
	public void run() {
		// TODO Auto-generated method stub

		System.out.println("Connected: " + socket);

		try {
			
			// Wait for a login messages
			// Continue look if failed login
//			while (SocketOpen) {
//				Message NewMessage = (Message) objectInputStream.readObject();
//				
//				if (NewMessage.getType().equals(new String("login"))) {
//					
//					
//					if (true) { //if login success
//						NewMessage.setStatus("success");
//						objectOutputStream.writeObject(NewMessage);
//						PrintMessage(NewMessage);
//						
//						break;
////					} else {
////						NewMessage.setStatus("failed");
////						objectOutputStream.writeObject(NewMessage);
//					}
//				} else {
//					System.out.println("message ignored: "
//							+ socket.getInetAddress()
//									.getHostAddress());
//				}
//			}
			
			while(this.person == null || this.person.isLoggedIn() == false) {
				Message NewMessage = (Message) objectInputStream.readObject();
				login(NewMessage);
			}
			
//			//For Testing
//			Message m = new Message();
//			m.setType("Chat");
//			m.appendToData("1234");	// Chat ID
//			m.appendToData("Fake Chat 1");	// Chat Name
//			m.appendToData("Harry, Daniel, Nick, Brian, Jacob"); // Members in chat
//			m.appendToData("Harry, 532134, hello1");	// Chat Message
//			m.appendToData("Harry, 532134, hello2");	// Chat Message
//			objectOutputStream.writeObject(m);	// Send Message
//
//			Message m2 = new Message();
//			m2.setType("Chat");
//			m2.appendToData("5324");	// Chat ID
//			m2.appendToData("Fake Chat 2");	// Chat Name
//			m2.appendToData("Harry, Daniel, Nick, Brian, Jacob"); // Members in chat
//			m2.appendToData("Harry, 532134, asdfnfaidsnjaflds");	// Chat Message
//			objectOutputStream.writeObject(m2);	// Send Message
//
//			
//			Message m3 = new Message();
//			m3.setType("Chat");
//			m3.appendToData("1234");	// Chat ID
//			m3.appendToData("Fake Chat 1");	// Chat Name
//			m3.appendToData("Harry, Daniel, Nick, Brian, Jacob"); // Members in chat
//			m3.appendToData("Harry, 532134, hello1");	// Chat Message
//			m3.appendToData("Harry, 532134, hello2");	// Chat Message
//			m3.appendToData("Jacob, 532134, hello");	// Chat Message
//			objectOutputStream.writeObject(m3);	// Send Message
			
			
			
			//Wait for other client messages after a good login
			while (SocketOpen) {
				Message NewMessage = (Message) objectInputStream.readObject();
				PrintMessage(NewMessage);
				if (NewMessage.getType().equals(new String("logout message"))) {
					NewMessage.setStatus("success");
					objectOutputStream.writeObject(NewMessage);
					SocketOpen = false;
					break;
				} else if (NewMessage.getType().equals(new String("text message"))) {
					objectOutputStream.writeObject(NewMessage);
					continue;
				}
				
				// failed to handle message
				// sending failed message back to client
				NewMessage.setStatus("fail");
				objectOutputStream.writeObject(NewMessage);
			}
			
		} catch (IOException e) {
			SocketOpen = false;
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			SocketOpen = false;
			e.printStackTrace();
		}
		
		try {
			//Closing Socket
			SocketOpen = false;
			socket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("Closed: " + socket);

	}
	
	private static void PrintMessage(Message msg) {
		System.out.println("-----------------------------------------------------------");
		System.out.println("Type: " + msg.getType());
		System.out.println("Status: " + msg.getStatus());
		System.out.println("Data: " + msg.getData());
	}
	
	private void login(Message m) {
		// validate that login() is being used correctly
		if(m.getType().equals("login") == false) return; 
		String[] parts = m.getData().split("\n");
		if(parts.length < 2) return;
		String username = parts[0];
		String password = parts[1];
		if (person == null || person.isLoggedIn() != false) {
			for (Person user : server.getUsers()) {
				// check to see if login information matches any user currently registered.
				if (user.login(username, password) == true) {
					// assign this thread to be associated with a user if login information is
					// correct
					this.person = user;
					try {
						// send successful login information back to server
						m.setStatus("success");
						m.setData(user.getUserType());
						objectOutputStream.writeObject(m);
						System.out.println("{ \nUser : " + username + "\nPassword : " + password +  "\nlogged in successfully\n}");
						return;
					} catch (IOException e) {
			
					}
				}
			}

			// user login information not found
			try {
				m.setStatus("failed");
				objectOutputStream.writeObject(m);
				System.out.println("{ \nUser : " + username + "\nPassword : " + password +  "\nfailed to login\n}");
			} catch (IOException e) {
				
			}
		}
	}

}
