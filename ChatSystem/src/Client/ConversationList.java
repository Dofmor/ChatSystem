package Client;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import Shared.Conversation;
import Shared.Message;

public class ConversationList extends JPanel {

//	private ArrayList<ConversationList> conversationList = new ArrayList<>();
	public static Boolean ChatOpened = false;
	
	private static List<Conversation> Conversations = new ArrayList<Conversation> (); 
	private static JPanel buttonContainer = new JPanel();
	private static JScrollPane scrollPane = new JScrollPane(buttonContainer, 
            JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
            JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
	
	private static Conversation CurrentConvo = null;

	private static ObjectOutputStream objectOutput;
	
	private static int countButtons = 0;
	private static JPanel centerPanel = new JPanel();
	private static JFrame GuiFrame;
	
	private static JPanel jp = new JPanel();
	private static JTextArea TextArea = new JTextArea(); // suggest columns & rows
	private static JScrollPane ScrollTextArea = new JScrollPane(TextArea);
	public static JButton ChatSendButton  = null;
	public static JButton AddUserSendButton = null;
	private static TextField textField = new TextField();
	private static TextField memberTextArea = new TextField();;
	
	public ConversationList(JFrame GuiFrame, JButton ChatSendButton, JButton AddUserSendButton, ObjectOutputStream objectOutput) {	
		this.GuiFrame = GuiFrame;
		this.ChatSendButton =  ChatSendButton;
		this.AddUserSendButton =  AddUserSendButton;
		this.objectOutput = objectOutput;
		
		
	    GuiFrame.getContentPane().add(centerPanel, BorderLayout.CENTER);
	    
	    
        this.setLayout(new BorderLayout(10, 1));
        this.add(scrollPane);
		buttonContainer.setLayout(new GridLayout(1000, 1));

		
		//////////////////////////

		
        jp.setLayout(null);
        
		textField.setBounds(10, 630, 675, 25);
		memberTextArea.setBounds(10, 10, 650, 25);
		ChatSendButton.setBounds(695,630,75,25); 
		AddUserSendButton.setBounds(670,10,100,25); 
		ScrollTextArea.setBounds(10, 45, 760, 575);

		jp.add(textField);
		jp.add(memberTextArea);
		jp.add(ChatSendButton);
		jp.add(AddUserSendButton);
		jp.add(ScrollTextArea);

		TextArea.setEditable(false);
		memberTextArea.setEditable(false);
		
        setChatVisible(false);
		
        GuiFrame.getContentPane().add(jp, BorderLayout.CENTER);
		NewButton("lol");

//		scrollPane.setPreferredSize(new Dimension(100,500));

	}
	
	public static JButton NewButton(String str) {
		System.out.println("new button with string:" + str);
		countButtons = countButtons + 1;
        JButton myButton = new JButton(str);
        myButton.setPreferredSize(new Dimension(200, 1));
		buttonContainer.setLayout(new GridLayout(countButtons*5, 1));
        buttonContainer.add(myButton);
        myButton.requestFocusInWindow(); 

//        myButton.setVisible(true);
//        scrollPane.repaint();
////		GuiFrame.pack();
//        GuiFrame.setVisible(false);
//        GuiFrame.setVisible(true);
        

//		GuiFrame.setSize(999,  699);
//		GuiFrame.setSize(1000,  700);

        
        return myButton;
	}
	
	public static void UpdateChatText() {
		
		if (CurrentConvo != null) {
			TextArea.setText(CurrentConvo.chatsToText());
			memberTextArea.setText(CurrentConvo.membersToText());
		}
	}
	
	public static void UpdateOrCreateChat(Conversation newConvo) {
		NewButton("FUCK YEAH!");

		for (int i = 0; i < Conversations.size(); i++) {
			if (Conversations.get(i).ID.equals(newConvo.ID) ) {
				Conversations.get(i).ID = newConvo.ID;
				Conversations.get(i).Name = newConvo.Name;
				Conversations.get(i).Members = newConvo.Members;
				Conversations.get(i).Chats = newConvo.Chats;
				System.out.println("NOOOOOOOOOOOOOOOOOO");
				return;
			}
		}
		
		JButton convoButton = NewButton(newConvo.Name);
		Conversations.add(newConvo);

		convoButton.addActionListener((ActionListener) new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	ChatOpened = true;
            	CurrentConvo = newConvo;
            	UpdateChatText();
            	System.out.println(CurrentConvo.ID);
                setChatVisible(true);
                
                Message newMsg = new Message();
    			newMsg.setType("refresh");
				try {
					objectOutput.writeObject(newMsg);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
            }
        });
	}
	public static Boolean isChatOpened() {
		return ChatOpened;

	}
	
	public static Message createChatMessage() {
		Message msg = new Message();
		return msg;

	}
	
	public static String getCurrentId() {
		if (CurrentConvo != null) {
			return CurrentConvo.ID;
		}
		return "-1";
	}
	
	public static void setChatVisible(Boolean bool) {
//        ScrollTextArea.setVisible(bool);
//        textField.setVisible(bool);
//        memberTextArea.setVisible(bool);
//        ChatSendButton.setVisible(bool);
//        AddUserSendButton.setVisible(bool);
//		memberTextArea.setVisible(bool);
	}
	
	public static String getTextForSending() {
		String text = textField.getText();
		textField.setText("");
		return text;
	}
}
