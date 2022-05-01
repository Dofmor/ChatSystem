package Shared;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Conversation {

	public String Name = null;
	public String ID = null;
	public List<String> Members = null;
	public ArrayList<String[]> Chats = null;

	public Conversation(String name, String id, List<String> members, ArrayList<String[]> chats) {
		Name = name;
		ID = id;
		Members = members;
		Chats = chats;
	}

	public Conversation(String data) {
		Chats = new ArrayList<String[]>();
		String temp, username, time, message;
		String[] Array;
		data = data.substring(0, data.length() - 4);

		List<String> DataList = Arrays.asList(data.split("\n"));
		for (int i = 0; i < DataList.size(); i++) {

			switch (i) {

			case 0:
				Name = DataList.get(i);
				System.out.println(Name);
				break;
			case 1:
				ID = DataList.get(i);
				System.out.println(ID);

				break;
			case 2:
				Members = Arrays.asList(DataList.get(i).split(" "));
				break;
			default:
//			    	List<String> tempList = Arrays.asList(DataList.get(i).split(","));
//			    	String[] Array  = new String[tempList.size()];
//			    	for (int j = 0; j < tempList.size(); j++) {
//			    		Array[j] = tempList.get(j);
//			    	}
//			    	Chats.add(Array);

				Array = new String[3];
				temp = DataList.get(i);
				System.out.println("**********");
				Scanner sc = new Scanner(temp);
				username = sc.next();
				System.out.println(username);
				time = sc.next();
				System.out.println(time);

				message = sc.nextLine();
				System.out.println(message);

				Array[0] = username;
				Array[1] = time;
				Array[2] = message;
				Chats.add(Array);

			}
		}

	}

	public Message convertToMessage() {
		Message msg = new Message();
		msg.setType("conversation data");
		msg.setData(toString());
		return msg;
	}

	public String toString() {
		String str = "";
		str = str + Name + "\n";
		str = str + ID + "\n";
		for (int i = 0; i < Members.size(); i++) {
			str = str + Members.get(i);
			if (Members.size() - 1 != i) {
				str = str + " ";
			}
		}
		str = str + "\n";
		if (Chats.size() != 0) {
			for (int i = 0; i < Chats.size(); i++) {
				str = str + Chats.get(i)[0] + " " + Chats.get(i)[1] + " " + Chats.get(i)[2] + "\n";
			}
		}
		str = str + "END\n";
		return str;
	}

	public String chatsToText() {
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
			if (Members.size() - 1 != i) {
				str = str + ", ";
			}
		}

		return str;
	}

	/**
	 * Adds a new username to the conversation object
	 * 
	 * @param userName - username of client to be added to conversation
	 */
	public void addMember(String userName) {
		Members.add(userName);
	}

	/**
	 * Returns true if username is found in conversation
	 * 
	 * @param userName - username to search
	 * @return - true if username found
	 */
	public boolean isMember(String userName) {
		return Members.contains(userName);
	}

	/**
	 * Appends a chat to Chat arraylist
	 * 
	 * @param chat - new chat to be appeneded
	 */
	public void addChat(String[] chat) {
		Chats.add(chat);
	}

}
