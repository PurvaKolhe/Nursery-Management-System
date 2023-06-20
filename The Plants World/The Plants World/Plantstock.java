//package com.plants.archive;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Dimension;

public class Plantstock extends JFrame {

	private JPanel contentPane;
	private JTextField textname;
	private JTextField textqty;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				Plantstock ps = new Plantstock();
				ps.setVisible(true);
			}
		});
	}
	
	
	Connection con;
	PreparedStatement pst,pst1;
	ResultSet rs,rs1;
	private JButton btnClear;
	private JButton btnHome;
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
	/**
	 * Create the frame.
	 */
	public Plantstock() {
		
		Connect();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 573, 428);
		contentPane = new JPanel();
		contentPane.setBackground(Color.LIGHT_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Plant Name");
		lblNewLabel.setFont(new Font("Arial", Font.BOLD, 18));
		lblNewLabel.setBounds(54, 85, 119, 34);
		contentPane.add(lblNewLabel);
		
		textname = new JTextField();
		textname.setFont(new Font("Arial", Font.BOLD, 18));
		textname.setBounds(221, 85, 172, 39);
		contentPane.add(textname);
		textname.setColumns(10);
		
		JLabel lblQuantity = new JLabel("Quantity");
		lblQuantity.setFont(new Font("Arial", Font.BOLD, 18));
		lblQuantity.setBounds(54, 154, 119, 34);
		contentPane.add(lblQuantity);
		
		textqty = new JTextField();
		textqty.setEditable(false);
		textqty.setFont(new Font("Arial", Font.BOLD, 18));
		textqty.setColumns(10);
		textqty.setBounds(221, 154, 172, 39);
		contentPane.add(textqty);
		
		JButton btnstock = new JButton("Check Stock");
		
		btnstock.setFont(new Font("Arial", Font.BOLD, 18));
		btnstock.setBounds(39, 259, 152, 53);
		contentPane.add(btnstock);
		
		btnClear = new JButton("Clear");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textname.setText("");
				textqty.setText("");
				
			}
		});
		btnClear.setFont(new Font("Arial", Font.BOLD, 18));
		btnClear.setBounds(201, 259, 152, 53);
		contentPane.add(btnClear);
		
		btnHome = new JButton("Home");
		btnHome.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				dispose();	
        		Home h=new Home();
        		h.setVisible(true);
			}
		});
		btnHome.setFont(new Font("Arial", Font.BOLD, 18));
		btnHome.setBounds(360, 259, 152, 53);
		contentPane.add(btnHome);
		
		JLabel lblNewLabel_1 = new JLabel("The Plants World");
		lblNewLabel_1.setForeground(Color.BLACK);
		lblNewLabel_1.setBackground(Color.WHITE);
		lblNewLabel_1.setFont(new Font("Arial", Font.BOLD, 25));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(103, 10, 305, 46);
		contentPane.add(lblNewLabel_1);
		
		btnstock.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(e.getSource() == btnstock) 
				{
					try {
							String pname = textname.getText();
							pst = con.prepareStatement("select sum(p_qty) from stock where p_name=?");
							pst.setString(1, pname);
							rs = pst.executeQuery();
							
							pst1 = con.prepareStatement("select sum(qty) from sales_product where pname=?");
							pst1.setString(1, pname);
							rs1 = pst1.executeQuery();
							
							 rs.next();
				             rs1.next();
				             int i1=rs.getInt(1)-rs1.getInt(1);
				             textqty.setText(i1+"");
				             
						
							
						
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
			
				}
			
				
			}
		});
	}
}
