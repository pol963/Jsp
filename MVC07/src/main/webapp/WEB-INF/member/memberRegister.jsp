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
			if(data != "NO"){
				alert("중복된 id입니다.");
				$("#id").focus();
			}else{
				alert("사용 가능한 id입니다.");
				$("#id").focus();
			}
		}	
		
		//파일첨부메서드 구현
		function add2() {
			
			//파일의 첨부여부 체크
			if($("#file").val() != ''){
				//파일이 있는 경우
				//formData형식으로 전환 자바스크립트에서 제공해주는 클래스
				var formData = new FormData();
				
				//formData변수에 append메서드를 이용해 file이란 이름으로 자바스크립트를 이용해
				//input태그의 name가 file라는 0번쨰 인덱스중 0번째에 있는 파일을 formData에 저장.
				formData.append("file",$("input[name=file]")[0].files[0]);
				
				//데이터를 서버에 전송하기 위한 Ajax
				$.ajax({
					
					url : "<url : value='/fileAdd.do' />", //fileAdd.do -> 파일업로드컨트롤러.
					type : "post",
					data : formData,
					
					//form데이터를 넘길때에는 processData 라는 변수에 false를 넣어줘야 합니다.
					processData : false,
					//마찬가지로 contentType변수에도 false를 넣어줘야 합니다.
					contentType : false,
					
					//파일을 업로드했다면 업로드한 파일의 이름을 받아올것.
					//내가올린 파일이 서버에 이미 있다면 파일이름을 서버는 바꿀것이기에 서버자체에 저장된 이름을 가져와야 합니다.
					//파일을 성공적으로 올릴시에 data를 받아옵니다.
					success : function (data) {//업로드된 실제 파일 이름을 전달 받기.
						//파일 이름을 임시로 저장해놓을 변수 하나 만들기.
						//db에 파일은 파일대로 파일 이름을 파일 이름대로 넘기기 위해 나중에 가져올때 파일이름을 가져오기위하여.
						$('#filename').val(data); //file라는 변수에 업로드된파일의 이름(data) 저장
							
						//form1태그에서 add엿을때는 위해서 바로 insert를 해줫으니ㅏ add2메서드에서는 insert를 아직안해줘기에 구현
						//파일이 업로드가 된 후에는 실제 데이터(각 파라메터)들을 업로드 시켜줘야 합니다.
						document.form1.action="<c:url value='/memberInsert.do'>";
						document.form1.submit();
					},
					error : function(){ alert("error"); }
				
					
				});
				
			}else{
				//없는 경우
				
				
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
		    
		    <!-- 파일 업로드구현 -->
		    <div class="form-group">
		      <label class="control-label col-sm-2" for="">첨부파일:</label>
		      <div class="col-sm-10">          
		        <input type="file" class="control-label" id="file" name="file">
		      </div>
		    </div>
		    
		    <!-- 
		    ajax에 의해 파일이 성공적으로 넘어갔다면 data(파일이름)을 리턴받아옵니다.
		    그 파일이름을 filename에 저장 hidden속성에 의해 눈에 보이지 않게 회원가입시 넘어갑니다. -->
		    <input type="hidden" id = "filename" name="filename" value="" />
		    
		 </form>
		
	</div>
    <div class="panel-footer" style="text-align: center;">
    
    	<c:if test="${sessionScope.userId == null || sessionScope.userId == '' }">
		  <input type="button" value="등록" class='btn btn-primary' onclick="add2()"/>
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

	