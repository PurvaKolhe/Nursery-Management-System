//package com.plants.archive;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.border.*;
public class Maint extends JFrame implements ActionListener
{
	public Maint()
	{
	super("MAINTAINANCE");
  	JButton bhome;
  	JPanel Pan1,Pan2;
  	JLabel l;
  	Pan1=new JPanel();
  	Pan2=new JPanel();
  	l=new JLabel();
    
    setSize(600, 400);
    setLocation(400,190);
   // addWindowListener(new BasicWindowMonitor());
     
    String val = " \nNURSERY SPENT LOT OF EFFORTS ON MAINTAINANCE OF PLANTS WHICH INCLUDE: :\n"+ 
                  "\n * CUTTING  \n" +
                  " * WATERING  \n"+
                  " * PROVIDING FERTILIZERS  \n"+
                  " * SRAYING OF ANTI-FUNGAL,ANTI-BACTERIAL \n"+
                  "  \n\n    SPRAYS IN ORDER TO CONTROL THE PEST AND DISEASE.\n"+
                  "      THE PLANTATION PERIOD OF EVERY PLANT IS OF ABOUT 2 TO 3 MONTHS.DURING THIS PERIOD\n"+
                  " CUTTING OF EVERY PLANT WAS DONE AFTER 15 DAYS TO REMOVE DEAD LEAVES,INFECTED BRANCHES \n"+
                  " AND LEAVES.WITHIN THIS 15 DAYS PERIOD ALSO PROVIDED FERTILIZERS TO ENHANCE THE GROWTH \n"+
                  " AND TO OBTAIN HEALTHY PLANTS.ALSO VARIOUS DISINFECTED SPRAYS ARE SPRAYED TO CONTROL EARLY\n"+
                  "INFECTION OF YOUNG PLANT";                  
                      JTextArea text=new JTextArea(val,20,40);
                      text.setEditable(false);
                      text.setForeground(Color.blue);
    
    Container contentPane=getContentPane();
    contentPane.setLayout(null);
    
      l.setFont(new Font("Mon0.1111otype Corsiva",Font.BOLD, 30));
      l.setForeground(Color.red);
      l.setText("The Plants World");
      l.setHorizontalTextPosition(SwingConstants.CENTER);
      getContentPane().add(l);
      l.setBounds(140, 10, 350, 50);
    
    Pan1.setBorder(new LineBorder(new Color(153, 51, 0), 2, true));
    Pan1.setForeground(new Color(153, 51, 0));
    Pan1.add(text);
    getContentPane();
    add(Pan1);
    Pan1.setBounds(40, 60, 670, 390);


    Pan2.setLayout(null);
    Pan2.setBorder(new LineBorder(new Color(153, 51, 0), 2, true));
    
    bhome=new JButton("HOME");
    bhome.setSize(10,30);
    bhome.setBounds(20,20,20,20);
    bhome.addActionListener(this);
    Pan2.add(bhome);   
    bhome.setBounds(250, 20, 100, 20);

    contentPane.add(Pan2);
    Pan2.setBounds(40, 455,  670, 60);
    setVisible(true);
    setSize(800,800);
    
  }

  public static void main(String args[])
   {
     Maint ma = new Maint();
    ma.setVisible(true);
  }
  
  public void actionPerformed(ActionEvent ae)
  {
  	    String str;
    	str=ae.getActionCommand();
        if(str.equals("HOME"))
          {
    	       dispose();	
               Home fm=new Home();
    	  }
		
  }
}