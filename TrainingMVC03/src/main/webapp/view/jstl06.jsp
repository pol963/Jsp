<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%
	List<String> list = new ArrayList<String>();
	list.add("파이썬");
	list.add("자바");
	list.add("c++");
	request.setAttribute("list", list);

%>    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<c:forEach var="f" items="${list}">
${list}<br>

</c:forEach>


</body>
</html>









