package ATM;

import SIM.Simulator;


public class ATM {
	//
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
		// ini
		sim = new Simulator();
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
	
	public boolean execute(String str){
		String[] args = str.split("\\s+");
		if(args.length >= 2 && args[1].equalsIgnoreCase("exit")) {
			reset(); 
			return true;
		}
		if(args.length <= 2 || args.length >= 4) {
			sim.execute("DIS Unexpected Command");
			return false;
		}
		switch(args[1].toUpperCase()){
			case "CARDREAD":
				try{
					if(receivedAcc == true) {
						sim.execute("DIS Cancelling previous transaction. Starting a new transaction for " + args[2]);
						reset();
					}
					return cardRead(args[0], Integer.parseInt(args[2]));
				}catch(NumberFormatException e){
					sim.execute("DIS Invalid card read, please re-enter the number correctly.");
					return false;
				}
			case "BUTTON":
				if(receivedAcc == false) {
					sim.execute("DIS Unexpected Command.");
					return false;
				}
				return button(args[0], args[2]);
				
			case "NUM":
				if(receivedAcc == false) {
					sim.execute("DIS Unexpected Command.");
					return false;
				}
				try {
					return num(args[0], Integer.parseInt(args[2]));
				}catch(NumberFormatException e){
					sim.execute("DIS Invalid entry, please re-enter the number correctly.");
					return false;
				}
				
			default:
				sim.execute("DIS Invalid Command");
				return false;
		}
				
	}
	
	public boolean cardRead(String time, int accNum){
		if(receivedAcc == false){
			transaction = new Transaction(bank.validate(accNum));
			if(transaction.acnt != null){
				receivedAcc = true;
				sim.execute("DIS Enter Pin");
				return true;
			}else{
				sim.execute("DIS Invalid Card Number");
				return false;
			}
		}else{
			sim.execute("DIS Unexpected Command");
			return false;
		}
	}
	
	public boolean num(String time, int num){
		if(receivedPin == false){
			if(transaction.acnt.validate(num) == false){
				sim.execute("DIS Invalid pin");
				return false;
			}
			else {
				receivedPin = true;
				sim.execute("DIS Choose Transaction");
				return true;
			}
		}
		else if(receivedOp == true){
			if(transaction.op.equals("W")){
				if(transaction.acnt.withdraw(num) == false){
					sim.execute("DIS Insufficient funds.");
					return false;
				}else{
					sim.execute("DIS " + time + " W $" + num);
					sim.execute("DIS Choose Transaction");
				}
				receivedOp = false;
				transaction.op = "";
				return true;
			}else{
				sim.execute("DIS Invalid operation");
				return false;
			}
		}else{
			sim.execute("DIS Unexpected command.");
			return false;
		}
	}
	
	public boolean button(String time, String name){
		if(name.equalsIgnoreCase("cancel")){
			if(receivedAcc == true){
				sim.execute("DIS EJECT CARD");
				reset();
				return true;
			}
			reset();
		}else if(receivedPin == true && receivedOp == false){
			if(name.equalsIgnoreCase("w")){
				receivedOp = true;
				transaction.op = "W";
				sim.execute("DIS Amount?");
				return true;
			}else if(name.equalsIgnoreCase("CB")){
				sim.execute("DIS " + time+" CB $"+transaction.acnt.getBalance());
				sim.execute("DIS Choose Transaction");
				return true;
			}else{
				sim.execute("DIS Invalid Button");
				return false;
			}
		}else if(name.equalsIgnoreCase("CB")){
			sim.execute("DIS " + time+" CB $"+transaction.acnt.getBalance());
			sim.execute("DIS Choose Transaction");
			return true;
		}else{
			sim.execute("DIS Unexpected Command.");
			return false;
		}
		return false;
	}

	
	public void setSim(Simulator s){
		this.sim = s;
	}
}
