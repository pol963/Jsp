<%@page import="kr.bit.model.*"%>
<%@page import="java.util.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
//ArrayList<MemberVO> list = (ArrayList<MemberVO>)request.getAttribute("list");
%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>

<script type="text/javascript">

	/* 
		memberList.jsp페이지에서 클라이언트의 입장으로 로그인을 할시에 틀리게 입력한다면 내보낼 메시지 구현.
		객체바인딩으로 인해 el문법으로 msg를 사용가능합니다.
	*/
	$(document).ready(function(){
		<c:if test="${!empty msg}" >
			alert("${msg}")
			
			/* 
			만약 로그인실패후 msg얼럿이 뜨면 msg는 session으로 만들어져있기에 그 뒤에 로그인에 성공하더라도
			msg는 유지됩니다. -> 계속해서 alert가 뜸.
			이를 방지하기위해 remove(삭제) 사용 var(msg) scope(session으로 만들어진)
			*/
			<c:remove var="msg" scope="session" />
		</c:if>
	});


	function deleteFn(num) {
		location.href="${ctx}/memberDelete.do?num="+num; //
	}
	
	
	/* 
		j쿼리 문법 -> 로그인화면창의 아이디칸에 값이 없다면 false를 반환 -> sumit작동 x 즉,null체크. 유효성검사 
		.val() -> #user_id의 id를 가지고 있는 값을 가져오는 메서드.
	*/
	function check() { 
		if($('#user_id').val()==''){
			alert("아이디를 입력하세요");
			return false;
		}
		
		if($('#password').val()==''){
			alert("비밀번호를 입력하세요");
			return false;
		}
		return true;
	}
	
	

</script>


</head>
<body>
MVC06 회원 정보 관리 -> 로그인후 관리.
	<div class="container">
		<h2>회원관리 시스템</h2>
		<div class="panel panel-default">
			
			<!-- bootstrap3사용 -> 페널 -->
			<div class="panel-heading">
			
				<!-- bootstrap3 form -->
				<form class="form-inline" action="${ctx}/memberLogin.do" method="post">
				
					<div class="form-group">
						<label for="user_id">ID:</label> 
						<input type="text" class="form-control" id="user_id" name="user_id">
					</div>

					<div class="form-group">
						<label for="pwd">Password:</label> 
						<input type="password" class="form-control" id="password" name="password">
					</div>
					
						<!-- 
						자바스크립트문법->로그인을 누르면  check메서드를 처리 리턴해주는것. 리턴값이 false라면 submit작동x
						onclick -> 개체를 클릭시 이벤트 발생.
						-->
					<button type="submit" class="btn btn-default" onclick="return check()">로그인</button>
					
				</form>
			</div>						<!-- heading -->
			
			<div class="panel-body">
				<div class="table-responsive">
					<table class="table table-hover">
						<thead>
							<tr>
								<th>번호</th>
								<th>아이디</th>
								<th>비밀번호</th>
								<th>이름</th>
								<th>나이</th>
								<th>이메일</th>
								<th>전화번호</th>
								<th>삭제</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="vo" items="${list}">
								<tr>
									<td>${vo.num}</td>
									<td><a href="${ctx}/memberContent.do?num=${vo.num}">${vo.id}</a></td>
									<td>${vo.pass}</td>
									<td>${vo.name}</td>
									<td>${vo.age}</td>
									<td>${vo.email}</td>
									<td>${vo.phone}</td>
									<td><input type="button" value="삭제"
										class="btn btn-warning" onclick="deleteFn(${vo.num})"></td>
								</tr>
							</c:forEach>
							<tr>
								<td colspan="8" align="center"><input type="button"
									value="회원가입" class="btn btn-primary"
									onclick="location.href='${ctx}/memberRegister.do'" /></td>
							</tr>
						</tbody>
					</table>
				</div>
			</div>
			
			<div class="panel-footer">
				회원관리 ERP 시스템
			</div>
		</div>
	</div>
</body>
</html>










