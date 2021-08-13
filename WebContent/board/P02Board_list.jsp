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
	<h2>게시글 목록</h2><hr>
	<table border = "1">
	<thead>
		<tr>
			<th>번호</th>
			<th>제목</th>
			<th>글쓴이</th>
			<th>게시일</th>
			<th>조회수</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach var = "board" items = "${boardList }">
			<tr>
				<%-- 실제로는 getter를 호출하는 것, 해당 변수의 접근제한자는  private --%>
				<td>${board.bId }</td>
				<td><a href = "/MyFirstWeb/boarddetail.do?bId=${board.bId }">${board.bTitle }</a></td>
				<td>${board.bName }</td>
				<td>${board.bDate }</td>
				<td>${board.bHit }</td>
			</tr>
		</c:forEach>
	</tbody>
	</table>
	<!-- 페이징 버튼 시작  -->
	<!-- 표현할 글이 있는 경우만 버튼을 나타냄  -->
	<c:if test = "${pageDTO.hasBoard() }">
		<!-- 이전으로 가는 버튼 - 시작페이지가 11이상이면 만들어줌  -->
		<c:if test = "${pageDTO.startPage > 10 }">
			<a href = "/MyFirstWeb/boardselect.do?page=${pageDTO.startPage - 10 }">[PREV]</a>
		</c:if>
		
		<!-- 페이지 번호 10개 묶음을 나열  -->		
		<c:forEach var = "pNo" begin = "${pageDTO.startPage }" end = "${pageDTO.endPage }">
			<a href = "/MyFirstWeb/boardselect.do?page=${pNo }" >[${pNo }]</a>
		</c:forEach>
		
		<!-- 다음으로 가는 버튼 -  -->
		<c:if test = "${pageDTO.endPage < pageDTO.totalPages }">
			<a href = "/MyFirstWeb/boardselect.do?page=${pageDTO.startPage + 10 }">[NEXT]</a>
		</c:if>
	</c:if>
	<!-- 페이징 버튼 끝  -->
	<form action = "/MyFirstWeb/boardreqwrite.do" >
		<input type = "submit" value = "글 작성" />
	</form>
	<form action = "/MyFirstWeb/logout.do" method = "post">
		<input type = "submit" value = "로그아웃" />
	</form>
</body>
</html>