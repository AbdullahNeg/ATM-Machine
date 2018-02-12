import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JTextField;

public class BankInfo {
	private double chequing;
	private double savings;
	private String name;
	private String PIN; 
	private String userID;
   
   public BankInfo(String string, String u, String string2, double c, double s)
   {
      name = string;
      userID = u;
      PIN = string2;
      chequing = c;
      savings = s;
   }
	
	public String getName(){	
		return name;
	}
 
	public void setChequing(double num){	
		chequing = num;	
	}
	
	public String getPIN(){
		return PIN;
	}
	
	public String getUserID(){
		return userID;
	}
	
	public double getChequing(){
		return chequing;
	}
	
	public void setSavings(double num){
		savings = num;
	}
	
	public double getSavings(){
		return savings;
	}
	
	public String toString(){
		return "\n\n\tName: " + name +
				"\n\n\tChequing Balance: $" + getChequing() +
				"\n\n\tSavings Balance: $" + savings;
	}
}
