package test;

public class OldCode_Container {
	// Old Code
	//	/* Book Catalogue, Inner Panel 1 Start */
	//	JPanel panel_bookCtlg_inner1 = new JPanel();
	//	panel_bookCtlg_inner1.setBorder(new LineBorder(new Color(0, 0, 0)));
	//	panel_bookCtlg.add(panel_bookCtlg_inner1);
	//	panel_bookCtlg_inner1.setLayout(null);
	//	/* Label */
	//	JLabel txt_bookCtlg_inner1 = new JLabel("Book Catalogue");
	//	//txt_bookCtlg.setHorizontalAlignment(SwingConstants.CENTER);
	//	txt_bookCtlg_inner1.setLabelFor(frame);
	//	txt_bookCtlg_inner1.setBounds(10, 20, 100, 25);
	//	panel_bookCtlg_inner1.add(txt_bookCtlg_inner1);
	//	/* Table Start */
	//	//int table_bookCtlg_rowCount = getTable_RowCount("book_ctlg");
	//	String[] table_bookCtlg_colNames = getTable_ColNames("book_ctlg");
	//	String[][] table_bookCtlg_rowData = getTable_RowData("book_ctlg");
	//	// Scroll Pane
	//	JScrollPane scrollPane_bookCtlg = new JScrollPane();
	//	scrollPane_bookCtlg.setBounds(20, 50, 1130, 190);
	//	panel_bookCtlg_inner1.add(scrollPane_bookCtlg);
	//	// The Table Object
	//	DefaultTableModel tableModel_bookCtlg = new DefaultTableModel(table_bookCtlg_rowData, table_bookCtlg_colNames);
	//	scrollPane_bookCtlg.setViewportView(new JTable(tableModel_bookCtlg));
	//	/* Table End */
	
//	// Store Number Text Field
//	JTextField txtEnt_bookStock_inner2_storeNum = new JTextField();
//	txtEnt_bookStock_inner2_storeNum.setColumns(36);
//	txtEnt_bookStock_inner2_storeNum.setBounds(110, 80, 200, 20);
//	panel_bookStock_inner2.add(txtEnt_bookStock_inner2_storeNum);
	

//	String[] arrayStr_employees_emplIDs = selectQuery("SELECT ID FROM employees");
//	DefaultComboBoxModel drpMenuModel_employees_inner2_ID = new DefaultComboBoxModel(arrayStr_employees_emplIDs);
//	JComboBox drpMenu_employees_inner2_ID = new JComboBox(drpMenuModel_employees_inner2_ID);
//	drpMenu_employees_inner2_ID.setBounds(100, 50, 60, 20);
//	panel_employees_inner2.add(drpMenu_employees_inner2_ID);
	
	// ISBN Text Field
//	JTextField txtEnt_bookStock_inner2_ISBN = new JTextField();
//	txtEnt_bookStock_inner2_ISBN.setColumns(13);
//	txtEnt_bookStock_inner2_ISBN.setBounds(110, 50, 200, 20);
//	panel_bookStock_inner2.add(txtEnt_bookStock_inner2_ISBN);
	
	/*
	// ID Text Field
	JTextField txtEnt_employees_inner2_ID = new JTextField();
	txtEnt_employees_inner2_ID.setColumns(4);
	txtEnt_employees_inner2_ID.setBounds(400, 50, 150, 20);
	panel_employees_inner2.add(txtEnt_employees_inner2_ID);
	*/
	

	
//	/* Bar Chart */
//	// The Bar Chart Data Set Container
//	DefaultCategoryDataset dataSet_sales_inner2 = new DefaultCategoryDataset();
//	// Bar Chart Values
//	String[] distCols_sales_StoreNum = selectQuery("SELECT DISTINCT store_number FROM sales");
//	String[] dataValues;
//	Double totalAmount_sales = 0.0;
//	// Sets the Dataset Container with the Total Amount (Total Profits) Made From Each Store in Sales
//	for(int i=0; i<distCols_sales_StoreNum.length; i++) {
//		dataValues = selectQuery("SELECT Amount FROM sales WHERE Store_Number = '" + distCols_sales_StoreNum[i] + "'");
//		for(String j : dataValues) {
//			totalAmount_sales += Double.parseDouble(j);
//		}
//		dataSet_sales_inner2.setValue(totalAmount_sales, "Total Amount", "Store " + distCols_sales_StoreNum[i]);
//		totalAmount_sales = 0.0;
//	}
//	// The Bar Chart Object
//	JFreeChart chart_sales_inner2 = ChartFactory.createBarChart("Profits from Stores", "Stores", "Profits", dataSet_sales_inner2, PlotOrientation.VERTICAL, false, true, false);
//	chart_sales_inner2.getCategoryPlot().setRangeGridlinePaint(Color.BLACK);		// Set Gridlines of Graph to Black Colour
	
//	// Number of Rows of the Table
//	List<String> tableRowNums = RMI_Server.selectQuery("SELECT count(*) FROM " + tableName);
//	int rowNums = Integer.parseInt(tableRowNums.get(0));
//	// Column Names of the Table
//	List<String> tableCols = RMI_Server.selectQuery("SELECT COLUMN_NAME FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_SCHEMA = 'uptown_books' AND TABLE_NAME = '" + tableName + "'");
//	// Row Values of the Table
//	List<List<String>> tableRows = RMI_Server.selectQuery_allRows(tableName);
//	
//	//System.out.print(tableCols);
//	//System.out.print(tableRows);
//	
//	// Convert List to Array
//	String[] columns = tableCols.toArray(new String[0]);
//	String[][] rows = tableRows.stream().map(l -> l.stream().toArray(String[]::new)).toArray(String[][]::new);
//	
//	// The JTable Object
//	JTable table = new JTable(rows, columns);
//	//table.setBounds(20, 50, 1000, 30+(30*rowNums));
//	//table.getTableHeader().setBounds(20, 50, 1200, 15);
//	table.setBounds(20, 60, 1200, 15*rowNums);
//	//panel.add(table.getTableHeader());
//	panel.add(table);
//	/*
//	JScrollPane scrollPane = new JScrollPane(table);
//	table.setFillsViewportHeight(true);
//	scrollPane.setSize(1200, 30+(30*rowNums));
//	panel.add(scrollPane);
//	//panel.add(table.getTableHeader(), BorderLayout.PAGE_START);		// Add to JPanel
//	//panel.add(table, BorderLayout.CENTER);
//	*/
}
