<%@page import="kr.bit.model.MemberDAO"%>
<%@page import="kr.bit.model.MemberVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%
	request.setCharacterEncoding("utf-8");
	
	String id = request.getParameter("id");
	String pass = request.getParameter("pass");
	String name = request.getParameter("name");
	int age =  Integer.parseInt(request.getParameter("age"));
	String email = request.getParameter("email");
	String phone = request.getParameter("phone");
	
	//MemberVO vo = new MemberVO(id, pass, name, age, email, phone);
	
	MemberVO vo = new MemberVO();
	
	vo.setId(id);
	vo.setPass(pass);
	vo.setName(name);
	vo.setAge(age);
	vo.setEmail(email);
	vo.setPhone(phone);
	
	//System.out.println(vo.toString());
	
	MemberDAO dao = new MemberDAO();
	int cnt = dao.memberInsert(vo);
	
	if(cnt>0) {
		//가입성공
		//out.println("insert success");
		response.sendRedirect("memberList.jsp");
		
		
	}else {
		//가입실패
		throw new ServletException("not insert");
	}
	
	
	%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

</body>
</html>