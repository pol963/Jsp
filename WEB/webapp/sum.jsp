<%@page import="kr.web.util.MyUtil"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
 <%
 	//비즈니스 로직 분리후 객체생성.
 	MyUtil my = new MyUtil();
 	int sum = my.hap();
 %>   
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<table border="1">
	<tr>
		<td>1~100까지의 총합.</td>
		<td><%= sum %></td>
	</tr>
</table>

</body>
</html>