
package kr.bit.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import kr.bit.model.MemberDAO;
import kr.bit.model.MemberVO;

public class MemberAjaxListController implements Controller{

	@Override
	public String requestHandler(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

			MemberDAO dao = new MemberDAO();
			List<MemberVO> list = dao.memberList();
			
			//List타입의 데이터를 gson을 이용하여 json으로 변경해주기.
			Gson g = new Gson();
			String json = g.toJson(list);
			System.out.println(json);
			
			
			//요청(ajax)에 대한 응답을 json형식으로 응답해주기.
			response.setContentType("text/json;charset=euc-kr");
			response.getWriter().print(json);
			
			
		return null;
	}

}

