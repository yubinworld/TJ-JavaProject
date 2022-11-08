package shopping;
/*
▶ 상품수정 및 삭제

상품삭제
프로시저명 : ShopDeleteGoods
In파라미터 : 삭제할 상품의 일련번호
Out파라미터 : 레코드 삭제 결과(1 혹은 0)
클래스명 : DeleteShop

*/
public class DeleteShop extends IConnectImpl{

	public DeleteShop() {
		
		super("education", "1234");
	}
	
	@Override
	public void execute() {
		try {
			//1.delete를 위한 쿼리문 생성. PK로 지정된 아이디 컬럼에
			//인파라미터를 설정한다. 
			String query = "DELETE FROM member WHERE id=?";
			//2.쿼리문을 인수로 prepared객체를 생성한다. 
			psmt = con.prepareStatement(query);
			//3.입력값을 받은 후 인파라미터를 세팅한다. 
			psmt.setString(1, scanValue("삭제할아이디"));
			//4.쿼리실행 및 결과출력
			System.out.println(psmt.executeUpdate()+"행이 삭제되었습니다");
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			close();
		}
	}
	
	public static void main(String[] args) {
		new DeleteShop().execute();		
	}
}


