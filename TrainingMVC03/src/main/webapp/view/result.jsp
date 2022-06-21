<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
	String data = request.getParameter("data");
	String num = request.getParameter("num");
	String name = request.getParameter("name");
%>    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<!-- ForwardController에서 주는 데이터 출력 -->
controller에서 받은 데이터 출력 = <%=data%><br>
<%=num %><br>
<%=name %>

</body>
</html>