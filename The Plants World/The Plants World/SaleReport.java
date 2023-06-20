import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;

class SaleReport extends JFrame implements ActionListener
{
	JPanel p1,p2,p3;
	JTable t1;
	JScrollPane sp;
	JButton ExitB,DeleteB;
	JLabel titleL;
	Font f;
	Connection con;
	Statement stat;

	public SaleReport()
	{
		setSize(600,600);
		setLayout(null);
		
 	     getContentPane().setBackground(new Color(158,158,158));
		
		f=new Font("Arial",Font.BOLD,30);

	     	p1=new JPanel();
		p1.setBounds(10,10,550,50);
		add(p1);

		
		titleL=new JLabel("Sale Report",JLabel.CENTER);
		titleL.setBounds(10,10,550,50);
		titleL.setForeground(Color.BLUE);

		titleL.setFont(f);
		titleL.setBorder(BorderFactory.createMatteBorder(2,2,2,2,Color.blue));	
		p1.add(titleL);	
		
		
		p2=new JPanel();
		p2.setBounds(10,70,550,400);
		add(p2);
		
		t1=new JTable();
		sp=new JScrollPane(t1);
		sp.setBounds(20,30,500,350);
		p2.add(sp);
		p2.setLayout(null);
		
		p3=new JPanel();
		p3.setBounds(10,480,550,50);
		p3.setBackground(new Color(13,99,217));
		add(p3);
		p3.setLayout(null);

		ExitB=new JButton("Exit");
		ExitB.setBounds(10,10,120,30);
		ExitB.addActionListener(this);
		ExitB.setBackground(Color.white);
		p3.add(ExitB);
		

		DeleteB=new JButton("Delete");
		DeleteB.setBounds(140,10,120,30);
		DeleteB.addActionListener(this);
		DeleteB.setBackground(Color.white);
		p3.add(DeleteB);
		
		try
		{
			  Class.forName("com.mysql.jdbc.Driver");  
				 con=DriverManager.getConnection("jdbc:mysql://localhost:3306/nms","root","");
			//Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			System.out.println("Connection Ok!");

			//con=DriverManager.getConnection("jdbc:odbc:addcust");
			System.out.println("Driver Ok!");
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		display();
		setVisible(true);
		setBackground(new Color(13,99,217));
		  setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		  Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
		  	int x = (d.width - getSize().width) / 2;
				int y = (d.height - getSize().height) / 2;
		  	setLocation(x, y);
	} 
	void display()
	{
		try{
			//String q="select tid,tname,tqty from tool";
			String q="select sales_id,pname,cname,qty,price,total from sales_product";
			stat=con.createStatement();
			ResultSet rs= stat.executeQuery(q);

			JResultModel r1=new JResultModel();
			r1.setResultSet(rs);
			t1.setModel(r1);
		}
		catch(Exception e)
		{
			System.out.println(e);
		}	

	}
	public void actionPerformed(ActionEvent ae)
	{
		if(ae.getSource()==ExitB)
		{   
			dispose();
			new Home();
		}
		if(ae.getSource()==DeleteB)
		{
			try{

				int row=t1.getSelectedRow();
				String id=t1.getValueAt(row,0).toString();
				int i=Integer.parseInt(id);

			String q1="delete  from sales_product where sales_id="+i;
			PreparedStatement stat1=con.prepareStatement(q1);
			int row1= stat1.executeUpdate();
			if(row1>0)
			{
				JOptionPane.showMessageDialog(this,"Record Deleted Successfully..!");
			}
			else
				JOptionPane.showMessageDialog(this,"Record Not Deleted..!");
			}
			catch(Exception e)
			{
				System.out.println(e);
			}
			display();
		}
	}
	public static void main(String args[])
	{
		new SaleReport();
	}
}


