package kr.bit.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.bit.model.MemberDAO;
import kr.bit.model.MemberVO;

public class MemberLoginController implements Controller{

	@Override
	public String requestHandler(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String user_id = request.getParameter("user_id");
		String password = request.getParameter("password");
		
		MemberVO vo = new MemberVO();
		vo.setId(user_id);
		vo.setPass(password);
		
		MemberDAO dao = new MemberDAO();
		String user_name = dao.memberLogin(vo);
		
		//user_name 에 값이 있다면 인증성공, 없다면 인증실패.
		if(user_name != null && !"".equals(user_name)) {
			//인증성공
		}else {
			//인증실패
		}
		
		
		
		return null;
	}

}
