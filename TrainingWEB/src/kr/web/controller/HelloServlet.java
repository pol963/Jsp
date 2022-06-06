package kr.web.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.web.util.MyUtil;


@WebServlet("/hs.do")
public class HelloServlet extends HttpServlet{

	@Override
	public void service(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {

		//요청을 받음 1~100까지의 총합
		MyUtil my = new MyUtil();
		int sum = my.hap();
		
		//요청한 클라이언트에게 응답.
		PrintWriter out = response.getWriter();
		out.print("<html>");
		out.print("<body>");
		out.print(sum);
		out.print("<body>");
		out.print("</html>");
	}

}
