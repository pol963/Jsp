<%@ page language="java" contentType="text/html; charset=UTF-8" 
	pageEncoding="UTF-8"%>

<%-- 위의 코드는 서버가 클라이언트에게 어떤 유형의 데이터를 응답할것인지에 대한 타입지정코드--%>
<%-- jsp에는 이미 request와 response객체가 이미 만들어져 있다. -> 내장객체.
	session,out,config,application,page,pageContext...모두 jsp의 내장객체입니다.
	내장객체는 서블릿으로 변환이될때 만들어져 있습니다. 
	따라서 jsp페이지에서 내장객체들을 쓴다는 것은 이미 서블릿변환이 되었다는 것 입니다. --%>

<%!
	public int hap(int s,int e){
	int sum = 0;
	for(int i = s; i <= e;i++){
		sum += i;
	}
	
	return sum;
}

%>


<%
int sum = 0;
for(int i = 0; i <= 100;i++){
	sum += i;
}

%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	1~100까지의 합은
	<%= " " +sum+" " %>
	입니다.
	<br> 
	임의의 두숫자의합은?
	<%= hap(20,30) %>
	
	
</body>
</html>