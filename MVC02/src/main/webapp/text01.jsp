<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
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

1~100까지의 합은 <%= " " +sum+" " %> 입니다.
<br>
임의의 두숫자의합은? <%= hap(20,30) %>
</body>
</html>