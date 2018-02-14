package ATM;

public class Bank {
	private Account[] accounts;
	private int numAccounts;
	//
	public Bank(){
		accounts = new Account[10];
		numAccounts = 2;
		accounts[0]= new Account(1234, 6789, 80.0);
		accounts[1]= new Account(6789, 4321, 60.0);	
	}
	
	/**
	 * Add a new account to the Bank
	 * 
	 * @param an account number 
	 * @param pn pin number of the account
	 * @param bal balance of the account
	 * @return true if account is added successfully, otherwise false.
	 */
	public boolean addAccount(int an, int pn, double bal){
		
		if(validate(an) == null)
			accounts[numAccounts] = new Account(an, pn, bal);
			
		numAccounts++;
			return true;
	}
	
	/**
	 * This will check the bank's account record and see if there an existing account
	 * with the given account number, it will return the account or null if 
	 * the account doesn't exist. 
	 * 
	 * @param an account number
	 * @return an existing account
	 */
	public Account validate(int an){
		for(int i = 0; i < numAccounts; i++){
			if(accounts[i].getAccountNumber() == an){
				return accounts[i];
			}
		}
		return null;
	}

	
	
}
