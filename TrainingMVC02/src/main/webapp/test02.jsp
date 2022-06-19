<%@page import="kr.bit.model.MyCalc"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
	MyCalc my = new MyCalc();
	int v = my.hap(1, 100);
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
		<td>1~100까지의 합</td>
		<td><%= v %></td>
	</tr>
</table>


</body>
</html>