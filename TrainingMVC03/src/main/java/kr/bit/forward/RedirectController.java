package kr.bit.forward;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/rc.do")
public class RedirectController extends HttpServlet {
	protected void service(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		//forward 간단 실습. 데이터를 view페이지로 전달하여 view페이지에서 출력해보기 -> result.jsp
		String data = "apple";
		int num = 1;
		String name = "kangmingi";
		response.sendRedirect("view/result.jsp?data="+data+"&num="+num+"&name="+name);
		
	}

}
