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

	private static SqlSessionFactory sqlSessionFactory;
	
	static { //초기화 블럭 프로그램 시작후 딱 한번만 실행되는 코드.
		try {
		String resource = "kr/bit/mybatis/config.xml";
		InputStream inputStream = Resources.getResourceAsStream(resource); //읽기
		sqlSessionFactory =  new SqlSessionFactoryBuilder().build(inputStream);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
	//회원리스트 전체보기.
	public List<MemberVO> memberList() {
		
		//Connection+statment
		SqlSession session = sqlSessionFactory.openSession();
		//selectList메소드가 vo로 묶고 ArrayList로 다시 묶어주는것을 다해줍니다. 타입은 지정.
		List<MemberVO> list = session.selectList("memberList"); 
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
	
	
	//회원로그인처리.
	public String memberLogin(MemberVO vo) {
		//session을 하나 얻어오기 sqlSessionFactory에서.
		SqlSession session = sqlSessionFactory.openSession();
		//user_name를 반환하기로 하여서 selectOne메소드를 이용해 하나의 값만 리턴받아오기.
		String user_name = session.selectOne("memberLogin",vo);
		session.close();
		return user_name;
		
		
	}
		
	
}


























