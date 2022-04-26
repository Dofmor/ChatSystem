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
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

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

	private Client client;
	
	private ArrayList<ConversationList> conversationList = new ArrayList<>();

	private static Socket socket;
	private static ObjectOutputStream objectOutput;
	private static ObjectInputStream objectInput;
	

	private static JFrame GuiFrame = new JFrame("Chat Window");
	private static JPanel LeftSideBar = new ConversationList();
	private static JButton NewChatButton = ((ConversationList) LeftSideBar).NewButton("Create New Chat");
	
	
	

	
//	private static JButton SendButton = new JButton("Send");
//	private static JTextField textField = new JTextField("", 20);	
//	private static JPanel centerPanel = new JPanel();
//	private static JTextArea TextArea = new JTextArea(); // suggest columns & rows
//	private static JScrollPane ScrollTextArea = new JScrollPane(TextArea);
     

	public ChatWindow(Socket sock, ObjectOutputStream output, ObjectInputStream input, Client Client) throws ClassNotFoundException  {
		socket = sock;
		objectOutput = output;
		objectInput = input;
		client =  Client;
		
	}
	
	public ChatWindow(Socket sock, ObjectOutputStream output, ObjectInputStream input) throws ClassNotFoundException  {
		socket = sock;
		objectOutput = output;
		objectInput = input;      
	}



	@Override
	public void processCommands() {
		// TODO Auto-generated method stub
		
		NewChatButton.setBackground(Color.WHITE);
		NewChatButton.setForeground(Color.BLACK);

		
		int GuiSizeX = 1000;
		int GuiSizeY = 700;
		
        GuiFrame.getContentPane().add(LeftSideBar, BorderLayout.WEST);
        GuiFrame.setLocationByPlatform(true);
        GuiFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        GuiFrame.pack();
        GuiFrame.setVisible(true);
        
 
		GuiFrame.setSize(GuiSizeX,  GuiSizeY);
		GuiFrame.setLocation(100, 150);
		GuiFrame.setResizable(false);
		GuiFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		GuiFrame.setDefaultLookAndFeelDecorated(true);

		GuiFrame.addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent e) {
            	client.SocketNotClosed = false;
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
		

        
        
//		textField.setBounds(10, 630, 600+75, 25);
//		SendButton.setBounds(700,630,75,24); 
//		centerPanel.add(textField);
//		centerPanel.add(SendButton);
//        centerPanel.setLayout(null);
//        GuiFrame.getContentPane().add(centerPanel, BorderLayout.CENTER);
        
//        TextArea.setEditable(false);
//       
//        ScrollTextArea.setBounds(10, 10, 750, 600);
//        centerPanel.add(ScrollTextArea);
//     

        
        

	}
	
	
	public void AddToSideBar(String str) {
//		countButtons = countButtons + 1;
//        JButton myButton = new JButton(str);
//        myButton.setPreferredSize(new Dimension(200, 40));
//        int num = countButtons;
//        if (num < 10) { num = 10; }
//		buttonContainer.setLayout(new GridLayout(num, 1));
//
//        buttonContainer.add(myButton);
	}
	
	public void AddToTextArea(String str) {
//		 TextArea.setText(TextArea.getText()+ str + "\n" );
	}
	
	public void ClearTextArea(String str) {
//		 TextArea.setText("");
	}
	
	

	private static void PrintMessage(Message msg) {
		System.out.println("Type: " + msg.getType());
		System.out.println("Status: " + msg.getStatus());
		System.out.println("Data: " + msg.getData());
		System.out.println("-----------------------------------------------------------");
	}
}
