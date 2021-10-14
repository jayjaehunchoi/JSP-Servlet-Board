<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<div class="container">
		<div class="row">
		<form method="post" action="/Matrip/boards/update" >
			<table class="table table-striped" style="text-align: center; border: 1px solid #dddddd">
				<thead>
					<tr>
						<th colspan="2" style="background-color: #eeeeee; text-align: center;">게시판 글수정 양식</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td><input type="text" class="form-control" placeholder="글 번호" name="bbsID" maxlength="50" value="${bbs.bbsID}" readonly></td>
					</tr>
					<tr>
						<td><input type="text" class="form-control" placeholder="글 제목" name="bbsTitle" maxlength="50" value="${bbs.bbsTitle}"></td>
					</tr>
					<tr>
						<td><textarea class="form-control" placeholder="글 내용" name="bbsContent" maxlength="2048" style="height: 350px;">${bbs.bbsContent}</textarea></td>
					</tr>
				</tbody>
			</table>
			<input type="file" name="file">파일 업데이트는 불가합니다.<br>
			<input type="submit" class="btn btn-primary pull-right" value="글 수정">
		</form>
		<a href=/Matrip/boards?page=<%=session.getAttribute("page") %>>취소</a>
		</div>
	</div>
</body>
</html>