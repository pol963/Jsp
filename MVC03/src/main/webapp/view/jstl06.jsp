<%@page import="java.util.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	//ArrayList의 경우. String타입의 데이터.
	List<String> list = new ArrayList<String>();
	list.add("Python");
	list.add("Java");
	list.add("Node.js");
	list.add("C++");
	list.add("JQuery");
	request.setAttribute("list", list);

%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<c:forEach var="SW" items="${list}">
	${SW}<br>
</c:forEach>


</body>
</html>