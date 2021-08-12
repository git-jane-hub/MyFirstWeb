<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>로그인 창</h2><hr>
	<form action = "/MyFirstWeb/login.do" method = "post">
		<input type = "text" name = "uId" placeholder = "ID" required /><br>
		<input type = "password" name = "uPw" placeholder = "PASSWORD" required /><br>
		<input type = "submit" value = "로그인" />
		<a href = "/MyFirstWeb/reqjoin.do">
			<input type = "button" value = "회원가입하기" /><br>
		</a>
	</form>
</body>
</html>