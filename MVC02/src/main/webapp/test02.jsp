<%@page import="kr.bit.model.MyCalc"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
	MyCalc mCal = new MyCalc();
	
	int sum = mCal.hap(1,10 );
	
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
		<td>합</td>
		<td><%= sum %></td>
	</tr>
</table>


</body>
</html>