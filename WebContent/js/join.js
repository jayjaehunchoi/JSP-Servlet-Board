
// 회원가입 유효성 검사
function validate() {

    if(join.userId.value == "") {
        alert('아이디를 입력하세요.');
        join.userId.focus() ;
        return false;
    }
    if(join.userPassword.value == "") {
        alert('비밀번호를 입력하세요.');
        join.userPassword.focus() ; 
        return false;
    }
   
    if(join.userPasswordCheck.value == "") {
        alert('비밀번호 확인을 입력하세요.');
        join.userPasswordCheck.focus() ;
        return false;
    }
    if(join.userEmailId.value == "") {
        alert('e-mail을 입력하세요.');
        join.userEmailId.focus() ;
        return false;
    }
    if(join.userEmailAddr.value == "") {
        alert('e-mail을 입력하세요.');
        join.userEmailAddr.focus() ;
        return false;
    }
    if(join.userEmailId.value == "" && join.emailList.selectedIndex == 0) {
        alert('e-mail을 입력하세요.');
        join.userEmailAddr.focus() ;
        return false;
    }
    
    if(join.userPassword.value !=  join.userPasswordCheck.value) {
      alert("비밀번호가 일치하지 않습니다. 다시 확인해 주세요.");
      join.userPasswordCheck.value = "";
      join.userPasswordCheck.focus();
      return false;
    }
    alert('회원가입이 완료되었습니다.') ;

    }

// 이메일주소 select 값 전달
function mailSelect(){
    join.userEmailAddr.value = join.emailList.value;
}

// 아이디 중복확인창 오픈
function openIdCheck(){ 
     window.open("idCheckForm", "idChkForm", "width=400, height=200");
 }

 // 새로운 아이디 입력시 다시 중복확인 필요하게 세팅
 function inputIdChk(){
    document.join.idDuplication.value ="idUncheck";
 }

 var httpRequest = null;
      
 // httpRequest 객체 생성
 function getXMLHttpRequest(){
	
     var httpRequest = new window.XMLHttpRequest();
  
     return httpRequest;    
 }
 
 
 // 아이디 값 전달
 function pValue() {
    document.idChk.userId.value = opener.document.join.userId.value;
    
}
 
 // 아이디 중복체크
 function idCheck(){

     var id = document.idChk.userId.value;

     if (!id) {
         alert("아이디를 입력하지 않았습니다.");
         return false;
     } 
     
     else
     {
         var param="id="+id
         httpRequest = getXMLHttpRequest();
         httpRequest.onreadystatechange = callback; //서버 응답 후 수행할 함수
         httpRequest.open("POST", "/Matrip/duplicateCheck", true); 
         httpRequest.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded'); 
         httpRequest.send(param);
     }
 }
 
 function callback(){
     if(httpRequest.readyState == 4){ //이상 없음, 응답 받음 XMLHttpRequest.DONE
         // 결과값을 가져온다.
         var resultText = httpRequest.responseText;
         
         if(resultText == 0){
             alert("사용할수없는 아이디입니다.");
             document.getElementById("cancelBtn").style.visibility='visible';
             document.getElementById("useBtn").style.visibility='hidden';
             document.getElementById("msg").innerHTML ="";
         } 
         else if(resultText == 1){ 
        	 alert("사용할수있습니다.");
             document.getElementById("cancelBtn").style.visibility='hidden';
             document.getElementById("useBtn").style.visibility='visible';
             document.getElementById("msg").innerHTML = "사용 가능한 아이디입니다.";
         }
     }
 }
 

 // 사용하기 클릭 시 부모창으로 값 전달 
 function sendCheckValue(){
     // 중복체크 결과인 idCheck 값을 전달한다.
     opener.document.join.idDuplication.value ="idCheck";
     // 회원가입 화면의 ID입력란에 값을 전달
     opener.document.join.userId.value = document.idChk.userId.value;
     
     if (opener != null) {
         opener.idChkForm = null;
         self.close();
     }    
 }    

