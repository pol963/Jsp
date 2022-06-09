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
	public void memberInsert(MemberVO vo) {
		String SQL = "insert into member(id,pass,name,age,email,phone) values(?,?,?,?,?,?)";
		getConnect();
		
		try {
			//conn이 db와 연결되어있는 객체이기에 conn을 이용해서 sql을 전송하는 객체만들기.
			ps=conn.prepareStatement(SQL);
			
		} catch (Exception e) {
			e.printStackTrace();
			
		}
		
	}
	
	
	
	
}
