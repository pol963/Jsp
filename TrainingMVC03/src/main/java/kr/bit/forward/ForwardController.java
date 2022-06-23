package kr.bit.forward;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.bit.model.MemberVO;

@WebServlet("/fc.do")
public class ForwardController extends HttpServlet {
	
	protected void service(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {

		String name ="gogomin";
		int age = 30;
		String email ="ppp@naver.com";
		
		MemberVO vo = new MemberVO();
		vo.setName(name);
		vo.setAge(age);
		vo.setEmail(email);
		
		request.setAttribute("vo", vo);
		RequestDispatcher rd = request.getRequestDispatcher("view/forward.jsp");
		rd.forward(request, response); 
		
		
	}

}
