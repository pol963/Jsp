package kr.bit.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.bit.model.MemberDAO;
import kr.bit.model.MemberVO;

@WebServlet("/memberList.do")
public class MemberListController extends HttpServlet {

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// 1. 클라이언트의 요청 받기(맵핑에 의해 memberList.do) //넘어오는 파라미터 값은 없기에 response만 사용.

		// 2. 회원 전체 리스트 가져오기 -> Model의 JDBC사용. -> DB와 연동하여 데이터를 Model로 가져와 다시
		// Controller로 가져오기.
		MemberDAO dao = new MemberDAO();

		// DAO의 메서드 호출 리턴값(리스트)을 list에 저장.
		ArrayList<MemberVO> list = dao.memberList();

		// 3. 회원 전체 리스트를 HTML로 만들어서 응답하기.
		//응답되는 데이터에 한글이 있는경우 encoding필수.
		//MINE TYPE 서버가 클라이언트에게 어떤 유형의 데이터타입을 보내는지 알려주는것.
		response.setContentType("text/html;charset=utf-8"); 
		
		/* System.out.println(list); */
		PrintWriter out = response.getWriter();
		out.println("<html>");
		out.println("<body>");
		out.println("<table border='1'>");
			out.println("<thead>");
			out.println("<tr>");
			out.println("<th>번호</th>");
			out.println("<th>아이디</th>");
			out.println("<th>비밀번호</th>");
			out.println("<th>이름</th>");
			out.println("<th>나이</th>");
			out.println("<th>이메일</th>");
			out.println("<th>전화번호</th>");
			out.println("</tr>");
			out.println("</thead>");
		out.println("<body>");
		
		for(MemberVO vo : list ) {
			out.println("<tr>");
				out.println("<td>"+vo.getNum()+"</td>");
				out.println("<td>"+vo.getId()+"</td>");
				out.println("<td>"+vo.getPass()+"</td>");
				out.println("<td>"+vo.getName()+"</td>");
				out.println("<td>"+vo.getAge()+"</td>");
				out.println("<td>"+vo.getEmail()+"</td>");
				out.println("<td>"+vo.getPhone()+"</td>");
			out.println("</tr>");
		}
		out.println("</tbody>");
		out.println("<tr>");
		out.println("<td colspan='7' align='center'>");
		out.println("<a href='member/memberRegister.html'>회원가입</a>");
		out.println("</td>");
		out.println("</tr>");
		out.println("</table>");
		out.println("</body>");
		out.println("</html>");

	}

}
