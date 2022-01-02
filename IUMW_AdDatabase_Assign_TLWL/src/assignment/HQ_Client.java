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
import java.util.Collection;
import java.util.List;

import javax.swing.BorderFactory;
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
import javax.swing.JViewport;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

public class HQ_Client {
	// Connect to RMI Server
	static Interface_Remote RMI_Server;
	
	// Store Number
	int hqStore_number = 0;
	
	public static void main(String[] args) throws Exception {
		RMI_Server = (Interface_Remote) Naming.lookup("//localhost/UptownBooks_Server");
		
		// Testing
		System.out.println(RMI_Server.getMessage());
		
		LoginPage();
	}
	
	
	// The Login Page
	public static void LoginPage() {
		// Create JFrame Object
		//frame = new JFrame();									// The JFrame Object
		JFrame frame = new JFrame();							// The JFrame Object
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	// When Window closed, program exited
		frame.setTitle("Uptown Books Database HQ");				// Window Title
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
					List<String> DB_Usernames = RMI_Server.selectQuery("SELECT ID FROM employees WHERE store_number = '0' ORDER BY ID");
					//System.out.println(DB_Usernames);
					List<String> DB_Passwords = RMI_Server.selectQuery("SELECT password FROM employees WHERE store_number = '0' ORDER BY ID");
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
		frame.setTitle("Uptown Books Database HQ");				// Window Title
		frame.setMinimumSize(new Dimension(1200, 600));			// Minimum Window Size

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
		/* Book Catalogue, Inner Panel 2 End */
		
		
		
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
		Make_tablePanel(frame, panel_bookStock, panel_bookStock_inner1, txt_bookStock_inner1, btn_bookStock_inner1_refreshPg, panel_bookStock_name, table_bookStock_tableName, scrollPane_bookStock, tableModel_bookStock, table_bookStock);
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
		String[] arrayStr_bookStock_bookStockISBN = selectQuery("SELECT DISTINCT ISBN FROM book_stock");
		DefaultComboBoxModel drpMenuModel_bookStock_inner2_bookStockISBN = new DefaultComboBoxModel(arrayStr_bookStock_bookStockISBN);
		JComboBox drpMenu_bookStock_inner2_bookStockISBN = new JComboBox(drpMenuModel_bookStock_inner2_bookStockISBN);
		drpMenu_bookStock_inner2_bookStockISBN.setBounds(110, 50, 200, 20);
		panel_bookStock_inner2.add(drpMenu_bookStock_inner2_bookStockISBN);
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
		// Stock Label
		JLabel txt_bookStock_inner2_textFieldStock = new JLabel("Stock");
		txt_bookStock_inner2_textFieldStock.setBounds(20, 110, 80, 20);
		panel_bookStock_inner2.add(txt_bookStock_inner2_textFieldStock);
		// Stock Text Field
		JTextField txtEnt_bookStock_inner2_stock = new JTextField();
		txtEnt_bookStock_inner2_stock.setColumns(36);
		txtEnt_bookStock_inner2_stock.setBounds(110, 110, 200, 20);
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
		Make_tablePanel(frame, panel_employees, panel_employees_inner1, txt_employees_inner1, btn_employees_inner1_refreshPg, panel_employees_name, table_employees_tableName, scrollPane_employees, tableModel_employees, table_employees);
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
		String[] arrayStr_employees_emplIDs = selectQuery("SELECT ID FROM employees");
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
		/* Offices, Inner Panel 1 End */
		/* Offices, Inner Panel 2 Start */
		JPanel panel_offices_inner2 = new JPanel();
		panel_offices_inner2.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_offices.add(panel_offices_inner2);
		panel_offices_inner2.setLayout(null);
		/* Labels and Text Fields */
		// Headers
		JLabel txt_offices_inner2_headAdd = new JLabel("Add New Store to Table");		// Add New Stores to the Table Offices
		txt_offices_inner2_headAdd.setBounds(20, 20, 300, 25);
		panel_offices_inner2.add(txt_offices_inner2_headAdd);
		// City Label
		JLabel txt_offices_inner2_city = new JLabel("City");
		txt_offices_inner2_city.setBounds(20, 50, 80, 20);
		panel_offices_inner2.add(txt_offices_inner2_city);
		// City Text Fields
		JTextField txtEnt_offices_inner2_city = new JTextField();
		txtEnt_offices_inner2_city.setColumns(36);
		txtEnt_offices_inner2_city.setBounds(100, 50, 200, 20);
		panel_offices_inner2.add(txtEnt_offices_inner2_city);
		// State Label
		JLabel txt_offices_inner2_state = new JLabel("State");
		txt_offices_inner2_state.setBounds(20, 80, 80, 20);
		panel_offices_inner2.add(txt_offices_inner2_state);
		// State Text Fields
		JTextField txtEnt_offices_inner2_state = new JTextField();
		txtEnt_offices_inner2_state.setColumns(36);
		txtEnt_offices_inner2_state.setBounds(100, 80, 200, 20);
		panel_offices_inner2.add(txtEnt_offices_inner2_state);
		// Postcode Label
		JLabel txt_offices_inner2_postcode = new JLabel("Postcode");
		txt_offices_inner2_postcode.setBounds(20, 110, 80, 20);
		panel_offices_inner2.add(txt_offices_inner2_postcode);
		// Postcode Text Fields
		JTextField txtEnt_offices_inner2_postcode = new JTextField();
		txtEnt_offices_inner2_postcode.setColumns(36);
		txtEnt_offices_inner2_postcode.setBounds(100, 110, 200, 20);
		panel_offices_inner2.add(txtEnt_offices_inner2_postcode);
		/* Buttons */
		// Error/Success in Inputs
		JLabel txt_offices_inner2_headAddErrTxt = new JLabel("");			// Label Add Success/Error
		txt_offices_inner2_headAddErrTxt.setForeground(Color.RED);
		txt_offices_inner2_headAddErrTxt.setBounds(20, 190, 300, 20);
		panel_offices_inner2.add(txt_offices_inner2_headAddErrTxt);
		// Buttons and Actions
		// Add New Office to Offices Table
		JButton btn_offices_inner2_add = new JButton("Add");				// Add New Office to Offices Table
		btn_offices_inner2_add.setBounds(20, 160, 80, 25);
		panel_offices_inner2.add(btn_offices_inner2_add);
		/* Offices, Inner Panel 2 End */
		
		
		
		// Sales Panels
		// Panels
		JPanel panel_sales = new JPanel();
		String panel_sales_name = "Sales";													// Panel Name
		tabbedPane.addTab(panel_sales_name, null, panel_sales, null);						// Add to Tabs
		panel_sales.setLayout(new BoxLayout(panel_sales, BoxLayout.Y_AXIS));				// Sets Panel Layout
		/* Sales, Inner Panel 1 Start */
		/* Inner Panel Object */
		JPanel panel_sales_inner1 = new JPanel();
		/* Label */
		JLabel txt_sales_inner1 = new JLabel();
		/* Refresh Button */
		JButton btn_sales_inner1_refreshPg = new JButton("Refresh");
		/* Scroll Pane */
		JScrollPane scrollPane_sales = new JScrollPane();
		/* Table Start */
		String table_sales_tableName = "sales";												// Table Name
		// The Table Object
		DefaultTableModel tableModel_sales = new  DefaultTableModel();
		JTable table_sales = new JTable();
		/* Table End */
		// Runs Function to Design and Render it
		Make_tablePanel(frame, panel_sales, panel_sales_inner1, txt_sales_inner1, btn_sales_inner1_refreshPg, panel_sales_name, table_sales_tableName, scrollPane_sales, tableModel_sales, table_sales);
		/* Sales, Inner Panel 1 End */
		/* Sales, Inner Panel 2 Start */
		JPanel panel_sales_inner2 = new JPanel();
		panel_sales_inner2.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_sales.add(panel_sales_inner2);
		panel_sales_inner2.setLayout(null);
		/* Labels and Text Fields */
		// Headers
		JLabel txt_sales_inner2_headBarChart = new JLabel("Bar Chart Profits to Store");	// Bar Chart of The Profits Compared to Each Store
		txt_sales_inner2_headBarChart.setBounds(20, 20, 300, 25);
		panel_sales_inner2.add(txt_sales_inner2_headBarChart);
		/* Bar Chart */
		// The Bar Chart Data Set Container
		DefaultCategoryDataset dataSet_sales_inner2 = new DefaultCategoryDataset();
		// Bar Chart Values
		String[] distCols_sales_StoreNum = selectQuery("SELECT DISTINCT store_number FROM sales");
		String[] dataValues_sales_StoreNum;
		Double totalAmount_sales = 0.0;
		// Sets the Dataset Container with the Total Amount (Total Profits) Made From Each Store in Sales
		for(int i=0; i<distCols_sales_StoreNum.length; i++) {
			dataValues_sales_StoreNum = selectQuery("SELECT Amount FROM sales WHERE Store_Number = '" + distCols_sales_StoreNum[i] + "'");
			for(String j : dataValues_sales_StoreNum) {
				totalAmount_sales += Double.parseDouble(j);
			}
			dataSet_sales_inner2.setValue(totalAmount_sales, "Total Amount", "Store " + distCols_sales_StoreNum[i]);
			totalAmount_sales = 0.0;
		}
		// The Bar Chart Object
		JFreeChart chart_sales_inner2 = ChartFactory.createBarChart("Profits from Stores", "Stores", "Profits", dataSet_sales_inner2, PlotOrientation.VERTICAL, false, true, false);
		chart_sales_inner2.getCategoryPlot().setRangeGridlinePaint(Color.BLACK);		// Set Gridlines of Graph to Black Colour
		// The Panel for the Chart, i.e. Contains the Chart Window in it
		ChartPanel panelChart_sales_inner2 = new ChartPanel(chart_sales_inner2);
		panelChart_sales_inner2.setBounds(20, 50, 283*distCols_sales_StoreNum.length, 210);
		panel_sales_inner2.add(panelChart_sales_inner2);
		/* Sales, Inner Panel 2 End */
		
		
		
		/* Button Actions, Start */
		// Book Catalogue
		// Book Catalogue, Add New Book to Catalogue
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
							String sql = "INSERT INTO book_ctlg (ISBN, Name, Author) VALUES ('" + ISBN + "', '" + Name + "', '" + Author + "')";
							
							// Runs Insert Query into Table Book_Ctlg
							updateTable_SQL(sql, table_bookCtlg_tableName, tableModel_bookCtlg);
							// Updates Drop Menu
							updateDropMenu(table_bookCtlg_tableName, "ISBN", drpMenuModel_bookStock_inner2_bookCtlgISBN, false);
							
							// Clears Text Fields
							txtEnt_bookCtlg_inner2_ISBN.setText("");
							txtEnt_bookCtlg_inner2_name.setText("");
							txtEnt_bookCtlg_inner2_author.setText("");
							
							txt_bookCtlg_inner2_addErrTxt.setText("New Input Successful");
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
		// Book Stock
		// Book Stock, Change the Stock of the Books
		btn_bookStock_inner2_change.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					long ISBN = Long.parseLong((String) drpMenuModel_bookStock_inner2_bookStockISBN.getSelectedItem());
					int storeNumber = Integer.parseInt((String) drpMenuModel_bookStock_inner2_bookStockStoreNum.getSelectedItem());
					int stock = Integer.parseInt(txtEnt_bookStock_inner2_stock.getText());
					
					if(String.valueOf(ISBN).equals("") || String.valueOf(storeNumber).equals("") || String.valueOf(stock).equals("")) {
						txt_bookStock_inner2_changeRemErrTxt.setText("Invalid Inputs in Text Fields");
					} else {
						if(String.valueOf(ISBN).chars().count() == 13 && checkTable_compEntry(tableModel_bookStock, String.valueOf(ISBN), String.valueOf(storeNumber), 0, 1)) {
							//txt_bookStock_inner2_changeErrTxt.setText("Inputs Valid");
							String sql = "UPDATE " + table_bookStock_tableName + " SET stock = " + stock + " WHERE (ISBN = " + ISBN + " AND store_number = " + storeNumber + ")";
							
							// Runs UPDATE Query and Updates Book Stock Table
							updateTable_SQL(sql, table_bookStock_tableName, tableModel_bookStock);
							
							// Clear Text Fields
							txtEnt_bookStock_inner2_stock.setText("");
							
							// Testing
							System.out.println(sql);
							
							txt_bookStock_inner2_changeRemErrTxt.setText("Change Successful");
						} else {
							txt_bookStock_inner2_changeRemErrTxt.setText("Invalid Inputs in Text Fields");
						}
						System.out.println("Btn Pressed");
					}
					
				} catch(Exception error) {
					txt_bookStock_inner2_changeRemErrTxt.setText("Invalid Inputs in Text Fields");
					System.out.println(error);
				}
			}
		});
		// Book Stock, Remove a Row from Table
		btn_bookStock_inner2_remFromTable.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					//long ISBN = Long.parseLong(txtEnt_bookStock_inner2_ISBN.getText());
					long ISBN = Long.parseLong((String) drpMenuModel_bookStock_inner2_bookStockISBN.getSelectedItem());
					int storeNumber = Integer.parseInt((String) drpMenuModel_bookStock_inner2_bookStockStoreNum.getSelectedItem());
					
					if(String.valueOf(ISBN).equals("") || String.valueOf(storeNumber).equals("") || !(checkTable_compEntry(tableModel_bookStock, String.valueOf(ISBN), String.valueOf(storeNumber), 0, 1))) {
						txt_bookStock_inner2_changeRemErrTxt.setText("Remove Unsuccessful, Invalid Input in ISBN");
					} else {
						String sql = "DELETE FROM " + table_bookStock_tableName + " WHERE ISBN = " + ISBN + " AND store_number = " + storeNumber;
						
						// Runs DELETE Query and Updates Table
						updateTable_SQL(sql, table_bookStock_tableName, tableModel_bookStock);
						// Updates Drop Down Menus
						updateDropMenu(table_bookStock_tableName, "ISBN", drpMenuModel_bookStock_inner2_bookStockISBN, true);
						updateDropMenu(table_bookStock_tableName, "store_number", drpMenuModel_bookStock_inner2_bookStockStoreNum, true);
						
						// Clear Text Fields
						txtEnt_bookStock_inner2_stock.setText("");
						
						// Testing
						System.out.println(sql);
						System.out.println("ISBN Pos: " + checkTable_Entry(tableModel_bookStock, String.valueOf(ISBN), 0));
						
						txt_bookStock_inner2_changeRemErrTxt.setText("Remove Successful");
					}
					
				} catch(Exception error) {
					txt_bookStock_inner2_changeRemErrTxt.setText("Remove Unsuccessful, Invalid Input in ISBN");
					System.out.println(error);
				}
			}
		});
		// Book Stock, Add a New Row to Table
		btn_bookStock_inner2_addToTable.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					//long ISBN = Long.parseLong(txtEnt_bookStock_inner2_ISBN.getText());
					long ISBN = Long.parseLong((String) drpMenuModel_bookStock_inner2_bookCtlgISBN.getSelectedItem());
					int storeNumber = Integer.parseInt((String) drpMenuModel_bookStock_inner2_officesStoreNum.getSelectedItem());
					
					if(String.valueOf(ISBN).equals("") || String.valueOf(storeNumber).equals("") || checkTable_compEntry(tableModel_bookStock, String.valueOf(ISBN), String.valueOf(storeNumber), 0, 1)) {	
						txt_bookStock_inner2_addErrTxt.setText("Add Unsuccessful, Invalid Input in ISBN");
					} else {
						String sql = "INSERT INTO " + table_bookStock_tableName + " (ISBN, Store_Number, Stock, Price) VALUES (" + ISBN + ", " + storeNumber + ", 0, 0)";
						
						// Runs INSERT Query
						updateTable_SQL(sql, table_bookStock_tableName, tableModel_bookStock);
						// Updates Drop Down Menus
						updateDropMenu(table_bookStock_tableName, "ISBN", drpMenuModel_bookStock_inner2_bookStockISBN, true);
						updateDropMenu(table_bookStock_tableName, "store_number", drpMenuModel_bookStock_inner2_bookStockStoreNum, true);
						
						// Clear Text Fields
						txtEnt_bookStock_inner2_stock.setText("");
						
						// Testing
						System.out.println(sql);
						
						txt_bookStock_inner2_addErrTxt.setText("Add Successful");
					}
				} catch(Exception error) {
					txt_bookStock_inner2_addErrTxt.setText("Add Unsuccessful, Invalid Input in ISBN");
					System.out.println(error);
				}
			}
		});
		// Employees
		// Employees, Change Name and or Password in Employee Table
		btn_employees_inner2_change.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					int ID = Integer.parseInt((String) drpMenuModel_employees_inner2_ID.getSelectedItem());
					String Name = txtEnt_employees_inner2_name.getText();
					String Password = txtEnt_employees_inner2_password.getText();
					
					if(String.valueOf(ID).equals("") || (Name.equals("") && Password.equals(""))) {
						txt_employees_inner2_changeOrRemoveEmplErrTxt.setText("Change Unsuccessful, Invalid Inputs in Text Fields");
					} else {
						String sql = "";
						Boolean flag = false;
						if(!(Name.contentEquals("")) && !(Password.equals(""))) {
							sql = "UPDATE " + table_employees_tableName + " SET name = '" + Name + "', password = '" + Password + "' WHERE ID = " + ID;
							flag = true;
						} else if(!(Name.contentEquals(""))) {
							sql = "UPDATE " + table_employees_tableName + " SET name = '" + Name + "' WHERE ID = " + ID;
							flag = true;
						} else if(!(Password.equals(""))) {
							sql = "UPDATE " + table_employees_tableName + " SET password = '" + Password + "' WHERE ID = " + ID;
							flag = true;
						}
						
						if(flag) {
							updateTable_SQL(sql, table_employees_tableName, tableModel_employees);
						} else {
							txt_employees_inner2_changeOrRemoveEmplErrTxt.setText("Change Unsuccessful, Invalid Inputs in Text Fields");
						}
						
						// Clear Text Fields
						txtEnt_employees_inner2_name.setText("");
						txtEnt_employees_inner2_password.setText("");
						
						txt_employees_inner2_changeOrRemoveEmplErrTxt.setText("Change Successful");
					}
				} catch(Exception error) {
					txt_employees_inner2_changeOrRemoveEmplErrTxt.setText("Change Unsuccessful, Invalid Inputs in Text Fields");
					System.out.println(error);
				}
			}
		});
		// Employees, Remove Employee Data/Row in Employee Table
		btn_employees_inner2_remove.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					int ID = Integer.parseInt((String) drpMenuModel_employees_inner2_ID.getSelectedItem());
					
					if(String.valueOf(ID).equals("") || checkTable_Entry(tableModel_employees, String.valueOf(ID), 0) < 0) {
						txt_employees_inner2_changeOrRemoveEmplErrTxt.setText("Remove Unsuccessful, Invalid Selection in ID Drop Menu");
					} else {
						String sql = "DELETE FROM " + table_employees_tableName + " WHERE ID = " + ID;
						
						// Runs DELETE Query
						updateTable_SQL(sql, table_employees_tableName, tableModel_employees);
						// Updates Drop Down Menu
						updateDropMenu(table_employees_tableName, "ID", drpMenuModel_employees_inner2_ID, false);
						
						txt_employees_inner2_changeOrRemoveEmplErrTxt.setText("Remove Successful");
					}
					
				} catch(Exception error) {
					txt_employees_inner2_changeOrRemoveEmplErrTxt.setText("Remove Unsuccessful, Invalid Selection in ID Drop Menu");
					System.out.println(error);
				}
			}
		});
		// Employees, Add Employee to Table
		btn_employees_inner2_add.addActionListener(new ActionListener() {
			public void  actionPerformed(ActionEvent e) {
				try {
					int storeNumber = Integer.parseInt((String) drpMenuModel_employees_inner2_officesStoreNum.getSelectedItem());
					String Name = txtEnt_employees_inner2_name.getText();
					String Password = txtEnt_employees_inner2_password.getText();
					
					if(Name.equals("") || Password.contentEquals("")) {
						txt_employees_inner2_addNewEmplErrTxt.setText("Add Unsuccesfull, Invalid Inputs in Fields");
					} else {
						String sql = "INSERT INTO " + table_employees_tableName + "(store_number, name, password) VALUES (0, '" + Name + "', '" + Password + "')";
						
						// Runs INSERT Query
						updateTable_SQL(sql, table_employees_tableName, tableModel_employees);
						// Updates Drop Down Menu
						updateDropMenu(table_employees_tableName, "ID", drpMenuModel_employees_inner2_ID, false);
						
						// Clear Text Fields
						txtEnt_employees_inner2_name.setText("");
						txtEnt_employees_inner2_password.setText("");
						
						txt_employees_inner2_addNewEmplErrTxt.setText("Add Succesfull");
					}
				} catch(Exception error) {
					txt_employees_inner2_addNewEmplErrTxt.setText("Add Unsuccesfull, Invalid Inputs in Fields");
					System.out.println(error);
				}
			}
		});
		// Offices
		// Offices, Add Office to Table
		btn_offices_inner2_add.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String City = txtEnt_offices_inner2_city.getText();
					String State = txtEnt_offices_inner2_state.getText();
					String Postcode = txtEnt_offices_inner2_postcode.getText();
					
					if(City.equals("") || State.equals("") || Postcode.equals("") || !(Postcode.chars().count() == 5) || checkTable_Entry(tableModel_offices, Postcode, 3) >= 0) {
						txt_offices_inner2_headAddErrTxt.setText("Add Unsuccessful, Invalid Inputs in Text Fields");
					} else {
						String sql = "INSERT INTO " + table_offices_tableName + "(City, State, Postcode) VALUES ('" + City + "', '" + State + "', '" + Postcode + "')";
						
						// Runs INSERT Query
						updateTable_SQL(sql, table_offices_tableName, tableModel_offices);
						// Updates Drop Down Menu
						updateDropMenu(table_offices_tableName, "store_number", drpMenuModel_bookStock_inner2_officesStoreNum, false);
						updateDropMenu(table_offices_tableName, "store_number", drpMenuModel_employees_inner2_officesStoreNum, false);
						
						// Clears Text Fields
						txtEnt_offices_inner2_city.setText("");
						txtEnt_offices_inner2_state.setText("");
						txtEnt_offices_inner2_postcode.setText("");
						
						txt_offices_inner2_headAddErrTxt.setText("Add Successful");
					}
				} catch(Exception error) {
					txt_offices_inner2_headAddErrTxt.setText("Add Unsuccessful, Invalid Inputs in Text Fields");
					System.out.println(error);
				}
			}
		});
		/* Button Actions, End */
		
		
		
		/* Refresh Buttons, Start */
		// Book Catalogue
		btn_bookCtlg_inner1_refreshPg.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updateTable(table_bookCtlg_tableName, tableModel_bookCtlg);
				updateDropMenu(table_bookCtlg_tableName, "ISBN", drpMenuModel_bookStock_inner2_bookCtlgISBN, false);
			}
		});
		// Book Stock
		btn_bookStock_inner1_refreshPg.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updateTable(table_bookStock_tableName, tableModel_bookStock);
				updateDropMenu(table_bookStock_tableName, "ISBN", drpMenuModel_bookStock_inner2_bookStockISBN, true);
				updateDropMenu(table_bookStock_tableName, "store_number", drpMenuModel_bookStock_inner2_bookStockStoreNum, true);
				updateDropMenu(table_bookCtlg_tableName, "ISBN", drpMenuModel_bookStock_inner2_bookCtlgISBN, false);
				updateDropMenu(table_offices_tableName, "store_number", drpMenuModel_bookStock_inner2_officesStoreNum, false);
			}
		});
		// Employees
		btn_employees_inner1_refreshPg.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updateTable(table_employees_tableName, tableModel_employees);
				updateDropMenu(table_employees_tableName, "ID", drpMenuModel_employees_inner2_ID, false);
				updateDropMenu(table_offices_tableName, "store_number", drpMenuModel_employees_inner2_officesStoreNum, false);
			}
		});
		// Offices
		btn_offices_inner1_refreshPg.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updateTable(table_offices_tableName, tableModel_offices);
				updateDropMenu(table_offices_tableName, "store_number", drpMenuModel_bookStock_inner2_officesStoreNum, false);
				updateDropMenu(table_offices_tableName, "store_number", drpMenuModel_employees_inner2_officesStoreNum, false);
			}
		});
		// Sales
		btn_sales_inner1_refreshPg.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updateTable(table_sales_tableName, tableModel_sales);
				updatesBarChart(table_sales_tableName, "Amount", "store_number", dataSet_sales_inner2);
			}
		});
		/* Refresh Buttons, End */
		
		
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
