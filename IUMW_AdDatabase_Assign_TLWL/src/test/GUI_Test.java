package test;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class GUI_Test implements ActionListener {
	
	private int count = 0;
	private JLabel label;
	private JFrame frame;
	private JPanel panel;
	
	public GUI_Test() {
		// Create JFrame Object
		frame = new JFrame();
		
		// Clickable Buttons
		JButton button = new JButton("Click me");
		button.addActionListener(this);
		// Panel (i.e. text)
		label = new JLabel("Number of clicks: 0");
		
		// Create Panel (i.e. Window)
		panel = new JPanel();
		panel.setBorder(BorderFactory.createEmptyBorder(100, 150, 100, 150));
		panel.setLayout(new GridLayout(0, 1));
		panel.add(button);	// Add Button to Panel
		panel.add(label);	// Add Label to Panel
		
		frame.add(panel, BorderLayout.CENTER);					// Add Panel to Frame
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	// What happens when window closed
		frame.setTitle("Java Frame GUI Test");					// Window Title
		frame.pack();
		frame.setVisible(true);
	}
	
	public static void main(String[] args) {
		new GUI_Test();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		count++;
		label.setText("Number of clicks: " + count);
	}
}
