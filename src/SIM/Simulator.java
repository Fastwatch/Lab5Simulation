package SIM;

import ATM.ATM;
import SIM.FileInput;
import java.util.Scanner;

public class Simulator {
	private ATM atm;
	
	public void start() {
		String input;
		Scanner in = new Scanner(System.in);
		atm = new ATM();
		atm.setSim(this);
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
		
		System.out.println("Goodbye...");
		in.close();
	}
	
	public void execute(String input) {
		String cmd = input.substring(0, input.indexOf(" "));
		String text = input.substring(input.indexOf(" "));
		switch(cmd.toUpperCase()) {
		case "DIS":
			System.out.println(text);
			break;
		case "PRINT":
			System.out.println(text);
			break;
		default:
			System.out.println("Print command could not be understood");
	}
	}
}
