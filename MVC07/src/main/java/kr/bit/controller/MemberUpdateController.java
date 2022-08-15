package kr.bit.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.bit.model.MemberDAO;
import kr.bit.model.MemberVO;

//파일이 넘어오는경우와 안넘어오는경우.

public class MemberUpdateController implements Controller{

	@Override
	public String requestHandler(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String ctx = request.getContextPath();
		
		int num = Integer.parseInt(request.getParameter("num"));
		int age = Integer.parseInt(request.getParameter("age"));
		String email = request.getParameter("email");
		String phone = request.getParameter("phone");
		
		//묶기
		MemberVO vo = new MemberVO();
		//파일이 넘어온경우.
		if(request.getParameter("mode").equals("fupdate")) {
			String filename = request.getParameter("filename");
			vo.setFilename(filename);
		}
		
		vo.setNum(num);
		vo.setAge(age);
		vo.setEmail(email);
		vo.setPhone(phone);
		
		//DAO에서 메서드 호출.
		MemberDAO dao = new MemberDAO();
		int cnt = -1;
		if(request.getParameter("mode").equals("fupdate")) {//파일이 있는업데이트.
			
			cnt = dao.memberUpdateFile(vo);
			
		}else { //파일이없는업데이트
			cnt = dao.memberUpdate(vo); //업데이트.
		}
		
		
		
		
		
		
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
