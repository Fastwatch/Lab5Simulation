package ATM;

public class Bank {
	private Account[] accounts;
	private int numAccounts;
	
	public Bank(){
		accounts = new Account[10];
		numAccounts = 2;
		accounts[0]= new Account(1234, 6789, 80.0);
		accounts[1]= new Account(6789, 4321, 60.0);	
	}
	
	public boolean addAccount(int an, int pn, double bal){
			return true;
	}
	
	
}