package kr.bit.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.bit.model.MemberDAO;


@WebServlet("/memberDelete.do")
public class MemberDeleteController extends HttpServlet {
	
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	
	 int num = Integer.parseInt(request.getParameter("num"));
	 
	 MemberDAO dao = new MemberDAO();
	 int cnt =dao.memberDelete(num);
	
	 if(cnt > 0) { //cnt가0보다 크단것은 삭제에 성공했다는 것 입니다.
		 response.sendRedirect("/MVC01/memberList.do");
	 }else {
		 throw new ServletException("Fail Delete");
	 }
	 
	}

}
