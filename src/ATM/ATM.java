package ATM;
import java.util.Scanner;

public class ATM {
	
	boolean receivedAcc = false;
	boolean receivedPin = false;
	boolean receivedOp = false;
	Bank bank;
	Transaction transaction;
	
	private class Transaction{
		private int an;
		private String op;
		private double amnt;
		private Account acnt;
		
		public int getAn() {
			return an;
		}
		public void setAn(int an) {
			this.an = an;
		}
		public String getOp() {
			return op;
		}
		public void setOp(String op) {
			this.op = op;
		}
		public double getAmnt() {
			return amnt;
		}
		public void setAmnt(double amnt) {
			this.amnt = amnt;
		}
		public Account getAcnt() {
			return acnt;
		}
		public void setAcnt(Account acnt) {
			this.acnt = acnt;
		}
		
	}
	
	public ATM() {
		// init
	}
	
	public void start() {
		bank = new Bank();
		// run through atm functionality
	}
	
	public void execute(String input){
		
		if(receivedAcc == false){
			try{
				int an = Integer.parseInt(input);
				transaction = new Transaction();
				transaction.setAcnt(bank.validate(an));
				if (transaction.getAcnt()==null){
					System.out.println("Invalid Account Number");
					transaction = null;
				}
				receivedAcc = true;
				
				transaction.setAn(an);
			}
			catch(Exception e){
				e.getStackTrace();
				transaction = null;
			}			
		}
		else if(receivedPin == false){
			try{
				int pn = Integer.parseInt(input);
				if (transaction.getAcnt().validate(pn)==false){
					System.out.println("Invalid Pin Number");
					transaction = null;
					receivedAcc=false;
				}
				receivedPin = true;
			}
			catch(Exception e){
				e.getStackTrace();
				transaction = null;
				receivedAcc=false;
			}
		}else if(receivedOp == false){
			input = input.toUpperCase();
			if(input.length()!=1||(input.charAt(0)!='W'||input.charAt(0)!='D')){
				System.out.println("Invalid operation");
				transaction = null;
				receivedAcc=false;
				receivedPin=false;
			}
			transaction.setOp(input);
			receivedOp=true;
		}else{
			
		}
		
	}
	
}
