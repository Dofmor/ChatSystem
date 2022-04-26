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
			
			while (SocketOpen) {
				Message NewMessage = (Message) objectInputStream.readObject();
				
				if (NewMessage.getType().equals(new String("login"))) {
					
					
					if (true) { //if login success
						NewMessage.setStatus("success");
						objectOutputStream.writeObject(NewMessage);
						PrintMessage(NewMessage);
						
						break;
//					} else {
//						NewMessage.setStatus("failed");
//						objectOutputStream.writeObject(NewMessage);
					}
				} else {
					System.out.println("message ignored: "
							+ socket.getInetAddress()
									.getHostAddress());
				}
			}
			
			
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

}
