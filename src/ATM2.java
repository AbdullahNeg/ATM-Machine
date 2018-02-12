import javax.swing.*;
//IN-PROGRESS
//HOME PAGE
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
public class ATM2 extends JFrame implements ActionListener
{
   //Create the necessary GUI components
   private JPanel panel1, panel2, panel3, panel4, panel5;
   private JTextArea display;
   private JButton[] keypad;
   private JButton withdraw, deposit, checkBalance, transfer, signout, placeholder;
   private String cardID, PIN;
   private static BankInfo user;
   private boolean dcheck, wcheck;
   
   public ATM2(BankInfo u)
   {
      user = u;
      //Set empty strings
      cardID = "";
      PIN = "";
      //Initiate the panels
      panel1 = new JPanel();
      panel2 = new JPanel(new GridLayout(4,3));//keypad
      panel3 = new JPanel();
      panel4 = new JPanel(new GridLayout(3,1, 1, 1));//left side buttons
      panel5 = new JPanel(new GridLayout(3,1, 1, 1));//right side buttons
      
      //display area with user instructions
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
      
      //Four default, temporary button for the left hand side. Will modify as needed
      withdraw = new JButton("WITHDRAW");
      panel4.add(withdraw);
      withdraw.addActionListener(this);
      placeholder = new JButton("");
      panel4.add(placeholder);
      placeholder.setVisible(false);
      deposit = new JButton("DEPOSIT");
      panel4.add(deposit);
      deposit.addActionListener(this);
      checkBalance = new JButton("ACCOUNT");
      checkBalance.addActionListener(this);
      panel5.add(checkBalance);
      transfer = new JButton("TRANSFER");
      panel5.add(transfer);
      transfer.addActionListener(this);
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
      
      display.setText("\n\n\tThe CS Bank of Dalhousie. \n\tPlease choose from the options.");
      display.setFont(new Font("Arial", Font.BOLD,20));
      display.setVisible(true);
      setTitle("ATM2");
      setSize(750,500);
      setLocationRelativeTo(null);
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      setVisible(true);
      setResizable(false);
      panel1.setBackground(Color.GRAY);
      panel4.setBackground(Color.GRAY);
      panel5.setBackground(Color.GRAY);
      panel3.setBackground(Color.GRAY);
   }
   
   public void actionPerformed(ActionEvent e)
   {
	   //displays user information
	   if (e.getSource() == checkBalance)
	   {
		   display.setText(user.toString());
		   display.setFont(new Font("Arial", Font.BOLD,20));
		   display.setVisible(true);
	   }
	   
	   //goes to withdraw with a checker
	   if (e.getSource() == withdraw)
	   {
		   wcheck = true;
		   dcheck = false;
		   super.dispose();
		   optionGUI sim = new optionGUI(user, wcheck, dcheck);
    	  	
	   }
      
	   //goes to deposit with a checker
	   if (e.getSource() == deposit)
	   {
		   wcheck = false;
		   dcheck = true;
		   super.dispose();
		   optionGUI sim = new optionGUI(user, wcheck, dcheck);
	   }
        
	   
	   //goes back to login when user is done
	   if(e.getSource() == signout)
	   {
		   super.dispose();
		   Login sim = new Login();
	   }
      
	   
	   //transfer funds from C2S/S2C
	   if (e.getSource() == transfer)
	   {
		   super.dispose();
		   transferGUI sim = new transferGUI(user);
	   }
   }

   public static void main(String[] args)
   {
      ATM2 sim = new ATM2(user);
   }
}
      
      
      