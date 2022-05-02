package kr.bit.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/calc.do")
public class CalcController extends HttpServlet {
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		//1. 클라이언트에서 넘어오는 폼 데이터 받기. (파라메터 수집->su1,su2)
		int su1 = Integer.parseInt(request.getParameter("su1"));
		int su2 = Integer.parseInt(request.getParameter("su2"));
		
		//2. 계산하기 계산하기위해서는 정수형으로 바꿔줘야합니다.
			int sum = 0;
			for(int i = su1; i<=su2;i++) {
				sum += i;
			}
		
			PrintWriter out = response.getWriter();
			out.println("total : " + sum);
			
			
			
	}

}
