import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
//FINSIHED
public class Login extends JFrame implements ActionListener {
	private JPanel panel1;
	private JPanel panel2;
	private JButton log, exit, create;
	private JButton[] keypad;
	private JLabel status, loginID, password;
	private JTextField user;
	private JPasswordField pass;
	private JLabel wrong;
	private String passINPUT;
	private static ArrayList<BankInfo> account = new ArrayList<BankInfo>(5);
	private static BankInfo bankuser;
	private boolean found = false;
	
	
	public Login(){
		passINPUT = "";
		//account informations in ArrayList
		account.add(new BankInfo("Matt", "4500500045005000", "0000", 0, 0));
		account.add(new BankInfo("Abdullah", "4500500145005001", "1111", 4000, 520));
		account.add(new BankInfo("Megan", "4500500245005002", "1212", 500, 700));
		account.add(new BankInfo("Curtis", "4500500345005003", "0987", 2500, 100));
		
		
		//panel
		panel1 = new JPanel();//above keypad
		panel2 = new JPanel(new GridLayout(4,3));//keypad
		
		//initialize keypad array with 13 capacity
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
		
		//JButton login and quit
		log = new JButton("LOGIN");
		log.addActionListener(this);
		log.setBackground(Color.GRAY);
		exit = new JButton("QUIT");
		exit.addActionListener(this);
		create = new JButton("CREATE NEW ACCOUNT");
		create.addActionListener(this);
		
		//JLabel for card# and PIN #
		status = new JLabel("Please enter your userID and password");
		loginID = new JLabel("Login:");
		password = new JLabel("Password:");
		wrong = new JLabel("INCORRECT LOGIN. TRY AGAIN!");
		wrong.setForeground(Color.RED);
		
		//JTextField for user card#
		user = new JTextField(15);
		//JPasswordField for PIN# to get bullet pointed
		pass = new JPasswordField(13);
		pass.setEditable(false);
		
		
		//adding to panel
		panel1.add(status);
		panel1.add(loginID);
		panel1.add(user);
		panel1.add(password);
		panel1.add(pass);
		panel1.add(wrong);
		wrong.setVisible(false);
		panel1.add(log);
		panel1.add(exit);
		panel1.add(create);
		add(panel1);
		add(panel2, BorderLayout.SOUTH);
		panel2.setPreferredSize(new Dimension(400, 150));
		
		//creating GUI
		setTitle("LOGIN");
		setSize(250, 350);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setVisible(true);
		panel1.setBackground(Color.GRAY);
		panel2.setBackground(Color.GRAY);
		
	}

	
	public void actionPerformed(ActionEvent e) {
		

		for (int i = 0; i < 10; i++)
	       {
			  //takes user input from keypad and sets in pass field
	          if (e.getSource() == keypad[i])
	          {
	             //Edit the input display
	             passINPUT = passINPUT + i;
	             pass.setText(passINPUT);
	          }
	       }
		
		
		//keypad[11] "CLEAR" clears passINPUT
		if(e.getSource() == keypad[11])
		{
	    	passINPUT = "";
	    	pass.setText(passINPUT);       
		}
		
		
		//If button keypad[12] "OK" was pressed. 
		//It would trigger login button as well.
		if(e.getSource() == keypad[12])
		{ 
			log.doClick();	
		}
		
		
		//before login, system checks if card# and PIN# are correct
		if(e.getSource() == log)
		{
			String inputID = user.getText();
			String inputPass = pass.getText();
         for (int i = 0; i < account.size(); i++)
         {
        	   //if correct the program will move on and open homepage
			   if(inputPass.equals(account.get(i).getPIN()) && inputID.equals(account.get(i).getUserID()))
			   {
				   super.dispose();
				   bankuser = account.get(i);
				   ATM2 sim = new ATM2(bankuser);
				   found = true;
				   break;
			   }
         }
         	//if wrong the program will display an error message
         	if (found == false)
         	{
         		wrong.setVisible(true);
         	}
         
		}
		
		if(e.getSource() == create){
			createGUI sim = new createGUI();
		}
		
		//if exit/quit button pressed. it closes the program
		if(e.getSource() == exit){
			System.exit(0);
		}
	}
	
	public static void main(String[]args){
		Login sim = new Login();
	}
}
