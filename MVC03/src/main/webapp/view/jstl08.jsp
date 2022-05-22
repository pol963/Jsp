<%@page import="java.util.*"%>
<%@page import="kr.bit.model.MemberVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<%
	MemberVO vo = new MemberVO();
	vo.setNum(1);
	vo.setId("coco");
	vo.setName("cocomong");
	vo.setEmail("coco@naver.com");

	List<MemberVO> list = new ArrayList<MemberVO>();
	list.add(vo);
	list.add(vo);
	list.add(vo);
	list.add(vo);
	list.add(vo);
	
	request.setAttribute("list",list);
	
	
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<body>
	<table border="1">
		<tr>
			<td>번호</td>
			<td>아이디</td>
			<td>이름</td>
			<td>이메일</td>
		</tr>
		<c:forEach var="vo" items="${list}">
		<tr>
			<td>${vo.num}</td>
			<td>${vo.id}</td>
			<td>${vo.name}</td>
			<td>${vo.email}</td>
		</tr>	
		</c:forEach>
		
	</table>
</body>
</html>