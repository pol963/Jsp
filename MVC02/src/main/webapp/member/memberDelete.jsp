<%@page import="kr.bit.model.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	//controller용 jsp.
	int num = Integer.parseInt(request.getParameter("num"));
	

	MemberDAO dao = new MemberDAO();
	int cnt =dao.memberDelete(num);
	if(cnt > 0) { //cnt가0보다 크단것은 삭제에 성공했다는 것 입니다.
		 response.sendRedirect("memberList.jsp");
	}else {
		 throw new ServletException("Fail Delete");
	}
	
%>    
