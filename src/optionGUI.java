import javax.swing.*;
//IN-PROGRESS
//HOME PAGE
import java.awt.*;
import java.awt.event.*;
public class optionGUI extends JFrame implements ActionListener
{
   //Create the necessary GUI components
   private JPanel panel1, panel2, panel3, panel4, panel5;
   private JTextArea display;
   private JButton[] keypad;
   private JButton chequing, savings, checkBalance, transfer, signout, placeholder;
   private static BankInfo account; //MATT: MADE STATIC
   private static boolean wcheck, dcheck; //MATT: ADDED THESE
   private boolean ccheck, scheck;
   //Strings for the client card number and PIN
   private String cardID, PIN;
   
   public optionGUI(BankInfo a, boolean w, boolean d)
   {
      wcheck = w; //MATT: NEW
      dcheck = d; //MATT: NEW
      account = a;
      //Set empty strings
      cardID = "";
      PIN = "";
      //Initiate the panels
      panel1 = new JPanel();
      panel2 = new JPanel(new GridLayout(4,3));
      panel3 = new JPanel();
      panel4 = new JPanel(new GridLayout(3,1, 1, 1));
      panel5 = new JPanel(new GridLayout(3,1, 1, 1));
      
      //Initiate the text field and text area
      display = new JTextArea(30,30);
      
      //Start adding and editing components
      panel3.add(display);
      display.setEditable(false);
      
      
      //An array of JButtons to represent the keypad
      keypad = new JButton[13];
      
      //Loop through and add buttons from 0-9
      keypad[0] = new JButton("0");
      keypad[0].setBackground(Color.GRAY);
      keypad[0].setOpaque(true);
      keypad[0].setBorderPainted(true);
      keypad[0].addActionListener(this);
      for (int i = 1; i <=9; i++)
      {
         keypad[i] = new JButton(""+i);
         panel2.add(keypad[i]);
         keypad[i].setBackground(Color.GRAY);
         keypad[i].setOpaque(true);
         keypad[i].setBorderPainted(true);
         keypad[i].addActionListener(this);
      }
      
      //Add two other buttons to the keypad for OK and Cancel/Correction
      keypad[11] = new JButton("CLEAR");
      keypad[11].setBackground(Color.RED);
      keypad[11].setOpaque(true);
      keypad[11].setBorderPainted(true);
      panel2.add(keypad[11]);
      keypad[11].addActionListener(this);
      panel2.add(keypad[0]);
      keypad[12] = new JButton("OK");
      keypad[12].setBackground(Color.GREEN);
      keypad[12].setOpaque(true);
      keypad[12].setBorderPainted(true);
      panel2.add(keypad[12]);
      keypad[12].addActionListener(this);
      
      //Four default, temporary buttons for the right hand side. Will modify as needed
      chequing = new JButton("CHEQUING");
      panel4.add(chequing);
      chequing.addActionListener(this);
      placeholder = new JButton("HOME");
      panel4.add(placeholder);
      placeholder.setVisible(false);
      placeholder.addActionListener(this);
      savings = new JButton("SAVING");
      panel4.add(savings);
      savings.addActionListener(this);
      checkBalance = new JButton("ACCOUNT");
      checkBalance.addActionListener(this);
      panel5.add(checkBalance);
      transfer = new JButton("TRANSFER");
      panel5.add(transfer);
      transfer.setVisible(false);
      signout = new JButton("LOGOUT");
      panel5.add(signout);
      signout.addActionListener(this);
      
      //Add all the panels
      panel1.add(panel2);
      add(panel4, BorderLayout.WEST);
      panel4.setVisible(true);
      add(panel5, BorderLayout.EAST);
      add(panel3);
      panel3.setPreferredSize(new Dimension(30,30));
      add(panel1,BorderLayout.SOUTH);
      panel1.setPreferredSize(new Dimension(400, 150));
      
      display.setText("\n\n\n\tWould you like to pick \n\tchequing or savings account.");
      display.setFont(new Font("Arial", Font.BOLD,20));
      display.setVisible(true);
      setTitle("OPTION");
      setSize(750,500);
      setLocationRelativeTo(null);
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      setVisible(true);
      setResizable(false);
      //MATT: ADDED PANEL COLOURS
      
      panel1.setBackground(Color.GRAY);
      panel4.setBackground(Color.GRAY);
      panel5.setBackground(Color.GRAY);
      panel3.setBackground(Color.GRAY);
   }
   

   
   public void actionPerformed(ActionEvent e)
   {
      
      if (e.getSource() == checkBalance)
      {
    	  display.setText(account.toString());
    	  display.setFont(new Font("Arial", Font.BOLD,20));
    	  display.setVisible(true);
      }

      if (e.getSource() == chequing)
    	  {
        super.dispose();
           if (wcheck == true && dcheck == false){
        	   super.dispose();
        	   ccheck = true;
        	   scheck = false;
        	   withdrawGUI sim = new withdrawGUI(account, ccheck, scheck);
    		  }
           else if (dcheck == true && wcheck == false)
           {
        	   super.dispose();
        	   ccheck = true;
        	   scheck = false;
        	   depositGUI sim = new depositGUI(account, ccheck, scheck);
    			  
    		  }
    	  }
      
    	  if (e.getSource() == savings)
    	  {
           super.dispose();
           if (wcheck == true && dcheck == false)
           {
        	   ccheck = false;
        	   scheck = true;
        	   withdrawGUI sim = new withdrawGUI(account, ccheck, scheck);
    		  }
           else if (dcheck == true && wcheck == false)
           {
        	  ccheck = false;
              scheck = true;
              depositGUI sim = new depositGUI(account, ccheck, scheck);
    			  
    		  }
    	  }
     
      
      if(e.getSource() == signout){
    	  super.dispose();
    	  Login sim = new Login();
      }
     
   }
   
   public static void main(String[] args)
   {
      optionGUI sim = new optionGUI(account, wcheck, dcheck); //MATT: CHANGED
   }
}
      
      
      