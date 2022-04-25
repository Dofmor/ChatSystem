package Server;

import Shared.*;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;

public class ServerThread implements Runnable {

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
			
			
			
			while (true) {
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
			
			
			while (true) {
				Message NewMessage = (Message) objectInputStream.readObject();
				
				if (NewMessage.getType().equals(new String("logout message"))) {
					NewMessage.setStatus("success");
					PrintMessage(NewMessage);
					objectOutputStream.writeObject(NewMessage);
					break;
				} else if (NewMessage.getType().equals(new String("text message"))) {
					PrintMessage(NewMessage);
					objectOutputStream.writeObject(NewMessage);
				}
				
			}
			
			
			
			
			
		} catch (Exception e) {
			System.out.println("Error:" + socket);
		}

	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		System.out.println("Connected: " + socket);

		try {
			System.out.println("closing socket");
			socket.close();
		} catch (IOException e) {
		}
		System.out.println("Closed: " + socket);

	}
	
	private static void PrintMessage(Message msg) {
		System.out.println(msg.getType());
		System.out.println(msg.getData());
	}

}
