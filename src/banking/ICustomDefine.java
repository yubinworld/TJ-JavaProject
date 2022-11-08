package banking;

public interface ICustomDefine {

	int MAKE =1;
	int DEPOSIT =2;
	int WITHDRAW = 3;
	int INQUIRE = 4;
	int AUTOSAVE = 5;
	int EXIT = 6;
	
	public static void showMenu() {
		System.out.println("---------✽Menu✽--------");
		System.out.println("1.계좌개설");
		System.out.println("2.입    금");
		System.out.println("3.출    금");
		System.out.println("4.계좌정보출력");
		System.out.println("5.저장 옵션");
		System.out.println("6.프로그램종료");

	}
}
