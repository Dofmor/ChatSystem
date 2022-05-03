import java.io.IOException;
import java.util.Scanner;

import Client.*;
import Shared.*;


class ClientDriver {
	
	public static void main(String[] args) throws ClassNotFoundException, IOException {
		
//		System.out.println("Enter ip address: ");
//		Scanner input = new Scanner(System.in);
		
//		String ip = input.nextLine();
//		ip = ip.trim();
		//Enter in own IP address when starting client to connect mutlitple clients
		Client client = new Client("10.0.0.160");
		client.run();
		
	}
}


