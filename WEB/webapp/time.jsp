<%@page import="java.util.Date"%>
<%@ page language="java" 
	contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
  
  <%
  	Date d = new Date();
  %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	CodingTree의 홈페이지 방문을 환영합니다.<br>
	동적인 페이지.<br>
	지금 시간은? <%= d.toString() %> <br>
	자바 코드를 사용하여 동적인페이지 구현.
	
			
	
</body>
</html>