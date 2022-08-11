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

<style type="text/css">
	table td{
		vertical-align: middle !important;
	}
</style>

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
	
	function logout() {
		location.href="<c:url value='/memberLogout.do'/>"; //MVC06/memberLogout.do
	}	
	
	
	//collapse의 onclick이벤트에 의해 실행되는 메서드.
	/* collapse1아이디를 가진 div의 .panel-body class에 접근 하여 html을 가져옴. 가져온 값을 html에 저장. */
	/* var html =  $("#collapse1 .panel-body").html();
	alert(html); */
	function memberList() {
		$.ajax({
			url : "<c:url value='/memberAjaxList.do'/>", //서버로 요청
			type : "get",
			dataType : "json",
			success : resultHtml, //<--회원리스트로 받기.([{},{},{}])
			error : function () { error("error");	}
		});
	}
	
	
	//콜백함수 서버와 통신후에 결과를 json데이터타입으로 받아서 콜백함수가 데이터를 받음.
	  function resultHtml(data){	
		//테이블변수안에 틀을 하나씩 넣어주기  
		  var html="<table class='table table-hover'>";
		  html+="<tr>";
		  html+="<th>번호</th>";
		  html+="<th>아이디</th>";
		  html+="<th>비밀번호</th>";
		  html+="<th>이름</th>";
		  html+="<th>나이</th>";
		  html+="<th>이메일</th>";
		  html+="<th>전화번호</th>";
		  html+="<th>삭제</th>";
		  html+="</tr>";	
		  //제이쿼리 반복문 $.each data에서 데이터를 빼서 index를받고 obj로 객체받기.
			//콜백함수가 받은 json데이터를 반복문 메서드를 이용해서 각인덱스의 데이터를 obj에 넣어주기.
		  $.each(data, function(index, obj){
			  html+="<tr>";
			  html+="<td>"+obj.num+"</td>";
			  html+="<td>"+obj.id+"</td>";
			  html+="<td>"+obj.pass+"</td>";
			  html+="<td>"+obj.name+"</td>";
			  html+="<td>"+obj.age+"</td>";
			  html+="<td>"+obj.email+"</td>";
			  html+="<td>"+obj.phone+"</td>";		  
			  html+="<td><input type='button' value='삭제' class='btn btn-warning' onclick='delFn("+obj.num+")'></td>";
			  html+="</tr>";
		  });	  
		  html+="</table>";	  
		  //jquery문법(javascript를 간편하게 줄인것 더 강력) 으로 id선택후 자식클래스 선택후 html을 보여주기.
		  $("#collapse1 .panel-body").html(html);	  
	  }
	
	//ajax 삭제기능.
	function delFn(num) {
		$.ajax({
			url : "<c:url value='/memberAjaxDelete.do'/>",
			type : "get",
			data : {"num":num},
			success : memberList,
			error : function(){ alert("error");	}
			
		});
		
	}
	
	
	
</script>


</head>
<body>
MVC07 회원 정보 관리 -> Ajax
	<div class="container">
		<h2>회원관리 시스템</h2>
		<div class="panel panel-default">
			
			<!-- bootstrap3사용 -> 페널 -->
			<div class="panel-heading">
			
				<!-- 회원인증 성공시 로그인화면 가리기. if문의 조건이 거짓이 되면(로그인정보가 일치하면) 거짓이되기에 if문안에있는
					코드가 안보이게 됩니다. 
					만약 데이터가 없다면 조건문이 참이 되기에 아래 코드가 계속보입니다.
					-->
					
				<c:if test="${sessionScope.userId==null || sessionScope.userId=='' }"> 	
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
				</c:if>
				
				<!-- 
				회원인증에 성공할시(데이터가 일치한다면 나올 화면) 로그아웃 활성화.  
				MemberLoginCotroller에서 setAttribute로 id와 name를 객체바인딩 해주었기에 el문법으로 쉽게접근이 가능.
				데이터가 있면 if문이 참이 되기에 if문 안의 코드가 보이게 됩니다.
				-->
				<c:if test="${sessionScope.userId!=null && sessionScope.userId!='' }">
				${sessionScope.userName}님 환영합니다.
				<button type="button" class="btn btn-warning" onclick="logout()">로그아웃</button>
				</c:if>
				
				
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
								<th>이미지</th>
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
									
									<!-- 
									if문으로 이미지가 있을때와 없을때를 구분하여 뿌려주기.
									 -->
									<td>
										<c:if test="${vo.filename != null && vo.filename != ''}">
											<img src="<c:out value='file_repo/${vo.filename}'/>" 
											width="60px" height="60px" >
										</c:if>
									</td>
									
									<!-- 본인의 아이디와 같다면 disabled속성이 없는if문을 실행 삭제버튼활성화.
									vo.id는 DAO에서 넘어온 id userID는 Controller에서 넘어온 id(입력한) -->
									<c:if test="${sessionScope.userId==vo.id}">
									<td><input type="button" value="삭제"
										class="btn btn-warning" onclick="deleteFn(${vo.num})"
										></td> <!-- disabled버튼 비활성화 속성. -->
									</c:if>
									
									<!-- 본인 아이디와 다르다면 비활성화. -->
									<c:if test="${sessionScope.userId!=vo.id}">
									<td><input type="button" value="삭제"
										class="btn btn-warning" onclick="deleteFn(${vo.num})"
										disabled="disabled"></td> <!-- disabled버튼 비활성화 속성. -->
									</c:if>	
										
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
	
	<div class="panel-group">
	  <div class="panel panel-default">
	    <div class="panel-heading">
	      <h4 class="panel-title"> 
	      	<!-- href속성은 아래 div의 id를 가르킨다. -->
	        <a data-toggle="collapse" href="#collapse1" onclick="memberList()">회원리스트보기</a>
	      </h4>
	    </div>
	    <div id="collapse1" class="panel-collapse collapse">
	      <div class="panel-body">Panel Body</div>
	      <div class="panel-footer">Panel Footer</div>
	    </div>
	  </div>
	</div>
			
	</div>
</body>
</html>
