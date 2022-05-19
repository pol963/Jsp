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
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String name = "kang";
		int age = 30;
		String email = "aaa@aaaa.com";
		
		//forward.jsp에 넘겨주기.
		MemberVO vo = new MemberVO();
		vo.setName(name);
		vo.setAge(age);
		vo.setEmail(email);
		
		//객체 바인딩 -> request의 메모리안에 데이터를 넣고 View의 jsp페이지에서 데이터를 꺼내서 사용할 수 있게끔 해주는 메소드.
		request.setAttribute("vo", vo);
		
		//forward작업.
		RequestDispatcher rd = request.getRequestDispatcher("view/forward.jsp"); //넘겨줄 jsp페이지 지정. -> 객체의뢰
		
		//jsp페이지에 request와 response를 같이 넘겨줌 즉, jsp페이지에서 새로 두 객체를 만들지 않음. -> 메모리공간의 공유
		rd.forward(request, response); 
		
		
	
	}

}
