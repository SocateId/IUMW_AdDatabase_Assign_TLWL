package assignment;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.Naming;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class HQ_Client {
	// Connect to RMI Server
	static Interface_Remote RMI_Server;
	
	public static void main(String[] args) throws Exception {
		RMI_Server = (Interface_Remote) Naming.lookup("//localhost/UptownBooks_Server");
		
		// Testing
		System.out.println(RMI_Server.getMessage());
		
		
		LoginPage();
	}
	
	//static JFrame frame;
	
	// The Login Page
	public static void LoginPage() {
		// Create JFrame Object
		//frame = new JFrame();									// The JFrame Object
		JFrame frame = new JFrame();							// The JFrame Object
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	// When Window closed, program exited
		frame.setTitle("Uptown Books Database");				// Window Title
		frame.setMinimumSize(new Dimension(350, 200));			// Minimum Window Size
		
		// The JPanel Objects
		JPanel panel = new JPanel();							
		
		// Login Page Elements
		JLabel txt_loginPg_title = new JLabel("Login Page");
		JLabel txt_loginPg_loginValid = new JLabel("");				// Login Text Success/Fail
		JTextField loginPg_txtEnt_user;								// Username Text Field
		JPasswordField loginPg_txtEnt_password;						// Password Text Field
		
		// Login Page
		frame.add(panel);										// Adds Panel To Window
		panel.setLayout(null);									// Sets Panel Layout
		
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
				try {
					// Rows of Employees
					List<String> emplyRows = RMI_Server.selectQuery("SELECT count(*) FROM employees");
					int tableRows = Integer.parseInt(emplyRows.get(0));
					// Database, the Usernames and Passwords of Employees
					List<String> DB_Usernames = RMI_Server.selectQuery("SELECT name FROM employees WHERE store_number = '0'");
					//System.out.println(DB_Usernames);
					List<String> DB_Passwords = RMI_Server.selectQuery("SELECT password FROM employees WHERE store_number = '0'");
					//System.out.println(DB_Passwords);
					
					Boolean flag = true;
					String username = loginPg_txtEnt_user.getText();
					String password = String.valueOf(loginPg_txtEnt_password.getPassword());
					System.out.println(username + ", " + password);
					
					for(int i=0; i<DB_Usernames.size(); i++) {
						if(username.equals(DB_Usernames.get(i)) && password.contentEquals(DB_Passwords.get(i))) {
							flag = false;
							break;
						}
					}
					
					// Login Failed
					if(flag) {
						txt_loginPg_loginValid.setText("Invalid Username or Password");
						
					// Login Successful
					} else {
						txt_loginPg_loginValid.setText("Login Success");
						frame.dispose();
						HQ_Page();
					}
				} catch(Exception error) {
					System.out.println(error);
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
	
	// Main Page
	public static void HQ_Page() {
		// Create JFrame Object
		JFrame frame = new JFrame();							// The JFrame Object
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	// When Window closed, program exited
		frame.setTitle("Uptown Books Database");				// Window Title
		frame.setMinimumSize(new Dimension(1200, 600));			// Minimum Window Size
		//frame.setLayout(new GridLayout(5, 1));

		// Frame Tabbed Panel Layout
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		frame.getContentPane().add(tabbedPane, BorderLayout.CENTER);
		
		// Panel Book Catalogue 
		// Panel
		JPanel panel_bookCtlg = new JPanel();
		tabbedPane.addTab("Book_Ctlg", null, panel_bookCtlg, null);						// Add to Tabs
		panel_bookCtlg.setLayout(new BoxLayout(panel_bookCtlg, BoxLayout.Y_AXIS));		// Panel is Box Layout										// Sets Panel Layout
		panel_bookCtlg.setBorder(BorderFactory.createLineBorder(Color.black));
		/* Book Catalogue, Inner Panel 1 Start */
		JPanel panel_bookCtlg_inner1 = new JPanel();
		panel_bookCtlg.add(panel_bookCtlg_inner1);
		panel_bookCtlg_inner1.setLayout(null);
		/* Label */
		JLabel txt_bookCtlg = new JLabel("Book Catalogue");
		//txt_bookCtlg.setHorizontalAlignment(SwingConstants.CENTER);
		txt_bookCtlg.setLabelFor(frame);
		txt_bookCtlg.setBounds(10, 20, 100, 25);
		panel_bookCtlg_inner1.add(txt_bookCtlg);
		/* Table Start */
		int table_bookCtlg_rowCount = getTable_RowCount("book_ctlg");
		String[] table_bookCtlg_colNames = getTable_ColNames("book_ctlg");
		String[][] table_bookCtlg_rowData = getTable_RowData("book_ctlg");
		// Scroll Pane
		JScrollPane scrollPane_bookCtlg = new JScrollPane();
		scrollPane_bookCtlg.setBounds(20, 50, 1130, 18+(17*table_bookCtlg_rowCount));
		panel_bookCtlg_inner1.add(scrollPane_bookCtlg);
		// The Table Object
		JTable table_bookCtlg = new JTable(table_bookCtlg_rowData, table_bookCtlg_colNames);
		scrollPane_bookCtlg.setViewportView(table_bookCtlg);
		/* Table End */
		
		/* Book Catalogue, Inner Panel 1 End */
		
		
		//Make_Table(panel_bookCtlg_inner1, "book_ctlg");
		// Buttons
		//JButton btn_bookCtlg = new JButton("Add Book");
		//btn_bookCtlg.setBounds(20, 80, 150, 25);
		//panel_bookCtlg.add(btn_bookCtlg);

		/*
		// Panel Book Stock
		JPanel panel_bookStk = new JPanel(new BorderLayout());
		frame.add(panel_bookStk);								// Adds Panel To Window
		JButton btn_bookStk = new JButton("Book Stock");
		panel_bookStk.add(btn_bookStk);
		
		// Panel Employees
		JPanel panel_emply = new JPanel(new BorderLayout());
		frame.add(panel_emply);								// Adds Panel To Window
		JButton btn_emply = new JButton("Book Stock");
		panel_emply.add(btn_emply);
		
		// Panel Offices
		JPanel panel_offices = new JPanel(new BorderLayout());
		frame.add(panel_offices);								// Adds Panel To Window
		JButton btn_offices = new JButton("Book Stock");
		panel_offices.add(btn_offices);
		
		// Panel Sales
		JPanel panel_sales = new JPanel(new BorderLayout());
		frame.add(panel_sales);								// Adds Panel To Window
		JButton btn_sales = new JButton("Book Stock");
		panel_sales.add(btn_sales);
		*/
		
		// Display Window
		frame.pack();											// Combine Window Elements, and Resize Them if Window Too Small
		frame.setVisible(true);									// Renders Window
	}
	
	// Get Table Rows
	public static int getTable_RowCount(String tableName) {
		try {
			// Number of Rows of the Table
			List<String> tableRowNums = RMI_Server.selectQuery("SELECT count(*) FROM " + tableName);
			int rowNums = Integer.parseInt(tableRowNums.get(0));
			
			return rowNums;
			
		} catch(Exception error) {
			System.out.println(error);
			return 0;
		}			
		
	}
	
	// Get Table Coulmns Names
	public static String[] getTable_ColNames(String tableName) {
		try {
			// Column Names of the Table
			List<String> tableCols = RMI_Server.selectQuery("SELECT COLUMN_NAME FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_SCHEMA = 'uptown_books' AND TABLE_NAME = '" + tableName + "'");
			String[] columnsNames = tableCols.toArray(new String[0]);
			
			return columnsNames;
			
		} catch(Exception error) {
			System.out.println(error);
			return null;
		}
	}
	
	// Get Table Row Data
	public static String[][] getTable_RowData(String tableName) {
		try {
			// Column Names of the Table
			List<List<String>> tableRows = RMI_Server.selectQuery_allRows(tableName);
			String[][] rowNames = tableRows.stream().map(l -> l.stream().toArray(String[]::new)).toArray(String[][]::new);
			
			return rowNames;
			
		} catch(Exception error) {
			System.out.println(error);
			return null;
		}
	}
	
	// Create Table Function
	public static void Make_Table(JPanel panel, String tableName) {
		try {
			// Number of Rows of the Table
			List<String> tableRowNums = RMI_Server.selectQuery("SELECT count(*) FROM " + tableName);
			int rowNums = Integer.parseInt(tableRowNums.get(0));
			// Column Names of the Table
			List<String> tableCols = RMI_Server.selectQuery("SELECT COLUMN_NAME FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_SCHEMA = 'uptown_books' AND TABLE_NAME = '" + tableName + "'");
			// Row Values of the Table
			List<List<String>> tableRows = RMI_Server.selectQuery_allRows(tableName);
			
			//System.out.print(tableCols);
			//System.out.print(tableRows);
			
			// Convert List to Array
			String[] columns = tableCols.toArray(new String[0]);
			String[][] rows = tableRows.stream().map(l -> l.stream().toArray(String[]::new)).toArray(String[][]::new);
			
			// The JTable Object
			JTable table = new JTable(rows, columns);
			//table.setBounds(20, 50, 1000, 30+(30*rowNums));
			//table.getTableHeader().setBounds(20, 50, 1200, 15);
			table.setBounds(20, 60, 1200, 15*rowNums);
			//panel.add(table.getTableHeader());
			panel.add(table);
			/*
			JScrollPane scrollPane = new JScrollPane(table);
			table.setFillsViewportHeight(true);
			scrollPane.setSize(1200, 30+(30*rowNums));
			panel.add(scrollPane);
			//panel.add(table.getTableHeader(), BorderLayout.PAGE_START);		// Add to JPanel
			//panel.add(table, BorderLayout.CENTER);
			*/
			
		} catch(Exception error) {
			System.out.println(error);
		}
	}
}
