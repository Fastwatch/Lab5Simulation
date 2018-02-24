package SIM;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import ATM.ATM;

/**
 * @author Riley Mahr rwmahr@gmail.com
 */
public class FileInput {
	
	public ATM atm;
	public Scanner in;
	
	
	/**
	 * Base class to read in input from a file
	 * @param in Scanner object passed in from main simulator
	 */
	public void readFile(Scanner in, ATM atm) {
		String fileName;
		File file = null;
		
		
			System.out.print("\nPlease enter a file path:");
			fileName = in.nextLine();
			
			file = new File(fileName);
			
			
			
			if(!file.exists() || !file.isFile()) {
				System.out.println("File does not exist");
				return;
			}
			
			try (BufferedReader br = new BufferedReader(new FileReader(fileName));){
				
				String line;
				
				while ((line = br.readLine()) != null) {
					System.out.println(line);
					atm.execute(line);					
				}
			} catch (IOException ex) {
				System.out.println("error occured in retrieving file");
			}				
		
		
	}
	
}
