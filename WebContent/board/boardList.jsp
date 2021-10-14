<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix="c" %>  
 <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<c:if test="${member ne null}"  var="result" scope="page">
  	<p>${member.loginId}님 환영합니다.</p>
</c:if>
<div class="container">
		<div class="row">
			<table class="table table-striped" style="text-align: center; border: 1px solid #dddddd">
				<thead>
					<tr>
						<th style="background-color: #eeeeee; text-align: center;">번호</th>
						<th style="background-color: #eeeeee; text-align: center;">제목</th>
						<th style="background-color: #eeeeee; text-align: center;">작성자</th>
						<th style="background-color: #eeeeee; text-align: center;">작성일</th>
					</tr>
				</thead>
				<c:forEach var="board" items="${bbs}">
				<tbody>
					<tr>
						<td>${board.bbsID}</td>
						<td><a href="/Matrip/boards/board?bbsID=${board.bbsID}">${board.bbsTitle}</a></td>
						<td>${board.loginId}</td>
						<td>${board.bbsDate}</td>
					</tr>
				</tbody>
				</c:forEach>	
			</table>
			</div>
	</div>
	<c:if test="${previousPage ne null}"  var="result" scope="page">
	  	<a href="/Matrip/boards?page=${previousPage}">이전</a>
	</c:if>
	<c:if test="${pageList ne null}"  var="result" scope="page">
	  	<c:forEach var="pages" items="${pageList}">
	  		<a href="/Matrip/boards?page=${pages}">${pages}</a>
	  	</c:forEach>
	</c:if>
	<c:if test="${nextPage ne null}"  var="result" scope="page">
	  	<a href="/Matrip/boards?page=${nextPage}">다음</a>
	</c:if>
	<br/>
	<a href="/Matrip/boards/write">글 작성</a>
	<a href="/Matrip">홈</a>
</body>
</html>