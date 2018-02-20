package ATM;

import SIM.Simulator;


public class ATM {
	
	private boolean receivedAcc = false;
	private boolean receivedPin = false;
	private boolean receivedOp = false;
	private Bank bank;
	Transaction transaction;
	private Simulator sim;
	
	private class Transaction{
		private String op;
		private Account acnt;

		public Transaction(Account a){
			acnt = a;
		}
		
		public String getOp() {
			return op;
		}
		public void setOp(String op) {
			this.op = op;
		}

		public Account getAcnt() {
			return acnt;
		}

	}
	
	public ATM() {
		// init
	}

	/**
	 *  Begins the simulator
	 */
	public void start() {
		bank = new Bank();
		// run through atm functionality
	}
	
	/**
	 * Resets all information that was collected from the transaction
	 */
	private void reset(){
		receivedAcc = false;
		receivedPin = false;
		receivedOp = false;
		transaction = null;
	}
	
	public void execute(String str){
		String[] args = str.split(" ");
		
		switch(args[1].toUpperCase()){
			case "CARDREAD":
				cardRead(args[0], Integer.parseInt(args[2]));
				break;
			case "BUTTON":
				button(args[0], args[2]);
				break;
			case "NUM":
				num(args[0], Integer.parseInt(args[2]));
				break;
			default:
				sim.execute("DISP Invalid Command");
		}
				
	}
	
	public void cardRead(String time, int accNum){
		if(receivedAcc == false){
			transaction = new Transaction(bank.validate(accNum));
			if(transaction.acnt != null){
				receivedAcc = true;
			}else{
				System.out.println("Invalid Card Number");
			}
		}else{
			System.out.println("Unexpected Command");
		}
	}
	
	public void num(String time, int num){
		if(receivedPin == false){
			if(transaction.acnt.validate(num) == false){
				System.out.println("Invalid pin");
			}
			else
				receivedPin = true;
		}
		else if(receivedOp == true){
			if(transaction.op.equals("W")){
				if(transaction.acnt.withdraw(num) == false){
					System.out.println("Insufficient funds.");
				}else{
					print(time + " W $" + num);
				}
				receivedOp = false;
				transaction.op = "";
			}else{
				System.out.println("Invalid operation");
			}
		}else{
			System.out.println("Unexpected command.");
		}
	}
	
	public void button(String time, String name){
		if(name.equalsIgnoreCase("cancel")){
			if(receivedAcc == true){
				System.out.println("CARD EJECTED");
			}
			reset();
		}else if(receivedPin == true && receivedOp == false){
			if(name.equalsIgnoreCase("w")){
				receivedOp = true;
				transaction.op = "W";
			}else if(name.equalsIgnoreCase("CB")){
				print(time+" CB "+transaction.acnt.getBalance());
			}else{
				System.out.println("Invalid Button");
			}
		}else{
			System.out.println("Unexpected Command");
		}
	}

	private void print(String string) {
		System.out.println(string);
		
	}
	
	public void setSim(Simulator s){
		this.sim = s;
	}
}
