package TEST;
import static org.junit.Assert.*;
import ATM.ATM;
import ATM.Account;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;


@SuppressWarnings("unused")
public class ATMTest {
	
	Account ac1, ac2;
	
	private static ATM atm;
	
	//test account
	
	private void setUpAccounts() {
		ac1= new Account(1234, 6789, 80.0);
		//ac2= new Account(6789, 4321, 60.0);	
	}
	
	private void setUpATM() {
		atm = new ATM();
		atm.start();
	}
	
	@Test
	public void testAccountDeposit () {
		setUpAccounts();
		assertEquals("incorrect balance", 80.0, ac1.getBalance(), .001);
		ac1.deposit(10.0);
		assertEquals("incorrect balance", 90.0, ac1.getBalance(), .001);
		ac1.deposit(-10.0);
		assertEquals("incorrect balance", 90.0, ac1.getBalance(), .001);
	}
	
	@Test
	public void testAccountWithdrawal () {
		setUpAccounts();
		assertEquals("incorrect balance", 80.0, ac1.getBalance(), .001);
		ac1.withdraw(10.0);
		assertEquals("incorrect balance", 70.0, ac1.getBalance(), .001);
		ac1.withdraw(-10.0);
		assertEquals("incorrect balance", 70.0, ac1.getBalance(), .001);
		ac1.withdraw(1000.0);
		assertEquals("incorrect balance", 70.0, ac1.getBalance(), .001);	
	}
	
	@Test
	public void testAtm() {
		setUpATM();
		//act number
		assertEquals("accepted invalid input", false, atm.execute("1111"));
		assertEquals("did not accept valid input", true, atm.execute("1234"));
		//invalid pin
		assertEquals("accepted invalid input", false, atm.execute("1111"));
		
		//valid act number and pin
		assertEquals("did not accept valid input", true, atm.execute("1234"));
		assertEquals("did not accept valid input", true, atm.execute("6789"));
		
		// invalid input
		assertEquals("accepted invalid input", false, atm.execute("F"));
		// valid input
		assertEquals("did not accept valid input", true, atm.execute("W"));
		assertEquals("accepted amount", true, atm.execute("20"));
		
		//valid act number and pin
		assertEquals("did not accept valid input", true, atm.execute("1234"));
		assertEquals("did not accept valid input", true, atm.execute("6789"));
		assertEquals("did not accept valid input", true, atm.execute("W"));
		assertEquals("did not accept valid input", true, atm.execute("20"));
		
		
		
	}

}
