<%@page import="kr.bit.model.MemberVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	//회원정보와 같은 "객체"의 경우.
	MemberVO vo = new MemberVO();
	vo.setNum(1);
	vo.setId("coco");
	vo.setName("cocomong");
	vo.setEmail("coco@naver.com");
	request.setAttribute("vo", vo);
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
			<td>번호</td>
			<td>아이디</td>
			<td>이름</td>
			<td>이메일</td>
		</tr>
		<tr>
			<td>${vo.num}</td> <!-- 해당부분은 getNum과 같은 getter,setter로 변환되어 호출되기에 private가 상관없습니다. -->
			<td>${vo.id}</td>
			<td>${vo.name}</td>
			<td>${vo.email}</td>
		</tr>
		
	</table>
</body>
</html>