<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
    
    int sum = 0;
    for(int i = 1; i <= 10 ; i++){
    	sum+=i;
    }
    
    %>
    
    <%!
    	public int hap(int a ,int b){
    	int sum = 0;
    	for(int i = a; i <= b ; i++){
    		sum += i;
    	}
    	
    	return sum;
    }
    
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
		<td>1~10까지의 합은</td>
		<td><%=sum %></td>
	</tr>
	<tr>
		<td>5~10까지의 합은</td>
		<td><%=hap(5,10)%></td>
	</tr>
</table>
</body>
</html>