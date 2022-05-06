package kr.bit.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.bit.model.MemberDAO;
import kr.bit.model.MemberVO;

@WebServlet("/memberInsert.do")
public class MemberInsertController extends HttpServlet {
  
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		//한글처리 -> 기본적으로 데이터는 1byte씩 주고받기때문에 2byte인 한글은 깨질게 됩니다. 해결하기위해 문자를 2byte씩 주고받게끔 요청하게 됩니다.
		request.setCharacterEncoding("utf-8");
		
		//1.파라메터 수집
		
		//정보받기.
		String id = request.getParameter("id");
		String pass = request.getParameter("pass");
		String name = request.getParameter("name");
		int age = Integer.parseInt(request.getParameter("age"));
		String email = request.getParameter("email");
		String phone = request.getParameter("phone");
		
		//VO에 묶어서 수집.
		MemberVO vo = new MemberVO(id, pass, name, age, email, phone);
		/*
		 	vo.setId(id); 의 형식이 FM.
		  
		  
		 */
		
		System.out.println(vo.toString());
		
		
		//Model과 연동하기.
		MemberDAO dao = new MemberDAO();
		//Model의 DAO클래스의 memberInsert메서드를 이영하여 vo에 담긴 회원정보를 DB에 저장.
		int cnt =dao.memberInsert(vo);
		
		PrintWriter out = response.getWriter();
		
		if(cnt > 0) {
			out.println("insert success");
		}else {

			throw new ServletException("Not insert");
			
		}
		

}
	}
