package kr.bit.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.bit.model.MemberDAO;
import kr.bit.model.MemberVO;

//많은 pojo를 줄이기. -> 6개의 POJO를 6개의 메서드로 변경.
public class MemberController { //스프링으로 넘어오면서 더이상 상속X 메서드이름과 파라메터 중복.
	
	//Content
	public String memberContent(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		int num = Integer.parseInt(request.getParameter("num"));
		MemberDAO dao = new MemberDAO();
		MemberVO vo = dao.memberContent(num);
		//객체 바인딩
		request.setAttribute("vo", vo);
		
		return "memberContent";
	}
	
	
	//delete
	public String memberDelete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String ctx = request.getContextPath();
		
		int num = Integer.parseInt(request.getParameter("num"));
		 
		 MemberDAO dao = new MemberDAO();
		 int cnt =dao.memberDelete(num);
		String nextPage = null;
		 if(cnt > 0) { //cnt가0보다 크단것은 삭제에 성공했다는 것 입니다. 
			 //경로앞의 redirect라는 키워드를 붙여줄 수있습니다.
			 nextPage="redirect:"+ctx+"/memberList.do";
		 }else {
			 throw new ServletException("Fail Delete");
		 }	
		return nextPage;
	}
	
	//insert
	public String memberInsert(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String ctx = request.getContextPath();
		
		String id = request.getParameter("id");
		String pass = request.getParameter("pass");
		String name = request.getParameter("name");
		int age = Integer.parseInt(request.getParameter("age"));
		String email = request.getParameter("email");
		String phone = request.getParameter("phone");
		
		MemberVO vo = new MemberVO();
		vo.setId(id);
		vo.setPass(pass);
		vo.setName(name);
		vo.setAge(age);
		vo.setEmail(email);
		vo.setPhone(phone);
		
		MemberDAO dao = new MemberDAO();
		int cnt =dao.memberInsert(vo);
		//PrintWriter out = response.getWriter();
		String nextPage = null;
		
		if(cnt > 0) {
			nextPage = "redirect:"+ctx+"/memberList.do";
		}else {
			throw new ServletException("Not insert");
		}
		
		return nextPage;
	}
	
	//list
	public String memberList(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		//POJO가 할일.
		
		// 1. Model연동
		MemberDAO dao = new MemberDAO();
		List<MemberVO> list = dao.memberList();
		
		// 2. 객체 바인딩.
		request.setAttribute("list", list);
		
		// 3. 다음 페이지 정보.
		return "memberList";
		
	}
	
	//Register
	public String memberRegister(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		
			
		return "memberRegister";
	}
	
	
	//update
	public String memberUpdate(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String ctx = request.getContextPath();
		
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
		
		String nextPage = null;
		
		if (cnt > 0) {
			// 수정성공
			nextPage="redirect:"+ctx+"/memberList.do";
		} else {
			// 수정실패-> 예외객체를 만들어서 WAS에게 던지자.
			throw new ServletException("not update");
		}
		
		return nextPage;
	}
	
}
