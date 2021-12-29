package assignment;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class HQ_Client {
	// The JFrame Object
	private JFrame frame;
	// The JPanel Objects
	private JPanel panel = new JPanel();
	
	// Login Page Text
	JLabel txt_loginPg_title = new JLabel("Login Page");
	JLabel txt_loginPg_success = new JLabel("Login Success");
	JLabel txt_loginPg_fail = new JLabel("Login Fail");
	
	// Login Page Buttons
	private HQ_Client() {
		// Create JFrame Object
		frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	// When Window closed, program exited
		frame.setTitle("Uptown Books Database");				// Window Title
		frame.setMinimumSize(new Dimension(1200, 600));			// Minimum Window Size
												
		
		
		// Login Page
		frame.add(panel);										// Adds Panel To Window
		panel.setLayout(null);
		// Username Text
		JLabel loginPg_txt_user = new JLabel("User");
		loginPg_txt_user.setBounds(10, 20, 80, 25);
		panel.add(loginPg_txt_user);
		// Username Text Field
		JTextField loginPg_txtEnt_user = new JTextField(26);
		loginPg_txtEnt_user.setBounds(100, 20, 165, 25);
		panel.add(loginPg_txtEnt_user);
		// Password Text
		JLabel loginPg_txt_password = new JLabel("Password");
		loginPg_txt_password.setBounds(10, 50, 80, 25);
		panel.add(loginPg_txt_password);
		// Password Text Field
		JPasswordField loginPg_txtEnt_password = new JPasswordField();
		loginPg_txtEnt_password.setBounds(100, 50, 165, 25);
		panel.add(loginPg_txtEnt_password);
		
		// Display Window
		frame.pack();											// Combine Window Elements, and Resize Them if Window Too Small
		frame.setVisible(true);									// Renders Window
	}
	
	public static void main(String[] args) {
		new HQ_Client();
	}
}
