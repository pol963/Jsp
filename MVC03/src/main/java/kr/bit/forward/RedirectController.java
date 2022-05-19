package kr.bit.forward;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/rc.do")
public class RedirectController extends HttpServlet {
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//가정 -> 클라이언트에서 fc.do라는 요청을 하였고 fc.do요청을 받은 컨트롤러는 view에 데이터를 넘겨준뒤 result.jsp페이지에서 출력하는 예제.
		
		// 1. forward 실습. 
		String data = "apple";
		String age = "30";
		String email = "aaa@aaaa.com";
		
		//Controller에서 View페이지로 전환하는 방법.
		// 1. redirect 
		response.sendRedirect("view/result.jsp?data="+data+"&age="+age+"&email="+email);
		  
		
		// 2. forward
		
		
	}

}
