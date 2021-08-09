<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%-- 객체를 생성할 필요없이 method에 작성된 내용으로 doGet 와 doPost을 실행 가능 --%>
	<%-- action에 주소 작성하는 방법
		 1. /에는 localhost:8181이 담겨있음(/만 작성하면 localhost:8181 페이지로 넘어감) - 타겟 페이지와 차이점을 찾아 기입해줌
		 2. 타겟 페이지의 주소를 전부 작성 --%>
	<form action = "http://localhost:8181/MyFirstWeb/apple" method = "post">
		<input type = "submit" value = "SUBMIT" />
	</form>
</body>
</html>