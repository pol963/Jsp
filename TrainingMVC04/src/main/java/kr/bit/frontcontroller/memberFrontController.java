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
		
		request.setCharacterEncoding("UTF-8");
		
		String url = request.getRequestURI();
		//System.out.println(url);
		
		String ctx = request.getContextPath();
		//System.out.println(ctx);
		
		String command = url.substring(ctx.length());
		System.out.println();
		System.out.println(command);
		Controller controller = null;
		String nextPage = null;
		
		HandlerMapping mapping = new HandlerMapping();
		controller = mapping.getController(command);
		nextPage = controller.requestHandler(request, response);
		
		if(nextPage!=null) {
			if(nextPage.indexOf("redirect:")!= -1) {
				response.sendRedirect(nextPage.split(":")[1]);
			}else {
				RequestDispatcher rd = 
						request.getRequestDispatcher(ViewResolver.makeView(nextPage));
				rd.forward(request, response);
			}
		}
		
	}

}
