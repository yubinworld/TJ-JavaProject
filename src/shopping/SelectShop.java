package shopping;

import java.sql.SQLException;

/*
 ▶ 상품조회
Statement객체를 사용하여 작성한다.
클래스명 : SelectShop
검색할 상품명을 입력받은 후 like를 통해 해당조건에 맞는 레코드만 출력한다. 
출력항목 : 일련번호, 상품명, 가격, 등록일, 제품코드
단, 등록일은 0000-00-00 00:00 형태로 출력해야 한다.	상품가격은 세자리마다 컴마를 찍어준다.
Statement객체는 인파라미터를 통한 동적쿼리를 작성할 수 없으므로 순수 Java변수를 사용한다.
 */
public class SelectShop extends IConnectImpl {
	public SelectShop() {
		super("education", "1234");
	}

	@Override
	public void execute() {
		try
		{
			while(true)
			{
				/*
				prepared 객체를 통해 인파라미터를 설정하면 문자인 경우
				자동으로 싱글쿼테이션을 추가하게 되므로 || 연산자를 
				추가해서 쿼리문을 작성해야한다. 
				 */
				String sql = "SELECT * FROM member "
//					+ " WHERE name LIKE '%?%'"; //에러발생:부적합한 열 인덱스 
					+ " WHERE name LIKE '%'||?||'%'";
				
				String quary = "SELECT id, pass, name, regidate,\"\n"
						+ "					+ \" to_char(regidate, 'yyyy.mm.dd hh24:mi') d1 \"     \n"
						+ "					+ \" FROM member\";"
						
				psmt = con.prepareStatement(sql);
				psmt.setString(1, scanValue("상품명: "));
				rs = psmt.executeQuery();
				while(rs.next()) {
					String proName = rs.getString(1);
					String price = rs.getString(2);
					String code = rs.getString(3);
					
					/*
					날짜를 문자형으로 추출하면 시간까지 출력되므로 날짜부분만
					잘라서 출력한다. 
					 */
					String regidate = rs.getString(4).substring(0, 10);

					System.out.printf("%s %s %s %s\n", proName, price, code, regidate);
				}
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		finally {
			close();
		}
	}
}
