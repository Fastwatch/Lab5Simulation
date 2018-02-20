package SIM;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Scanner;

import ATM.ATM;

/**
 * @author Andrew Krill atkrill@uwm.edu
 */

public class ConsoleInput {

	public static void main(String args[]) {
		boolean isRunning = true;
		Scanner scanner = new Scanner(System.in);
		String input;
		
		while (isRunning) {
			input = scanner.next();
			String timeStamp = getTime();
			System.out.println(input);
			System.out.println(timeStamp);

		}
				
	}
	
	private static String getTime() {
		//Time Formated as HH:MM:SS
		//That is, Hour, Min, Sec 
		String timeStamp = new SimpleDateFormat("HH:mm:ss").format(Calendar.getInstance().getTime());
		return timeStamp;
	}
	
}
 