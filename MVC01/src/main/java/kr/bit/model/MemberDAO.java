package kr.bit.model;

//JDBC.

import java.sql.*;
import java.util.ArrayList;
public class MemberDAO {

	//DB는 기본적으로 커넥션이 필요.
	private Connection conn;
	
	//sql전송할수있는객체.
	private PreparedStatement ps;
	
	//DB에 있는 데이터를 가져와서 저장하는 객체. 결과의 집합을 가지고 있는 객체.
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
			}finally { //에러에 유무에 상관없이 무조건 실행되는 블럭.
				dbClose();
			}
			return cnt; //성공시1 실패면0
		}//memberInsert
		
		
		//회원 전체 리스트 가져오기. 회원한명->VO를 이용 // 회원 전체 -> ArrayList를 이용하는게 일반적.
		public ArrayList<MemberVO> memberList() {
			
			//DB에서 사용하기 위한 SQL만들기.
			String SQL = "Select * from member";
			
			//DB연결
			getConnect();
			
			//담기위한 ArrayList
			ArrayList<MemberVO> list = new ArrayList<MemberVO>();
			
			//DB작업이기에 예외처리해주기.
			try {
				//sql문 컴파일.
				ps = conn.prepareStatement(SQL);
				rs = ps.executeQuery();
				
				//rs는 실질적 바로 데이터를 가르키고 있는것이 아니라 테이블결과의 첫번째 즉 num,id,name...등이있는 컬럼을 가르키고 있습니다.
				//따라서 next()라는 메서드를 사용하면 next메서드는 다음컬럼으로 이동하는 메서드입니다.
				//이동하고 데이터가 있다면 true없다면 false를 반환합니다.
				//즉 rs.next()를 처음 실행하면 num,id...등이 있는 컬럼에서 다음컬럼 으로 이동하고 데이타가 있다면 true 없다면 false를 반환합니다.
				while(rs.next()) {
					//데이터 가져오기.
					int num = rs.getInt("num");
					String id = rs.getString("id");
					String pass = rs.getString("pass");
					String name = rs.getString("name");
					int age = rs.getInt("age");
					String email = rs.getString("email");
					String phone = rs.getString("phone");
					
					//데이터 묶기.
					MemberVO vo = new MemberVO(num, id, pass, name, age, email, phone);
					
					//데이터 담기.
					list.add(vo);
					
				}
				
				
			} catch (Exception e) {
				e.printStackTrace();
			}finally {
				dbClose();
			}
			
			return list;
			
		}//memberList
		
		//회원삭제메서드 파라미터는 어떤회원을 삭제할 것인지.
		public int memberDelete(int num) {
			String SQL = "delete from member where num=?";
			getConnect();
			int cnt = -1;
			try {
				ps = conn.prepareStatement(SQL);
				ps.setInt(1, num);
				cnt=ps.executeUpdate();
			} catch (Exception e) {
				e.printStackTrace();
			}finally {
				dbClose();
			}
			return cnt;
		}//memberDelete
	
		//회원 상세보기 VO에 한명의 정보만 담아서 넘겨주기.
		public MemberVO memberContent(int num) {
			String SQL ="select * from member where num=?";
			getConnect();
			MemberVO vo=null;
			try {
				ps=conn.prepareStatement(SQL);
				ps.setInt(1, num);
				rs=ps.executeQuery();
				
				if(rs.next()) {//next->커서를 한칸뒤로 이동하여 데이터가 있다면 참. 없다면 거짓.
					//데이터가 있을시 (참) 데이터를 가져와서VO로 묶어주기. -> 회원 한명의 정보만.
					num = rs.getInt("num");
					String id = rs.getString("id");
					String pass = rs.getString("pass");
					String name = rs.getString("name");
					int age = rs.getInt("age");
					String email = rs.getString("email");
					String phone = rs.getString("phone");
					//묶기. vo는 지역변수.
					vo = new MemberVO(num, id, pass, name, age, email, phone);
					
				}
		
			} catch (Exception e) {
				e.printStackTrace();
			}finally {
				dbClose();
			}
			return vo;
			
		}//memberContent
		
		
		//회원정보수정.
		public int memberUpdate(MemberVO vo) {
			//where -> 조건.
			String SQL ="update member set age=?, email=?, phone=? where num=?";
			getConnect();
			int cnt =-1;
			
			try {
				ps=conn.prepareStatement(SQL);
				ps.setInt(1, vo.getAge());
				ps.setString(2, vo.getEmail());
				ps.setString(3, vo.getPhone());
				ps.setInt(4, vo.getNum());
				
				
				cnt=ps.executeUpdate();
				
			} catch (Exception e) {
				e.printStackTrace();
			}finally {
				dbClose();
			}
			return cnt;
			
		}
		
		
		//데이터 베이스 연결 끊기 
		public void dbClose() {
			try {
				if(rs != null) rs.close();
				if(ps != null) ps.close();
				if(conn != null) conn.close();
				
			} catch (Exception e) {
				// TODO: handle exception
			}
		}//dbClose
		
		
		
		
	
}


























