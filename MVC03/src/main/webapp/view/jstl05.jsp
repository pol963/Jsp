<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	//Controller에서 해당 JSP에게 데이터를 넘겨줫다고 가정.
	String[] str = {"사과","바나나","포도","수박","망고"};
	request.setAttribute("str",str);

%>    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<!-- item : 여러개를 받을 수 있는 태그 속성. -->
<c:forEach var="f" items="${str}">
${f}<br>
</c:forEach>


</body>
</html>