package kr.bit.model;

import java.sql.*;

public class MemberDAO {

	private Connection conn;
	private PreparedStatement ps;
	private ResultSet rs;
	
	
	public void getConnect() {
		
		String url ="jdbc:mysql://localhost:3306/test?characterEncoding=UTF-8&serverTimeZone=UTC";
		String user ="root";
		String password="admin12345";
		
		try {
			Class.forName("com.mysql.jdbc.Driver");//동적로딩 실행시메모리에 올리기. ->드라이버로딩
			conn = DriverManager.getConnection(url, user, password); //db연결 각 db회사에서 실행 개발자x
		} catch (Exception e) {
			e.printStackTrace();
		}
			
		
	}
	
	//회원 가입 -> 회원저장.
	public int memberInsert(MemberVO vo) {
		String SQL = "insert into member(id,pass,name,age,email,phone) values(?,?,?,?,?,?)";
		getConnect();
		
		int cnt=0;
		
		try {
			//conn이 db와 연결되어있는 객체이기에 conn을 이용해서 sql을 전송하는 객체만들기.
			ps=conn.prepareStatement(SQL); //먼저 , 미리 컴파일을 시키는 것. 실행은 아직 안한것.
			
			ps.setString(1, vo.getId());
			ps.setString(2, vo.getPass());
			ps.setString(3, vo.getName());
			ps.setInt(4, vo.getAge());
			ps.setString(5, vo.getEmail());
			ps.setString(6, vo.getPhone());
			
			cnt=ps.executeUpdate();
			
			
		} catch (Exception e) {
			e.printStackTrace();
			
		}
		
		return cnt;
		
	}
	
	
	
	
}
