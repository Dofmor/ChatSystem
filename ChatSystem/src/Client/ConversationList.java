package Client;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class ConversationList extends JPanel {

	private static JPanel buttonContainer = new JPanel();
	private static JScrollPane scrollPane = new JScrollPane(buttonContainer, 
            JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
            JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
	
	private static int countButtons = 0;

	
	public ConversationList() {		
        this.setLayout(new BorderLayout(10, 10));
        this.add(scrollPane);
		buttonContainer.setLayout(new GridLayout(1000, 1));
		 
	}
	
	public static JButton NewButton(String str) {
		
		countButtons = countButtons + 1;
		
        JButton myButton = new JButton(str);
        myButton.setPreferredSize(new Dimension(200, 40));
		buttonContainer.setLayout(new GridLayout(10, 1));
        buttonContainer.add(myButton);
        return myButton;

	}
}
