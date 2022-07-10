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
	
	
	public List<MemberVO> memberList() {
		
		//Connection+statment
		SqlSession session = sqlSessionFactory.openSession();
		//selectList메소드가 vo로 묶고 ArrayList로 다시 묶어주는것을 다해줍니다. 타입은 지정.
		List<MemberVO> list = session.selectList("memberList"); 
		session.close();
		return list;
		
		
	}
	
		
		
	
}


























