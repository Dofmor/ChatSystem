import Client.*;
import Shared.*;

public class ClientDriver {
	public static void main(String[] args) {

		ClientUserInterface dlInterface;
		dlInterface = new ClientGui();
		dlInterface.processCommands();

	}
}