import javax.swing.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;

public class ITGUI implements ITuserInterface {
	public void processCommands(){
	}

	 IT techPerson;
	 //Creates a frame variable to hold the frame
	 private JFrame frame;
	 //Created a panel variable to hold the panel
	 private JPanel panel, panel2;
	 Box infoBox;
	 JLabel temp, tempTitle;

	 public ITGUI(){
		 techPerson = new IT();
		 createWindow();
	 }
	 
//	 //Creates the GUI window
	 public void createWindow(){
		 //Creates frame with the title of DVD Collection GUI
	     frame = new JFrame("IT GUI");
	     //Sets it so if you click the close tab button program exits
	     frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	     //Calls the create UI function with frame passed into it
	     createUI(frame);
	     //Sets the frame size
	     frame.setSize(650, 350);      
	     //Centers the frame
	     frame.setLocationRelativeTo(null);
	     //Sets the frame visible
	     frame.setVisible(true);
	 }
	 
	 private void createUI(final JFrame frame){ 
		 panel = new JPanel(new FlowLayout());
		 panel2 = new JPanel(new FlowLayout());

		 JButton addButton = new JButton("Create User");
		 JButton removeButton = new JButton("Delete User");
		 JButton chatLogButton = new JButton("Get Chat Log");
		 JButton exitButton = new JButton("Exit");
		
		 addButton.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		       createUser();
		    }
		 });
		
		 removeButton.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		       deleteUser();
		    }
		 });
		  
		 chatLogButton.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		       getChatLog();
		    }
		 });
		
		 exitButton.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		       JOptionPane.showMessageDialog(frame, "Program Ending Have A Nice Day!");
		       System.exit(0);
		    }
		 });
		
		 frame.getRootPane().setDefaultButton(exitButton);
		 
		 panel.add(addButton);
		 panel.add(removeButton);
		 panel.add(chatLogButton);
		 panel.add(exitButton);
		 
		 infoBox = Box.createVerticalBox();
		 tempTitle = new JLabel("Info Returned Below");
		 temp = new JLabel("");
		 infoBox.add(tempTitle, BorderLayout.NORTH);
		 infoBox.add(temp, BorderLayout.CENTER);
		 panel2.add(infoBox);
		 panel2.setBackground(Color.LIGHT_GRAY);
		 
		 frame.add(panel, BorderLayout.NORTH);
		 frame.add(panel2, BorderLayout.CENTER);
		 frame.getContentPane();
	    }
	 
	
	//MODIFY DVD WITH PARAMS FOR MODIFY BUTTON
	private void createUser() {
		String name = JOptionPane.showInputDialog("Enter name");
		if (name == null) {
			return;		// dialog was cancelled
		}
		String userName = JOptionPane.showInputDialog("Enter Username");
		if (userName == null) {
			return;		// dialog was cancelled
		}
		String passWord = JOptionPane.showInputDialog("Enter Password");
		if (passWord == null) {
			return;		// dialog was cancelled
		}
		String userType = JOptionPane.showInputDialog("Enter User Type");
		if (userType == null) {
			return;		// dialog was cancelled
		}

		techPerson.createUser(name, userName, passWord, userType);
	}

	private void deleteUser() {
		String name = JOptionPane.showInputDialog("Enter name");
		if (name == null) {
			return;		// dialog was cancelled
		}
		String userName = JOptionPane.showInputDialog("Enter Username");
		if (userName == null) {
			return;		// dialog was cancelled
		}
		String passWord = JOptionPane.showInputDialog("Enter Password");
		if (passWord == null) {
			return;		// dialog was cancelled
		}
		String userType = JOptionPane.showInputDialog("Enter User Type");
		if (userType == null) {
			return;		// dialog was cancelled
		}

		techPerson.deleteUser(name, userName, passWord, userType);	
	}
	
	private void getChatLog() {
		temp.setText(techPerson.getChatLog());
		frame.getContentPane();
	}
}
