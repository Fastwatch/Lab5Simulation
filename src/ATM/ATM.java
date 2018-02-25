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
		
		switch(args[1].toUpperCase()){
			case "CARDREAD":
				try{
					return cardRead(args[0], Integer.parseInt(args[2]));
				}catch(NumberFormatException e){
					System.out.println("String cannot be formatted to a number.");
					return false;
				}
			case "BUTTON":
				return button(args[0], args[2]);
				
			case "NUM":
				return num(args[0], Integer.parseInt(args[2]));
				
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
				sendToSim("DIS Enter Pin");
				return true;
			}else{
				sendToSim("DIS Invalid Card Number");
				return false;
			}
		}else{
			sendToSim("DIS Unexpected Command");
			return false;
		}
	}
	
	public boolean num(String time, int num){
		if(receivedPin == false){
			if(transaction.acnt.validate(num) == false){
				sendToSim("DIS Invalid pin");
				return false;
			}
			else {
				receivedPin = true;
				sendToSim("DIS Choose Transaction");
				return true;
			}
		}
		else if(receivedOp == true){
			if(transaction.op.equals("W")){
				if(transaction.acnt.withdraw(num) == false){
					sendToSim("DIS Insufficient funds.");
					return false;
				}else{
					sendToSim("DIS " + time + " W $" + num);
					sendToSim("DIS Choose Transaction");
				}
				receivedOp = false;
				transaction.op = "";
				return true;
			}else{
				sendToSim("DIS Invalid operation");
				return false;
			}
		}else{
			sendToSim("DIS Unexpected command.");
			return false;
		}
	}
	
	public boolean button(String time, String name){
		if(name.equalsIgnoreCase("cancel")){
			if(receivedAcc == true){
				sendToSim("DIS EJECT CARD");
				reset();
				return true;
			}
			reset();
		}else if(receivedPin == true && receivedOp == false){
			if(name.equalsIgnoreCase("w")){
				receivedOp = true;
				transaction.op = "W";
				sendToSim("DIS Amount?");
				return true;
			}else if(name.equalsIgnoreCase("CB")){
				sendToSim("DIS " + time+" CB $"+transaction.acnt.getBalance());
				sendToSim("DIS Choose Transaction");
				return true;
			}else{
				sendToSim("DIS Invalid Button");
				return false;
			}
		}else if(name.equalsIgnoreCase("CB")){
			sendToSim("DIS " + time+" CB $"+transaction.acnt.getBalance());
			sendToSim("DIS Choose Transaction");
			return true;
		}else{
			sendToSim("DIS Unexpected Command.");
			return false;
		}
		return false;
	}

	private void sendToSim(String string) {
		sim.execute(string);
	}
	
	public void setSim(Simulator s){
		this.sim = s;
	}
}
