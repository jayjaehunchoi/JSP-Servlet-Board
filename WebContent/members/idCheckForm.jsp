<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html> 
<html>
<head>
<meta charset="UTF-8">
<title>idCheckForm.jsp</title> 
<link rel="stylesheet" href="../css/join.css">
<script src="../js/join.js"> </script>
</head> 
<body onload="pValue()">
  <div style="text-align: center"> 
  아이디 중복확인 <br><br>
  <form name="idChk" method="post"> 
  <input type="text" id= "userId" name="userId" placeholder="ID를 입력하세요" autofocus >      
  <input type="submit" value="중복확인" onclick="idCheck()"> 
  <div id="msg"></div>
  <br>
  <input id="cancelBtn" type="button" value="취소" onclick="window.close()"><br>
  <input id="useBtn" type="button" value="사용하기" onclick="sendCheckValue()">

  </form> 
  </div> 
</body> 
</html>