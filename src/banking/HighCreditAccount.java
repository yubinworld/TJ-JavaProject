package banking;

public class HighCreditAccount extends NormalAccount{
	
	public int rate; //이율 
	public String grade; // 신용등급
	
	public HighCreditAccount (String accountNum, String name, int balance, int rate, String grade ) {
		
		super(accountNum, name, balance);
		
		this.rate = rate;
		this.grade= grade;
	}
	
	@Override
	public void deposit(int deposit) {
		
		if(grade.equals("A"))
			balance = balance + (balance *(rate+7)/100) + deposit;
		
		else if(grade.equals("B"))
			balance = balance + (balance *(rate+4)/100)+ deposit;
		
		else if(grade.equals("C"))
			balance = balance + (balance *(rate+2)/100) + deposit;
	}	

	@Override
	public void showAccInfo() {
		System.out.println("***계좌정보출력***");
		System.out.println("------------------");
		System.out.println("계좌번호> "+ accountNum);
		System.out.println("고객이름> "+ name);
		System.out.println("잔고> "+ balance);
		System.out.println("기본이자> "+ rate+"%");
		System.out.println("신용등급> " + grade);
		System.out.println("------------------");
		
	
	}
}
