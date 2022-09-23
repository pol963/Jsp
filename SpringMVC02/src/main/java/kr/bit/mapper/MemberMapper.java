package kr.bit.mapper;

import java.util.List;

import kr.bit.model.MemberVO;

 /*
  DAO의 역할은 db와 server을 연결시켜 data를 전달해주는 역할입니다.
  스프링에서는 DAO없이 MemberMapper.xml파일의 namespace의 이름이 해당 패키지와 일치한다면
  따로 DAO를 사용하지않고 스프링 내부적으로 맵핑시켜 줍니다.
  
  */

public interface MemberMapper {

	
	//인터페이스로 인한 기능 정의. namespace가 해당인터페이스를 가르킨다면 memberList라는 이름과일치하는 SQL에 자동맵핑.
	public List<MemberVO> memberList();
	public int memberInsert(MemberVO vo);
	public int memberDelete(int num);
	public MemberVO memberContent(int num);
	public int memberUpdate(MemberVO vo);
	
}
