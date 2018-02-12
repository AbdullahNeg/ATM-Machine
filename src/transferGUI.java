import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class transferGUI extends JFrame implements ActionListener
{
   //Create the necessary GUI components
   private JPanel panel1, panel2, panel3, panel4, panel5, panel6, panel7;
   private JTextArea display;
   private JButton[] keypad;
   private JButton C2S, blank, S2C, logout, placeholder1, placeholder2;
   private static BankInfo account;
   private BankFeature feature;
   private boolean keypadcheck;
   //Strings for the client card number and PIN
   private String cardID, PIN, transferType;
  
   
   public transferGUI(BankInfo u)
   {
	   account = u;
	   feature = new BankFeature(account);

      //Set empty strings
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
      C2S = new JButton("Chequing to saving");
      panel6.add(C2S);
      C2S.addActionListener(this);
      placeholder1 = new JButton("");
      panel6.add(placeholder1);
      placeholder1.setVisible(false);
      blank = new JButton("HOME");
      blank.setVisible(true);
      blank.addActionListener(this);
      panel6.add(blank);
      S2C = new JButton("Saving to chequing");
      S2C.addActionListener(this);
      panel7.add(S2C);
      placeholder2 = new JButton("");
      panel7.add(placeholder2);
      placeholder2.setVisible(false);
      logout = new JButton("LOGOUT");
      panel7.add(logout);
      logout.addActionListener(this);
      
      
      //Add all the panels
      //panel2.add(panel3);
      panel2.add(panel4);
      //panel1.add(panel2);
      //panel1.add(panel5);
      add(panel6, BorderLayout.WEST);
      panel6.setVisible(true);
      add(panel7, BorderLayout.EAST);
      add(panel5);
      panel5.setPreferredSize(new Dimension(30,30));
      //add(panel7);
      //add(panel5);
      add(panel2,BorderLayout.SOUTH);
      panel2.setPreferredSize(new Dimension(400, 150));
      
      display.setText("\n\n\n\tPlease pick\n\tchequing to saving\n\tor saving to chequing.");
      display.setFont(new Font("Arial", Font.BOLD,16));
      display.setVisible(true);
      setTitle("TRANSFER");
      setSize(725,500);
      setLocationRelativeTo(null);
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      setVisible(true);
      setResizable(false);
      //MATT: ADDED PANEL BACKGROUND COLORS TO MATCH UP WITH ATM2 DESIGN
      panel2.setBackground(Color.GRAY);
      panel6.setBackground(Color.GRAY);
      panel7.setBackground(Color.GRAY);
      panel5.setBackground(Color.GRAY);
   }
   
   public void actionPerformed(ActionEvent e)
   {
      if (e.getSource() == logout){
         super.dispose();
         Login sim = new Login();
      }
      
      //Loop through the keypad array to see if any of the buttons were pressed 
	   for (int i = 0; i < 10; i++)
       {
          if (e.getSource() == keypad[i])
          {
             //Edit the input display
             cardID = cardID + i;
             display.setText("\n\n             Enter the amount you would like to transfer: \n\n\t                 " +cardID);
          }
       }
	   
      if (e.getSource() == keypad[11]){
         cardID = "";
         display.setText("\n\n             Enter the amount you would like to transfer: \n\n\t                 " +cardID);
      }
      
      if (e.getSource() == C2S)
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
         transferType = "C2S";
         cardID = "";
    	   display.setText("\n\n             Enter the amount you would like to transfer:");
    	   display.setFont(new Font("Arial", Font.BOLD, 18));
         display.setVisible(true);

      }
      
      if (e.getSource() == S2C)
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
         transferType = "S2C";
         cardID = "";
    	   display.setText("\n\n             Enter the amount you would like to transfer:");
         display.setFont(new Font("Arial", Font.BOLD, 18));
         display.setVisible(true);
      }
      

      if (e.getSource() == keypad[12]){
         double num = Double.parseDouble(cardID);
         
         if (transferType == null){ //check if user has chosen C2S or S2C
            display.setText("Please choose chequing to saving or saving to chequing.");
            display.setFont(new Font("Arial", Font.BOLD, 18));
            display.setVisible(true);
            cardID = "";
         }
         else if (transferType.equals("C2S")){
    	      display.setText(feature.transferC2S(num));
    	      display.setFont(new Font("Arial", Font.BOLD,18));
            display.setVisible(true); 
            cardID = "";
         }
         else if (transferType.equals("S2C")){
            display.setText(feature.transferS2C(num));
    	      display.setFont(new Font("Arial", Font.BOLD,18));
            display.setVisible(true); 
            cardID = "";
         }

        }
      
      if(e.getSource() == blank){
    	  super.dispose();
    	  ATM2 atm = new ATM2(account);
      }
      }

   public static void main(String[] args){
      transferGUI sim = new transferGUI(account);
   }
}