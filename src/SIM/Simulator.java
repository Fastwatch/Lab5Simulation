package SIM;
import ATM.ATM;
import java.util.Scanner;

public class Simulator {
	private static ATM atm;
	
	
	public static void main(String args[]) {
		String input;
		Scanner in = new Scanner(System.in);
		atm = new ATM();
		atm.start();
		System.out.println("Welcome to the atm simulator..");
		do{
			System.out.print("\nplease enter an account number or \"exit\" to quit: ");
			input = in.nextLine();
			if (input.equals("1234") && !input.equals("exit")) { //atm.execute(input)) {
				System.out.print("\nplease enter the pin number: ");
				
				if(in.nextLine().equals("6789")) {
					boolean validAction = false;
					do {
						System.out.print("\nWithdraw(W) or Deposit(D): ");
						
						input = in.nextLine();
						if(input.equals("W") || input.equals("D") ) {
							//valid atm prints out string
							validAction = true;
							
							//loop amount
							boolean validAmount = false;
							do {
								System.out.print("\nplease enter the amount: ");
							
								if(in.nextLine().equals("20")) {
									validAmount = true;
									
								} else {
									System.out.println("ya broke");
								}
							} while (!validAmount);
							
							
						} else {
							System.out.print("\nwrong answer");
						}
					
					} while(!validAction);
					
					//loop action
				
				} else {
					System.out.println("/nIncorrect pin");
				}
				
				//atm.execute(in.nextLine);
			} else if (!input.equals("exit")) {
				//no account
				System.out.println("\nThat account doesn't exist");
			}

		} while( !input.equals("exit") );
		
		System.out.print("\nGoodbye..");
		
		in.close();
		
	}
}
