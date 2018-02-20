package SIM;

import ATM.ATM;
import SIM.FileInput;
import java.util.Scanner;

public class Simulator {
	private static ATM atm;
	
	public static void main(String args[]) {
		String input;
		Scanner in = new Scanner(System.in);
		atm = new ATM();
		atm.start();
		System.out.println("Welcome to the atm simulator..");
		
		boolean exit = false;
		
		do {
			System.out.print("Enter \"F\" for file input, \"C\" for console input or \"E\" for exit: ");
			input = in.nextLine();
			
			switch(input.toUpperCase()) {
				case "F":
					FileInput fn = new FileInput();
					fn.readFile(in, atm);
					break;
				case "C":
					// Console Input
					break;
				case "E":
					exit = true;
					break;
				default:
					System.out.println("Invalid Input, please enter a valid character");
			}
		
		} while (!exit);
		
		
		
		/*
		do{
			System.out.print("\nPlease enter an account number or \"exit\" to quit: ");
			input = in.nextLine();
			
			if(atm.execute(input) == false){
				continue;  //if the account number is incorrect, it will continue(restart) do loop
			} 
			
			System.out.print("\nplease enter the pin number: ");
			input = in.nextLine();
			
			if(atm.execute(input) == false){
				continue;	//if the pin number is incorrect, it will continue(restart) do loop
			} 
					
			boolean validAction = false;
			//loop through the state of withdrawal/deposit
				do {
					System.out.print("\nWithdraw(W) or Deposit(D): ");
						
					input = in.nextLine();
					if(atm.execute(input) == false){
						continue;
					}
					validAction = true;
					
				} while(!validAction);
					
					//loop action
				//if incorrect amount or transaction completed, it will restart
				boolean validAmount = false;
				do {
						
						System.out.print("\nplease enter the amount: ");
						input = in.nextLine();
						
						if(atm.execute(input) == false) {
							continue;					
						} 
						else 
							validAmount = true;
						
				} while (!validAmount);
				 
				
				//atm.execute(in.nextLine);
			

		} while( !input.equals("exit") );
		
		System.out.print("\nGoodbye..");
		
		
		*/
		
		System.out.println("Goodbye...");
		in.close();
	}
}
