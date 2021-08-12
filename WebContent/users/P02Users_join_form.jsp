<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>회원가입 창</h2><hr>
	<form action = "/MyFirstWeb/join.do" method = "post">
		<input type = "text" name = "uId" placeholder = "ID" required /><br>
		<input type = "password" name = "uPw" placeholder = "PASSWORD" required /><br>
		<input type = "text" name = "uName" placeholder = "NAME" required /><br>
		<input type = "email" name = "uEmail" placeholder = "E-MAIL" required /><br>
		<input type = "submit" value = "회원가입" />
		<a href = "/MyFirstWeb/login.do"><input type = "button" value = "로그인하기" /></a><br>
	</form>
</body>
</html>