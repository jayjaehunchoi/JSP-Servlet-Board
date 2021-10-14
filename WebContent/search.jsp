<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<form action="/Matrip/search">
	<input class ="join" value="${input}" name="search" type="text">
	<input class = "joinBt" type="submit" value="검색">
	<select name="searchType" >
	    <option value="blog">=== 선택 ===</option>
	    <option value="blog" selected>블로그</option>
	    <option value="cafearticle">카페</option>
	    <option value="kin">지식IN</option>
	    <option value="local">지역</option>
  	</select>
</form>
<div class="container">
		<div class="row">
			<table class="table table-striped" style="text-align: center; border: 1px solid #dddddd">
				<thead>
					<tr>
						<th style="background-color: #eeeeee; text-align: center;">제목</th>
						<th style="background-color: #eeeeee; text-align: center;">요약</th>
					</tr>
				</thead>
				<h3>${type} 검색 결과</h3>
				<c:forEach var="results" items="${searchResults}">
				<tbody>
					<tr>
					<c:if test="${results.link eq ''}"  var="result" scope="page">
				  		<td><a href="void(0);" onclick="alert('링크가 등록되지 않았습니다.');return false;">${results.title}</a></td>
				  	</c:if>
				  	<c:if test="${results.link ne ''}"  var="result" scope="page">
				  		<td><a href="${results.link}">${results.title}</a></td>
				  	</c:if>
				  	<c:if test="${results.description eq ''}"  var="result" scope="page">
				  		<td>요약이 등록되지 않았습니다.</td>
				  	</c:if>
				  	<c:if test="${results.link ne ''}"  var="result" scope="page">
				  		<td>${results.description}</td>
				  	</c:if>
					</tr>
					
				</tbody>
				</c:forEach>	
			</table>
			</div>
	</div>
<a href="/Matrip">홈</a>
</body>
</html>