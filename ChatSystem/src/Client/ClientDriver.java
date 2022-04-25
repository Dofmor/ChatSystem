package Client;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class ClientDriver {

	public static void main(String[] args) {
//
//		// creating client socket
//		int port = 7777;
//		InetAddress serverIP;
//		Socket socket;
//		try {
//			serverIP = InetAddress.getByName("127.0.0.1");
//			socket = new Socket(serverIP, port);
//
//		} catch (UnknownHostException e) {
//
//			System.out.println("Could not get ip address");
//			return;
//		} catch (IOException e) {
//
//			System.out.println("Could not create socket");
//			return;
//		}
//
//		System.out.println("Test 1");
//		// creating object streams
//		OutputStream outputStream;
//		ObjectOutputStream objectOutput;
//		InputStream inputStream;
//		ObjectInputStream objectInput;
//
//		System.out.println("Test 2");
//		try {
//			System.out.println("Test 3");
//			outputStream = socket.getOutputStream();
//			System.out.println("Test 4");
//
//			objectOutput = new ObjectOutputStream(outputStream);
//			System.out.println("Test 5");
//
//			inputStream = socket.getInputStream();
//			System.out.println("Test 6");
//
//			objectInput = new ObjectInputStream(inputStream);
//			System.out.println("Test 7");
//
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			System.out.println("could not create ObjectOutputStream");
//
//			return;
//		}
//		
//		System.out.println("Test 3");
//		
//		LoginWindow loginWindow = new LoginWindow(socket, objectOutput, objectInput);
//		loginWindow.processCommands();
//		
//		System.out.println("Test 4");
//
//		// closing client socket
//		try {
//			socket.close();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}

}
