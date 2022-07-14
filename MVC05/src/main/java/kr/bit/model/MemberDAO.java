package kr.bit.model;

import java.io.InputStream;

//MyBatis를 이용.

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
public class MemberDAO {

	//Connection Pool기법을 이용하여 DB와 연동해서 요청을 처리하기 위한 변수 선언.
	//SqlSessionFactory자료형을 변수를 선언 -> 
	private static SqlSessionFactory sqlSessionFactory;
	
	//초기화 블럭 프로그램 시작후 딱 한번만 실행되는 코드.
	
	static { 
		
		try {
			//환경설정파일을 가르키는 경로.
		String resource = "kr/bit/mybatis/config.xml";
		//설정읽어들이기.
		InputStream inputStream = Resources.getResourceAsStream(resource); //읽기
		
		//sqlSession을 담고있는 Factory메모리공간을 가르키는 객체생성. -> build메서드를 이용하여 설정을 읽어드린걸로 객체생성.
		sqlSessionFactory =  new SqlSessionFactoryBuilder().build(inputStream);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
	//회원리스트 전체보기.
	public List<MemberVO> memberList() {
		
		//[Connection+statment] = SqlSession.
		//아래코드는 즉 sqlSessionFactory에서 sqlSession을 꺼내와서 session에 담아준것.
		SqlSession session = sqlSessionFactory.openSession();
		
		//selectList메소드가 vo로 묶고 ArrayList로 다시 묶어주는것을 다해줍니다. 타입은 지정.
		//selectList메서드 -> 여러개를 선택하여 리턴받아올 수 있는 메서드. -> 기본적으로 List를 리턴합니다.
		List<MemberVO> list = session.selectList("memberList"); 
		//DB작업이 끝나면 close하여 반납을 해줘야 합니다. sqlSession을 SqlSessionFactory에 반납.
		session.close();
		return list;
		
	}
	
	//회원가입.
	public int memberInsert(MemberVO vo) {
		
		SqlSession session = sqlSessionFactory.openSession(); //JDBC의 Connection
		int cnt = session.insert("memberInsert", vo);
		session.commit(); //insert... 등 실제로 DB에 변동이 있다면 commit로 확정지어줘야 합니다.
		session.close();
		return cnt;
		
	}
	
	//회원삭제.
	public int memberDelete(int num) {
		SqlSession session = sqlSessionFactory.openSession();
		int cnt = session.delete("memberDelete", num);
		session.commit();
		session.close();
		return cnt;
	}
	
	//상세보기
	public MemberVO memberContent(int num) {
		SqlSession session = sqlSessionFactory.openSession();
		MemberVO vo = session.selectOne("memberContent",num);
		session.close();
		return vo;
	}
	
	
	//업데이트
		
	public int memberUpdate(MemberVO vo) {
		SqlSession session = sqlSessionFactory.openSession();
		int cnt = session.update("memberUpdate", vo);
		session.commit();
		session.close();
		return cnt;
	}
		
	
}


























