package kr.bit.model;

//JDBC.

import java.sql.*;
public class MemberDAO {

	//DB는 기본적으로 커넥션이 필요.
	private Connection conn;
	
	//sql전송할수있는객체.
	private PreparedStatement ps;
	
	//DB에 있는 데이터를 가져와서 저장하는 객체.
	private ResultSet rs;
	
	
	
	//DB의 기능을 메소드단위로 구현하기.
	
	//DB연결
	public void getConnect() {
		//DB연결을 위한 정보 3개필요.
		String url ="jdbc:mysql://localhost:3306/test?characterEncoding=UTF-8&serverTimeZone=UTC";
		String user ="root";
		String password="admin12345";
		
		//DB연결 객체를 생성하기 위해 필요한것. MySQL Driver Loding
		
		
		//DB,네트워크...등등할시 무조건 예외처리.
		try { 
			//연결 -> 동적연결 -> 실행시점에서 객체를 생성한다는 것 not컴파일시 객체생성.
			//자바는 클래스를 메모리에 올려야 생성이된것 아래코드가 메모리에 클래스를 올린것입니다. ->이러한것을 동적 로딩이라고 합니다.
			Class.forName("com.mysql.jdbc.Driver"); //"경로" 경로안에 클래스를 메모리에올리는 과정.
			
			// DriverManager.getConnection(url,user,password); ->접속시도.
			// 접속성공시 연결정보를 넘겨줌. 넘겨준 연결정보 conn에 저장.
			conn = DriverManager.getConnection(url,user,password);
			
			//결론 Mysql의 Driver과 java의 DriverManager가 연결. getConnection메소드로 3개의 정보를 넘겨주고 연결정보를 conn에저장.
			
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}
	
		//회원저장 메서드.
		public int memberInsert(MemberVO vo) {
			
			//SQL문 작성. -> 회원정보를 저장하기위한 SQL문.
			String sql = "insert into member(id,pass,name,age,email,phone) value(?,?,?,?,?,?)";
			
			//getConnect()메서드를 호출해 동작하여 conn객체를 만들어줘서 DB와 연결한뒤 진행.
			getConnect();
			
			int cnt = -1;
			
			//SQL을 DB에 전송하는 객체.
			try {
				
				//미리 DB에 날려서 오류가있는지 확인. 혹은 미리 컴파일을 해놓고 ?값은 나중에줘서 속도를 빠르게하기위해 먼저 DB에 .
				ps=conn.prepareStatement(sql); //ps는 이미 컴파일된 SQL을 가지고있습니다. "컴파일"
				
				//?에 값 설정하기. -> 미리 컴파일된 sql문에 ?에 값만 설정하는것. "셋팅"
				ps.setString(1, vo.getId());
				ps.setString(2, vo.getPass());
				ps.setString(3, vo.getName());
				ps.setInt(4, vo.getAge());
				ps.setString(5, vo.getEmail());
				ps.setString(6, vo.getPhone());
			
				//성공한 low만큼 숫자가넘어옵니다.
				cnt = ps.executeUpdate(); //실제 전송 "실행"
			
			} catch (Exception e) {
				e.printStackTrace();
			}
			return cnt; //성공시1 실패면0
			
		}
	
	
}


























