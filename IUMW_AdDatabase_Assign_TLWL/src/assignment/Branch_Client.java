package assignment;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.Naming;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import org.jfree.data.category.DefaultCategoryDataset;

public class Branch_Client {
	// Connect to RMI Server
	static Interface_Remote RMI_Server;
	
	// Store Number
	static int branchStore_number = 1;
	
	public static void main(String[] args) throws Exception {
		RMI_Server = (Interface_Remote) Naming.lookup("//localhost/UptownBooks_Server");
		
		// Testing
		System.out.println(RMI_Server.getMessage());
		
		LoginPage();
	}
	
	// The Login Page
	public static void LoginPage() {
		// Create JFrame Object
		//frame = new JFrame();																// The JFrame Object
		JFrame frame = new JFrame();														// The JFrame Object
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);								// When Window closed, program exited
		frame.setTitle("Uptown Books Database Branch Office " + branchStore_number);		// Window Title
		frame.setMinimumSize(new Dimension(400, 200));										// Minimum Window Size
		
		// The JPanel Objects
		JPanel panel = new JPanel();							
		
		// Login Page Elements
		JLabel txt_loginPg_title = new JLabel("Login Page");
		JLabel txt_loginPg_loginValid = new JLabel("");				// Login Text Success/Fail
		JTextField loginPg_txtEnt_user;								// Username Text Field
		JPasswordField loginPg_txtEnt_password;						// Password Text Field
		
		// Login Page
		frame.add(panel);											// Adds Panel To Window
		panel.setLayout(null);										// Sets Panel Layout
		
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
					List<String> DB_Usernames = RMI_Server.selectQuery("SELECT ID FROM employees WHERE store_number = '0' OR store_number = '" + branchStore_number + "' ORDER BY ID");
					System.out.println(DB_Usernames);
					List<String> DB_Passwords = RMI_Server.selectQuery("SELECT password FROM employees WHERE store_number = '0' OR store_number = '" + branchStore_number + "' ORDER BY ID");
					System.out.println(DB_Passwords);
					
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
						Branch_Page();
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
	public static void Branch_Page() {
		// Create JFrame Object
		JFrame frame = new JFrame();													// The JFrame Object
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);							// When Window closed, program exited
		frame.setTitle("Uptown Books Database Branch Office " + branchStore_number);	// Window Title
		frame.setMinimumSize(new Dimension(1200, 600));									// Minimum Window Size
		
		// Frame Tabbed Panel Layout
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		frame.getContentPane().add(tabbedPane, BorderLayout.CENTER);
		
		// Panel Book Catalogue 
		// Panel
		JPanel panel_bookCtlg = new JPanel();
		String panel_bookCtlg_name = "Book Catalogue";									// Panel Name
		tabbedPane.addTab(panel_bookCtlg_name, null, panel_bookCtlg, null);				// Add to Tabs
		panel_bookCtlg.setLayout(new BoxLayout(panel_bookCtlg, BoxLayout.Y_AXIS));		// Sets Panel Layout		
		/* Book Catalogue, Inner Panel 1 Start */
		/* Inner Panel Object */
		JPanel panel_bookCtlg_inner1 = new JPanel();
		/* Label */
		JLabel txt_bookCtlg_inner1 = new JLabel();
		/* Refresh Button */
		JButton btn_bookCtlg_inner1_refreshPg = new JButton("Refresh");
		/* Scroll Pane */
		JScrollPane scrollPane_bookCtlg = new JScrollPane();
		/* Table Start */
		String table_bookCtlg_tableName = "book_ctlg";
		// The Table Object
		DefaultTableModel tableModel_bookCtlg = new DefaultTableModel();
		JTable table_bookCtlg = new JTable();
		/* Table End */
		// Runs Function to Design and Render it
		Make_tablePanel(frame, panel_bookCtlg, panel_bookCtlg_inner1, txt_bookCtlg_inner1, btn_bookCtlg_inner1_refreshPg, panel_bookCtlg_name, table_bookCtlg_tableName, scrollPane_bookCtlg, tableModel_bookCtlg, table_bookCtlg);	
		// Change Table Height to Accommodate Branch UI
		scrollPane_bookCtlg.setBounds(20, 50, 1130, 450);
		//.setBounds(20, 50, 1130, 190);
		/* Book Catalogue, Inner Panel 1 End */
		
		// Panel Book Stock 
		// Panel
		JPanel panel_bookStock = new JPanel();
		String panel_bookStock_name = "Book Stock";											// Panel Name
		tabbedPane.addTab(panel_bookStock_name, null, panel_bookStock, null);				// Add to Tabs
		panel_bookStock.setLayout(new BoxLayout(panel_bookStock, BoxLayout.Y_AXIS));		// Sets Panel Layout
		/* Book Stock, Inner Panel 1 Start */
		/* Inner Panel Object */
		JPanel panel_bookStock_inner1 = new JPanel();
		/* Label */
		JLabel txt_bookStock_inner1 = new JLabel();
		/* Refresh Button */
		JButton btn_bookStock_inner1_refreshPg = new JButton("Refresh");
		/* Scroll Pane */
		JScrollPane scrollPane_bookStock = new JScrollPane();
		/* Table Start */
		String table_bookStock_tableName = "book_stock";									// Table Name
		// The Table Object
		DefaultTableModel tableModel_bookStock = new DefaultTableModel();
		JTable table_bookStock = new JTable();
		/* Table End */
		// Runs Function to Design and Render it
		Make_tablePanel_Branch(frame, panel_bookStock, panel_bookStock_inner1, txt_bookStock_inner1, btn_bookStock_inner1_refreshPg, panel_bookStock_name, table_bookStock_tableName, scrollPane_bookStock, tableModel_bookStock, table_bookStock, branchStore_number);
		/* Book Stock, Inner Panel 1 End */
		/* Book Stock, Inner Panel 2 Start */
		JPanel panel_bookStock_inner2 = new JPanel();
		panel_bookStock_inner2.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_bookStock.add(panel_bookStock_inner2);
		panel_bookStock_inner2.setLayout(null);
		/* Labels and Text Fields */
		// Headers
		JLabel txt_bookStock_inner2 = new JLabel("Change/Remove Book Stock");									// Change/Remove Book Stock Header
		txt_bookStock_inner2.setBounds(20, 20, 200, 25);
		panel_bookStock_inner2.add(txt_bookStock_inner2);
		JLabel txt_bookStock_inner2_addRemToHQ = new JLabel("Add Books from Book Catalogue to Table");	// Add Books from Book Catalogue to Stock of HQ Header
		txt_bookStock_inner2_addRemToHQ.setBounds(400, 20, 300, 25);
		panel_bookStock_inner2.add(txt_bookStock_inner2_addRemToHQ);
		// ISBN Label
		JLabel txt_bookStock_inner2_ISBN = new JLabel("ISBN");
		txt_bookStock_inner2_ISBN.setBounds(20, 50, 80, 20);
		panel_bookStock_inner2.add(txt_bookStock_inner2_ISBN);
		// ISBN Drop Menu
		String[] arrayStr_bookStock_bookStockISBN = selectQuery("SELECT DISTINCT ISBN FROM book_stock WHERE store_number = '" + branchStore_number + "'");
		DefaultComboBoxModel drpMenuModel_bookStock_inner2_bookStockISBN = new DefaultComboBoxModel(arrayStr_bookStock_bookStockISBN);
		JComboBox drpMenu_bookStock_inner2_bookStockISBN = new JComboBox(drpMenuModel_bookStock_inner2_bookStockISBN);
		drpMenu_bookStock_inner2_bookStockISBN.setBounds(110, 50, 200, 20);
		panel_bookStock_inner2.add(drpMenu_bookStock_inner2_bookStockISBN);
		/*
		// Store Number Label
		JLabel txt_bookStock_inner2_txtFieldStoreNum = new JLabel("Store Number");
		txt_bookStock_inner2_txtFieldStoreNum.setBounds(20, 80, 80, 20);
		panel_bookStock_inner2.add(txt_bookStock_inner2_txtFieldStoreNum);
		// Store Number Drop Menu
		String[] arrayStr_bookStock_bookStockStoreNum = selectQuery("SELECT DISTINCT store_number FROM book_stock");
		DefaultComboBoxModel drpMenuModel_bookStock_inner2_bookStockStoreNum = new DefaultComboBoxModel(arrayStr_bookStock_bookStockStoreNum);
		JComboBox drpMenu_bookStock_inner2_bookStockStoreNum = new JComboBox(drpMenuModel_bookStock_inner2_bookStockStoreNum);
		drpMenu_bookStock_inner2_bookStockStoreNum.setBounds(110, 80, 200, 20);
		panel_bookStock_inner2.add(drpMenu_bookStock_inner2_bookStockStoreNum);
		*/
		// Stock Label
		JLabel txt_bookStock_inner2_textFieldStock = new JLabel("Stock");
		txt_bookStock_inner2_textFieldStock.setBounds(20, 80, 80, 20);
		panel_bookStock_inner2.add(txt_bookStock_inner2_textFieldStock);
		// Stock Text Field
		JTextField txtEnt_bookStock_inner2_stock = new JTextField();
		txtEnt_bookStock_inner2_stock.setColumns(36);
		txtEnt_bookStock_inner2_stock.setBounds(110, 80, 200, 20);
		panel_bookStock_inner2.add(txtEnt_bookStock_inner2_stock);
		// ISBN from Book Catalogue Label
		JLabel txt_bookStock_inner2_addToTable = new JLabel("Book Ctlg, ISBN");
		txt_bookStock_inner2_addToTable.setBounds(400, 50, 100, 20);
		panel_bookStock_inner2.add(txt_bookStock_inner2_addToTable);
		// ISBN from Book Catalogue Drop Menu
		String[] arrayStr_bookStock_bookCtlgISBN = selectQuery("SELECT ISBN FROM book_ctlg");
		DefaultComboBoxModel drpMenuModel_bookStock_inner2_bookCtlgISBN = new DefaultComboBoxModel(arrayStr_bookStock_bookCtlgISBN);
		JComboBox drpMenu_bookStock_inner2_bookCtlgISBN = new JComboBox(drpMenuModel_bookStock_inner2_bookCtlgISBN);
		drpMenu_bookStock_inner2_bookCtlgISBN.setBounds(500, 50, 200, 20);
		panel_bookStock_inner2.add(drpMenu_bookStock_inner2_bookCtlgISBN);
		/*
		// Store Number from Offices Label
		JLabel txt_bookStock_inner2_remFromTable = new JLabel("Offices, Store #");
		txt_bookStock_inner2_remFromTable.setBounds(400, 80, 100, 20);
		panel_bookStock_inner2.add(txt_bookStock_inner2_remFromTable);
		// Store Number from Offices Drop Menu
		String[] arrayStr_bookStock_officesStoreNum = selectQuery("SELECT store_number FROM offices");
		DefaultComboBoxModel drpMenuModel_bookStock_inner2_officesStoreNum = new DefaultComboBoxModel(arrayStr_bookStock_officesStoreNum);
		JComboBox drpMenu_bookStock_inner2_officesStoreNum = new JComboBox(drpMenuModel_bookStock_inner2_officesStoreNum);
		drpMenu_bookStock_inner2_officesStoreNum.setBounds(500, 80, 200, 20);
		panel_bookStock_inner2.add(drpMenu_bookStock_inner2_officesStoreNum);
		*/
		/* Buttons */
		// Error/Success in Inputs
		JLabel txt_bookStock_inner2_changeRemErrTxt = new JLabel();				// Label Change/Remove Stock Success/Error
		txt_bookStock_inner2_changeRemErrTxt.setForeground(Color.RED);
		txt_bookStock_inner2_changeRemErrTxt.setBounds(20, 190, 300, 20);
		panel_bookStock_inner2.add(txt_bookStock_inner2_changeRemErrTxt);
		JLabel txt_bookStock_inner2_addErrTxt = new JLabel();				// Label Add Book from Stock Table Success/Error
		txt_bookStock_inner2_addErrTxt.setForeground(Color.RED);
		txt_bookStock_inner2_addErrTxt.setBounds(400, 190, 300, 20);
		panel_bookStock_inner2.add(txt_bookStock_inner2_addErrTxt);
		// Buttons and Actions
		// Change Stock Button
		JButton btn_bookStock_inner2_change = new JButton("Change Stock");		
		btn_bookStock_inner2_change.setBounds(20, 160, 120, 25);
		panel_bookStock_inner2.add(btn_bookStock_inner2_change);
		// Remove from Table Button
		JButton btn_bookStock_inner2_remFromTable = new JButton("Remove");				
		btn_bookStock_inner2_remFromTable.setBounds(160, 160, 100, 25);
		panel_bookStock_inner2.add(btn_bookStock_inner2_remFromTable);
		// Add to Table Button
		JButton btn_bookStock_inner2_addToTable = new JButton("Add");					
		btn_bookStock_inner2_addToTable.setBounds(400, 160, 80, 25);
		panel_bookStock_inner2.add(btn_bookStock_inner2_addToTable);
		/* Book Stock, Inner Panel 2 End */
		
		
		
		// Panel Employees
		// Panel
		JPanel panel_employees = new JPanel();
		String panel_employees_name = "Employees";											// Panel Name
		tabbedPane.addTab(panel_employees_name, null, panel_employees, null);				// Add to Tabs
		panel_employees.setLayout(new BoxLayout(panel_employees, BoxLayout.Y_AXIS));		// Sets Panel Layout
		/* Employees, Inner Panel 1 Start */
		/* Inner Panel Object */
		JPanel panel_employees_inner1 = new JPanel();
		/* Label */
		JLabel txt_employees_inner1 = new JLabel();
		/* Refresh Button */
		JButton btn_employees_inner1_refreshPg = new JButton("Refresh");
		/* Scroll Pane */
		JScrollPane scrollPane_employees = new JScrollPane();
		/* Table Start */
		String table_employees_tableName = "employees";										// Table Name
		// The Table Object
		DefaultTableModel tableModel_employees = new DefaultTableModel();
		JTable table_employees = new JTable();
		/* Table End */
		// Runs Function to Design and Render it
		Make_tablePanel_Branch(frame, panel_employees, panel_employees_inner1, txt_employees_inner1, btn_employees_inner1_refreshPg, panel_employees_name, table_employees_tableName, scrollPane_employees, tableModel_employees, table_employees, branchStore_number);
		/* Employees, Inner Panel 1 End */
		/* Employees, Inner Panel 2 Start */
		JPanel panel_employees_inner2 = new JPanel();
		panel_employees_inner2.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_employees.add(panel_employees_inner2);
		panel_employees_inner2.setLayout(null);
		/* Labels and Text Fields */
		// Headers
		JLabel txt_employess_inner2_changeOrRemoveEmpl = new JLabel("Change Name and or Password, or Remove Employee");		// Change Name and or Password, or Remove Employee
		txt_employess_inner2_changeOrRemoveEmpl.setBounds(20, 20, 350, 25);
		panel_employees_inner2.add(txt_employess_inner2_changeOrRemoveEmpl);
		JLabel txt_employees_inner2_addNewEmpl = new JLabel("Add New Employee");											// Add New Employee
		txt_employees_inner2_addNewEmpl.setBounds(400, 20, 250, 25);
		panel_employees_inner2.add(txt_employees_inner2_addNewEmpl);
		// ID Label
		JLabel txt_employees_inner2_ID = new JLabel("ID");
		txt_employees_inner2_ID.setBounds(20, 50, 80, 20);
		panel_employees_inner2.add(txt_employees_inner2_ID);
		// ID Drop Down Menu
		String[] arrayStr_employees_emplIDs = selectQuery("SELECT ID FROM employees WHERE store_number = '" + branchStore_number + "'");
		DefaultComboBoxModel drpMenuModel_employees_inner2_ID = new DefaultComboBoxModel(arrayStr_employees_emplIDs);
		JComboBox drpMenu_employees_inner2_ID = new JComboBox(drpMenuModel_employees_inner2_ID);
		drpMenu_employees_inner2_ID.setBounds(100, 50, 60, 20);
		panel_employees_inner2.add(drpMenu_employees_inner2_ID);
		// Name Label
		JLabel txt_employees_inner2_txtFieldName = new JLabel("Name");
		txt_employees_inner2_txtFieldName.setBounds(20, 80, 80, 20);
		panel_employees_inner2.add(txt_employees_inner2_txtFieldName);
		// Name Text Field
		JTextField txtEnt_employees_inner2_name = new JTextField();
		txtEnt_employees_inner2_name.setColumns(36);
		txtEnt_employees_inner2_name.setBounds(100, 80, 460, 20);
		panel_employees_inner2.add(txtEnt_employees_inner2_name);
		// Password Label
		JLabel txt_employees_inner2_txtFieldPassword = new JLabel("Password");
		txt_employees_inner2_txtFieldPassword.setBounds(20, 110, 80, 20);
		panel_employees_inner2.add(txt_employees_inner2_txtFieldPassword);
		// Password Text Field
		JTextField txtEnt_employees_inner2_password = new JTextField();
		txtEnt_employees_inner2_password.setColumns(36);
		txtEnt_employees_inner2_password.setBounds(100, 110, 460, 20);
		panel_employees_inner2.add(txtEnt_employees_inner2_password);
		/*
		// Store Number from Offices Label
		JLabel txt_employees_inner2_officesStoreNum = new JLabel("Offices, Store #");
		txt_employees_inner2_officesStoreNum.setBounds(400, 50, 100, 20);
		panel_employees_inner2.add(txt_employees_inner2_officesStoreNum);
		// Store Number from Offices Drop Down Menu
		String[] arrayStr_employees_officesStoreNum = selectQuery("SELECT store_number FROM offices");
		DefaultComboBoxModel drpMenuModel_employees_inner2_officesStoreNum = new DefaultComboBoxModel(arrayStr_employees_officesStoreNum);
		JComboBox drpMenu_employees_inner2_officesStoreNum = new JComboBox(drpMenuModel_employees_inner2_officesStoreNum);
		drpMenu_employees_inner2_officesStoreNum.setBounds(500, 50, 60, 20);
		panel_employees_inner2.add(drpMenu_employees_inner2_officesStoreNum);
		*/
		/* Buttons */
		// Error/Success in Inputs
		JLabel txt_employees_inner2_changeOrRemoveEmplErrTxt = new JLabel();			// Label Change/Remove Success/Error
		txt_employees_inner2_changeOrRemoveEmplErrTxt.setForeground(Color.RED);
		txt_employees_inner2_changeOrRemoveEmplErrTxt.setBounds(20, 190, 300, 20);
		panel_employees_inner2.add(txt_employees_inner2_changeOrRemoveEmplErrTxt);
		JLabel txt_employees_inner2_addNewEmplErrTxt = new JLabel();					// Label Add Employee Success/Error
		txt_employees_inner2_addNewEmplErrTxt.setForeground(Color.RED);
		txt_employees_inner2_addNewEmplErrTxt.setBounds(400, 190, 300, 20);
		panel_employees_inner2.add(txt_employees_inner2_addNewEmplErrTxt);
		// Buttons and Actions
		// Change Name and or Password in Employee Table
		JButton btn_employees_inner2_change = new JButton("Change");					
		btn_employees_inner2_change.setBounds(20, 160, 90, 25);
		panel_employees_inner2.add(btn_employees_inner2_change);
		// Remove Employee Data in Employee Table
		JButton btn_employees_inner2_remove = new JButton("Remove");					
		btn_employees_inner2_remove.setBounds(130, 160, 90, 25);
		panel_employees_inner2.add(btn_employees_inner2_remove);
		// Add Employee Data to Employee Table
		JButton btn_employees_inner2_add = new JButton("Add");					
		btn_employees_inner2_add.setBounds(400, 160, 80, 25);
		panel_employees_inner2.add(btn_employees_inner2_add);
		/* Employees, Inner Panel 2 End */
		
		// Panel Offices
		// Panel
		JPanel panel_offices = new JPanel();
		String panel_offices_name = "Offices";												// Panel Name
		tabbedPane.addTab(panel_offices_name, null, panel_offices, null);					// Add to Tabs
		panel_offices.setLayout(new BoxLayout(panel_offices, BoxLayout.Y_AXIS));			// Sets Panel Layout
		/* Offices, Inner Panel 1 Start */
		/* Inner Panel Object */
		JPanel panel_offices_inner1 = new JPanel();
		/* Label */
		JLabel txt_offices_inner1 = new JLabel();
		/* Refresh Button */
		JButton btn_offices_inner1_refreshPg = new JButton("Refresh");
		/* Scroll Pane */
		JScrollPane scrollPane_offices = new JScrollPane();
		/* Table Start */
		String table_offices_tableName = "offices";											// Table Name
		// The Table Object
		DefaultTableModel tableModel_offices = new DefaultTableModel();
		JTable table_offices = new JTable();
		/* Table End */
		// Runs Function to Design and Render it
		Make_tablePanel(frame, panel_offices, panel_offices_inner1, txt_offices_inner1, btn_offices_inner1_refreshPg, panel_offices_name, table_offices_tableName, scrollPane_offices, tableModel_offices, table_offices);
		// Change Table Height to Accommodate Branch UI
		scrollPane_offices.setBounds(20, 50, 1130, 450);
		/* Offices, Inner Panel 1 End */
		
		
		
		
		
		
		// Display Window
		frame.pack();											// Combine Window Elements, and Resize Them if Window Too Small
		frame.setVisible(true);									// Renders Window
	}
	

	// Check Table Composite Entry
	public static boolean checkTable_compEntry(DefaultTableModel table, String entry_one, String entry_two, int col_one, int col_two) {
		for(int i=0; i<table.getRowCount(); i++) {
			if(table.getValueAt(i, col_one).equals(entry_one) && table.getValueAt(i, col_two).equals(entry_two)) {
				return true;
			}
		}
		
		return false;
	}
	
	// Check Table Entry Exist
	public static int checkTable_Entry(DefaultTableModel table, String entry, int col) {
		for(int i=0; i<table.getRowCount(); i++) {
			if(table.getValueAt(i, col).equals(entry)) {
				System.out.println(i);
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
	
	// Get Table Columns Names
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
	
	// Get Table Row Data Specific to a Branch
	public static String[][] getTable_RowData_Branch(String tableName, int storeNumber) {
		try {
			// Column Names of the Table
			List<List<String>> tableRows = RMI_Server.selectQuery_allRows_Branch(tableName, storeNumber);
			String[][] rowNames = tableRows.stream().map(l -> l.stream().toArray(String[]::new)).toArray(String[][]::new);
			
			return rowNames;
			
		} catch(Exception error) {
			System.out.println("getTable_RowData Error: " + error);
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
	
	// Updates Bar Chart
	public static void updatesBarChart(String tableName, String column, String distCol, DefaultCategoryDataset dataSet) {
		try {
			// Clears Dataset
			dataSet.clear();

			// Gets New Data Values from Table
			String[] distinctColumn = selectQuery("SELECT DISTINCT " + distCol + " FROM " + tableName);
			String[] dataValues;
			Double totalAmount = 0.0;
			
			// Adds Data Values to Dataset Container
			for(int i=0; i<distinctColumn.length; i++) {
				dataValues = selectQuery("SELECT " + column + " FROM " + tableName + " WHERE " + distCol + " = '" + distinctColumn[i] + "'");
				for(String j : dataValues) {
					totalAmount += Double.parseDouble(j);
				}
				dataSet.setValue(totalAmount, "Total Amount", "Store " + distinctColumn[i]);
				totalAmount = 0.0;
			}
		} catch(Exception error) {
			System.out.println(error);
		}
	}
	
	// Update Drop Down Menu
	public static void updateDropMenu(String tableName, String column, DefaultComboBoxModel dropMenuModel, Boolean distinctValues) {
		String[] table_rowData = (distinctValues) ? selectQuery("SELECT DISTINCT " + column + " FROM " + tableName) : selectQuery("SELECT " + column + " FROM " + tableName);
		
		dropMenuModel.removeAllElements();
		
		for(String i : table_rowData) {
			dropMenuModel.addElement(i);;
		}
	}
	
	// Updates Table and Runs SQL
	public static void updateTable_SQL(String sql, String tableName, DefaultTableModel table) {
		try {
			// Runs Query Change/Affect Table
			RMI_Server.updateQuery(sql);
			
			// Gets New Data from Database
			String[][] table_rowData = getTable_RowData(tableName);
			
			// Reset Table Value
			table.setRowCount(0);
			// Inserts New Data from Database
			for(int i=0; i<table_rowData.length; i++) {
				table.addRow(table_rowData[i]);
			}
			
		} catch(Exception error) {
			System.out.println(error);
		}
	}
	
	// Updates Table
	public static void updateTable(String tableName, DefaultTableModel table) {
		try {
			// Gets New Data from Database
			String[][] table_rowData = getTable_RowData(tableName);
			
			// Reset Table Value
			table.setRowCount(0);
			// Inserts New Data from Database
			for(int i=0; i<table_rowData.length; i++) {
				table.addRow(table_rowData[i]);
			}
		} catch(Exception error) {
			System.out.println(error);
		}
	}
	
	//selectQuery_allRows_Branch(String table, int storeNumber)
	// Create Table Panel Function, specific to Branch Office
	public static void Make_tablePanel_Branch(JFrame frame, JPanel panel, JPanel panel_inner, JLabel label, JButton button, String panelName, String tableName, JScrollPane scrollPane, DefaultTableModel tableModel, JTable table, int storeNumber) {
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
			
			// Refresh Button
			button.setBounds(120, 20, 90, 25);
			panel_inner.add(button);
			
			// Scroll Pane
			scrollPane.setBounds(20, 50, 1130, 190);
			panel_inner.add(scrollPane);
			
			// Table Column Names & Row Data
			String[] table_colNames = getTable_ColNames(tableName);
			String[][] table_rowData = getTable_RowData_Branch(tableName, storeNumber);
			// The Table
			//tableModel = new DefaultTableModel(table_rowData, table_colNames);
			for(int i=0; i<table_colNames.length; i++) {
				tableModel.addColumn(table_colNames[i]);
			}
			for(int i=0; i<table_rowData.length; i++) {
				tableModel.addRow(table_rowData[i]);
			}
			
			table.setModel(tableModel);
			table.setEnabled(false);
			scrollPane.setViewportView(table);
		} catch(Exception error) {
			System.out.println("Make_tablePanel_Branch Error: " + error);
		}
	}
	
	// Create Table Panel Function
	public static void Make_tablePanel(JFrame frame, JPanel panel, JPanel panel_inner, JLabel label, JButton button, String panelName, String tableName, JScrollPane scrollPane, DefaultTableModel tableModel, JTable table) {
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
			
			// Refresh Button
			button.setBounds(120, 20, 90, 25);
			panel_inner.add(button);
			
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
			
			table.setModel(tableModel);
			table.setEnabled(false);
			scrollPane.setViewportView(table);
		} catch(Exception error) {
			System.out.println(error);
		}
	}
	
	// Runs a SQL Query and Returns a List Array
	public static List<String> selectQuery_RetList(String sql) {
		try {
			List<String> queryResult_list = RMI_Server.selectQuery(sql);
			
			return queryResult_list;
			
		} catch(Exception error) {
			System.out.println(error);
			return null;
		}
	}
		
	// Runs a SQL Query and Returns a String Array
	public static String[] selectQuery(String sql) {
		try {
			List<String> queryResult_list = RMI_Server.selectQuery(sql);
			String[] queryResult = queryResult_list.toArray(new String[0]);;
			
			return queryResult;
			
		} catch(Exception error) {
			System.out.println(error);
			return null;
		}
	}
}
