<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Document</title>
  <link rel="stylesheet" href="../css/join.css">
  <script src="../js/join.js"> </script>
</head>
<body>
  회원가입
  <br><br>
  <form name="join" onsubmit="return validate();" action="/Matrip/member/save" method="post">
    <c:if test="${idError ne null}"  var="result" scope="page">
  		<p>${idError}</p>
  	</c:if>
  아이디 <input class ="join" id="userId" name="userId" type="text" onkeydown="inputIdChk()">  
  <input class="checkDuplication" type="button" value="중복확인" onclick="openIdCheck()"><br>
  <input type="hidden" name="idDuplication" value="idUnchecked">
  	<c:if test="${passwordError ne null}"  var="result" scope="page">
  		<p>${passwordError}</p>
  	</c:if>
  비밀번호 <input class ="join" id="userPassword" name="userPassword" type="password"><br>
  비밀번호확인 <input class ="join" id="userPasswordCheck" name="userPasswordCheck" type="password"><br>
  	<c:if test="${emailError ne null}"  var="result" scope="page">
  		<p>${emailError}</p>
  	</c:if>
  이메일 <input class ="join" type="text" name="userEmailId"> 
        <span class="at">@</span>
        <input class="join" type="text" name="userEmailAddr">
        <select class="join" id="emailList" name="emailList"  onchange="mailSelect();">
          <option value="" selected>직접입력</option>
          <option value="naver.com">naver.com</option>
          <option value="gmail.com">gmail.com</option>
          <option value="hanmail.net">hanmail.net</option>
          <option value="nate.com">nate.com</option>
        </select>
  <br>
  <input class = "joinBt" type="submit" value="회원가입"> <br>
</form>
 <a href="/Matrip">홈</a>
</body>
</html>