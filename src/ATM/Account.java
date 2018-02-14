package ATM;

public class Account {
	private int accountNumber, pinCode;
	private double balance;
//
	public Account(int an, int pn, double balance){
		this.setAccountNumber(an);
		this.pinCode = pn;
		this.balance = balance;
	}
	
	/**
	 * Checks if the pin input matches the account number's pin
	 * 
	 * @param pin account pin number
	 * @return true if the pin matches the account pin number
	 */
	public boolean validate(int pin){
		return pin==this.pinCode;
	}
	
	/**
	 * Withdraw money by the given amt, but if the amt is incorrect it will return false and not
	 * do anything, if it is negative it will simply throw an exception
	 * 
	 * @param amt amount to be withdraw from account
	 * @return true if money can be withdraw
	 */
	public boolean withdrawl(double amt){
		if(amt < 0) throw new IllegalArgumentException("Cannot withdraw negative money");
		if(amt > balance){
			System.out.println("Unable to process withdrawal, your balance is: " + balance);
			return false;
		} 
		
		balance = balance - amt;
		System.out.println("Successful withdrawal of $"+ amt + " from account number: " + accountNumber);
		return true;
	}
	
	/**
	 * Deposit money to the account
	 * 
	 * @param amt amount to be deposit to the account
	 */
	public void deposit(double amt){
		if(amt < 0) throw new IllegalArgumentException("Cannot deposit negative money");
		balance = balance + amt;
		
		System.out.println("Successful deposit $"+ amt + " to account number: " + accountNumber);
		
	}

	public int getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(int accountNumber) {
		this.accountNumber = accountNumber;
	}
	
}
