package kr.bit.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.bit.model.MemberDAO;
import kr.bit.model.MemberVO;

@WebServlet("/memberList.do")
public class MemberListController extends HttpServlet {
	
	protected void service(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		//1. 클라이언트의 요청 받기(맵핑에 의해 memberList.do) //넘어오는 파라미터 값은 없기에 response만 사용.
		
		
		//2. 회원 전체 리스트 가져오기 -> Model의 JDBC사용. -> DB와 연동하여 데이터를 Model로 가져와 다시 Controller로 가져오기.
		MemberDAO dao = new MemberDAO();
		
		//DAO의 메서드 호출 리턴값(리스트)을 list에 저장.
		ArrayList<MemberVO> list = dao.memberList();
		
		
		//3. 회원 전체 리스트를 HTML로 만들어서 응답하기.
		
		
		
	}

}
