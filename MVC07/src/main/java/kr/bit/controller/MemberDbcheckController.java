package kr.bit.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.bit.model.MemberDAO;

public class MemberDbcheckController implements Controller{

	@Override
	public String requestHandler(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		//ajax로 넘긴 데이터 받아주기.
		String id = request.getParameter("id");
		
		//받은데이터로 DB에 있는 아이디 중복체크 메서드 구현후 호출.
		MemberDAO dao = new MemberDAO();
		
		//dao의 memberDbcheck메서드 호출.
		String dbDouble = dao.memberDbCheck(id);
		
		//비동기처리 -> 다른jsp페이지로 넘기지않고 바로 ajax에 넘겨줌 받은 ajax는 메서드를 호출.
		//ajax() 함수에 만들어놓은 callback(dbCheck)함수로 바로 응답.
		response.getWriter().print(dbDouble);
		
		
		return null;
	}

}
