package kr.bit.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
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


		//회원 전체 리스트 가져온 후 View에 넘겨주기.
		MemberDAO dao = new MemberDAO();
		ArrayList<MemberVO> list = dao.memberList();
		
		//객체 바인딩 (request객체 바인딩)
		request.setAttribute("list", list );
		//forward 기법 이용
		// 의뢰하기.
		RequestDispatcher rd = request.getRequestDispatcher("member/memberList.jsp");
		//객체넘겨주기 -> 이떄 request에는 객체바인딩에 의해 데이터가 들어있는 상태로 jsp에서 데이터 사용가능.
		rd.forward(request, response);

	}

}
