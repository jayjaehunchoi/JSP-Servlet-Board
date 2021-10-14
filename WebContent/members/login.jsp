<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Document</title>
  <link rel="stylesheet" href="./css/main.css">
  <script src="./js/main.js"> </script>
</head>
<body>
  로그인 <br> <br>
  <form action="/Matrip/member/ok" method="post">
  <c:if test="${loginError ne null}"  var="result" scope="page">
  	<p>${loginError}</p>
  </c:if>
    <input class ="login" name="userId" type="text" placeholder="ID"> <br>
    <input class ="login" name="userPassword" type="password" placeholder="password"><br>
    <input class ="loginBt" type="submit" value="로그인"> <br>
    <a href="/Matrip/member/join"> 회원가입 </a>
  </form>
  <a href="/Matrip">홈</a>
</body>
</html>