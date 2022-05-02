import java.io.IOException;
import java.util.Scanner;

import Client.*;
import Shared.*;


class ClientDriver {
	
	public static void main(String[] args) throws ClassNotFoundException, IOException {
		
		System.out.println("Enter ip address: ");
		Scanner input = new Scanner(System.in);
		
		String ip = input.nextLine();
		ip = ip.trim();
		Client client = new Client(ip);
		client.run();
		
	}
}


