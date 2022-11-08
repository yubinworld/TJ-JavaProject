package shopping;

import java.sql.SQLException;

/*
 ▶ 상품수정 및 삭제
프로시저 작성후 CallableStatement객체를 사용하여 호출하도록 한다. 
상품수정
프로시저명 : ShopUpdateGoods
In파라미터 : 상품명, 가격, 제품코드, 수정할 상품의 일련번호
Out파라미터 : 레코드 수정 결과(1 혹은 0)
클래스명 : UpdateShop
 */
public class UpdateShop extends IConnectImpl{

		public UpdateShop() {
			super("education","1234");
		}
			@Override
			public void execute() 
			{
				//update 쿼리문 생성
				String sql = "UPDATE member "
						+ " SET name=?, pass=? "
						+ " WHERE id=?";
				try {
					//prepared객체 생성
					psmt = con.prepareStatement(sql);
					//사용자가 exit을 입력할때까지 계속 실행되는 구조로 구현함.
					while(true) {
						/*
						인파라미터값 설정시 인덱스만 일치하면 순서는 상관없다.
						scanValue() 메서드가 반환하는 값으로 즉시 인파라미터를
						세팅한다.  
						*/
						psmt.setString(3, scanValue("상품코드"));
						psmt.setString(1, scanValue("상품명"));
						psmt.setString(2, scanValue("상품가"));
						
						//쿼리문 실행 및 결과확인
						int affected = psmt.executeUpdate();
						System.out.println(affected +"행이 업데이트 되었습니다.");
					}
				}
				catch(SQLException e) {
					e.printStackTrace();
				}
				finally {
					close();
				}
			}

			public static void main(String[] args) {
				new UpdateShop().execute();
			}
		}
