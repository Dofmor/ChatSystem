package Client;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.StringReader;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.AbstractButton;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.ScrollPaneLayout;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

import Server.ServerThread;
import Shared.Message;

public class ChatWindow implements ClientUserInterface {

	private static Client client;
	private static Socket socket;
	private static ObjectOutputStream objectOutput;
	private static ObjectInputStream objectInput;
	
	private static JButton ChatSendButton = new JButton("Send");
	private static JButton AddUserSendButton = new JButton("Add User");
	private static JFrame GuiFrame = new JFrame("Chat Window");
	private static JPanel ConversationList= new ConversationList(GuiFrame,ChatSendButton,AddUserSendButton);
	private static JButton NewChatButton = ((ConversationList) ConversationList).NewButton("Create New Chat");
	

	
	

	
//	private static JButton SendButton = new JButton("Send");
//	private static JTextField textField = new JTextField("", 20);	
//	private static JTextArea TextArea = new JTextArea(); // suggest columns & rows
//	private static JScrollPane ScrollTextArea = new JScrollPane(TextArea);
     

	public ChatWindow(Socket sock, ObjectOutputStream output, ObjectInputStream input, Client Client) throws ClassNotFoundException  {
		socket = sock;
		objectOutput = output;
		objectInput = input;
		client =  Client;
		
	}


	@Override
	public void processCommands() {
		// TODO Auto-generated method stub
		
		NewChatButton.setBackground(Color.WHITE);
		NewChatButton.setForeground(Color.BLACK);

		
		int GuiSizeX = 1000;
		int GuiSizeY = 700;
		
        GuiFrame.getContentPane().add(ConversationList, BorderLayout.WEST);
        GuiFrame.setLocationByPlatform(true);
        GuiFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        GuiFrame.pack();
        GuiFrame.setVisible(true);
        
 
		GuiFrame.setSize(GuiSizeX,  GuiSizeY);
		GuiFrame.setLocation(100, 150);
		GuiFrame.setResizable(false);
		GuiFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JFrame.setDefaultLookAndFeelDecorated(true);

		GuiFrame.addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent e) {
            	Client.SocketNotClosed = false;
            	try {
					objectOutput.writeObject(new Message("logout message", "", "","","",""));
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
//                System.exit(0);
            }
        });


		//Create a new chat listener
		NewChatButton.addActionListener((ActionListener) new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//                JOptionPane.showMessageDialog(this, textBox.getText());
            	System.out.println("create new chat pressed");
            	
            	String Name = JOptionPane.showInputDialog("Enter Name Of Chat");
				if (Name == null) { return; }
				String User = JOptionPane.showInputDialog("Enter a Person To Add To Chat");
				if (User == null) { return; }
				try {
					objectOutput.writeObject(new Message("New Chat", Name+'\n'+User, "","","",""));
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
            }
        });
		

        
		
		ChatSendButton.addActionListener((ActionListener) new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	System.out.println("send button pressed");
            	if (((ConversationList) ConversationList).ChatOpened) {
            		
            		String currentChatId = ((ConversationList) ConversationList).getCurrentId();
            		
            		if (currentChatId.equals(new String("-1"))) {} else {
                		String text = ((ConversationList) ConversationList).getTextForSending();

                		try {

                			Message newMsg = new Message();
                			newMsg.setType("Message");
                			newMsg.appendToData(currentChatId);
                			newMsg.appendToData(text);
    						objectOutput.writeObject(newMsg);
    						
                		} catch (IOException e1) {
    						// TODO Auto-generated catch block
    						e1.printStackTrace();
    					}
            		}
            		
            		

            	}
            }
        });
		
		AddUserSendButton.addActionListener((ActionListener) new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	System.out.println("send button pressed");
            	if (((ConversationList) ConversationList).ChatOpened) {

            		String currentChatId = ((ConversationList) ConversationList).getCurrentId();
            		if (currentChatId.equals(new String("-1"))) {} else {
                		String Name = JOptionPane.showInputDialog("Enter User To Add");
                		if (Name == null) { return; }
                		try {
                			Message newMsg = new Message();
                			newMsg.setType("New Chat User");
                			newMsg.appendToData(currentChatId);
                			newMsg.appendToData(Name);
    						objectOutput.writeObject(newMsg);
    					} catch (IOException e1) {
    						// TODO Auto-generated catch block
    						e1.printStackTrace();
    					}
            		}
            		
            		
            	}
            }
        });
	}
	
	public void NewConversationMessage(Message Message) {
		
		String Chat_ID = "";
		String Chat_Name = "";
		List<String> Members = null;		
		ArrayList<String[]> Chats = new ArrayList<String[]>();
		
		
		List<String> DataList = Arrays.asList(Message.getData().split("\n"));
		for (int i = 0; i < DataList.size(); i++) {
			
			switch(i) {

				case 0: Chat_ID = DataList.get(i);
						break;
				case 1: Chat_Name = DataList.get(i);
						break;
				case 2: 
						Members = Arrays.asList( DataList.get(i).split(", "));	
						break;
			    default :
			    	List<String> tempList = Arrays.asList(DataList.get(i).split(", "));
			    	String[] Array  = new String[tempList.size()];
			    	for (int j = 0; j < tempList.size(); j++) {
			    		Array[j] = tempList.get(j);
			    	}
			    	Chats.add(Array);
			}
		}
				
		((ConversationList) ConversationList).UpdateOrCreateChat(Chat_Name,Chat_ID,Members,Chats);

		//Print All Variables
//		System.out.println("\"" + Chat_ID + "\"");
//		System.out.println("\"" + Chat_Name + "\"");
//
//		for (int i = 0; i < Members.size(); i++) {
//			System.out.print("\"");
//			System.out.print(Members.get(i));
//			System.out.print("\" ");
//		} System.out.println("");
//
//		
//		for (int i = 0; i < Chats.size(); i++) {
//			;
//			for (int j = 0; j < Chats.get(i).length; j++) {
//				System.out.print("\"");
//				System.out.print(Chats.get(i)[j]);
//				System.out.print("\" ");
//			}
//			System.out.println("");
//		}

		
	}
	

	private static void PrintMessage(Message msg) {
		System.out.println("Type: " + msg.getType());
		System.out.println("Status: " + msg.getStatus());
		System.out.println("Data: " + msg.getData());
		System.out.println("-----------------------------------------------------------");
	}
}
