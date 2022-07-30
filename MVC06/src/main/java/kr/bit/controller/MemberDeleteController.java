package kr.bit.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.bit.model.MemberDAO;

public class MemberDeleteController implements Controller{

	@Override
	public String requestHandler(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String ctx = request.getContextPath(); //MVC06
		
		int num = Integer.parseInt(request.getParameter("num"));
		 
		 MemberDAO dao = new MemberDAO();
		 int cnt =dao.memberDelete(num);
		String nextPage = null;
		 if(cnt > 0) { //cnt가0보다 크단것은 삭제에 성공했다는 것 입니다.
			 
			 request.getSession().invalidate();
			 //경로앞의 redirect라는 키워드를 붙여줄 수있습니다.
			 nextPage="redirect:"+ctx+"/memberList.do";
		 }else {
			 throw new ServletException("Fail Delete");
		 }	
		
		
		return nextPage;
	}

}
