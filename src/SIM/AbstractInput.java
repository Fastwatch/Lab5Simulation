package SIM;

import ATM.ATM;;

public class AbstractInput {
	
	public ATM atm;
	
	public void setAtm(ATM atm) {
		this.atm = atm;
	}
	
	public void executeCommand(String cmd, String time, String inp) {
		// always pass method(time, inp) in that order
		switch(cmd.toUpperCase()) {
			case "CARDREAD":
				// something
				break;
			case "NUM":
				//something
				break;
			case "DIS":
				// text sent to display
				break;
			case "PRINT":
				break;
			case "BUTTON":
				break;
			default:
				System.out.println("Command could not be understood");
		}
		
	}
}
