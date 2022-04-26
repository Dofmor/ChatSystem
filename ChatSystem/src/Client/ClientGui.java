package Client;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import Shared.Message;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

public class ClientGui implements ClientUserInterface {
	
	private static ObjectOutputStream objectOutputStream;
	private static ObjectInputStream objectInputStream;
	
	public ClientGui(ObjectOutputStream objectOutputStream, ObjectInputStream objectInputStream) {
		this.objectOutputStream = objectOutputStream;
		this.objectInputStream = objectInputStream;
	}

	
	
	
	private static String[] optionsToChoose = {"None of the listed"};

	private static JFrame GuiFrame = new JFrame("Chat Window");;
	private static JButton SendButton = new JButton("Send");
	private static JTextField textField = new JTextField("", 20);
	private static JComboBox ChatChoice = new JComboBox<>(optionsToChoose);

	
	@Override
	public void processCommands() {
		// TODO Auto-generated method stub

		
		

		
		
	}
	
	private static void PrintMessage(Message msg) {
		System.out.println("Type: " + msg.getType());
		System.out.println("Status: " + msg.getStatus());
		System.out.println("Data: " + msg.getData());
		System.out.println("-----------------------------------------------------------");
	}
	
}
