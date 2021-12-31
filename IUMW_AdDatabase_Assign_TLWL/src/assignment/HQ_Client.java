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
import javax.swing.JViewport;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

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
		String panel_bookCtlg_name = "Book Catalogue";
		tabbedPane.addTab(panel_bookCtlg_name, null, panel_bookCtlg, null);						// Add to Tabs
		panel_bookCtlg.setLayout(new BoxLayout(panel_bookCtlg, BoxLayout.Y_AXIS));		// Sets Panel Layout
		//panel_bookCtlg.setBorder(BorderFactory.createLineBorder(Color.black));
		
		// Old Code
//		/* Book Catalogue, Inner Panel 1 Start */
//		JPanel panel_bookCtlg_inner1 = new JPanel();
//		panel_bookCtlg_inner1.setBorder(new LineBorder(new Color(0, 0, 0)));
//		panel_bookCtlg.add(panel_bookCtlg_inner1);
//		panel_bookCtlg_inner1.setLayout(null);
//		/* Label */
//		JLabel txt_bookCtlg_inner1 = new JLabel("Book Catalogue");
//		//txt_bookCtlg.setHorizontalAlignment(SwingConstants.CENTER);
//		txt_bookCtlg_inner1.setLabelFor(frame);
//		txt_bookCtlg_inner1.setBounds(10, 20, 100, 25);
//		panel_bookCtlg_inner1.add(txt_bookCtlg_inner1);
//		/* Table Start */
//		//int table_bookCtlg_rowCount = getTable_RowCount("book_ctlg");
//		String[] table_bookCtlg_colNames = getTable_ColNames("book_ctlg");
//		String[][] table_bookCtlg_rowData = getTable_RowData("book_ctlg");
//		// Scroll Pane
//		JScrollPane scrollPane_bookCtlg = new JScrollPane();
//		scrollPane_bookCtlg.setBounds(20, 50, 1130, 190);
//		panel_bookCtlg_inner1.add(scrollPane_bookCtlg);
//		// The Table Object
//		DefaultTableModel tableModel_bookCtlg = new DefaultTableModel(table_bookCtlg_rowData, table_bookCtlg_colNames);
//		scrollPane_bookCtlg.setViewportView(new JTable(tableModel_bookCtlg));
//		/* Table End */
		
		/* Book Catalogue, Inner Panel 1 Start */
		/* Inner Panel Object */
		JPanel panel_bookCtlg_inner1 = new JPanel();
		/* Label */
		JLabel txt_bookCtlg_inner1 = new JLabel();
		/* Scroll Pane */
		JScrollPane scrollPane_bookCtlg = new JScrollPane();
		/* Table Start */
		String table_bookCtlg_tableName = "book_ctlg";
		// The Table Object
		DefaultTableModel tableModel_bookCtlg = new DefaultTableModel();
		/* Table End */
		// Runs Function to Design and Render it
		Make_tablePanel(frame, panel_bookCtlg, panel_bookCtlg_inner1, txt_bookCtlg_inner1, panel_bookCtlg_name, table_bookCtlg_tableName, scrollPane_bookCtlg, tableModel_bookCtlg);	
		/* Book Catalogue, Inner Panel 1 End */
		/* Book Catalogue, Inner Panel 2 Start */
		JPanel panel_bookCtlg_inner2 = new JPanel();
		panel_bookCtlg_inner2.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_bookCtlg.add(panel_bookCtlg_inner2);
		panel_bookCtlg_inner2.setLayout(null);
		/* Labels and Text Fields */
		// Header
		JLabel txt_bookCtlg_inner2 = new JLabel("Add New Book");
		txt_bookCtlg_inner2.setBounds(20, 20, 100, 25);
		panel_bookCtlg_inner2.add(txt_bookCtlg_inner2);
		// ISBN Label
		JLabel txt_bookCtlg_inner2_ISBN = new JLabel("ISBN");
		txt_bookCtlg_inner2_ISBN.setBounds(20, 50, 80, 20);
		panel_bookCtlg_inner2.add(txt_bookCtlg_inner2_ISBN);
		// ISBN Text Field
		JTextField txtEnt_bookCtlg_inner2_ISBN = new JTextField();
		txtEnt_bookCtlg_inner2_ISBN.setColumns(13);
		txtEnt_bookCtlg_inner2_ISBN.setBounds(100, 50, 200, 20);
		panel_bookCtlg_inner2.add(txtEnt_bookCtlg_inner2_ISBN);
		// Name Label
		JLabel txt_bookCtlg_inner2_name = new JLabel("Name");
		txt_bookCtlg_inner2_name.setBounds(20, 80, 80, 20);
		panel_bookCtlg_inner2.add(txt_bookCtlg_inner2_name);
		// Name Text Field
		JTextField txtEnt_bookCtlg_inner2_name = new JTextField();
		txtEnt_bookCtlg_inner2_name.setColumns(36);
		txtEnt_bookCtlg_inner2_name.setBounds(100, 80, 200, 20);
		panel_bookCtlg_inner2.add(txtEnt_bookCtlg_inner2_name);
		// Author Label
		JLabel txt_bookCtlg_inner2_author = new JLabel("Author");
		txt_bookCtlg_inner2_author.setBounds(20, 110, 80, 20);
		panel_bookCtlg_inner2.add(txt_bookCtlg_inner2_author);
		// Author Text Field
		JTextField txtEnt_bookCtlg_inner2_author = new JTextField();
		txtEnt_bookCtlg_inner2_author.setColumns(36);
		txtEnt_bookCtlg_inner2_author.setBounds(100, 110, 200, 20);
		panel_bookCtlg_inner2.add(txtEnt_bookCtlg_inner2_author);
		/* Buttons */
		// Error in Inputs
		JLabel txt_bookCtlg_inner2_addErrTxt = new JLabel();
		txt_bookCtlg_inner2_addErrTxt.setForeground(Color.RED);
		txt_bookCtlg_inner2_addErrTxt.setBounds(20, 190, 200, 20);
		panel_bookCtlg_inner2.add(txt_bookCtlg_inner2_addErrTxt);
		// Button Add Book
		JButton btn_bookCtlg_inner2_add = new JButton("Add Book");
		btn_bookCtlg_inner2_add.setBounds(20, 160, 90, 25);
		panel_bookCtlg_inner2.add(btn_bookCtlg_inner2_add);
		btn_bookCtlg_inner2_add.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if(txtEnt_bookCtlg_inner2_ISBN.getText().equals("") || txtEnt_bookCtlg_inner2_name.getText().equals("") || txtEnt_bookCtlg_inner2_author.getText().equals("")) {
						txt_bookCtlg_inner2_addErrTxt.setText("Invalid Inputs in Text Fields");
					} else {
						long ISBN = Long.parseLong(txtEnt_bookCtlg_inner2_ISBN.getText());
						String Name = txtEnt_bookCtlg_inner2_name.getText();
						String Author = txtEnt_bookCtlg_inner2_author.getText();
						
						if((String.valueOf(ISBN)).chars().count() == 13 && checkTable_Entry(tableModel_bookCtlg, String.valueOf(ISBN), 0) < 0) {
							// Runs Insert Query
							RMI_Server.updateQuery("INSERT INTO book_ctlg (ISBN, Name, Author) VALUES ('" + ISBN + "', '" + Name + "', '" + Author + "')");
							
							// Clears Text Fields
							txtEnt_bookCtlg_inner2_ISBN.setText("");
							txtEnt_bookCtlg_inner2_name.setText("");
							txtEnt_bookCtlg_inner2_author.setText("");
							//tableModel_bookCtlg.getValueAt(String.valueOf(ISBN), "ISBN");
							// Update Table Here
							tableModel_bookCtlg.addRow(new String[]{String.valueOf(ISBN), Name, Author});
							scrollPane_bookCtlg.setViewportView(new JTable(tableModel_bookCtlg));
							
							txt_bookCtlg_inner2_addErrTxt.setText("New Input Succesfull");
						} else {
							txt_bookCtlg_inner2_addErrTxt.setText("Invalid Inputs in Text Fields");
						}
					}
				} catch(Exception error) {
					txt_bookCtlg_inner2_addErrTxt.setText("Invalid Inputs in Text Fields");
					System.out.println(error);
				}
			}
		});
		/* Book Catalogue, Inner Panel 2 End */
		
		// Panel Book Stock 
		// Panel
		JPanel panel_bookStock = new JPanel();
		String panel_bookStock_name = "Book Stock";
		tabbedPane.addTab(panel_bookStock_name, null, panel_bookStock, null);						// Add to Tabs
		panel_bookStock.setLayout(new BoxLayout(panel_bookStock, BoxLayout.Y_AXIS));	// Sets Panel Layout
		/* Book Catalogue, Inner Panel 1 Start */
		/* Inner Panel Object */
		JPanel panel_bookStock_inner1 = new JPanel();
		/* Label */
		JLabel txt_bookStock_inner1 = new JLabel();
		/* Scroll Pane */
		JScrollPane scrollPane_bookStock = new JScrollPane();
		/* Table Start */
		String table_bookStock_tableName = "book_stock";
		// The Table Object
		DefaultTableModel tableModel_bookStock = new DefaultTableModel();
		/* Table End */
		// Runs Function to Design and Render it
		Make_tablePanel(frame, panel_bookStock, panel_bookStock_inner1, txt_bookStock_inner1, panel_bookStock_name, table_bookStock_tableName, scrollPane_bookStock, tableModel_bookStock);
		/* Book Catalogue, Inner Panel 1 End */
		/* Book Catalogue, Inner Panel 2 Start */
		JPanel panel_bookStock_inner2 = new JPanel();
		panel_bookStock_inner2.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_bookStock.add(panel_bookStock_inner2);
		panel_bookStock_inner2.setLayout(null);
		
//		JPanel panel_bookCtlg_inner2 = new JPanel();
//		panel_bookCtlg_inner2.setBorder(new LineBorder(new Color(0, 0, 0)));
//		panel_bookCtlg.add(panel_bookCtlg_inner2);
//		panel_bookCtlg_inner2.setLayout(null);
		
		// Display Window
		frame.pack();											// Combine Window Elements, and Resize Them if Window Too Small
		frame.setVisible(true);									// Renders Window
	}
	
	// Check Table Entry Exist
	public static int checkTable_Entry(DefaultTableModel table, String entry, int col) {
		for(int i=0; i<table.getRowCount(); i++) {
			if(table.getValueAt(i, col).equals(entry)) {
				return i;
			}
		}
		
		return -1;
	}
	
	// Get Table Rows
	public int getTable_RowCount(String tableName) {
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
	public static void Make_tablePanel(JFrame frame, JPanel panel, JPanel panel_inner, JLabel label, String panelName, String tableName, JScrollPane scrollPane, DefaultTableModel tableModel) {
		try {
			// The Inner Panel, Table Panel
			panel_inner.setBorder(new LineBorder(new Color(0, 0, 0)));
			panel.add(panel_inner);
			panel_inner.setLayout(null);
			
			// Inner Panel Label
			label.setText(panelName);
			label.setLabelFor(frame);
			label.setBounds(10, 20, 100, 25);
			panel_inner.add(label);
			
			// Scroll Pane
			scrollPane.setBounds(20, 50, 1130, 190);
			panel_inner.add(scrollPane);
			
			// Table Column Names & Row Data
			String[] table_colNames = getTable_ColNames(tableName);
			String[][] table_rowData = getTable_RowData(tableName);
			// The Table
			//tableModel = new DefaultTableModel(table_rowData, table_colNames);
			for(int i=0; i<table_colNames.length; i++) {
				tableModel.addColumn(table_colNames[i]);
			}
			for(int i=0; i<table_rowData.length; i++) {
				tableModel.addRow(table_rowData[i]);
			}
			scrollPane.setViewportView(new JTable(tableModel));

//			// Number of Rows of the Table
//			List<String> tableRowNums = RMI_Server.selectQuery("SELECT count(*) FROM " + tableName);
//			int rowNums = Integer.parseInt(tableRowNums.get(0));
//			// Column Names of the Table
//			List<String> tableCols = RMI_Server.selectQuery("SELECT COLUMN_NAME FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_SCHEMA = 'uptown_books' AND TABLE_NAME = '" + tableName + "'");
//			// Row Values of the Table
//			List<List<String>> tableRows = RMI_Server.selectQuery_allRows(tableName);
//			
//			//System.out.print(tableCols);
//			//System.out.print(tableRows);
//			
//			// Convert List to Array
//			String[] columns = tableCols.toArray(new String[0]);
//			String[][] rows = tableRows.stream().map(l -> l.stream().toArray(String[]::new)).toArray(String[][]::new);
//			
//			// The JTable Object
//			JTable table = new JTable(rows, columns);
//			//table.setBounds(20, 50, 1000, 30+(30*rowNums));
//			//table.getTableHeader().setBounds(20, 50, 1200, 15);
//			table.setBounds(20, 60, 1200, 15*rowNums);
//			//panel.add(table.getTableHeader());
//			panel.add(table);
//			/*
//			JScrollPane scrollPane = new JScrollPane(table);
//			table.setFillsViewportHeight(true);
//			scrollPane.setSize(1200, 30+(30*rowNums));
//			panel.add(scrollPane);
//			//panel.add(table.getTableHeader(), BorderLayout.PAGE_START);		// Add to JPanel
//			//panel.add(table, BorderLayout.CENTER);
//			*/
		} catch(Exception error) {
			System.out.println(error);
		}
	}
}
