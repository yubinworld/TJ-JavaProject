package shopping;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Scanner;


/*
 ▶ 상품입력
PreparedStatement객체를 사용하여 작성한다.
클래스명 : InsertShop
상품명, 상품가격, 상품코드를 scanValue() 메소드로 입력받아 사용한다. 
입력이 완료되면 입력된 행의 갯수를 반환하여 출력한다.
 */

public class InsertShop extends IConnectImpl{
	
	PreparedStatement psmt;
	
	public InsertShop() {
		super(ORACLE_DRIVER, "eucation", "1234");
	}

	//쿼리 실행을 위한 멤버메서드
	@Override
	public void execute() {
		try {
			//1.쿼리문 준비 : 값의 세팅이 필요한 부분을 ?(인파라미터)로 기술한다.
			String query = "INSERT INTO member VALUES (?, ?, ?)";
			
			//2.prepared객체생성 : 객체 생성시 준비한 쿼리문을 인수로 전달한다.
			psmt = con.prepareStatement(query);
			
			//3.사용자로부터 insert할 내용을 입력받는다. 
			Scanner scan = new Scanner(System.in);
			System.out.print("상품명:" );
			String proName = scan.nextLine();
			System.out.print("상품가격:" );
			int price = scan.nextInt();
			System.out.print("상품코드:" );
			String code = scan.nextLine();			
			
			/*
			4.인파라미터 설정 : ?의 순서대로 설정하고, 인덱스는 1부터 
				시작한다. 
				자료형이 
					숫자면 setInt()
					문자면 setString()
					날짜면 setDate()
				입력값이 문자 혹은 날짜면 자동으로 싱글쿼테이션이 추가된다.
			 */
			psmt.setString(1, proName);
			psmt.setInt(2, price);
			psmt.setString(3, code);
			
			//날짜입력1 : 날짜를 문자열로 입력하는 경우
//			psmt.setString(4, "2022-10-27");
			
			//날짜입력2 : Date형으로 입력하는 경우
			/*
			현재날짜를 Java에서 입력하는 경우 아래와 같이 변환과정을
			거쳐야한다. util패키지로 시간을 얻어온 후 sql패키지로 타입을
			변환한다. 이때는 date형으로 입력되므로 setDate()로 인파라미터를
			설정해야한다. 
			*/
			java.util.Date utilDate = new java.util.Date();
			java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
			psmt.setDate(4, sqlDate);			
			 
			//5.쿼리실행 및 결과값 반환
			int affected = psmt.executeUpdate();
			System.out.println(affected +"행이 입력되었습니다.");
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			close();
		}
	}

	public static void main(String[] args) {
		new InsertShop().execute();
	}
	
}
/*
 
 create table
 acc_index number primaryKey,
 acc_id varchar2(100),
 cust_name varchar2(50) not null,
 acc_money number not null);
 
 desc banking_
 drop table 
 
 
 
 
 
 --시퀀스 생성
 create sequence
 
 
 
 
 commit;
 
 
 
 */
























