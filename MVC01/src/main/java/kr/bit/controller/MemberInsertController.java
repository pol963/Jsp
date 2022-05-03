package kr.bit.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.bit.model.MemberVO;

@WebServlet("/memberInsert.do")
public class MemberInsertController extends HttpServlet {
  
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
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
		
		

}
	}
