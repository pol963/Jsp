<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ page import="kr.bit.model.*"%>

<%
//한글처리 -> 기본적으로 데이터는 1byte씩 주고받기때문에 2byte인 한글은 깨질게 됩니다. 해결하기위해 문자를 2byte씩 주고받게끔 요청하게 됩니다.
request.setCharacterEncoding("utf-8");

//1.파라메터 수집

//정보받기.
String id = request.getParameter("id");
String pass = request.getParameter("pass");
String name = request.getParameter("name");
int age = Integer.parseInt(request.getParameter("age"));
String email = request.getParameter("email");
String phone = request.getParameter("phone");

MemberVO vo = new MemberVO();
vo.setId(id);
vo.setPass(pass);
vo.setName(name);
vo.setAge(age);
vo.setEmail(email);
vo.setPhone(phone);

MemberDAO dao=new MemberDAO();
int cnt=dao.memberInsert(vo);
if(cnt>0) {
    // 가입성공 
    response.sendRedirect("memberList.jsp");
}else {
    // 가입실패-> 예외객체를 만들어서  WAS에게 던지자.
    throw new ServletException("not insert");    }
%>
