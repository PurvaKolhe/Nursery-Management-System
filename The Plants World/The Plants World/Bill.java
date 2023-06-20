import java.awt.BorderLayout;


import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JSpinner;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JTextArea;

public class Bill extends JFrame {

	private JPanel contentPane;
	private JTextField textId;
	private JComboBox textName,textCust;
	private JTextField textCost;
	private JTextField textPay;
	private JTextField textBal;
	DefaultTableModel model;
	private JTable table1;
	private JTextField textPrice;
	private JTextField textqty;
	private JTextArea textarea;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				Bill bill = new Bill();
				bill.setVisible(true);
			}
		});
	}
	
	Connection con;
	PreparedStatement pst,pst1;
	ResultSet rs;
	DefaultTableModel df;
	public void Connect() 
	{			
			try {
					Class.forName("com.mysql.jdbc.Driver");
					con = DriverManager.getConnection("jdbc:mysql://localhost/nms","root","");
			}  catch (ClassNotFoundException ex) {
				System.out.println(ex);
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}		
	}
	
//	public void billrep() 
//	{
//		String total = textCost.getText();
//		String pay = textPay.getText();
//		String bal = textBal.getText();
//		
//		DefaultTableModel df = new DefaultTableModel();
//		df = (DefaultTableModel)table1.getModel();
//		
//		textbill.setText(textbill.getText() + "*****************************\n");
//		textbill.setText(textbill.getText() + "            BILL             \n");
//		textbill.setText(textbill.getText() + "*****************************\n" );
//		
//		textbill.setText(textbill.getText() + "Product Id "+ "\t" + "Name" + "\t" + "Price" + "\t" + "Total" + "\n");
//		
//		
//		
//	}
	
	public void exe() {
		String total = textCost.getText();
		String pay = textPay.getText();
		String bal = textBal.getText();
		
		DefaultTableModel df = new DefaultTableModel();
		df = (DefaultTableModel)table1.getModel();
		
		textarea.setText(textarea.getText() + "*****************************\n");
		textarea.setText(textarea.getText() + "            BILL             \n");
		textarea.setText(textarea.getText() + "*****************************\n" );
		
		textarea.setText(textarea.getText() + "Product Id "+ "\t"+"Customer Name"+ "\t"+"Plant Name" + "\t" + "Price" + "\t" + "Total" + "\n");
	}
	
	public void sales()
	{
		String totalcost = textCost.getText();
		String pay = textPay.getText();
		String bal = textBal.getText();
		
		int lastid = 0;
				
		try {
			// add total into db
			String query = "insert into sales(subtotal,pay,bal)values(?,?,?)";
			pst = con.prepareStatement(query,Statement.RETURN_GENERATED_KEYS);
			pst.setString(1, totalcost);
			pst.setString(2, pay);
			pst.setString(3, bal);
			
			pst.executeUpdate();
			rs = pst.getGeneratedKeys();
			
			if(rs.next())
			{
				lastid = rs.getInt(1);
			}			
			int rows = table1.getRowCount();
						
			//add product into db
			String query1 = "insert into sales_product(sales_id ,pname,cname ,price ,qty ,total )values(?,?,?,?,?,?)";
			pst1 = con.prepareStatement(query1);
			
			String pname="";
			String price;
			String qty;
			int total = 0;
			
			for(int i=0;i<table1.getRowCount(); i++)
			{
				pname = (String)table1.getValueAt(i,1);
				price = (String)table1.getValueAt(i, 2);
				qty = (String)table1.getValueAt(i,3);
				total = (int)table1.getValueAt(i, 4);
				
				pst1.setInt(1, lastid);
				pst1.setString(2, pname);
				pst1.setString(3, textCust.getSelectedItem().toString());
				pst1.setString(4, price);
				pst1.setString(5, qty);
				pst1.setInt(6, total);
				pst1.executeUpdate();
								
			}
			
			JOptionPane.showMessageDialog(this,"Sales Saved Successfully");
			
		}catch(SQLException ex) {
			System.out.println(ex);
		}
		
	}
	
	/**
	 * Create the frame.
	 */
	public Bill() {
		Connect();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 784, 592);
		contentPane = new JPanel();
		contentPane.setBackground(Color.LIGHT_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("The Plants World");
		lblNewLabel.setFont(new Font("Arial", Font.BOLD, 22));
		lblNewLabel.setBounds(325, 26, 276, 58);
		contentPane.add(lblNewLabel);
		
		JLabel cstNewLabel_1_1 = new JLabel("Select Customer");
		cstNewLabel_1_1.setFont(new Font("Arial", Font.BOLD, 18));
		cstNewLabel_1_1.setBounds(67, 90, 155, 31);
		contentPane.add(cstNewLabel_1_1);
		
		textCust = new JComboBox();
		textCust.setFont(new Font("Arial", Font.BOLD, 18));
		//textName.setColumns(10);
		textCust.setBounds(222, 91, 148, 33);
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			Connection c1 = DriverManager.getConnection("jdbc:mysql://localhost/nms","root","");
		    String q = "Select cno,cname from customer_receive";
			Statement st = c1.createStatement();
			ResultSet rs =st.executeQuery(q);

			while(rs.next())
			{
				textCust.addItem(rs.getString(2));
			//	
			}
			
			 
		}
		catch(Exception e)
		{
			System.out.println(""+e.toString());
		}
		contentPane.add(textCust);
		
		
		JLabel lblNewLabel_1 = new JLabel("Plant Id");
		lblNewLabel_1.setFont(new Font("Arial", Font.BOLD, 18));
		lblNewLabel_1.setBounds(67, 131, 93, 31);
		contentPane.add(lblNewLabel_1);
		
		textId = new JTextField();
		textId.setFont(new Font("Arial", Font.BOLD, 18));
		textId.setBounds(182, 130, 148, 33);
		contentPane.add(textId);
		//------------------------------------
		try
		{
			String q = "select max(id) from stock";
			System.out.println(""+q);
			Statement stmt = con.createStatement();
			ResultSet rst = stmt.executeQuery(q);
			int no=0;
			if(rst.next())
			{
				no=rst.getInt(1)+1;
				System.out.println("no");
			}
			textId.setText(""+no);
			textId.setEditable(false);
		}
		catch(Exception e)
		{
		     System.out.println("Connection Failed"+e.toString());
		}
		//------------------------------------
		
		textId.setColumns(10);
		
		// search plant form stock
		textId.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER) 
				{
					try {
						String pid = textId.getText();
						pst = con.prepareStatement("select * from stock where id =?");
						pst.setString(1, pid);
						rs = pst.executeQuery();
						
						if(rs.next()==false) {
							JOptionPane.showMessageDialog(null,"Plant Not found");
							
						}
						else {
							
							 String pname = rs.getString("p_name");
							// textName.setText(pname.trim());
							 
							 String pprice = rs.getString("p_price");
							 textPrice.setText(pprice.trim());
							 				 
						}
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			
				}
			}
		});
		
		
		
		JLabel lblNewLabel_1_1 = new JLabel("Plant Name");
		lblNewLabel_1_1.setFont(new Font("Arial", Font.BOLD, 18));
		lblNewLabel_1_1.setBounds(67, 193, 105, 31);
		contentPane.add(lblNewLabel_1_1);
		
		textName = new JComboBox();
		textName.setFont(new Font("Arial", Font.BOLD, 18));
		//textName.setColumns(10);
		textName.setBounds(182, 191, 148, 33);
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			Connection c1 = DriverManager.getConnection("jdbc:mysql://localhost/nms","root","");
		    String q = "Select id,p_name,p_price from stock";
			Statement st = c1.createStatement();
			ResultSet rs =st.executeQuery(q);

			while(rs.next())
			{
				textName.addItem(rs.getString(2));
			//	
			}
			
			 
		}
		catch(Exception e)
		{
			System.out.println(""+e.toString());
		}
		contentPane.add(textName);
		
		JLabel lblNewLabel_1_2 = new JLabel("Total Cost");
		lblNewLabel_1_2.setFont(new Font("Arial", Font.BOLD, 18));
		lblNewLabel_1_2.setBounds(367, 131, 93, 31);
		contentPane.add(lblNewLabel_1_2);
		
		textCost = new JTextField();
		textCost.setFont(new Font("Arial", Font.BOLD, 18));
		textCost.setColumns(10);
		textCost.setBounds(488, 130, 148, 33);
		contentPane.add(textCost);
		
		JLabel lblNewLabel_1_3 = new JLabel("Payment");
		lblNewLabel_1_3.setFont(new Font("Arial", Font.BOLD, 18));
		lblNewLabel_1_3.setBounds(367, 193, 93, 31);
		contentPane.add(lblNewLabel_1_3);
		
		textPay = new JTextField();
		textPay.setFont(new Font("Arial", Font.BOLD, 18));
		textPay.setColumns(10);
		textPay.setBounds(488, 192, 148, 33);
		contentPane.add(textPay);
		
		JLabel lblNewLabel_1_4 = new JLabel("Balance");
		lblNewLabel_1_4.setFont(new Font("Arial", Font.BOLD, 18));
		lblNewLabel_1_4.setBounds(367, 252, 93, 31);
		contentPane.add(lblNewLabel_1_4);
		
		textBal = new JTextField();
		textBal.setFont(new Font("Arial", Font.BOLD, 18));
		textBal.setColumns(10);
		textBal.setBounds(488, 251, 148, 33);
		contentPane.add(textBal);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("Quantity");
		lblNewLabel_1_1_1.setFont(new Font("Arial", Font.BOLD, 18));
		lblNewLabel_1_1_1.setBounds(67, 312, 93, 31);
		contentPane.add(lblNewLabel_1_1_1);
		
		JButton btnAdd = new JButton("Calculate");
		btnAdd.setFont(new Font("Arial", Font.BOLD, 16));
		btnAdd.setBounds(350, 309, 121, 37);
		contentPane.add(btnAdd);
		
		// Add Button
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				int price,qty,total;
				
				price= Integer.parseInt(textPrice.getText());
				qty= Integer.parseInt(textqty.getText());
				total = price * qty;
				
		
				df = (DefaultTableModel)table1.getModel();
				df.addRow(new Object[] 
						{
							textId.getText(),	
							textName.getSelectedItem().toString(),
							textPrice.getText(),
							textqty.getText(),
							total
								
						});
				
				int sum=0;
				for(int i=0; i<table1.getRowCount(); i++)
				{
					sum = sum + Integer.parseInt(table1.getValueAt(i, 4).toString());
				}
				
				textCost.setText(String.valueOf(sum));
		
				textId.setText("");
				//textName.setText("");
				textqty.setText("");
				textPrice.setText("");
				
				textId.requestFocus();
				
						
			}
		});
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(92, 373, 452, 133);
		contentPane.add(scrollPane);
		
		table1 = new JTable();
		scrollPane.setViewportView(table1);
		table1.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Id", "Name", "Price", "Quantity", "Total Cost"
			}
		));
		
		JButton btnPrint = new JButton("Print Bill");
		btnPrint.setFont(new Font("Arial", Font.BOLD, 16));
		btnPrint.setBounds(505, 306, 105, 42);
		contentPane.add(btnPrint);
		//print bill
		btnPrint.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				int totalcost = Integer.parseInt(textCost.getText());
				int pay = Integer.parseInt(textPay.getText());
				int tot = pay - totalcost;
				textBal.setText(String.valueOf(tot));
				sales();
				
//				billrep(); 
				

//				BillReport br = new BillReport();
//				br.show();
//				
			}
		});
		
		JLabel lblNewLabel_1_1_2 = new JLabel("Plant Price");
		lblNewLabel_1_1_2.setFont(new Font("Arial", Font.BOLD, 18));
		lblNewLabel_1_1_2.setBounds(67, 252, 105, 31);
		contentPane.add(lblNewLabel_1_1_2);
		
		textPrice = new JTextField();
		textPrice.setFont(new Font("Arial", Font.BOLD, 18));
		textPrice.setColumns(10);
		textPrice.setBounds(182, 251, 148, 33);
		contentPane.add(textPrice);
		
		textqty = new JTextField();
		textqty.setFont(new Font("Arial", Font.BOLD, 16));
		textqty.setBounds(182, 309, 148, 33);
		contentPane.add(textqty);
		textqty.setColumns(10);
		
		JButton btnprintbill = new JButton("Home");
		btnprintbill.setFont(new Font("Arial", Font.BOLD, 18));
		btnprintbill.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();	
        		Home h=new Home();
        		h.setVisible(true);
			}
		});
		btnprintbill.setBounds(581, 404, 121, 51);
		contentPane.add(btnprintbill);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(883, 434, 2, 2);
		contentPane.add(scrollPane_1);
	
	
		
	
	}
}
