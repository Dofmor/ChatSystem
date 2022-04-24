import Server.*;

public class ServerDriver {
	public static void main(String[] args) {
		Server server = new Server(7777);
		server.run();
	}
}