package kr.bit.model;

import java.sql.*;
import java.util.ArrayList;

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
		}finally {
			dbClose();
		}
		return cnt;
		
	}
	
	
	public ArrayList<MemberVO> memberList() {
		String SQL = "select * from member";
		getConnect();
		ArrayList<MemberVO> list = new ArrayList<MemberVO>();
		try {
			ps=conn.prepareStatement(SQL);
			rs = ps.executeQuery();	
			while(rs.next()) {
				int num = rs.getInt("num");
				String id = rs.getString("id");
				String pass = rs.getString("pass");
				String name = rs.getString("name");
				int age = rs.getInt("age");
				String email = rs.getString("email");
				String phone = rs.getString("phone");
				
				MemberVO vo = new MemberVO(num, id, pass, name, age, email, phone);
				list.add(vo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			dbClose();
		}
		return list;
	}
	
	
	public int memberDelete(int num) {
		
		
		String SQL = "delete from member where num=?";
		getConnect();
		int cnt = -1;
		try {
			ps=conn.prepareStatement(SQL);
			ps.setInt(1, num);
			cnt = ps.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			dbClose();
		}
		
		return cnt;
	}//delete
	
	public MemberVO memberContent(int num) {
		String SQL ="select * from member where num=?";
		getConnect();
		MemberVO vo = null;
		try {
			ps=conn.prepareStatement(SQL);
			ps.setInt(1, num);
			rs=ps.executeQuery();
			
			if(rs.next()) {
				num = rs.getInt("num");
				String id = rs.getString("id");
				String pass = rs.getString("pass");
				String name = rs.getString("name");
				int age = rs.getInt("age");
				String email = rs.getString("email");
				String phone = rs.getString("phone");
				vo = new MemberVO(num, id, pass, name, age, email, phone);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			dbClose();
		}
		return vo;
	}
	
	public int memberUpdate(MemberVO vo) {
		String SQL = "update member set age=?,email=?,phone=? where num=?";
		getConnect();
		int cnt = -1;
		try {
		
			ps=conn.prepareStatement(SQL);
			ps.setInt(1, vo.getAge());
			ps.setString(2, vo.getEmail());
			ps.setString(3, vo.getPhone());
			ps.setInt(4, vo.getNum() );
			
			cnt = ps.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			dbClose();
		}
		return cnt;
		
		
		
	}
	
	
	public void dbClose() {
		try {
			if(rs!=null) rs.close();
			if(ps!=null) ps.close();
			if(conn!=null)conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	
	
	
	
}















