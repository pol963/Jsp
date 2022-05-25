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
import kr.bit.controller.MemberListController;
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
			
			
			
			
		}else if(command.equals("/memberRegister.do")) {//회원가입화면
			RequestDispatcher rd = request.getRequestDispatcher("member/memberRegister.html");
			rd.forward(request, response);
			
		}else if(command.equals("/memberContent.do")) {
			int num = Integer.parseInt(request.getParameter("num"));
			MemberDAO dao = new MemberDAO();
			MemberVO vo = dao.memberContent(num);
			
			
			//객체 바인딩
			request.setAttribute("vo", vo);
			RequestDispatcher rd = request.getRequestDispatcher("member/memberContent.jsp");
			rd.forward(request, response);
			
		}else if(command.equals("/memberUpdate.do")) {
			int num = Integer.parseInt(request.getParameter("num"));
			int age = Integer.parseInt(request.getParameter("age"));
			String email = request.getParameter("email");
			String phone = request.getParameter("phone");
			
			//묶기
			MemberVO vo = new MemberVO();
			vo.setNum(num);
			vo.setAge(age);
			vo.setEmail(email);
			vo.setPhone(phone);
			
			//DAO에서 메서드 호출.
			MemberDAO dao = new MemberDAO();
			int cnt = dao.memberUpdate(vo); //업데이트.
			if (cnt > 0) {
				// 가입성공
				response.sendRedirect("/MVC04/memberList.do");
			} else {
				// 가입실패-> 예외객체를 만들어서 WAS에게 던지자.
				throw new ServletException("not update");
			}
			
		}else if(command.equals("/memberDelete.do")) {
			int num = Integer.parseInt(request.getParameter("num"));
			 
			 MemberDAO dao = new MemberDAO();
			 int cnt =dao.memberDelete(num);
			
			 if(cnt > 0) { //cnt가0보다 크단것은 삭제에 성공했다는 것 입니다.
				 response.sendRedirect("/MVC04/memberList.do");
			 }else {
				 throw new ServletException("Fail Delete");
			 }
			
		}//if
		
	}

}
















