package SIM;
import ATM.ATM;

public class Simulator {
	private static ATM atm;
	
	public static void main(String args[]) {
		atm = new ATM();
		atm.start();
	}
}
