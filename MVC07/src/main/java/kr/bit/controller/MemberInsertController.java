package kr.bit.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.bit.model.MemberDAO;
import kr.bit.model.MemberVO;

public class MemberInsertController implements Controller{

	@Override
	public String requestHandler(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String ctx = request.getContextPath();
		
		String id = request.getParameter("id");
		String pass = request.getParameter("pass");
		String name = request.getParameter("name");
		int age = Integer.parseInt(request.getParameter("age"));
		String email = request.getParameter("email");
		String phone = request.getParameter("phone");
		
		MemberVO vo = new MemberVO();
		
		//회원가입화면에서 파일이 추가가 되거나 되지않거나에 따라 파라메터 갯수가 변하기에 if문으로 분류
		
		if(request.getParameter("mode").equals("fadd")) {
			//히든속성으로 넘어오는 input태그 파라메터.
			String filename = request.getParameter("filename");
			vo.setFilename(filename);
		}
		
		
		vo.setId(id);
		vo.setPass(pass);
		vo.setName(name);
		vo.setAge(age);
		vo.setEmail(email);
		vo.setPhone(phone);
		
		MemberDAO dao = new MemberDAO();
		int cnt=-1;
		if(request.getParameter("mode").equals("fadd")) {
			cnt =dao.memberInsertFile(vo); //파일 이름을 저장해야하는 경우
		}else {
			cnt =dao.memberInsert(vo);//파일 이름을 저장할 필요 없는경우
		}
		
		
		//PrintWriter out = response.getWriter();
		String nextPage = null;
		
		if(cnt > 0) {
			nextPage = "redirect:"+ctx+"/memberList.do";
		}else {
			throw new ServletException("Not insert");
		}
		
		return nextPage;
	}

	
	
}
