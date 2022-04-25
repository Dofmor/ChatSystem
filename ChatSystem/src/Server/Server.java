package Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server {
	
	private int port;
	private ArrayList<ServerThread> serverThreads = new ArrayList<>();

	public Server(int port) {
		this.port = port;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public ArrayList<ServerThread> getserverThreads() {
		return serverThreads;
	}

	public void setserverThreads(ArrayList<ServerThread> serverThreads) {
		this.serverThreads = serverThreads;
	}

	public void run() {
		// TODO Auto-generated method stub

		ServerSocket server;

		try {

			// Setting the server to run on socket 7777
			server = new ServerSocket(port);
			// server.setReuseAddress(true);

			//
			System.out.println("Accepting new clients");
			while (true) {
				Socket client = server.accept();
				System.out.println("Accepted Client " + client);
				ServerThread newThread = new ServerThread(client, this);
				serverThreads.add(newThread);
				newThread.run();

			}
		} catch (IOException e) {
			System.out.println("could not create socket");
		}

	}
}
