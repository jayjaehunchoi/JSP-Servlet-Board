<%@page import="java.net.URLEncoder"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%> 
 <%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix="c" %>  
 <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<div class="container">
		<div class="row">
			<table class="table table-striped" style="text-align: center; border: 1px solid #dddddd">
				<thead>
					<tr>
						<th colspan="3" style="background-color: #eeeeee; text-align: center;">게시판 글보기</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td style="width: 20%;" >글 제목</td>
						<td colspan="2">${bbs.bbsTitle}</td>
					</tr>
					<tr>
						<td>작성자</td>
						<td colspan="2">${bbs.loginId}</td>
					</tr>
					<tr>
						<td>작성일자</td>
						<td>${bbs.bbsDate}</td>
					</tr>
					<tr>
						<td>내용</td>
						<td colspan="2" style="min-height: 200px; text-align: left;">${bbs.bbsContent}</td>
					</tr>
					<tr>
						<td>파일</td>
						<c:choose>
							<c:when test="${file eq null}">
							<td colspan="2" style="min-height: 200px; text-align: left;">첨부파일 없음</td>
							</c:when>
							<c:otherwise>
								<td colspan="2" style="min-height: 200px; text-align: left;"><a href="/Matrip/boards/download?file=${files}">${file.fileName}</a></td>		
							</c:otherwise>
						</c:choose>
						
					</tr>
				</tbody>
			</table>
<div class="container">
		<div class="row">
			<table class="table table-striped" style="text-align: center; border: 1px solid #dddddd">
				<thead>
					<tr>
						<th colspan="4" style="background-color: #eeeeee; text-align: center;">댓글</th>
					</tr>
				</thead>
				<c:if test="${fn:length(commentList) ne 0}"  var="result" scope="page">
				<c:forEach var="comments" items="${commentList}">
				<tbody>
					<tr>
						<td style="width: 20%;">${comments.memberLoginId}</td>
						<td colspan="2">${comments.commentContent}</td>
						<c:if test="${member.loginId eq comments.memberLoginId}"  var="result" scope="page">
						<td><a onclick="return confirm('정말로 삭제하시겠습니까?')" href="/Matrip/comments/delete?commentId=${comments.id}">삭제</a></td>
						</c:if>
					</tr>
				</tbody>
				</c:forEach>
				</c:if>	
			</table>
		</div>
	</div>
	<form method="post" action="/Matrip/comments/save">
			<table class="table table-striped" style="text-align: center; border: 1px solid #dddddd">
				<thead>
					<tr>
						<th colspan="4" style="background-color: #eeeeee; text-align: center;">댓글 입력</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td><input type="text" class="form-control" placeholder="댓글을 입력하세요" name="commentContent" maxlength="50"></td>
						<td colspan="3"><input type="submit" class="btn btn-primary pull-right" value="입력"></td>
					</tr>
				</tbody>
			</table>
		</form>
			<a href="/Matrip/boards?page=<%=session.getAttribute("page") %>" class="btn btn-primary">목록</a>
			<c:if test="${member.loginId eq bbs.loginId}"  var="result" scope="page">
			  	<a href="/Matrip/boards/update?bbsID=${bbs.bbsID}" class="btn btn-primary">수정</a>
				<a onclick="return confirm('정말로 삭제하시겠습니까?')" href="/Matrip/boards/delete?bbsID=${bbs.bbsID}" class="btn btn-primary">삭제</a>
			</c:if>			
		</div>
	</div>
	
</body>
</html>