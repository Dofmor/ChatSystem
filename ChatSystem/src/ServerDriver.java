import java.util.Scanner;

import Server.*;

public class ServerDriver {
	public static void main(String[] args) {
		System.out.println("Enter an IP address: ");
		Scanner input = new Scanner(System.in);
		String ip = input.nextLine();
		ip = ip.trim();

		Server server = new Server(7777, "10.0.0.210");
		server.run();
	}
}
