package Testing;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.Executors;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Server.Server;
import Server.ServerThread;
import Shared.Message;

class ServerThreadTest {
	
	
	//SERVER MUST ALREADY BE RUNNING BEFORE STARTING TEST

	ServerThread st = null;

	private int port = 7777;
	private String host = "localhost";
	TestClient admin = null;
	TestClient client = null;

	@BeforeEach
	public void init() {
		 admin = new TestClient();
		 Message m2 = new Message("login message", "admin"+'\n'+"password", "","","","");
		 admin.send(m2);
		 m2 = admin.login();
	}

	@Test
	public void testLogin() {
		client = new TestClient();
		Message m1 = new Message("login message", "username"+'\n'+"password", "","","","");
		client.send(m1);
		m1 = client.receive();
		Message m2 = new Message("login message", "nick"+'\n'+"guess", "","","","");
		client.send(m2);
		m2 = client.receive();
		assertTrue(m1.getStatus().equals("failed") && m2.getStatus().equals("success"));
	}
	
	@Test
	public void testRefresh() {
		Message m1 = new Message("refresh", ""+'\n'+""+'\n'+"", "","","","");
		admin.send(m1);
		m1 = admin.receive();
		System.out.println(m1.toString());
		assertTrue(m1.getType().equals("conversation data"));
	}
	
	@Test
	public void testLogout() {
		Message m = new Message("logout message", "admin"+'\n'+"guess", "","","","");
		admin.send(m);
		m = admin.receive();
		System.out.print(m.toString());
		assertTrue(m.getStatus().equals("success"));
	}
	
	@Test
	public void testTextMessage() {
		Message m = new Message("text message", "admin"+'\n'+"guess", "","","","");
		admin.send(m);
		m = admin.receive();
		admin.flush();
		System.out.print(m.toString());
		assertTrue(m.getStatus().equals("conversation data"));
	}
	
	@Test
	public void testNewChatUser() {
		Message m = new Message("new chat user", "2\nnick", "","","","");
		admin.send(m);
		m = admin.receive();
		System.out.print(m.toString());
		assertTrue(m.getStatus().equals("conversation data"));
	}
	
//	@Test
//	public void testNewChat() {
//		Message m = new Message("new chat", "newchattest\njohn", "","","","");
//		admin.send(m);
//		m = admin.receive();
//		System.out.print(m.toString());
//		assertTrue(m.getStatus().equals("conversation data"));
//	}
	
	@Test
	public void testCreateUser() {
		Message m = new Message("create user", "newuser\npass\nIT", "","","","");
		admin.send(m);
		m = admin.receive();
		System.out.print(m.toString());
		assertTrue(m.getType().equals("IT command return info"));
	}
	
	@Test
	public void testDeleteUser() {
		Message m = new Message("delete user", "john", "","","","");
		admin.send(m);
		m = admin.receive();
		System.out.print(m.toString());
		assertTrue(m.getType().equals("IT command return info"));
	}
	
	@Test
	public void testgetChatLog() {
		Message m = new Message("get chat log", "", "","","","");
		admin.flush();
		admin.send(m);
		m = admin.receive();
		System.out.print(m.toString());
		assertTrue(m.getType().equals("IT command return info"));
	}
	
	



	@AfterEach
	public void end() {
		admin.logout();
	}

	public class TestClient {

		private OutputStream outputStream;
		private ObjectOutputStream objectOutputStream;
		private InputStream inputStream;
		private ObjectInputStream objectInputStream;
		Socket socket = null;

		TestClient() {
			try {
				socket = new Socket(host, port);
				outputStream = socket.getOutputStream();
				objectOutputStream = new ObjectOutputStream(outputStream);
				inputStream = socket.getInputStream();
				objectInputStream = new ObjectInputStream(inputStream);
			} catch (Exception e) {
				System.out.print(e);
			}

		}
		
		public void send(Message m) {
			try {
				objectOutputStream.writeObject(m);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		public Message login() {
			Message m = null;
			try {
				m = (Message) objectInputStream.readObject();
				flush();

			} catch (ClassNotFoundException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return m;
		}
		
		public Message receive() {
			Message m = null;
			try {
				m = (Message) objectInputStream.readObject();
			} catch (ClassNotFoundException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return m;
		}
		
		
		public void logout() {
			try {
				objectInputStream.close();
				socket.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		public void flush() {
			try {
				while(objectInputStream.available() > 0) {
					objectInputStream.readObject();
				}
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		

	}
}
