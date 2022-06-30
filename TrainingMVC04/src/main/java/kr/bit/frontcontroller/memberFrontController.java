package kr.bit.frontcontroller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.bit.controller.Controller;
import kr.bit.controller.MemberContentCotroller;
import kr.bit.controller.MemberDeleteController;
import kr.bit.controller.MemberInsertController;
import kr.bit.controller.MemberListController;
import kr.bit.controller.MemberRegisterController;
import kr.bit.controller.MemberUpdateController;
import kr.bit.model.MemberDAO;
import kr.bit.model.MemberVO;

@WebServlet("*.do")
public class memberFrontController extends HttpServlet {
	protected void service(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		request.setCharacterEncoding("euc-kr");
		
		String url = request.getRequestURI();
		//System.out.println(url);
		
		String ctx = request.getContextPath();
		//System.out.println(ctx);
		
		String command = url.substring(ctx.length());
		System.out.println();
		System.out.println(command);
		Controller controller = null;
		String nextPage = null;
		if(command.equals("/memberList.do")) {
			
			controller = new MemberListController();
			nextPage=controller.requestHandler(request, response);
			RequestDispatcher rd = request.getRequestDispatcher(nextPage);
			rd.forward(request, response);
			
		}else if(command.equals("/memberInsert.do")) {
			
			controller = new MemberInsertController();
			nextPage=controller.requestHandler(request, response);
			response.sendRedirect(nextPage);
			
		}else if(command.equals("/memberRegister.do")) {
			
			controller = new MemberRegisterController();
			nextPage=controller.requestHandler(request, response);
			RequestDispatcher rd = request.getRequestDispatcher(nextPage);
			rd.forward(request, response);
			
		}else if(command.equals("/memberContent.do")) {
			
			controller = new MemberContentCotroller();
			nextPage=controller.requestHandler(request, response);
			RequestDispatcher rd = request.getRequestDispatcher(nextPage);
			rd.forward(request, response);
			
		}else if(command.equals("/memberUpdate.do")) {
			
			controller = new MemberUpdateController();
			nextPage=controller.requestHandler(request, response);
			response.sendRedirect(nextPage);
			
		}else if(command.equals("/memberDelete.do")) {
			
			controller = new MemberDeleteController();
			nextPage=controller.requestHandler(request, response);
			response.sendRedirect(nextPage);
			
		}//if_end
		
	}

}
