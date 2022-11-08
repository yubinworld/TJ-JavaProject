package banking;//출력

import java.util.InputMismatchException;
import java.util.Scanner;


public class BankingSystemMain {
		
	public static void main(String[] args) {

	Scanner scan = new Scanner(System.in);	
	AccountManager account = new AccountManager();
	AutoSaver autoSaver = new AutoSaver(account);  //쓰레드 참조변수 선언
	
	
	while(true) {
		try {
			
			ICustomDefine.showMenu();
		
			int selectacc = scan.nextInt();
			if(selectacc<1 || selectacc>7) {
				MenuSelectException ms = new MenuSelectException();
				throw ms;
			}
		
		//사용자는 수행할 기능의 메뉴를 선택한다. 
		//선택한 번호에 따라 switch문으로 각 메서드를 호출한다.
			switch(selectacc) {
			case ICustomDefine.MAKE: 
				account.makeAccount();
				break;
			case ICustomDefine.DEPOSIT:
				account.depositMoney();
				break;
			case ICustomDefine.WITHDRAW:
				account.withdrawMoney();
				break;
			case ICustomDefine.INQUIRE:
				account.showAccInfo();
				break;
			case ICustomDefine.AUTOSAVE:
				
//				if((!autoSaver.isAlive())) {
//						autoSaver = new AutoSaver(account); 
//				}
				account.autoSave(autoSaver);
				break;
			case ICustomDefine.EXIT:
				account.saveAccInfo();
				System.out.println("프로그램을 종료합니다.");
				return;
				}
			}
		
			catch(InputMismatchException e) {
				scan.nextLine();
				System.out.println("올바른 메뉴 선택");
			}
			catch(NullPointerException e) {
				
			}	
			catch(MenuSelectException e) {
			System.out.println(e.getMessage());
			}
		}
	}	
}
