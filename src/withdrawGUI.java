//Import everything
//ATM GUI design attempt #2
import javax.swing.*;
//progress
import java.awt.*;
import java.awt.event.*;
public class withdrawGUI extends JFrame implements ActionListener
{
   //Create the necessary GUI components
   private JPanel panel1, panel2, panel3, panel4, panel5, panel6, panel7;
   private JTextArea display;
   private JButton[] keypad;
   private JButton twentyc, fiftyc, hundredc, otherc, home, accountinfo;
   private static BankInfo account; //MATT: MADE STATIC
   private static boolean ccheck, scheck, keypadcheck; //MATT: ADDED
   private BankFeature feature;
   //Strings for the client card number and PIN
   private String cardID, PIN;
  
   
   public withdrawGUI(BankInfo a, boolean c, boolean s) //MATT: MODIFIED CONSTRUCTOR
   {
	   account = a;
      ccheck = c;
      scheck = s; 
	   feature = new BankFeature(account); //MATT: MODIFIED BANKFEATURE CONSTRUCTOR
	   

      //Set empty string
      cardID = "";
      PIN = "";
      //Initiate the panels
      panel1 = new JPanel(new GridLayout(1,2));
      panel2 = new JPanel();
      panel3 = new JPanel();
      panel4 = new JPanel(new GridLayout(4,3));
      panel5 = new JPanel();
      panel6 = new JPanel(new GridLayout(3,1, 1, 1));
      panel7 = new JPanel(new GridLayout(3,1, 1, 1));
      
      //Initiate the text field and text area
      display = new JTextArea(30, 30);
      
      //Start adding and editing components
      panel5.add(display);
      
      display.setEditable(false);

      
      //An array of JButtons to represent the keypad
      keypad = new JButton[13];
      
      //Loop through and add buttons from 0-9
      keypad[0] = new JButton("0");
      keypad[0].setBackground(Color.GRAY);
      keypad[0].setOpaque(true);
      keypad[0].setBorderPainted(true);
      //keypad[0].addActionListener(this);
      for (int i = 1; i <=9; i++)
      {
         keypad[i] = new JButton(""+i);
         panel4.add(keypad[i]);
         keypad[i].setBackground(Color.GRAY);
         keypad[i].setOpaque(true);
         keypad[i].setBorderPainted(true);
         //keypad[i].addActionListener(this);
      }
      
      //Add two other buttons to the keypad for OK and Cancel/Correction
      keypad[11] = new JButton("CLEAR");
      keypad[11].setBackground(Color.RED);
      keypad[11].setOpaque(true);
      keypad[11].setBorderPainted(true);
      panel4.add(keypad[11]);
      //keypad[11].addActionListener(this);
      panel4.add(keypad[0]);
      keypad[12] = new JButton("OK");
      keypad[12].setBackground(Color.GREEN);
      keypad[12].setOpaque(true);
      keypad[12].setBorderPainted(true);
      panel4.add(keypad[12]);
      //keypad[12].addActionListener(this);
      
      //Four default, temporary buttons for the right hand side. Will modify as needed
      twentyc = new JButton("TWENTY");
      panel6.add(twentyc);
      twentyc.addActionListener(this);
      home = new JButton("HOME");
      panel6.add(home);
      home.setVisible(true);
      home.addActionListener(this);
      fiftyc = new JButton("FIFTY");
      fiftyc.addActionListener(this);
      panel6.add(fiftyc);
      hundredc = new JButton("HUNDRED");
      hundredc.addActionListener(this);
      panel7.add(hundredc);
      accountinfo = new JButton("ACCOUNT");
      panel7.add(accountinfo);
      accountinfo.setVisible(false);
      accountinfo.addActionListener(this);
      otherc = new JButton("OTHER");
      panel7.add(otherc);
      otherc.addActionListener(this);
      
      
      //Add all the panels
      panel2.add(panel4);
      add(panel6, BorderLayout.WEST);
      panel6.setVisible(true);
      add(panel7, BorderLayout.EAST);
      add(panel5);
      panel5.setPreferredSize(new Dimension(30,30));
      add(panel2,BorderLayout.SOUTH);
      panel2.setPreferredSize(new Dimension(400, 150));
      
      display.setText("\n\n\nPlease pick the amount of money you would to withdraw.");
      display.setFont(new Font("Arial", Font.BOLD,20));
      display.setVisible(true);
      setTitle("WITHDRAW");
      setSize(750,500);
      setLocationRelativeTo(null);
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      setVisible(true);
      setResizable(false);
      //MATT: ADDED PANEL COLOURS
      panel2.setBackground(Color.GRAY);
      panel6.setBackground(Color.GRAY);
      panel7.setBackground(Color.GRAY);
      panel5.setBackground(Color.GRAY);
   }
   
   public void actionPerformed(ActionEvent e)
   {
      //Loop through the keypad array to see if any of the buttons were pressed 
	   for (int i = 0; i < 10; i++)
       {
          if (e.getSource() == keypad[i])
          {
             //Edit the input display
             cardID = cardID + i;
             display.setText("\n\n\tPlease enter amount of money:" + "\n\t" +cardID);
          }
       }
	   
	   if(e.getSource() == keypad[11]){
	    	cardID = "";
	        display.setText("\n\n\tPlease enter amount of money:" + "\n\t" +cardID);
	  }
	  

      if (e.getSource() == twentyc)
      {
         if (ccheck == true && scheck == false)
         {
    	      display.setText(feature.WithdrawFROMC(20.0));
    	      display.setFont(new Font("Arial", Font.BOLD,18));
    	      display.setVisible(true);
    	      home.setVisible(true);
    	      accountinfo.setVisible(true);
         }
         else if (ccheck == false && scheck == true)
         {
        	 display.setText(feature.WithdrawFROMS(20.0));
    	     display.setFont(new Font("Arial", Font.BOLD,18));
    	     display.setVisible(true);
    	     home.setVisible(true);
    	     accountinfo.setVisible(true);
         }
      }
      
      if (e.getSource() == fiftyc)
      {
         if (ccheck == true && scheck == false) //MATT: ADDED
         {
    	      display.setText(feature.WithdrawFROMC(50.0));
    	      display.setFont(new Font("Arial", Font.BOLD,18));
    	      display.setVisible(true);
    	      home.setVisible(true);
    	      accountinfo.setVisible(true);
         }
         else if (ccheck == false && scheck == true) //MATT: ADDED
         {
    	      display.setText(feature.WithdrawFROMS(50.0));
    	      display.setFont(new Font("Arial", Font.BOLD,18));
    	      display.setVisible(true);
    	      home.setVisible(true);
    	      accountinfo.setVisible(true);
         }
          
      }
      
      
      if (e.getSource() == hundredc)
      {
         if (ccheck == true && scheck == false) //MATT: ADDED
         {
    	      display.setText(feature.WithdrawFROMC(100.0));
    	      display.setFont(new Font("Arial", Font.BOLD,18));
    	      display.setVisible(true);
    	      home.setVisible(true);
    	      accountinfo.setVisible(true);
         }
         else if (ccheck == false && scheck == true) //MATT: ADDED
         {
    	      display.setText(feature.WithdrawFROMS(100.0));
    	      display.setFont(new Font("Arial", Font.BOLD,18));
    	      display.setVisible(true);
    	      home.setVisible(true);
    	      accountinfo.setVisible(true);
         }
          
      }
      
      if (e.getSource() == otherc)
      {
         if (keypadcheck == false)
         {
              for (int i = 1; i <=9; i++)
            {
               keypad[i].addActionListener(this);
            }

              keypad[0].addActionListener(this);
              keypad[11].addActionListener(this);
              keypad[12].addActionListener(this);
            keypadcheck = true;
         }
           cardID = "";
           display.setText("\n\n\tPlease enter amount of money:" + "\n\t" +cardID);
      }
      
      if(e.getSource() == keypad[12])
      {
    	  double num = Double.parseDouble(cardID);
        if (ccheck == true && scheck == false) //MATT: ADDED
         {
    	      display.setText(feature.WithdrawFROMC(num));
    	      display.setFont(new Font("Arial", Font.BOLD,18));
    	      display.setVisible(true);
    	      home.setVisible(true);
    	      accountinfo.setVisible(true);
         }
         
         else if (ccheck == false && scheck == true) //MATT: ADDED
         {
            display.setText(feature.WithdrawFROMS(num));
    	      display.setFont(new Font("Arial", Font.BOLD,18));
            display.setVisible(true);
            home.setVisible(true);
            accountinfo.setVisible(true);
         }
          
      }
      
      if(e.getSource() == home){
    	  super.dispose();
          ATM2 atm = new ATM2(account);
      }
      
      if(e.getSource() == accountinfo){
    	  display.setText(account.toString());
          display.setFont(new Font("Arial", Font.BOLD,20));
          display.setVisible(true);
      }

   }
      
   

public static void main(String[] args)
   {
      withdrawGUI sim = new withdrawGUI(account, ccheck, scheck);  //MATT: CHANGED
   }
}
      
      
      