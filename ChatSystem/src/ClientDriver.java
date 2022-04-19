import Client.*;
import Shared.*;

public class ClientDriver {
	public static void main(String[] args) {
		System.out.println("Hello World");

		ClientUserInterface dlInterface;
		dlInterface = new ClientGui();
		dlInterface.processCommands();

	}
}