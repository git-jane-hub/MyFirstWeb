<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>게시글 본문</h2><hr>
	<table border = "1"  >
		<tr>
			<td>글번호</td>
			<td>${board.bId }</td>
			<td>조회수</td>
			<td>${board.bHit }</td>
		</tr>
		<tr>
			<td>게시일</td>
			<td>${board.bDate }</td>
		</tr>
		<tr>
			<td>글제목</td>
			<td>${board.bTitle }</td>
		</tr>
		<tr>
			<td>본문</td>
			<%-- 본문은 길어질 수 있기 때문에 textarea로 작성 --%>
			<td><textarea cols = "10" rows = "10" name = "content" readonly>${board.bContent }</textarea></td>
		</tr>
		<tr>
			<td>글쓴이</td>
			<td>${board.bName }</td>
		</tr>
		<tr>
			<%-- 리스트로 돌아가기,수정, 삭제  --%>
			<td>
				<a href = "/MyFirstWeb/boardselect.do">
					<input type = "button" value = "글 목록" />
				</a>
				<form action = "/MyFirstWeb/boardupdate.do" method = "post">
					<input type = "hidden" name = "bId" value = "${board.bId }" />
					<input type = "submit" value = "글 수정" />
				</form>
				<form action = "/MyFirstWeb/boarddelete.do" method = "post">
					<%-- 사용자 눈에는 보이지 않는 버튼 - hidden
					데이터를 전달하는 용도 - post 방식으로 전송시에는 url을 작성할 수 없기 때문 --%>
					<input type = "hidden" name = "bId" value = "${board.bId }" />
					<%-- form에서 action으로 이동하는 버튼이 삭제 --%>
					<input type = "submit" value = "글 삭제" />
				</form>
			</td>
		</tr>
	</table>
	
</body>
</html>