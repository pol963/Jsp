<%@page import="kr.bit.model.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
	
	//MemberVO vo =  (MemberVO)request.getAttribute("vo");

%>

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
	function update() {

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
				
				url : "<c:url value='/fileAdd.do'/>", // fileAdd.do(파일업로드), //fileAdd.do -> 파일업로드컨트롤러.
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
					//파일이 있는 경우에 fadd를 넣어서 넘겨주기.
					document.form1.action="<c:url value='/memberUpdate.do'/>?mode=fupdate";
					document.form1.submit();
				//alert(data);
				
				//C:\eGovFrame-4.0.0\workspace.edu\.metadata\.plugins\
				//org.eclipse.wst.server.core\tmp0\wtpwebapps\MVC07 -> 임시업로드 경로
				
				},
				error : function(){ alert("error"); }
			
				
			});
			
		}else{
			//없는 경우 -> 넘겨줄 파일이 없다해도 가입은 가능하게끔.
			//파일이 없는경우 mode에 add를 담아서 넘겨주기.
			document.form1.action="<c:url value='/memberUpdate.do'/>?mode=update";
			document.form1.submit();
		}
		
	}
	
	function frmreset() {
		document.form1.reset();
	}
	function getFile(filename) {
		location.href="<c:url value='/fileGet.do'/>?filename="+filename;
	}
	
	//파일 삭제 메서드 구현.
	function delFile(num,filename) {
		location.href="<c:url value='/fileDel.do'/>?num="+num+"&filename="+filename;
	}
	
</script>

</head>
<body>

<!-- 
보통 bootstrap3로 레이아웃을 잡습니다 -> panels사용. 
bootstrap이란 프레임워크->재사용가능한 요소의 집합.	
 -->
<div class="container">
<h2>상세화면</h2>  
  <div class="panel panel-default">
    <div class="panel-heading">
    	<c:if test="${sessionScope.userId != null && sessionScope.userId !='' && sessionScope.userId == vo.id}">
			<lable>
			<img src='<c:out value="file_repo/${vo.filename }"/>' width="60px" height="60px" />
			${sessionScope.userName}님 반갑습니다.
			</lable>
		</c:if>
		<c:if test="${sessionScope.userId == null || sessionScope.userId =='' }">
			<lable>반갑습니다.</lable>
		</c:if>
    </div>
    <div class="panel-body">
    <form id="form1" name="form1" class="form-horizontal"  method="post">
    	<input type="hidden" name="num" value="${vo.num}"/>
    	
    	<input type="hidden" name="filename" id="filename" value=""/>
    	
    	<div class="form-group">
    		<label class="control-lable col-sm-2">번호:</label>
    		<div class="col-sm-10">
    			<c:out value="${vo.num}" />
    		</div>
    	</div>
    	<div class="form-group">
    		<label class="control-lable col-sm-2">아이디:</label>
    		<div class="col-sm-10">
    			<c:out value="${vo.id}" />
    		</div>
    	</div>
    	<div class="form-group">
    		<label class="control-lable col-sm-2">비밀번호:</label>
    		<div class="col-sm-10">
    			<c:out value="${vo.pass}" />
    		</div>
    	</div>
    	<div class="form-group">
    		<label class="control-lable col-sm-2">이름:</label>
    		<div class="col-sm-10">
    			<c:out value="${vo.name}" />
    		</div>
    	</div>
    	<div class="form-group">
    		<label class="control-lable col-sm-2">나이:</label>
    		<div class="col-sm-10">
    			<input type="text" class="form-control"
    			 id="age" name="age" value="${vo.age}" style="width: 10%" >
    		</div>
    	</div>
    	<div class="form-group">
    		<label class="control-lable col-sm-2">이메일:</label>
    		<div class="col-sm-10">
    			<input type="text" class="form-control"
    			 id="email" name="email" value="${vo.email}" style="width: 30%">
    		</div>
    	</div>
    	
    	<div class="form-group">
    		<label class="control-lable col-sm-2">전화번호:</label>
    		<div class="col-sm-10">
    			<input type="text" class="form-control"
    			 id="phone" name="phone" value="${vo.phone}" style="width: 30%">
    		</div>
    	</div>
    	
    	<div class="form-group">
    		<label class="control-lable col-sm-2">첨부파일:</label>
    		<div class="col-sm-10">
				<input type="file" id="file" name="file" />
				    
				<!-- 첨부 파일이 있는 경우에만 첨부파일이 나오게 하기. -->			
				<c:if test="${vo.filename != null && vo.filename != '' }">
					<a href="javascript:getFile('${vo.filename}')"><c:out value='${vo.filename }'/></a>
				</c:if>
				
				<!-- 
				리스트에서 회원인증을 하엿고 인증한 아이디와 content.jsp의 상세보기가 같다면
				파일삭제 기능 구현. 파일이 있다면.
				 -->
				 <c:if test="${sessionScope.userId != null && sessionScope.userId==vo.id && 
				 					vo.filename != null && vo.filename !=''}">
				 		<a href="javascript:delFile('${vo.num}','${vo.filename}')"><span class="glyphicon glyphicon-remove"></span></a>
				 </c:if>
			
			
    		</div>
    	</div>
    	
    	
    	
    	</form>
    </div>
    
    <div class="panel-footer" style="text-align: center;">
    
    	<c:if test="${!empty sessionScope.userId }">
    	  <c:if test="${sessionScope.userId==vo.id }">
    	    <input type="button" value="수정하기" class='btn btn-primary'
    	    onclick="update()" />
    	</c:if>
    	
    	<c:if test="${sessionScope.userId!=vo.id }">  
    	  <input type="button" value="수정하기" class='btn btn-primary'
    	  onclick="update()" disabled="disabled"/>
    	</c:if>
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