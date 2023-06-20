
import java.awt.BorderLayout;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.lang.Object;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Logger;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.Color;

public class AddPlants extends JFrame {

	private JPanel contentPane;
	private JTextField pltname;
	private JTextField pltqty;
	private JTextField pltprice;
	private JTextField pltId;
	private JComboBox pltcategory;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					AddPlants frame = new AddPlants();
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
		
		AddPlants addplt=new AddPlants();
		addplt.show();
	}

	/**
	 * Create the frame.
	 */
	Connection con;
	PreparedStatement pst;
	ResultSet rs;
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
	
	
	public AddPlants() {
		
		Connect();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 892, 539);
		contentPane = new JPanel();
		contentPane.setBackground(Color.LIGHT_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel titleLabel = new JLabel("Add New Plants");
		titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		titleLabel.setFont(new Font("Arial", Font.BOLD, 25));
		titleLabel.setBounds(193, 21, 217, 61);
		contentPane.add(titleLabel);
		
		JLabel lblNewLabel = new JLabel("Plant Name");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Arial", lblNewLabel.getFont().getStyle() | Font.BOLD, lblNewLabel.getFont().getSize() + 11));
		lblNewLabel.setBounds(52, 197, 133, 25);
		contentPane.add(lblNewLabel);
		
		pltname = new JTextField();
		pltname.setFont(new Font("Arial", Font.BOLD, 20));
		pltname.setHorizontalAlignment(SwingConstants.CENTER);
		pltname.setBounds(227, 191, 211, 36);
		contentPane.add(pltname);
		pltname.setColumns(10);
		
		JLabel lblPlantCategory = new JLabel("Plant Category");
		lblPlantCategory.setFont(new Font("Arial", Font.BOLD, 21));
		lblPlantCategory.setHorizontalAlignment(SwingConstants.CENTER);
		lblPlantCategory.setBounds(449, 129, 165, 27);
		contentPane.add(lblPlantCategory);
		
		JLabel lblPlantQuantity = new JLabel("Plant Quantity");
		lblPlantQuantity.setFont(new Font("Arial", Font.BOLD, 21));
		lblPlantQuantity.setHorizontalAlignment(SwingConstants.CENTER);
		lblPlantQuantity.setBounds(52, 271, 165, 32);
		contentPane.add(lblPlantQuantity);
		
		pltqty = new JTextField();
		pltqty.setHorizontalAlignment(SwingConstants.CENTER);
		pltqty.setFont(new Font("Arial", Font.BOLD, 20));
		pltqty.setColumns(10);
		pltqty.setBounds(227, 269, 211, 36);
		contentPane.add(pltqty);
		
		JLabel lblDate = new JLabel("Price");
		lblDate.setFont(new Font("Arial", Font.BOLD, 21));
		lblDate.setHorizontalAlignment(SwingConstants.LEFT);
		lblDate.setBounds(459, 197, 133, 25);
		contentPane.add(lblDate);
		
		pltprice = new JTextField();
		pltprice.setHorizontalAlignment(SwingConstants.CENTER);
		pltprice.setFont(new Font("Arial", Font.BOLD, 20));
		pltprice.setColumns(10);
		pltprice.setBounds(622, 191, 211, 36);
		contentPane.add(pltprice);
		
		JButton Addplt = new JButton("Add Plant");
		Addplt.setFont(new Font("Arial", Font.BOLD, 21));
		Addplt.setBounds(225, 380, 144, 42);
		contentPane.add(Addplt);
		// Add Button Code
		Addplt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Class.forName("com.mysql.jdbc.Driver");
					Connection c1 = DriverManager.getConnection("jdbc:mysql://localhost/nms","root","");
					
					Statement s1 = c1.createStatement();
					String ss1 = ("insert into stock values('" + pltId.getText() + "','"
							+ pltname.getText() + "','" + pltcategory.getSelectedItem().toString() + "','" + pltprice.getText() + "','" + pltqty.getText() + "')");
					s1.executeUpdate(ss1);

					c1.close();
					
					JOptionPane.showMessageDialog(null,"Records For "+pltname.getText()+" Saved Successfully");

					pltId.setText("");
					pltname.setText("");
					//pltcategory.setText("");
					pltprice.setText("");
					pltqty.setText("");

				} catch (Exception ex) {
					System.out.println(ex);
				}

			}
			}
		);
	
		JLabel lblPlantId = new JLabel("Plant Id");
		lblPlantId.setFont(new Font("Arial", Font.BOLD, 21));
		lblPlantId.setHorizontalAlignment(SwingConstants.CENTER);
		lblPlantId.setBounds(33, 130, 133, 25);
		contentPane.add(lblPlantId);
		
		pltId = new JTextField();
		pltId.setHorizontalAlignment(SwingConstants.CENTER);
		pltId.setFont(new Font("Arial", Font.BOLD, 20));
		pltId.setColumns(10);
		pltId.setBounds(226, 124, 211, 36);
		
		//------------------------------
		try
		{
			//Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
		     //conn=DriverManager.getConnection("jdbc:odbc:addcust");
		   

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
			pltId.setText(""+no);
			//pltId.setEditable(false);
		}
		catch(Exception e)
		{
		     System.out.println("Connection Failed"+e.toString());
		}
		//--------------------------------
		contentPane.add(pltId);
		//------------------------------
		pltcategory = new JComboBox();
		//pltcategory.setHorizontalAlignment(SwingConstants.CENTER);
		//pltcategory.setFont(new Font("Arial", Font.BOLD, 20));
		//pltcategory.setColumns(10);
		pltcategory.setBounds(622, 124, 211, 36);
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			Connection c1 = DriverManager.getConnection("jdbc:mysql://localhost/nms","root","");
		String q = "Select cid,cat_nm from palnt_cat";
			Statement st = c1.createStatement();
			ResultSet rs =st.executeQuery(q);

			while(rs.next())
			{
				pltcategory.addItem(rs.getString(2));
			}

		}
		catch(Exception e)
		{
			System.out.println(""+e.toString());
		}
		//------------------------------
	
		contentPane.add(pltcategory);
		
		JButton btnNewButton = new JButton("Search");
		btnNewButton.setFont(new Font("Arial", Font.BOLD, 21));
		btnNewButton.setBounds(65, 380, 133, 42);
		contentPane.add(btnNewButton);
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnUpdate.setFont(new Font("Arial", Font.BOLD, 21));
		btnUpdate.setBounds(400, 380, 144, 42);
		contentPane.add(btnUpdate);
		
		JButton btnHome = new JButton("Home");
		btnHome.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				dispose();	
        		Home h=new Home();
        		h.setVisible(true);
			}
		});
		btnHome.setFont(new Font("Arial", Font.BOLD, 21));
		btnHome.setBounds(582, 380, 144, 42);
		contentPane.add(btnHome);
		
		JButton button = new JButton("New button");
		button.setBounds(70, 394, 85, 21);
		contentPane.add(button);
		
		JButton btnNewButton_1 = new JButton("Clear");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				pltId.setText("");
				pltname.setText("");
				//pltcategory.setText("");
				pltprice.setText("");
				pltqty.setText("");
				
			}
		});
		btnNewButton_1.setFont(new Font("Arial", Font.BOLD, 21));
		btnNewButton_1.setBounds(613, 280, 133, 42);
		contentPane.add(btnNewButton_1);
		
		//update button
		btnUpdate.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(pltId.getText().isEmpty() || pltname.getText().isEmpty() || pltcategory.getSelectedItem().toString().isEmpty() ||
						pltqty.getText().isEmpty() || pltprice.getText().isEmpty())
				{
					
				}
				else 
				{
					try {
						con = DriverManager.getConnection("jdbc:mysql://localhost/nms","root","");
						String UpdateQuery = "Update stock set p_qty= '"+ pltqty.getText() +"' "+" where id="+pltId.getText();
					Statement Add = con.createStatement();
					Add.executeUpdate(UpdateQuery);
					JOptionPane.showMessageDialog(null,"Plant Quantity Updated Successfully.");
					}catch(SQLException e1){
						e1.printStackTrace();
					}
					
				}
				
				
			}
		});
	
		
		// Search Plant
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.getSource() == btnNewButton) 
				{
					try {
							String pid = pltId.getText();
							pst = con.prepareStatement("select * from stock where id =?");
							pst.setString(1, pid);
							rs = pst.executeQuery();
							
							if(rs.next()==false) {
								JOptionPane.showMessageDialog(null,"Plant Not found");
								
							}
							else {
								
								 String pname = rs.getString("p_name");
								 pltname.setText(pname.trim());
								 
								 String pcategory = rs.getString("p_categary");
								// pltcategory.setText(pcategory.trim());
								 
								 String pqty = rs.getString("p_qty");
								 pltqty.setText(pqty.trim());
								 
								 String pprice = rs.getString("p_price");
								 pltprice.setText(pprice.trim());
								 
							}
						
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
			
				}
				
			}
		});
	

	}
}
