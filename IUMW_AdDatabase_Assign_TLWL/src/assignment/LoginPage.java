package assignment;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/* Login Page for Database */
public class LoginPage {
	// The JFrame Object
	private JFrame frame;
	// The JPanel Objects
	private JPanel panel = new JPanel();
	
	// Login Page Elements
	JLabel txt_loginPg_title = new JLabel("Login Page");
	JLabel txt_loginPg_loginValid = new JLabel("");				// Login Text Success/Fail
	JTextField loginPg_txtEnt_user;								// Username Text Field
	JPasswordField loginPg_txtEnt_password;						// Password Text Field
	
	public LoginPage(List<String> usernames, List<String> passwords) {
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
		loginPg_txt_user.setBounds(20, 20, 80, 25);
		panel.add(loginPg_txt_user);
		// Username Text Field
		loginPg_txtEnt_user = new JTextField(26);
		loginPg_txtEnt_user.setBounds(120, 20, 165, 25);
		panel.add(loginPg_txtEnt_user);
		// Password Text
		JLabel loginPg_txt_password = new JLabel("Password");
		loginPg_txt_password.setBounds(20, 50, 80, 25);
		panel.add(loginPg_txt_password);
		// Password Text Field
		loginPg_txtEnt_password = new JPasswordField();
		loginPg_txtEnt_password.setBounds(120, 50, 165, 25);
		panel.add(loginPg_txtEnt_password);
		// Login Button
		JButton loginPg_btn_login = new JButton("Login");
		loginPg_btn_login.setBounds(20, 80, 80, 25);
		// Event Listener for Button
		loginPg_btn_login.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Boolean flag = true;
				String username = loginPg_txtEnt_user.getText();
				String password = String.valueOf(loginPg_txtEnt_password.getPassword());
				System.out.println(username + ", " + password);
				
				for(int i=0; i<usernames.size(); i++) {
					if(username.equals(usernames.get(i)) && password.contentEquals(passwords.get(i))) {
						txt_loginPg_loginValid.setText("Login Success");
						flag = false;
						break;
					}
				}
				
				if(flag) {
					txt_loginPg_loginValid.setText("Invalid Username or Password");
				}
			}
		});				
		panel.add(loginPg_btn_login);
		// Login Text, Successful/Failed Login
		txt_loginPg_loginValid.setBounds(20, 110, 300, 25);
		panel.add(txt_loginPg_loginValid);
		
		// Display Window
		frame.pack();											// Combine Window Elements, and Resize Them if Window Too Small
		frame.setVisible(true);									// Renders Window
	}
}
