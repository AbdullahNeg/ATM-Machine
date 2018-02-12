
public class BankFeature {
	private static double twenty;
	private static double fifty;
	private static double hundred;
	private static double other;
	private static BankInfo account;
	
	public BankFeature(BankInfo u){
		account = u;
		twenty = 20.00;
		fifty = 50.00;
		hundred = 100.00;
		other = 0.0;
	}
	
	public void setOther(String input){
		other = Double.parseDouble(input);
	}
	
	public double getTwenty(){
		return twenty;
	}
	
	public double getFifty(){
		return fifty;
	}
	
	public double getHundred(){
		return hundred;
	}
	
	public double getOther(){
		return other;
	}
	
	
	//Transferring funds from Chequing to savings
	public String transferC2S(double number){
		if(account.getChequing() < number)
			return "\n\n                 NOT ENOUGH BALANCE IN CHEQUING.";
		else{
			double moneyTaking = account.getChequing() - number;
			double moneyAdding = account.getSavings() + number;
			account.setChequing(moneyTaking);	
			account.setSavings(moneyAdding);
         return "\n\tTransfer complete!" + account.toString();
		}
	}
	
	//transferring funds from saving to chequing
	public String transferS2C(double number){
		if(account.getSavings() < number)
			return "\n\n                 NOT ENOUGH BALANCE IN SAVINGS.";
		else{
			double moneyTaking = account.getSavings() - number;
			double moneyAdding = account.getChequing() + number;
			account.setChequing(moneyAdding);
			account.setSavings(moneyTaking);
         return "\n\tTransfer complete!" + account.toString();
		}
	}
	
	//withdraw funds from chequing balance
	public static String WithdrawFROMC(double number){
		if(number > account.getChequing())
			return "\n\n\tNOT ENOUGH FUNDS";
		else{
			double moneyTaking = account.getChequing() - number;
			account.setChequing(moneyTaking);
			return "\n\n\tTRANSACTION COMPLETE!";
		}
	}
	
	//deposit funds into chequing balance
	public String DepositTOC(double number){
		double moneyAdding = account.getChequing() + number;
		account.setChequing(moneyAdding);
		return "\n\n\tTRANSACTION COMPLETE!";
		
	}
	
	//withdraw funds from savings balance
	public String WithdrawFROMS(double number){
		if(number > account.getSavings())
			return "\n\n\tNOT ENOUGH BALANCE IN SAVINGS.";
		else{
			double moneyTaking = account.getSavings() - number;
			account.setSavings(moneyTaking);
			return "\n\n\tTRANSACTION COMPLETE!";
		}
	}
	
	//deposit funds into savings
	public String DepositTOS(double number){
		double moneyAdding = account.getSavings() + number;
		account.setSavings(moneyAdding);
		return "\n\n\tTRANSACTION COMPLETE!";
	}
	
	public String TransferFunds(double number, BankInfo acc){
		return "\n\n\tTRANSACTION COMPLETE!";
	}
}
