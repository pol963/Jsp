package kr.bit.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.bit.model.MemberDAO;

@WebServlet("/memberContent.do")
public class MemberContentController extends HttpServlet {
	
	//한사람의 정보만 가져오게끔 하는 메서드를 호출하여 응답을 해주기.
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		//고유번호확인.
		int num = Integer.parseInt(request.getParameter("num"));
		//DAO클래스를 사용하기 위한 객체 생성. 
		MemberDAO dao = new MemberDAO();
		
		
	
	}

}
