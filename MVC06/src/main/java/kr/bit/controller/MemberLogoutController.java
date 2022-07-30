package kr.bit.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MemberLogoutController implements Controller{

	//세션 끊어주기 -> 로그아웃 작업.
	@Override
	public String requestHandler(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String ctx = request.getContextPath();	//MVC06
		
		//세션을 가져와서 1.강제로 종료하기.
		request.getSession().invalidate();
		//2. 브라우저 닫기.
		//3. 30분기다리기.
		return "redirect:"+ctx+"/memberList.do";
	}

}
