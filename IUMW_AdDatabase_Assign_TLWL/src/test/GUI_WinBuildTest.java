package test;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.CardLayout;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JToolBar;
import javax.swing.Box;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JTabbedPane;
import javax.swing.JLabel;
import javax.swing.JTable;
import java.awt.Color;
import javax.swing.border.LineBorder;
import java.awt.FlowLayout;
import javax.swing.BoxLayout;
import java.awt.Component;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.border.BevelBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class GUI_WinBuildTest {

	private JFrame frmUptownBooksDatabase;
	private JTable table1;
	private JTextField textField_table1_ISBN;
	private JTextField textField_table1_Name;
	private JTextField textField_table1_Author;
	private JTextField textField_table2_ISBN;
	private JTextField textField_table2_storeNum;
	private JTextField textField_table2_stock;
	private JTable table2;
	private JTable table3;
	private JTextField textField_table3_inner2_name;
	private JTextField textField_table3_inner2_pwd;
	private JComboBox comboBox_table4_inner2_state;
	private JTextField textField_table4_inner2_postcode;
	private JTable table4;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI_WinBuildTest window = new GUI_WinBuildTest();
					window.frmUptownBooksDatabase.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public GUI_WinBuildTest() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmUptownBooksDatabase = new JFrame();
		frmUptownBooksDatabase.setTitle("Uptown Books Database");
		frmUptownBooksDatabase.setBounds(100, 100, 1200, 600);
		frmUptownBooksDatabase.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		frmUptownBooksDatabase.getContentPane().add(tabbedPane, BorderLayout.CENTER);
		
		JPanel panel_1 = new JPanel();
		panel_1.setToolTipText("");
		tabbedPane.addTab("Tab 1", null, panel_1, null);
		panel_1.setLayout(new BoxLayout(panel_1, BoxLayout.Y_AXIS));
		
		JPanel panel_1_innerPanel_1 = new JPanel();
		panel_1_innerPanel_1.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_1.add(panel_1_innerPanel_1);
		panel_1_innerPanel_1.setLayout(null);
		
		JLabel txt_innerPanel1 = new JLabel("Table 1");
		txt_innerPanel1.setBounds(20, 20, 100, 25);
		panel_1_innerPanel_1.add(txt_innerPanel1);
		
		JScrollPane scrollPane_table1 = new JScrollPane();
		scrollPane_table1.setBounds(20, 50, 1130, 190);
		panel_1_innerPanel_1.add(scrollPane_table1);
		
		String[] cols = {"ISBN", "Name", "Author"};
		String[][] rows = {{"9780134601533", "Database Concepts", "David Kroenke"}, {"9780199734825", "Biology of Spiders", "Rainer Foelix"}, {"9780764507526", "Database Development For Dummies", "Allen G. Taylor"}, {"9781119056072", "Coding with JavaScript For Dummies", "Chris Minnick"}, {"9781292263359", "Modern Database Management", "Global Edition", "Jeff Hoffer"}};
		
		table1 = new JTable(rows, cols);
		table1.setEnabled(false);
		table1.setBackground(Color.WHITE);
		scrollPane_table1.setViewportView(table1);
		
		JPanel panel_1_innerPanel_2 = new JPanel();
		panel_1_innerPanel_2.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_1.add(panel_1_innerPanel_2);
		panel_1_innerPanel_2.setLayout(null);
		
		JLabel txt_innerPanel2 = new JLabel("Add New Book");
		txt_innerPanel2.setBounds(20, 20, 100, 25);
		panel_1_innerPanel_2.add(txt_innerPanel2);
		
		JLabel txt_table1_ISBN = new JLabel("ISBN");
		txt_table1_ISBN.setBounds(20, 50, 80, 20);
		panel_1_innerPanel_2.add(txt_table1_ISBN);
		
		textField_table1_ISBN = new JTextField();
		textField_table1_ISBN.setBounds(100, 50, 200, 20);
		panel_1_innerPanel_2.add(textField_table1_ISBN);
		textField_table1_ISBN.setColumns(13);
		
		JLabel txt_table1_Name = new JLabel("Name");
		txt_table1_Name.setBounds(20, 80, 80, 20);
		panel_1_innerPanel_2.add(txt_table1_Name);
		
		textField_table1_Name = new JTextField();
		textField_table1_Name.setColumns(36);
		textField_table1_Name.setBounds(100, 80, 200, 20);
		panel_1_innerPanel_2.add(textField_table1_Name);
		
		JLabel txt_table1_Author = new JLabel("Author");
		txt_table1_Author.setBounds(20, 110, 80, 20);
		panel_1_innerPanel_2.add(txt_table1_Author);
		
		textField_table1_Author = new JTextField();
		textField_table1_Author.setColumns(36);
		textField_table1_Author.setBounds(100, 110, 200, 20);
		panel_1_innerPanel_2.add(textField_table1_Author);
		
		JButton btnNewButton = new JButton("Add Book");
		btnNewButton.setBounds(20, 160, 90, 25);
		panel_1_innerPanel_2.add(btnNewButton);
		
		JLabel lblNewLabel = new JLabel("Invalid Inputs in Text Fields");
		lblNewLabel.setForeground(Color.RED);
		lblNewLabel.setBounds(20, 190, 200, 20);
		panel_1_innerPanel_2.add(lblNewLabel);
		
		JPanel panel_2 = new JPanel();
		tabbedPane.addTab("Tab 2", null, panel_2, null);
		panel_2.setLayout(new BoxLayout(panel_2, BoxLayout.Y_AXIS));
		
		JPanel panel2_inner1 = new JPanel();
		panel2_inner1.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_2.add(panel2_inner1);
		panel2_inner1.setLayout(null);
		
		JLabel txt_panel2_inner1 = new JLabel("Table 2");
		txt_panel2_inner1.setBounds(20, 20, 100, 25);
		panel2_inner1.add(txt_panel2_inner1);
		
		JScrollPane scrollPane_table2 = new JScrollPane();
		scrollPane_table2.setBounds(20, 50, 1130, 190);
		panel2_inner1.add(scrollPane_table2);
		
		table2 = new JTable();
		table2.setModel(new DefaultTableModel(
			new Object[][] {
				{"9780134601533", "0", "50", "0.0"},
				{"9780199734825", "0", "50", "0.0"},
				{"9780764507526", "0", "50", "0.0"},
				{"9781119056072", "0", "50", "0.0"},
				{"9781292263359", "0", "50", "0.0"},
				{"9780134601533", "1", "15", "43.99"},
				{"9780764507526", "1", "0", "40.99"},
				{"9781292263359", "1", "2", "73.99"},
			},
			new String[] {
				"ISBN", "Store_Number", "Stock", "Price"
			}
		));
		table2.getColumnModel().getColumn(0).setPreferredWidth(112);
		table2.getColumnModel().getColumn(1).setPreferredWidth(166);
		table2.getColumnModel().getColumn(2).setPreferredWidth(144);
		table2.getColumnModel().getColumn(3).setPreferredWidth(145);
		scrollPane_table2.setViewportView(table2);
		
		JPanel panel2_inner2 = new JPanel();
		panel2_inner2.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_2.add(panel2_inner2);
		panel2_inner2.setLayout(null);
		
		JLabel txt_panel2_inner2 = new JLabel("Change Book Stock");
		txt_panel2_inner2.setBounds(20, 20, 200, 25);
		panel2_inner2.add(txt_panel2_inner2);
		
		JLabel txt_table2_ISBN = new JLabel("ISBN");
		txt_table2_ISBN.setBounds(20, 50, 80, 20);
		panel2_inner2.add(txt_table2_ISBN);
		
		textField_table2_ISBN = new JTextField();
		textField_table2_ISBN.setBounds(110, 50, 200, 20);
		textField_table2_ISBN.setColumns(13);
		panel2_inner2.add(textField_table2_ISBN);
		
		JLabel txt_table2_storeNum = new JLabel("Store Number");
		txt_table2_storeNum.setBounds(20, 80, 80, 20);
		panel2_inner2.add(txt_table2_storeNum);
		
		textField_table2_storeNum = new JTextField();
		textField_table2_storeNum.setBounds(110, 80, 200, 20);
		textField_table2_storeNum.setColumns(36);
		panel2_inner2.add(textField_table2_storeNum);
		
		JLabel txt_table2_stock = new JLabel("Stock");
		txt_table2_stock.setBounds(20, 110, 80, 20);
		panel2_inner2.add(txt_table2_stock);
		
		textField_table2_stock = new JTextField();
		textField_table2_stock.setBounds(110, 110, 200, 20);
		textField_table2_stock.setColumns(36);
		panel2_inner2.add(textField_table2_stock);
		
		JButton btn_table2_changeStock = new JButton("Change Stock");
		btn_table2_changeStock.setBounds(20, 160, 100, 25);
		panel2_inner2.add(btn_table2_changeStock);
		
		JLabel lblNewLabel_1 = new JLabel("Invalid Inputs in Text Fields");
		lblNewLabel_1.setBounds(20, 190, 200, 20);
		lblNewLabel_1.setForeground(Color.RED);
		panel2_inner2.add(lblNewLabel_1);
		
		JLabel txt_panel2_inner2_addRemToHQ = new JLabel("Add/Remove data to/from Table of HQ");
		txt_panel2_inner2_addRemToHQ.setBounds(400, 20, 250, 25);
		panel2_inner2.add(txt_panel2_inner2_addRemToHQ);
		
		JLabel txt_panel2_inner2_dropDownISBN = new JLabel("Add, Input ISBN from Table Book Catalogue");
		txt_panel2_inner2_dropDownISBN.setBounds(400, 50, 250, 20);
		panel2_inner2.add(txt_panel2_inner2_dropDownISBN);
		
		JButton btn_table2_addBookStock = new JButton("Add");
		btn_table2_addBookStock.setBounds(400, 160, 80, 25);
		panel2_inner2.add(btn_table2_addBookStock);
		
		JButton btn_table2_remBookStock = new JButton("Remove");
		btn_table2_remBookStock.setBounds(500, 160, 80, 25);
		panel2_inner2.add(btn_table2_remBookStock);
		
		JLabel txt_table2_drpDownErrSucc = new JLabel("Add Succesfull");
		txt_table2_drpDownErrSucc.setForeground(Color.RED);
		txt_table2_drpDownErrSucc.setBounds(400, 190, 300, 20);
		panel2_inner2.add(txt_table2_drpDownErrSucc);
		
		JLabel txt_panel2_inner2_dropDownISBN_1 = new JLabel("Remove, Input ISBN from Table Book Stock");
		txt_panel2_inner2_dropDownISBN_1.setBounds(400, 80, 250, 20);
		panel2_inner2.add(txt_panel2_inner2_dropDownISBN_1);
		
		JPanel panel_3 = new JPanel();
		tabbedPane.addTab("Tab 3", null, panel_3, null);
		panel_3.setLayout(new BoxLayout(panel_3, BoxLayout.Y_AXIS));
		
		JPanel panel3_inner1 = new JPanel();
		panel3_inner1.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_3.add(panel3_inner1);
		panel3_inner1.setLayout(null);
		
		JLabel txt_table3 = new JLabel("Table 3");
		txt_table3.setBounds(20, 20, 100, 25);
		panel3_inner1.add(txt_table3);
		
		JScrollPane scrollPane_table3 = new JScrollPane();
		scrollPane_table3.setBounds(20, 50, 1130, 190);
		panel3_inner1.add(scrollPane_table3);
		
		table3 = new JTable();
		table3.setModel(new DefaultTableModel(
			new Object[][] {
				{"1000", "0", "Timothy", "123"},
				{"1001", "1", "Jeff", "1234"},
				{"1002", "1", "Bob", "12345"},
			},
			new String[] {
				"ID", "Store_Number", "Name", "Password"
			}
		));
		table3.getColumnModel().getColumn(1).setPreferredWidth(136);
		table3.getColumnModel().getColumn(2).setPreferredWidth(144);
		table3.setEnabled(false);
		scrollPane_table3.setViewportView(table3);
		
		JPanel panel3_inner2 = new JPanel();
		panel3_inner2.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel3_inner2.setLayout(null);
		panel_3.add(panel3_inner2);
		
		JLabel txt_table3_inner2_chgNamePass = new JLabel("Change Name and or Password, or Remove Employee");
		txt_table3_inner2_chgNamePass.setBounds(20, 20, 350, 25);
		panel3_inner2.add(txt_table3_inner2_chgNamePass);
		
		JLabel txt_table3_inner2_IDDrpDown = new JLabel("ID");
		txt_table3_inner2_IDDrpDown.setBounds(20, 50, 80, 20);
		panel3_inner2.add(txt_table3_inner2_IDDrpDown);
		
		JLabel txt_table3_inner2_name = new JLabel("Name");
		txt_table3_inner2_name.setBounds(20, 80, 80, 20);
		panel3_inner2.add(txt_table3_inner2_name);
		
		JLabel txt_table3_inner2_password = new JLabel("Password");
		txt_table3_inner2_password.setBounds(20, 110, 80, 20);
		panel3_inner2.add(txt_table3_inner2_password);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"1000", "1001", "1002"}));
		comboBox.setBounds(100, 50, 60, 20);
		panel3_inner2.add(comboBox);
		
		textField_table3_inner2_name = new JTextField();
		textField_table3_inner2_name.setColumns(36);
		textField_table3_inner2_name.setBounds(100, 80, 450, 20);
		panel3_inner2.add(textField_table3_inner2_name);
		
		textField_table3_inner2_pwd = new JTextField();
		textField_table3_inner2_pwd.setColumns(36);
		textField_table3_inner2_pwd.setBounds(100, 110, 450, 20);
		panel3_inner2.add(textField_table3_inner2_pwd);
		
		JButton btn_table3_inner2_changeNmPwd = new JButton("Change");
		btn_table3_inner2_changeNmPwd.setBounds(20, 160, 90, 25);
		panel3_inner2.add(btn_table3_inner2_changeNmPwd);
		
		JLabel txt_table3_inner2_addEmply = new JLabel("Add New Employee to HQ");
		txt_table3_inner2_addEmply.setBounds(400, 20, 250, 25);
		panel3_inner2.add(txt_table3_inner2_addEmply);
		
		JButton btn_table2_addBookStock_1 = new JButton("Add");
		btn_table2_addBookStock_1.setBounds(400, 160, 80, 25);
		panel3_inner2.add(btn_table2_addBookStock_1);
		
		JButton btn_table2_remBookStock_1 = new JButton("Remove");
		btn_table2_remBookStock_1.setBounds(130, 160, 90, 25);
		panel3_inner2.add(btn_table2_remBookStock_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Invalid Inputs in Text Fields");
		lblNewLabel_1_1.setForeground(Color.RED);
		lblNewLabel_1_1.setBounds(20, 190, 300, 20);
		panel3_inner2.add(lblNewLabel_1_1);
		
		JLabel txt_table2_drpDownErrSucc_1 = new JLabel("Add Unsuccesfull, Invalid Inputs in Fields");
		txt_table2_drpDownErrSucc_1.setForeground(Color.RED);
		txt_table2_drpDownErrSucc_1.setBounds(400, 190, 300, 20);
		panel3_inner2.add(txt_table2_drpDownErrSucc_1);
		
		JPanel panel_4 = new JPanel();
		tabbedPane.addTab("Tab 4", null, panel_4, null);
		panel_4.setLayout(new BoxLayout(panel_4, BoxLayout.Y_AXIS));
		
		JPanel panel4_inner1 = new JPanel();
		panel4_inner1.setLayout(null);
		panel4_inner1.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_4.add(panel4_inner1);
		
		JLabel txt_table4 = new JLabel("Table 4");
		txt_table4.setBounds(20, 20, 100, 25);
		panel4_inner1.add(txt_table4);
		
		JScrollPane scrollPane_table4 = new JScrollPane();
		scrollPane_table4.setBounds(20, 50, 1130, 190);
		panel4_inner1.add(scrollPane_table4);
		
		table4 = new JTable();
		table4.setModel(new DefaultTableModel(
			new Object[][] {
				{"0", "Kuala Lumpur", "Selangor", "43200"},
				{"1", "Subang Jaya", "Selangor", "40150"},
			},
			new String[] {
				"Store_Number", "City", "State", "Postcode"
			}
		));
		table4.getColumnModel().getColumn(0).setPreferredWidth(104);
		table4.getColumnModel().getColumn(1).setPreferredWidth(104);
		table4.getColumnModel().getColumn(2).setPreferredWidth(100);
		scrollPane_table4.setViewportView(table4);
		
		JPanel pane4_inner2 = new JPanel();
		pane4_inner2.setLayout(null);
		pane4_inner2.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_4.add(pane4_inner2);
		
		JLabel txt_table4_inner2_headAdd = new JLabel("Add New Store");
		txt_table4_inner2_headAdd.setBounds(20, 20, 350, 25);
		pane4_inner2.add(txt_table4_inner2_headAdd);
		
		JLabel txt_table4_inner2_city = new JLabel("City");
		txt_table4_inner2_city.setBounds(20, 50, 80, 20);
		pane4_inner2.add(txt_table4_inner2_city);
		
		JLabel txt_table4_inner2_state = new JLabel("State");
		txt_table4_inner2_state.setBounds(20, 80, 80, 20);
		pane4_inner2.add(txt_table4_inner2_state);
		
		JLabel txt_table4_inner2_postcode = new JLabel("Postcode");
		txt_table4_inner2_postcode.setBounds(20, 110, 80, 20);
		pane4_inner2.add(txt_table4_inner2_postcode);
		
		JComboBox comboBox_table4_inner2_city = new JComboBox();
		comboBox_table4_inner2_city.setModel(new DefaultComboBoxModel(new String[] {"Iskandar Puteri", "Johor Bahru"}));
		comboBox_table4_inner2_city.setBounds(100, 50, 150, 20);
		pane4_inner2.add(comboBox_table4_inner2_city);
		
		comboBox_table4_inner2_state = new JComboBox();
		comboBox_table4_inner2_state.setModel(new DefaultComboBoxModel(new String[] {"Johor", "Penang", "Perak", "Sabah", "Sarawak", "Selangor"}));
		comboBox_table4_inner2_state.setBounds(100, 80, 150, 20);
		pane4_inner2.add(comboBox_table4_inner2_state);
		
		textField_table4_inner2_postcode = new JTextField();
		textField_table4_inner2_postcode.setColumns(5);
		textField_table4_inner2_postcode.setBounds(100, 110, 150, 20);
		pane4_inner2.add(textField_table4_inner2_postcode);
		
		JButton btn_table4_inner2_addStore = new JButton("Add");
		btn_table4_inner2_addStore.setBounds(20, 160, 80, 25);
		pane4_inner2.add(btn_table4_inner2_addStore);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("Add Unsuccessful, Invalid Inputs in Text Fields");
		lblNewLabel_1_1_1.setForeground(Color.RED);
		lblNewLabel_1_1_1.setBounds(20, 190, 300, 20);
		pane4_inner2.add(lblNewLabel_1_1_1);
	}
}
