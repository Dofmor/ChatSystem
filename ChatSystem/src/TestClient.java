

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

public class TestClient {
		public static void main(String[] args) throws Exception {
			Scanner sc = new Scanner(System.in);
//			System.out.print("Enter the port number to connect to: <7777>");
//			int port = sc.nextInt();
//			System.out.print("Enter the host address to connect to: <localhost> ");
//			String host = sc.next();
			int port = 7777;
			String host = "localhost";
			// Connect to the ServerSocket at host:port
			Socket socket = new Socket(host, port);
			System.out.println("Connected to " + host + ":" + port);

			// Create object input and output streams
			OutputStream outputStream = socket.getOutputStream();
			ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
			InputStream inputStream = socket.getInputStream();
			ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);

//			// Attempt to login until successful
//			while (login(objectInputStream, objectOutputStream) == false) {
//				System.out.println("Invalid login");
//			}
			
			while(true) {
				System.out.print("Enter message ('logout' to quit): ");
				String text = sc.nextLine();
				if (text.equals("logout")) {
					break;
				}
			}

//			// keep receiving messages until logged out
//			while (true) {
//				System.out.print("Enter message ('logout' to quit): ");
//				String text = sc.nextLine();
//				if (text.equals("logout")) {
//					if (logout(objectInputStream, objectOutputStream) == true)
//						break;
//				}
//				objectOutputStream.writeObject(new Message("text", "undefined", text));
//				Message textMsg = (Message) objectInputStream.readObject();
//				System.out.println(textMsg.getText());
//			}

			sc.close();
			socket.close();
			System.out.println("You are now logged out");
		}

//		private static void printMessage(Message msg) {
//			System.out.println("Type: " + msg.getType());
//			System.out.println("Status: " + msg.getStatus());
//			System.out.println("Text: " + msg.getText() + "\n");
//		}
//
//		private static boolean login(ObjectInputStream objectInputStream, ObjectOutputStream objectOutputStream)
//				throws IOException, ClassNotFoundException {
//			try {
//				objectOutputStream.writeObject(new Message("login", "status", ""));
//				Message login = (Message) objectInputStream.readObject();
//				printMessage(login);
//				if (login.getStatus().equals("success"))
//					return true;
//			} catch (Exception e) {
//				System.out.print("Failed to login");
//			}
//			return false;
//
//		}
//
//		private static boolean logout(ObjectInputStream objectInputStream, ObjectOutputStream objectOutputStream)
//				throws IOException, ClassNotFoundException {
//			try {
//				objectOutputStream.writeObject(new Message("logout", "status", ""));
//				Message logout = (Message) objectInputStream.readObject();
//				printMessage(logout);
//				if (logout.getStatus().equals("success"))
//					return true;
//			} catch (Exception e) {
//
//			}
//
//			return false;
//		}
}
