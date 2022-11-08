package banking;//나머지 함수 

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.Scanner;



public class AccountManager {
	
	Scanner scan = new Scanner(System.in);
	
	
	//계좌 정보 저장 hashSet
	HashSet<Account> accHashSet;
	
	public AccountManager() {
		
		//멤버변수가 컬렉셔녀으로 바뀜 hashSet<E> 생성
		accHashSet = new HashSet<Account>();
		
		//생성자, 복원파일
		readAccInfo();
		
	}
	//계좌선택
	public void makeAccount() {
		
		System.out.println("***신규계좌개설***");
		System.out.println("1.보통계좌");
		System.out.println("2.신용신뢰계좌");
		int selectacc = scan.nextInt();
		scan.nextLine();
		makeAccount(selectacc);
	
	}
	//개좌 생성
	public void makeAccount(int selectacc) {
		
		String accountNum, name, grade;
		int balance, rate;
		
		System.out.println("계좌번호: ");
		accountNum = scan.nextLine();
		System.out.println("고객이름: ");
		name = scan.nextLine();
		System.out.println("잔고: ");
		balance = scan.nextInt();
		
	// 1.보통계좌 개설
		if(selectacc == 1) {
		scan.nextLine();
		System.out.println("기본이자%(정수형태로입력): ");
		rate = scan.nextInt();
		scan.nextLine();
		
		//일반계좌 객체 생성, 컬렉션에 add, 인덱싱x
		NormalAccount normAcc = 
				new NormalAccount(accountNum, name, balance, rate);	
		
		boolean overCheck= accHashSet.add(normAcc);
		if(overCheck == false) {
			System.out.println("중복계좌발견됨. 덮어쓸까요?(y or n)");
			String openAccount = scan.nextLine();
			if(openAccount.equalsIgnoreCase("y")) {
				accHashSet.remove(normAcc);
				accHashSet.add(normAcc);
				System.out.println("계좌개설이 완료되었습니다.");
				
			}
			else if(openAccount.equalsIgnoreCase("n")) {
				System.out.println("계좌개설 종료");
			}
		}
	}
	//중복계좌 체크
	
	// 2.신용계좌 개설 (0)
	else if(selectacc == 2) {
			scan.nextLine();
			System.out.println("기본이자%(정수형태로입력): ");
			int rate1 = scan.nextInt();
			System.out.print("신용등급(A,B,C등급): ");
			String grade1 = scan.nextLine();
			scan.nextLine();
			HighCreditAccount highAcc = 
					new HighCreditAccount(accountNum, name, balance, rate1, grade1);
			
			//객체 생성과 동시에 컬렉션에 add하는 것도 가능.
		
		
			//중복계좌 체크
			boolean overCheck= accHashSet.add(highAcc);
			if(overCheck == false) {
				System.out.println("중복계좌발견됨. 덮어쓸까요?(y or n)");
				String openAccount = scan.nextLine();
				if(openAccount.equalsIgnoreCase("y")) {
					accHashSet.remove(highAcc);
					accHashSet.add(highAcc);
					System.out.println("계좌개설이 완료되었습니다.");
					
				}
				else if(openAccount.equalsIgnoreCase("n")) {
					System.out.println("계좌개설 종료");
					return;
				}
			}
		}
	}		

		//계좌 입금
		public void depositMoney() {
			try {
					scan.nextLine();
					
					System.out.println("***입   금***");
					System.out.println("계좌번호와 입금할 금액을 입력하세요.");
					System.out.println("계좌번호: ");
					String accountNum = scan.nextLine();
					System.out.println("입금액: ");
					int deposit = scan.nextInt();
					
					//입금액이 0원 이상일때 계좌보유여부 확인 후 입금처리
					if(deposit<0) {
						
					}
					if(deposit%500!=0) {
						
					}
					
					//이터레터로 계좌있는지 확인
					boolean findAccount = false;
					Iterator<Account> itr = accHashSet.iterator();
					
					while(itr.hasNext()) {
						Account account = itr.next();
						if(accountNum.compareTo(account.accountNum)==0) {
								account.deposit(deposit);
								System.out.println("입금이 완료되었습니다.");
								findAccount = true;
						}
				}	
				if(findAccount == false) 
					System.out.println("등록안된 계좌번호");
				
			}
			
				catch(InputMismatchException e) {
					scan.nextLine();
					System.out.println("문자는 입력할 수 없음");
				}
		}
		//계좌 출금 
		public void withdrawMoney() {
			try {
				
				boolean findAccount = false;
				
				scan.nextLine();
				System.out.println("계좌번호와 출금할 금액을 입력하세요.");
				System.out.println("출금은 1000원 단위로만 가능");
				System.out.println("계좌번호: ");
				String accountNum = scan.nextLine();
				System.out.println("출금액: ");
				int withdraw = scan.nextInt();
				
				//출금액은 0을 초과해야된다
				if(withdraw<0) {    
				//
				}
				//1000원 단위로만 출금할 수 있다.
				if(withdraw%1000!=0) {
				//
				}
				//이터레이터 사용, 계좌확인
				Iterator<Account> itr = accHashSet.iterator();
				while(itr.hasNext()) {
					Account account = itr.next();
					
					//잔액있으면 계좌출금
					if(accountNum.compareTo(account.accountNum)==0) {
							account.balance -= withdraw;
							System.out.println("출금이 완료되었습니다.");
							findAccount = true;
					}
					//잔액부족
					else {
						System.out.println("잔고가 부족합니다. 금액전체를 출금할까요?");
						System.out.println("YES : 금액전체 출금처리");
						System.out.println("NO : 출금요청취소");
						while(true) {
							String withdrawselect = scan.nextLine();
		
							if(withdrawselect.equalsIgnoreCase("yes")) {
								System.out.println("전체 금액 출력");
								account.balance -= account.balance;
								findAccount = true;
								break;
							}
							else if(withdrawselect.equalsIgnoreCase("no")) {
								System.out.println("출금 취소");
								findAccount = true;
								break;
								
							}
						}
					}
				}	
			
				
			if(findAccount == false) 
				System.out.println("등록안된 계좌번호");
		}
		catch(InputMismatchException e) {
			scan.nextLine();
			System.out.println("문자는 입력할 수 없음");
		}
	}	
		//계좌정보 출력 확장 for문
		public void showAccInfo() {
			for(Account acc : accHashSet) {
				acc.showAccInfo();
			}
			System.out.println("전체 정보가 출력됨");
		}
		
		//계좌번호직렬화 
		public void saveAccInfo() {
			try {
				
				ObjectOutputStream out =
						new ObjectOutputStream(
								new FileOutputStream("src/banking/AccountManager.obj"));
				
				for(Account acc : accHashSet) {
					out.writeObject(acc);
				}
				out.close();
			}
			catch (Exception e) {
				System.out.println("계좌 정보 파일 저장시 예외 발생");
			}
		}
		//복원(역직렬화)을 위한 스트림 생성
		public void readAccInfo() {
			try {
				ObjectInputStream in = 
						new ObjectInputStream(
							new FileInputStream("src/banking/AccountManager.obj"));
				
				//데이터 복원
				while(true) {
					Account acc = (Account)in.readObject();
					//읽어와서 다시 컬렉션에 저장
					accHashSet.add(acc);
					if(acc ==null) break;
				}
				in.close();
			}
			catch (Exception e) {
				System.out.println("모든 계좌를 불러왔습니다.");
			}
			System.out.println("계좌 정보 복원 완료");
		}
		
		
		//자동저장 on / off
		public void autoSave(AutoSaver autoThread) {
			System.out.println("***자동저장옵션선택***");
			System.out.println("1.자동저장on ");
			System.out.println("2.자동저장off ");
			System.out.println("----------------");
			int autoselec = scan.nextInt();
			scan.nextLine();
			
			//자동저장on
			if(autoselec == 1) {
				if(autoThread.isAlive()) {

					System.out.println("이미 자동저장이 실행중입니다.");
				}
				else {
					//독립쓰레드를 종속으로
//					autoThread.setDaemon(true);
					
					//스레드 실행
					autoThread.start();
					System.out.println("자동 저장을 실행합니다.");
				}
			}
			
			//자동저장off
			else if(autoselec ==2) {
				if(autoThread.isAlive()) {
					autoThread.interrupt();
					System.out.println("자동 저장이 중지되었습니다.");
				}
			}
		}
		
		
		public void autoSaveFile() {
			try
			{
				PrintWriter out = new PrintWriter(
						new FileWriter("src/banking/AutoSaveAccount.txt"));
				
				for(Account acc: accHashSet) {
					if(acc instanceof HighCreditAccount) {
						out.printf("계좌번호:%s, 예금주:%s, 잔고:%d, 이자:%d, 신용등급:%s",
								((HighCreditAccount)acc).accountNum, 
								((HighCreditAccount)acc).name,
								((HighCreditAccount)acc).balance,
								((HighCreditAccount)acc).rate,
								((HighCreditAccount)acc).grade);
						out.println();
					}
					else if(acc instanceof NormalAccount) {
						out.printf("계좌번호:%s, 고객이름:%s, 잔고:%d, 이자:%d",
								((NormalAccount)acc).accountNum, 
								((NormalAccount)acc).name,
								((NormalAccount)acc).balance,
								((NormalAccount)acc).rate);
						out.println();
					}
				}
				out.close();
				
			} 
			catch (IOException e) {
				e.printStackTrace();
			}
			
		}

	//예외/ 금액 단위가 올바르지 않은 경우
	class ErrMoneyException {
		public ErrMoneyException() {
			System.out.println("올바른 금액단위를 입력해주세요.");
		}
					
	}
}

