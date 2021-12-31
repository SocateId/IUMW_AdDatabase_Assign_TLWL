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

public class GUI_WinBuildTest {

	private JFrame frmUptownBooksDatabase;
	private JTable table;

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
		panel_1.add(panel_1_innerPanel_1);
		panel_1_innerPanel_1.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Table 1");
		lblNewLabel.setBounds(20, 20, 100, 25);
		panel_1_innerPanel_1.add(lblNewLabel);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(20, 50, 1130, 90);
		panel_1_innerPanel_1.add(scrollPane);
		
		String[] cols = {"ISBN", "Name", "Author"};
		String[][] rows = {{"9780134601533", "Database Concepts", "David Kroenke"}, {"9780199734825", "Biology of Spiders", "Rainer Foelix"}, {"9780764507526", "Database Development For Dummies", "Allen G. Taylor"}, {"9781119056072", "Coding with JavaScript For Dummies", "Chris Minnick"}, {"9781292263359", "Modern Database Management", "Global Edition", "Jeff Hoffer"}};
		
		table = new JTable(rows, cols);
		scrollPane.setViewportView(table);
		
		JPanel panel_1_innerPanel_2 = new JPanel();
		panel_1.add(panel_1_innerPanel_2);
		
		JPanel panel_2 = new JPanel();
		tabbedPane.addTab("Tab 2", null, panel_2, null);
	}
}
