<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import = "domain.member.Member, utils.MemberConst" %>
<%
	Member member = (Member)session.getAttribute(MemberConst.MY_SESSION_ID);
	long id = member.getId();
	String pw = member.getPassword();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="../js/mypage.js"> </script>
</head>
<body>
	<form name = "pwChk" onsubmit="return pwCheck();" method = "post"> 
	본인확인을 위해 비밀번호를 입력해주세요. <br>
	<input type = password id="userPassword" name="userPassword"> <br>
	<input class = "okBt" type="submit" onclick="return confirm('정말로 삭제하시겠습니까?')" value="확인"> 
	<input class = "cancelBt" type="button" value="취소" onclick = "location.href='update'"> 
	<br>
	</form>
</body>
</html>