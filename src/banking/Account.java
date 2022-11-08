package banking;

import java.util.HashSet;
import java.io.Serializable;

public abstract class Account implements Serializable{
	
		public String accountNum; //계좌번호
		public String name; //고객이름
		public int balance; //잔고
		
		public Account(HashSet<Account> accHashSet) {
		
		}
		
		public Account(String accountNum, String name, int balance) {
			
			this.accountNum = accountNum;
			this.name = name;
			this.balance = balance;
		}
	
		public void showAccInfo() {
			System.out.println("-----✽계좌정보출력✽-----");
			System.out.println("계좌번호: "+ accountNum);
			System.out.println("고객이름: "+ name);
			System.out.println("잔고: "+ balance);
			System.out.println("︎---------------------");
		}
		public void deposit(int deposit) {
			
		}
		
		@Override
		public int hashCode() {
			return accountNum.hashCode();
			
		}
		
		@Override
		public boolean equals(Object obj) {
			
			Account account = (Account)obj;
			if(this.accountNum.equals(account.accountNum)) {
				return true;
			}
			else 
				
			return false;
			
		}
	}


