<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ page import = "domain.member.Member, utils.MemberConst" %>
 <%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<%
	Member member = (Member)session.getAttribute(MemberConst.MY_SESSION_ID);
	String loginId = member.getLoginId();
	String[] email = member.getEmail().split("@");
	String emailId = email[0];
	String emailAddr = email[1];
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="../js/mypage.js"> </script>
</head>
<body>
	회원정보수정<br>
	<form name="modify" onsubmit="return validate();" action="/Matrip/member/update" method="post">
  아이디 <input class ="modify" id="userId" name="userId" type="text" value = <%= loginId %> readonly> <br>
 비밀번호 <input class ="modify" id="userPassword" name="userPassword" type="password"> 8~16자의 영문+숫자+특수기호 조합으로 입력<br>
 <div class = "chkMsg" id="pwChkMsg"> </div>  
  비밀번호확인 <input class ="join" id="userPasswordCheck" name="userPasswordCheck" type="password"><br>
 <div class = "chkMsg" id="pw2ChkMsg"> </div>  
 	<c:if test="${emailError ne null}"  var="result" scope="page">
  		<p>${emailError}</p>
  	</c:if>
  이메일 <input class ="modify" type="text" name="userEmailId" value = <%= emailId %>> 
        <span class="at">@</span>
        <input class="modify" type="text" name="userEmailAddr" value = <%= emailAddr %>>
        <select class="modify" id="emailList" name="emailList"  onchange="mailSelect();">
          <option value="" selected>직접입력</option>
          <option value="naver.com">naver.com</option>
          <option value="gmail.com">gmail.com</option>
          <option value="hanmail.net">hanmail.net</option>
          <option value="nate.com">nate.com</option>
        </select>
  <br>
 <div class = "chkMsg" id="emailChkMsg"> </div> 
  <input class = "modifyBt" type="submit" value="수정">  
  <input class = "deleteBt" type="button" value="탈퇴" onclick = "location.href='checkPw'">
  <input class = "cancelBt" type="button" value="취소" onclick = "location.href='mypage'"> <br>
</form>


</body>
</html>