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

		int num = Integer.parseInt(request.getParameter("num"));
		
		MemberDAO dao = new MemberDAO();
		int cnt =dao.memberDelete(num);
		String nextPage = null;
		if(cnt>0) {
			nextPage="redirect:/TrainingMVC04/memberList.do";
		}else {
			throw new ServletException("cant delete"); 
		}
		return nextPage;
	}

}
