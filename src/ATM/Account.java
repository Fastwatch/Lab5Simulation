package ATM;

public class Account {
	int accountNumber, pinCode;
	double balance;

	public Account(int an, int pn, double balance){
		this.accountNumber = an;
		this.pinCode = pn;
		this.balance = balance;
	}
	
	public boolean validate(int pin){
		return pin==this.pinCode;
	}
	
}
