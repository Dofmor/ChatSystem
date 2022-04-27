package Client;
import Shared.*;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;


public class Conversation {
		//	private ArrayList<String> Users = new ArrayList<>();
//
//	
	public String Name = null;
	public String ID = null;
	public List<String> Members = null;		
	public ArrayList<String[]> Chats = null;
	public static JPanel JPanel = null;
	
	
	public Conversation(String name,String id, List<String> members, ArrayList<String[]> chats){
		Name = name;
		ID = id;
		Members = members;
		Chats = chats;
	}
	
	public String dataToText() {
		String str = "";
		for (int i = 0; i < Chats.size(); i++) {
			str = str + Chats.get(i)[0] + ": ";
			str = str + Chats.get(i)[2] + "\n";
		}
		
		return str;
	}
	
	public String membersToText() {
		String str = "";
		for (int i = 0; i < Members.size(); i++) {
			str = str + Members.get(i);
			if (Members.size()-1 != i) {
				str = str + ", ";
			}
		}
		
		return str;
	}
	
}
