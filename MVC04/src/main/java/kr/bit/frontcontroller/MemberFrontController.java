package kr.bit.frontcontroller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.bit.controller.Controller;
import kr.bit.controller.MemberContentController;
import kr.bit.controller.MemberDeleteController;
import kr.bit.controller.MemberInsertController;
import kr.bit.controller.MemberListController;
import kr.bit.controller.MemberRegisterController;
import kr.bit.controller.MemberUpdateController;
import kr.bit.model.MemberDAO;
import kr.bit.model.MemberVO;

//맵핑의 *.do는 어떠한 요청이 오든 다 이 FrontController에서 받겟다는 뜻입니다. 
@WebServlet("*.do")
public class MemberFrontController extends HttpServlet {
	protected void service(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		//한글 깨짐 방지.
		request.setCharacterEncoding("EUC-KR");
		
		
		//FrontController의 역할.
		
		// 1. 클라이언트가 어떠한 요청을 했는지 파악하기. 
		//request객체에는 클라이언트가 요청을했을때 오는 URL또한 들어있습니다. 따라서 URL정보로 어떠한 요청을 했는지 파악이 가능합니다.
		String url = request.getRequestURI();
		System.out.println(url);
		
		//tomcat에 웹프로젝트(ex)MVC04)를 등록하면 URL을 불러올때 공통적으로 Context pass(ex)MVC04)가 불려온다.
		String ctx = request.getContextPath();
		System.out.println(ctx);
		
		//이를 이용하여 url과 contextpass를 이용하여 뒤의 경로를 추려볼 수 있다.
		//클라이언트가 어떤 요청을 했는지 정확히 파악해보기.
		//ctx를 제외한 뒤까지 가져와서 저장.
		String command = url.substring(ctx.length()); //뒤 파라미터 생략시 끝까지.
		System.out.println(command);
		
		Controller controller=null;
		String nextPage=null;
		
		//요청(command)에 따른 분기 작업.
		if(command.equals("/memberList.do")) {//회원리스트 보기
			
			controller = new MemberListController();
			nextPage = controller.requestHandler(request, response);
			
			RequestDispatcher rd = request.getRequestDispatcher(nextPage);
			rd.forward(request, response);
			
		}else if(command.equals("/memberInsert.do")){//회원가입
			
			controller = new MemberInsertController();
			nextPage = controller.requestHandler(request, response);
			
			response.sendRedirect(nextPage);
			
			
		}else if(command.equals("/memberRegister.do")) {//회원가입화면
			
			controller = new MemberRegisterController();
			nextPage = controller.requestHandler(request, response);
			
			RequestDispatcher rd = request.getRequestDispatcher(nextPage);
			rd.forward(request, response);
			
		}else if(command.equals("/memberContent.do")) {
			
			
			controller = new MemberContentController();
			nextPage = controller.requestHandler(request, response);
			
			
			RequestDispatcher rd = request.getRequestDispatcher(nextPage);
			rd.forward(request, response);
			
		}else if(command.equals("/memberUpdate.do")) {
			
			controller = new MemberUpdateController();
			nextPage = controller.requestHandler(request, response);
			
			response.sendRedirect(nextPage);
			
			
		}else if(command.equals("/memberDelete.do")) {
			
			controller = new MemberDeleteController();
			nextPage = controller.requestHandler(request, response);
			
			response.sendRedirect(nextPage);
			
			
			
		}//if
		
	}

}
















