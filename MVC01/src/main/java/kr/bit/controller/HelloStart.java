package kr.bit.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/h.do")
//1. 클라이언트의 요청을 받는 클래스 (파라메터 수집) -> Controller(Servlet)
public class HelloStart extends HttpServlet {
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	
		//2. 처리하는 작업(비즈니스 로직) -> Model(Java Class)
		int sum = 0;
		for(int i = 1; i <= 100 ; i++) {
			sum += i;
		}
		
		//3. 클라이언트의 요청결과를 응답 반환 하는 작업(프리젠테이션 로직) -> View(JSP)
		PrintWriter out = response.getWriter();
		out.println("<html>");
		out.println("<body>");
		out.println(sum);
		out.println("</body>");
		out.println("</html>");
		
	}

}
