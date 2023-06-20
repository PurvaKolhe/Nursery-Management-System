//package com.plants.archive;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.border.*;

class Home extends JFrame implements ActionListener {
    JPanel p1, p2, p3, p4;
    JButton b1, b3, b5;
    JLabel l1, l2;
    Menu Stock, Maintenance, Customer, Bill,Report;
    MenuBar mb;
    MenuItem i1, i2, i3, i4, i5, i6, i7, i8,i9,i10;
    Icon img;

    public Home()
    {
        super("Nursery Home");
        p1 = new JPanel();
        p2 = new JPanel();
        p4 = new JPanel();

        img = new ImageIcon("res\\bag.jpg");
        
        l2 = new JLabel();
        l2.setIcon(img);
        l2.setBounds(10, 100, 480, 310);
        p1.add(l2);
        b1 = new JButton("STOCK");
        b3 = new JButton("SALE");

        mb = new MenuBar();
        Stock = new Menu("PLANT");
        Maintenance = new Menu("MAINTENANCE");
        i6 = new MenuItem("INFORMATION");
        Customer = new Menu("CUSTOMER");
        i7 = new MenuItem("CUSTOMER ISSUE");
        i8 = new MenuItem("CUSTOMER REGISTRATION");
        
        Report = new Menu("Report");
        i9 = new MenuItem("Stock Report");
        i10 = new MenuItem("Sale Report");
        i1 = new MenuItem("PLANT DETAILS");
//        Bill = new Menu("BILL");
//        i3 = new MenuItem("FOR SALE");
//        i4 = new MenuItem("FOR RENT");
        i5 = new MenuItem("EXIT");

        i1.addActionListener(this);
//        i3.addActionListener(this);
//        i4.addActionListener(this);
        i5.addActionListener(this);
        i6.addActionListener(this);
        i7.addActionListener(this);
        i8.addActionListener(this);
        i9.addActionListener(this);
        i10.addActionListener(this);
        Stock.add(i1);
//        Stock.add(Bill);
//        Bill.add(i3);
//        Bill.add(i4);
        Stock.addSeparator();
        Stock.add(i5);
        Stock.addSeparator();
        Maintenance.addActionListener(this);
        mb.add(Stock);
        mb.add(Maintenance);
        mb.add(Customer);
        mb.add(Report);
        Maintenance.add(i6);
        Customer.add(i7);
        Customer.add(i8);
        Report.add(i9);
        Report.add(i10);
        setMenuBar(mb);

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                setVisible(false);
            }
        });

        Container contentPane = getContentPane();
        getContentPane().setLayout(null);

        p2.setBackground(new Color(190, 170, 255));
        p2.setLayout(null);
        contentPane.add(p2);
        p2.setBounds(500, 125, 230, 260);
        p2.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 4, true));

        p4.setBackground(new Color(51-153-255));
        p4.setLayout(null);
        contentPane.add(p4);
        p4.setBounds(190, 16, 380, 50);

        p1.setBackground(new Color(204,204,250));
        p1.setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        contentPane.add(p1);
        p1.setBounds(0, 0, 800, 575);

        l1 = new JLabel("The Plants World", JLabel.CENTER);

        b1.setFont(new Font("Arial", 0, 18));
        b1.setMnemonic('a');
        b1.setForeground(new Color(255, 51, 51));
        b1.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
        p2.add(b1);
        b1.addActionListener(this);
        b1.setBounds(20, 20, 180, 40);

        b3.setFont(new Font("Arial", 0, 18));
        // b3.setMnemonic('a');
        b3.setForeground(new Color(255, 51, 51));
        b3.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
        p2.add(b3);
        b3.addActionListener(this);
        b3.setBounds(20, 100, 180, 40);
        b5 = new JButton("LOGOUT");
        b5.setBounds(20, 186, 180, 40);
        p2.add(b5);

        b5.setFont(new Font("Arial", 0, 18));
        // b5.setMnemonic('b');
        b5.setForeground(new Color(255, 51, 51));
        b5.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
        b5.addActionListener(this);

        Font f = new Font("Charlemagne", Font.BOLD, 25);
        l1.setFont(f);
        l1.setForeground(Color.black);
        //l1.setBackground(Color.green);
        p4.add(l1);
        l1.setBounds(10, 5, 330, 40);
        setLocation(350,140);
        setSize(800, 575);
        setVisible(true);

    }

    public void actionPerformed(ActionEvent e) {

           if(e.getActionCommand().equalsIgnoreCase("Customer Registration"))
           {
               dispose();
               new CustomerBill();
           }


        if (e.getActionCommand().equalsIgnoreCase("Stock Report")) {
            dispose();
            new Report();
        }

        if (e.getActionCommand().equalsIgnoreCase("Sale Report")) {
            dispose();
            new SaleReport();
        }
        if (e.getActionCommand().equalsIgnoreCase("Sale")) {
            dispose();
        	Bill bill = new Bill();
        	bill.show();
        }
//        if (e.getActionCommand().equalsIgnoreCase("Repwin")) {
//            dispose();
//            new RepWin();
//
//        }
        if (e.getActionCommand().equalsIgnoreCase("Stock")) {
            dispose();
        	Plantstock ps = new Plantstock();
			ps.show();

        }
        if (e.getActionCommand().equalsIgnoreCase("Information")) {
            dispose();
            new Maint();

        }
//        if (e.getActionCommand().equalsIgnoreCase("Plant Details")) {
//            dispose();
//            new pdetail();
//            new AddPlants();
//
//        }
        
        if (e.getSource() == i1) {
        	dispose();
         AddPlants addplt =	new AddPlants();
         addplt.show();        
         }
        
        if (e.getSource() == i5) {
        	dispose();
          
         }
        
        
        if (e.getActionCommand().equalsIgnoreCase("For Sale")) {
            dispose();
        	Bill bill = new Bill();
			bill.show();
        }

//        if (e.getActionCommand().equalsIgnoreCase("PURCHASE REPORT"))
//        {
//            dispose();
//            new RepWin();
//        }

        if (e.getActionCommand().equalsIgnoreCase("Customer Issue")) {
           dispose();
            new CustomerIssue();
        }
//        if (e.getActionCommand().equalsIgnoreCase("Rbill")) {
//            dispose();
//            new Rbill();
//        }
        if(e.getSource()==b5) {
        	dispose();
        }

    }

    public static void main(String args[]) {

//        new UserLogin();
        new LoginForm();

        //h.setDefaultCloseOperation(h.EXIT_ON_CLOSE);
    }
}