package kr.bit.model;


//MyBatis를 이용.

import java.util.List;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

//@Repository 어노테이션. -> Spring Container에서 관리를 해줍니다. -> 전처리과정.
//Spring Container에 DAO객체가 생성. 의존성을 줄이기위해. 
//따라서 다른 클래스에서 기능을 이용할 시 Spring Container에서 사용가능.->DI(Dependency injection) 의존성주입.
@Repository
public class MemberDAO {

	//Connection Pool기법을 이용하여 DB와 연동해서 요청을 처리하기 위한 변수 선언.
	//SqlSessionFactory자료형을 변수를 선언
	//Spring Container에 Autowired어노테이션을 사용해 넣기.
	@Autowired 
	private SqlSessionFactory sqlSessionFactory;
	
	
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
		
		//connection 받아오기. ->sqlSession받아오기.
		SqlSession session = sqlSessionFactory.openSession(); //JDBC의 Connection
		//insert메서드로 memberInsert맵핑한 Id로 sql의 결과 받아오기 매개변수로 vo넘겨주기.
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
	
	
	//selectOne메서드는 한사람의 데이터만 가져옵니다. not 전부. ->vo로 리턴합니다.
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


























