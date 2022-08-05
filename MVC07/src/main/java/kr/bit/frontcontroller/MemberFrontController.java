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
		request.setCharacterEncoding("utf-8");
		
		
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
		
		
		//핸들러 매핑. HandlerMapping생성자가 호출될때 객체또한생성 .
		HandlerMapping mapping = new HandlerMapping();
		
		//getController에 패스와 .do를 요청하여 맞는 value값(POJOController)을 리턴받아서 리턴받은데이터를 controller에 저장.
		controller=mapping.getController(command); 	
		
		//controller안에 있는 경로와 클라이언트의 요청에 담긴 request,response데이터를 같이 nextPage에 저장.
		nextPage = controller.requestHandler(request, response);
		
		
		
		//Forward와 Ridiract 별도 처리.-> 중복코드 줄이기.
		if(nextPage != null) {
			if(nextPage.indexOf("redirect:")!=-1) { //-1이라면데이터가없음을 의미 -1이 아니라면 데이터가 있을음 의미.
				response.sendRedirect(nextPage.split(":")[1]); //.split메서드를 이용 :을 기준으로 짤라서 데이터를 넘겨주기.
			}else {
				//경로중 디렉토리가 바뀌면 모든 코드를 바꿀필요없이 아래 코드의 경로만 바꾸면 해결.
				RequestDispatcher rd = request.getRequestDispatcher(ViewResolver.makeView(nextPage));
				rd.forward(request, response);
				
				
			}
			
		}
		
		
	}

}
















