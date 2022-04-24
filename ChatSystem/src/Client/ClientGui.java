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
		
		
		while (true) {
						
			JTextField Username = new JTextField();
			JTextField Password = new JPasswordField();
			Object[] Message = {"Username:", Username,"Password:", Password};
			int optionChoosen = JOptionPane.showConfirmDialog(null, Message, "Login", JOptionPane.OK_CANCEL_OPTION);
			if (optionChoosen == JOptionPane.OK_OPTION) {
				if (Login(Username.getText(), Password.getText()) == true) {
					break;
				}else {
					
					JFrame jFrame = new JFrame();
			        String msg  = "Login Failed!";
			        JOptionPane.showMessageDialog(jFrame, msg);
					continue;
				}
	
			}			
			break;
		}
		
		
		

		
		
		
		
	}

}
