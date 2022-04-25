package Client;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ClientGui implements ClientUserInterface {
	
	public Boolean Login(String Username, String Password) {

		return false;
	}
	
	
	@Override
	public void processCommands() {
		// TODO Auto-generated method stub
		
		
//		JFrame jFrame = new JFrame();
//        String msg  = "Client Gui Being Made";
//        JOptionPane.showMessageDialog(jFrame, msg);
//		
		
//		boolean validLogin = false;
		
		
		
		JFrame Gui = new JFrame("Chat Window");
		JPanel panel = new JPanel();
		LayoutManager layout = new FlowLayout();
		panel.setLayout(layout);
		
		JTextField userName = new JTextField();
		JTextField userPassword = new JTextField();
		
		panel.add(userName);
		panel.add(userPassword);
		
		Gui.add(panel);
		
		Gui.setSize(560, 200);
		Gui.setLocationRelativeTo(null); // Center on screen
		Gui.setVisible(true); // make visible
		

		
		
		
		
	}

}
