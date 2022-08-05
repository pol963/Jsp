package kr.bit.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.bit.model.MemberDAO;
import kr.bit.model.MemberVO;

public class MemberLoginController implements Controller{

	
	//로그인 페이지에서 id와 password를 입력하여 넘겨주면 받아서 처리하기.
	@Override
	public String requestHandler(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String ctx = request.getContextPath();
		
		//받기. 
		String user_id = request.getParameter("user_id");
		String password = request.getParameter("password");
		
		//묶기.
		MemberVO vo = new MemberVO();
		vo.setId(user_id);
		vo.setPass(password);
		
		//묶은데이터와 함께 DAO에서 회원인증
		MemberDAO dao = new MemberDAO();
		//memberLogin메서드를 구현 이후 user_name를 리턴받기. -> 해당회원의 name를 리턴받기.
		String user_name = dao.memberLogin(vo);
		
		//리턴받은 user_name가 값이 있는지 없는지 있을때와 없을때를 구현. -> 있다면 성공적으로 데이터가져온것 없다면 실패.
		if(user_name != null && !"".equals(user_name)) {
			
			//성공햇다면 모든 웹 사이트에서 알아야 하기 때문에 session 사용. 객체바인딩의 이유.
			//클라이언에게서 요청이오면 서버는 인식하기 위해서 Session이라는 하나의 메모리 공간을 만들게 됩니다. 이를 httpSession이라고합니다.
			//이를 가지고 오기 위한 방법으 getSession()입니다.
			//setAttribute("",) -> 이름이""인 속성의 값을   로 지정합니다.  -> 객체바인딩.
			//다른jsp페이지들이 회원인증을 했는지 안했는지를 확인해볼려면 HttpSession객체를 가지고와서 확인해봐야 합니다.
			//가지고 오는것이 getSession() getAttribute() 
			//위의 코드로 가지고왓는대 없다면 id등이 없다면 회원인증을 안한것. 따라서 jsp페이의 기능...등을 이용x
			//즉,다른jsp페이지에서 인증이되었다면 해당 기능을 이용하거나 못 이용하거나 페이지 이동 등등을 지정하기위해 객체바인딩 -> 서버에 httpSession에 객체바인딩.
			
			//클라이언트의 요청이 있을때 서버에서 만드는 httpsession타입의 session생성후 세션가져오기.
			HttpSession session = request.getSession(); 
			//클라이언트와 서버에 연결되어 있는 session에 객체바인딩.
			session.setAttribute("userId", user_id);
			session.setAttribute("userName", user_name);
			
		}else {
			
			//실패시.
			request.getSession().setAttribute("userId", "");
			request.getSession().setAttribute("userName", "");
			
			//실패시에msg의 속성에 사용자의 정보가 올바르지 않습니다. 라는 값을 넣기.
			request.getSession().setAttribute("msg", "사용자의 정보가 올바르지 않습니다.");
			
		}
		
		
		return "redirect:"+ctx+"/memberList.do";
	}

}



