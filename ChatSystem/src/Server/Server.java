package Server;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;



public class Server {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		

		ServerSocket server;
		
		try {
			
			//Setting the server to run on socket 2233
			server = new ServerSocket(7778);
			server.setReuseAddress(true);
			
			//
			while (true) {
				System.out.println("accepting new clients");
				Socket client = server.accept();
				System.out.println("Client accepted");
				ClientHandler clientSocket = new ClientHandler(client);
				new Thread(clientSocket).start();
				System.out.println("new thread");
				
			}
		}
		catch(IOException e) {
			System.out.println("could not create socket");
		}

	}
	
	
	/**
	 * new thread for client
	 * @author danielmorales
	 *
	 */
	private static class ClientHandler implements Runnable {
		
		private final Socket handler;
		
		public ClientHandler (Socket client) {
			handler = client;
		}
		
		
		@Override
		public void run() {
			
			System.out.println("Thread opened"); //Acknowledging that the thread successfully opened.
			OutputStream outputStream;
			ObjectOutputStream objectOutput;
			InputStream inputStream;
			ObjectInputStream objectInput;
			
			try {
				outputStream = handler.getOutputStream();
				objectOutput = new ObjectOutputStream(outputStream);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				System.out.println("could not create ObjectOutputStream");
				
				return;
				
			}
			try {
				
				inputStream = handler.getInputStream();
				objectInput = new ObjectInputStream(inputStream);
			}
			catch(IOException e){
				System.out.println("Could not create ObjectInputStream");
				return;
			}
		}
	}
	
}



