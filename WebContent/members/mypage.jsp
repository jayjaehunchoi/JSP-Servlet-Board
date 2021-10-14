<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="../js/mypage.js"> </script>
</head>
<body>
<h2>내 정보</h2>
<ul>
    <li>loginId=${member.loginId}</li>
     <li>password=********</li>
    <li>email=${member.email}</li>
</ul>
<div class="container">
		<div class="row">
			<table class="table table-striped" style="text-align: center; border: 1px solid #dddddd">
				<thead>
					<tr>
						<th style="background-color: #eeeeee; text-align: center;">번호</th>
						<th style="background-color: #eeeeee; text-align: center;">제목</th>
						<th style="background-color: #eeeeee; text-align: center;">작성일</th>
					</tr>
				</thead>
				<h3>내가 쓴 최근 게시물</h3>
				<c:forEach var="board" items="${bbs}">
				<tbody>
					<tr>
						<td>${board.bbsID}</td>
						<td><a href="/Matrip/boards/board?bbsID=${board.bbsID}">${board.bbsTitle}</a></td>
						<td>${board.bbsDate}</td>
					</tr>
				</tbody>
				</c:forEach>	
			</table>
			</div>
	</div>
	<br/>
<a href ="/Matrip/member/update">회원정보 관리</a>
<a href="/Matrip">홈</a>
<a href="/Matrip/logout">로그아웃</a>
</body>
</html>