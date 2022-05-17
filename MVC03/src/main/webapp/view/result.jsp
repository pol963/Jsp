<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
	String data = request.getParameter("data");
String age = request.getParameter("age");
String email = request.getParameter("email");

%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
Controller에서 받은 값 출력<br>
<%= data %><br>
<%= age %><br>
<%= email %>
</body>
</html>