<%@page import="kr.bit.model.MemberVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%

	MemberVO vo = (MemberVO)request.getAttribute("vo");

%>
    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
이름 : <%=vo.getName() %> <br>
나이 : <%=vo.getAge() %> <br>
이메일 : <%=vo.getEmail() %>
</body>
</html>