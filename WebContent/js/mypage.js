 let PASSWORD_REGEX = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[~!@#$%^&*()+|=])[A-Za-z\\d~!@#$%^&*()+|=]{8,16}$";
let EMAIL_REGEX = "^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$";

function validate() {
	let email = modify.userEmailId.value + "@" + modify.userEmailAddr.value;
	
	 if(modify.userPassword.value == "") {
        alert('비밀번호를 입력하세요.');
        modify.userPassword.focus() ; 
		modify.pwChkmsg.value.innerHTML = "비밀번호를 입력하세요.";
        return false;
    }

    if(modify.userPasswordCheck.value == "") {
        alert('비밀번호 확인을 입력하세요.');
        modify.userPasswordCheck.focus() ;
        return false;
    }

	if(modify.userEmailId.value == "") {
        alert('e-mail을 입력하세요.');
        modify.userEmailId.focus() ;
        return false;
    }
    if(modify.userEmailAddr.value == "") {
        alert('e-mail을 입력하세요.');
        modify.userEmailAddr.focus() ;
        return false;
    }

	if(!PASSWORD_REGEX.test(modify.userPassword.value)){ //패스워드 유효성검사
        alert("비밀번호는 8~16자의 영문+숫자+특수기호 조합으로 입력하세요.");
        return false;
    }

	if(!PASSWORD_REGEX.test(email)){ //이메일
        alert("e-mail을 확인해주세요");
        return false;
    }

	alter("회원정보가 수정되었습니다!")
}

function mailSelect(){
    modify.userEmailAddr.value = modify.emailList.value;
}

