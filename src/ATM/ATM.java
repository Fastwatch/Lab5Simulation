package ATM;


public class ATM {
	
	private boolean receivedAcc = false;
	private boolean receivedPin = false;
	private boolean receivedOp = false;
	private Bank bank;
	Transaction transaction;
	
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
	
	/**
	 * This will call the corresponding actions to be executed, with each input being
	 * determined by the states that it is currently in. These states are:(Actor entering account number, actor entering pin number
	 * ,or actor entering the operation to be performed on the account)   
	 * 
	 * @param input determines what action will be executed, either it is the 
	 * account number, pin number, or the operation(withdrawal/deposit)
	 */
	public boolean execute(String input){
	
		if(receivedAcc == false){
			try{
				if(input.equals("exit")) return false;
				int an = Integer.parseInt(input);
				if(bank.validate(an) == null){
					System.out.println("Invalid account number.");
					reset();
					return false;
				}
				transaction = new Transaction(bank.validate(an));
				receivedAcc = true;
				return true;
			}
			catch(NumberFormatException e){
				System.out.println("Invalid account number. \nCannot format the input string '"+ input +"' to a int.");
				//e.printStackTrace();
				reset();
				
				return false;
			}			
		}
		else if(receivedPin == false){
			try{
				int pn = Integer.parseInt(input);
				
				if (transaction.getAcnt().validate(pn)==false){
					reset();
					System.out.println("Invalid Pin Number");
					return false;
				}
				receivedPin = true;
				return true;
			}
			catch(Exception e){
				e.getStackTrace();
				reset();
			}
		}else if(receivedOp == false){
			input = input.toUpperCase();
			if(input.length()==1){
				if((input.equals("W")||input.equals("D"))){
					transaction.setOp(input);
					receivedOp = true;
					return true;
				}
				System.out.println("Invalid operation");
				return false;
			}
			
		}else if(receivedOp == true && receivedAcc == true && receivedPin == true){
			try{
				double amt = Double.parseDouble(input);
				if(transaction.getOp().equals("W")){
					transaction.getAcnt().withdrawl(amt);
				}
				else{
					transaction.getAcnt().deposit(amt);
				}
				reset();
				return true;
			}
			catch(NumberFormatException e){
				//e.printStackTrace();
				System.out.println("Error formatting double value.");
				reset();
				return false;
			}			
			
			
		}
		reset();
		return false;
		
	}
	
}
