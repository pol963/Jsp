<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<c:set var="ctx" value="${pageContext.request.contextPath}" />
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
	
	<script type="text/javascript">
		function add() {
			//form의 데이터 유효성체크 부분 넣기 
			document.form1.action="<c:url value='/memberInsert.do' />"
			document.form1.submit();
		}
		function frmreset() {
			document.form1.reset();
		}
		function doublecheck() {
			if($("#id").val()==''){
				alert("아이디를 입력하세요");
				$("#id").focus();
				return;  
			}
			
			var id = $("#id").val();
			
			/* 서버와 클라이언트간의 비동기통신. */
			$.ajax({
				
				/* 경로 */
				url : "<c:url value='/memberDbcheck.do' />",
				
				/* 서버가 요청하는 타입 */
				type : "POST",
				
				/* url경로에 데이터를 넘기는 값 id라는 변수에 id저장.*/
				data : {"id" : id},
				
				/* 요청이성공할시 발생하는 이벤트 즉,응답에 대한 메서드 구현(콜백함수)*/
				success : dbCheck,
				
				//요청실패시 메서드 호출 alert보여주기.
				error : function () { alert("error"); }
				
				});
			
		}
		//요청 성공시 데이터 받아와서 처리하는 콜백함수 구현.
		function dbCheck(data) {
			//요청시에 데이터를 받아왔는데 데이터가 있다면 중복이 되었다는 것.
			if(data != "null"){
				alert("중복된 id입니다.");
				$("#id").focus();
			}else{
				alert("사용 가능한 id입니다.");
				$("#id").focus();
			}
		}	
	
	</script>

</head>
<body>


	<!-- 회원 등록 -->

<div class="container">
  <h2>회원 가입 화면</h2>
  <div class="panel panel-default">
   	<div class="panel-heading">
    	<c:if test="${sessionScope.userId != null && sessionScope.userId !='' }">
			<lable>${sessionScope.userName}님 반갑습니다.</lable>
		</c:if>
		<c:if test="${sessionScope.userId == null || sessionScope.userId =='' }">
			<lable>반갑습니다.</lable>
		</c:if> 
    </div>
    <div class="panel-body">
		
		<form id="form1" name="form1" class="form-horizontal" method="post">
		    <div class="form-group">
		      <label class="control-label col-sm-2" for="id">아이디:</label>
		      <div class="col-sm-10">
		      
		        <table>
		        <tr>
		      	  <td><input type="text" class="form-control" id="id" name="id" 
		      	  placeholder="아이디를 입력하세요."></td>
		      	  <td>
		      	  	<input type="button" value="중복체크" class="btn btn-warning" 
		      	  	onclick="doublecheck()"/>
		      	  </td>
		        </tr>
		      
		        
		      </table>
		      
		      </div>
		    </div>
		    
		    <div class="form-group">
		      <label class="control-label col-sm-2" for="pass">패스워드:</label>
		      <div class="col-sm-10">          
		        <input type="password" class="form-control" id="pass" name="pass" 
		        placeholder="패스워드를 입력하세요." style="width: 30%">
		      </div>
		    </div>
		    
		    <div class="form-group">
		      <label class="control-label col-sm-2" for="name">이름:</label>
		      <div class="col-sm-10">          
		        <input type="text" class="form-control" id="name" name="name" 
		        placeholder="이름 입력" style="width: 10%">
		      </div>
		    </div>
		    
		    <div class="form-group">
		      <label class="control-label col-sm-2" for="age">나이:</label>
		      <div class="col-sm-10">          
		        <input type="text" class="form-control" id="age" name="age" 
		        placeholder="나이 입력" style="width: 10%">
		      </div>
		    </div>
		    
		    <div class="form-group">
		      <label class="control-label col-sm-2" for="email">이메일:</label>
		      <div class="col-sm-10">          
		        <input type="text" class="form-control" id="email" name="email" 
		        placeholder="이메일을 입력하세요." style="width: 30%">
		      </div>
		    </div>
		    
		    <div class="form-group">
		      <label class="control-label col-sm-2" for="phone">전화번호:</label>
		      <div class="col-sm-10">          
		        <input type="text" class="form-control" id="phone" name="phone" 
		        placeholder="전화번호를 입력하세요." style="width: 30%">
		      </div>
		    </div>
		    
		 </form>
		
	</div>
    <div class="panel-footer" style="text-align: center;">
    
    	<c:if test="${sessionScope.userId == null || sessionScope.userId == '' }">
		  <input type="button" value="등록" class='btn btn-primary' onclick="add()"/>
		</c:if>
		
		<c:if test="${sessionScope.userId != null && sessionScope.userId != '' }">
		  <input type="button" value="등록" class='btn btn-primary' onclick="add()" disabled="disabled"/>
		</c:if>
		
		<input type="button" value="취소하기" class='btn btn-warning'
		onclick="frmreset()"/>
		
		<input type="button" value="리스트" onclick="location.href='${ctx}/memberList.do'" 
		class='btn btn-success'/>
	</div>
  </div>
</div>



</body>
</html>

	