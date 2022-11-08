package banking;

public class NormalAccount extends Account {
	
	public int rate; //이자
	
	public NormalAccount(String accountNum, String name, int balance) {
	
		super(accountNum, name, balance);
	}
	
	public NormalAccount(String accountID, String customName, int accMoney, int rate)
	{
		super(accountID, customName, accMoney);
		this.rate = rate;
	}

	@Override
	public void deposit(int deposit) {
		balance = balance + (balance * rate /100) + deposit;
	}
	
	@Override
	public void showAccInfo() {
		super.showAccInfo();
		System.out.println("기본이자> " + rate+"%");
	}
		
}