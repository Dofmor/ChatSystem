package Testing;

import static org.junit.jupiter.api.Assertions.*;

import java.io.FileNotFoundException;

import org.junit.jupiter.api.Test;

import Server.Log;
import Shared.Message;

class Logtest {


	void test() throws FileNotFoundException {
	

		Message m=new Message();
		Log l= new Log(m, "", "");
		l.LogMessage();
		l.sendLogs();
	}
}
