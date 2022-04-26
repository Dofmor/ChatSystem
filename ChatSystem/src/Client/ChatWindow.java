package Client;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.LayoutManager;
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

	private ArrayList<ConversationList> conversationList = new ArrayList<>();

	public  boolean SocketNotClosed = true;

	private static Socket socket;
	private static ObjectOutputStream objectOutput;
	private static ObjectInputStream objectInput;
	

	private static JFrame GuiFrame = new JFrame("Chat Window");
	private static JButton SendButton = new JButton("Send");
	private static JTextField textField = new JTextField("", 20);
	private static JPanel LeftSideBar = new JPanel();
	private static JPanel centerPanel = new JPanel();
	private static JPanel buttonContainer = new JPanel();
	private static JScrollPane scrollPane = new JScrollPane(buttonContainer, 
            JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
            JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
			
	private static JTextArea TextArea = new JTextArea(); // suggest columns & rows
	private static JScrollPane ScrollTextArea = new JScrollPane(TextArea);
     
	private static int countButtons = 0;
	private static int countLines = 0;

	
	public ChatWindow(Socket sock, ObjectOutputStream output, ObjectInputStream input) throws ClassNotFoundException  {
		socket = sock;
		objectOutput = output;
		objectInput = input;
		
		
        Thread thread = new Thread("New Thread") {
            public void run(){
           
           	 try {
        	        while (SocketNotClosed) {
       				Message NewMessage2 = (Message) objectInput.readObject();
       					PrintMessage(NewMessage2);
        	        }
           	 }
           	 
           	catch (IOException e) {
        			e.printStackTrace();
        		} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
           	 
            }
         };

         thread.start();
      
	}



	@Override
	public void processCommands() {
		// TODO Auto-generated method stub
		
		

		
        LeftSideBar.setLayout(new BorderLayout(10, 10));
        LeftSideBar.add(scrollPane);
		buttonContainer.setLayout(new GridLayout(1000, 1));
        GuiFrame.getContentPane().add(LeftSideBar, BorderLayout.WEST);
        GuiFrame.getContentPane().add(centerPanel, BorderLayout.CENTER);
        GuiFrame.setLocationByPlatform(true);
        GuiFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        GuiFrame.pack();
        GuiFrame.setVisible(true);
        
        centerPanel.setLayout(null);

        
		
		int GuiSizeX = 1000;
		int GuiSizeY = 700;
		
		GuiFrame.setSize(GuiSizeX,  GuiSizeY);
		GuiFrame.setLocation(100, 150);
		GuiFrame.setResizable(false);
		GuiFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		GuiFrame.setDefaultLookAndFeelDecorated(true);

		textField.setBounds(10, 630, 600+75, 25);
		SendButton.setBounds(700,630,75,24); 
		
		centerPanel.add(textField);
		centerPanel.add(SendButton);


		GuiFrame.addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent e) {
            	SocketNotClosed = false;
            	try {
					objectOutput.writeObject(new Message("logout message", "", "","","",""));
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
                System.exit(0);
            }
        });


	
		countButtons = countButtons + 1;
        JButton myButton = new JButton("Create New Chat");
        myButton.setPreferredSize(new Dimension(200, 40));
		buttonContainer.setLayout(new GridLayout(10, 1));
		myButton.setBackground(Color.WHITE);
		myButton.setForeground(Color.BLACK);
        buttonContainer.add(myButton);
        
        
        TextArea.setEditable(false);
       
        ScrollTextArea.setBounds(10, 10, 750, 600);
        centerPanel.add(ScrollTextArea);
        
	}
	
	
	public void AddToSideBar(String str) {
		countButtons = countButtons + 1;
        JButton myButton = new JButton(str);
        myButton.setPreferredSize(new Dimension(200, 40));
        int num = countButtons;
        if (num < 10) { num = 10; }
		buttonContainer.setLayout(new GridLayout(num, 1));

        buttonContainer.add(myButton);
	}
	
	public void AddToTextArea(String str) {
		 TextArea.setText(TextArea.getText()+ str + "\n" );
	}
	
	public void ClearTextArea(String str) {
		 TextArea.setText("");
	}
	
	

	private static void PrintMessage(Message msg) {
		System.out.println("Type: " + msg.getType());
		System.out.println("Status: " + msg.getStatus());
		System.out.println("Data: " + msg.getData());
		System.out.println("-----------------------------------------------------------");
	}
}
