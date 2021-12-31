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

public class GUI_WinBuildTest {

	private JFrame frmUptownBooksDatabase;
	private JTable table1;
	private JTextField textField_table1_ISBN;
	private JTextField textField_table1_Name;
	private JTextField textField_table1_Author;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTable table2;

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
				{null, null, null, null},
			},
			new String[] {
				"ISBN", "Store_Number", "Stock", "Price"
			}
		));
		scrollPane_table2.setViewportView(table2);
		
		JPanel panel2_inner2 = new JPanel();
		panel_2.add(panel2_inner2);
		panel2_inner2.setLayout(null);
		
		JLabel txt_panel2_inner2 = new JLabel("Increase/Decrease Book Stock");
		txt_panel2_inner2.setBounds(70, 9, 146, 14);
		panel2_inner2.add(txt_panel2_inner2);
		
		JLabel txt_table1_ISBN_1 = new JLabel("ISBN");
		txt_table1_ISBN_1.setBounds(221, 9, 23, 14);
		panel2_inner2.add(txt_table1_ISBN_1);
		
		textField = new JTextField();
		textField.setBounds(249, 6, 110, 20);
		textField.setColumns(13);
		panel2_inner2.add(textField);
		
		JLabel txt_table1_Name_1 = new JLabel("Name");
		txt_table1_Name_1.setBounds(364, 9, 27, 14);
		panel2_inner2.add(txt_table1_Name_1);
		
		textField_1 = new JTextField();
		textField_1.setBounds(396, 6, 294, 20);
		textField_1.setColumns(36);
		panel2_inner2.add(textField_1);
		
		JLabel txt_table1_Author_1 = new JLabel("Author");
		txt_table1_Author_1.setBounds(695, 9, 33, 14);
		panel2_inner2.add(txt_table1_Author_1);
		
		textField_2 = new JTextField();
		textField_2.setBounds(733, 6, 294, 20);
		textField_2.setColumns(36);
		panel2_inner2.add(textField_2);
		
		JButton btnNewButton_1 = new JButton("Add Book");
		btnNewButton_1.setBounds(1032, 5, 77, 23);
		panel2_inner2.add(btnNewButton_1);
		
		JLabel lblNewLabel_1 = new JLabel("Invalid Inputs in Text Fields");
		lblNewLabel_1.setBounds(523, 33, 132, 14);
		lblNewLabel_1.setForeground(Color.RED);
		panel2_inner2.add(lblNewLabel_1);
	}
}