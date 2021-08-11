<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>글 수정</h2><hr>
	<form action = "/MyFirstWeb/boardupdateok.do" method = "post">
		<!-- 수정된 데이터를 업데이트하기 위해서 DB에 데이터를 전부 전달해야함 
			input태그의 hidden속성을 이용해 사용자가 수정할 수 없는 데이터도 전달 -->
		<input type = "hidden" name = "bId" value = "${board.bId }" />
		<input type = "hidden" name = "bHit" value = "${board.bHit }" />
		<input type = "hidden" name = "bDate" value = "${board.bDate }" />
		<input type = "hidden" name = "bName" value = "${board.bName }" />
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
				<%-- 수정할 수 있어야하는 부분 --%>
				<td>글제목</td>
				<td>
					<input type = "text" name = "bTitle" value = "${board.bTitle }" />
				</td>
			</tr>
			<tr>
				<%-- 수정할 수 있어야하는 부분 --%>
				<td>본문</td>
				<td><textarea cols = "10" rows = "10" name = "bContent">${board.bContent }"</textarea></td>
			</tr>
			<tr>
				<td>글쓴이</td>
				<td>${board.bName }</td>
			</tr>
			<tr>
				<td>
					<input type = "submit" value = "수정 완료" />
					<input type = "reset" value = "초기화" />
					<a href = "/MyFirstWeb/boardselect.do">
						<input type = "button" value = "글목록" />
					</a>
				</td>
			</tr>
		</table>
	</form>
</body>
</html>