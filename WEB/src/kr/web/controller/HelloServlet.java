package kr.web.controller;

import java.io.*;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import kr.web.util.MyUtil;

//매핑의 발전. ANNOTATION으로 매핑가능.
@WebServlet("/hs.do") // web.xml에서 했던 매핑을 이렇게 간단하게 가능.


//서블릿의 골격. 서블릿은 새로운프로그램이 아닌 그냥 자바 입니다.

public class HelloServlet extends HttpServlet{

	//service메서드는 매개변수로 요청을 받는객체 응답을 할수있는 응답객체가 있어야 합니다.
	// req요청받고 resp응답해줌.
	public void service(HttpServletRequest req , HttpServletResponse resp) 
		throws ServletException,IOException{
		
		
		//1~100까지의 합은?
		MyUtil my = new MyUtil();
		int sum = my.hap();
		
		
		//요청한 클라이언트에게 응답하기.
		//getWriter -> 요청한 클라이언트와 IO를 구축.
		//out객체는 브라우저에 빨대를 연결한 상태로 문자열을 내려보낼수 있게 해줍니다.
		PrintWriter out = resp.getWriter();
		out.print("<html>");
		out.print("<body>");
		out.print(sum);
		out.print("<body>");
		out.print("</html>");
		
		
	}
	
	
}
