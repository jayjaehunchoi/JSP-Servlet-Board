package web.controller.member;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import domain.member.Member;
import service.MemberService;
import utils.MemberConst;

public class MemberSaveController implements MemberController{
	private MemberService memberService = MemberService.getInstance();
	@Override
	public String process(Map<String, String> paramMap, Map<String, Object> model, HttpServletRequest request) {
		
		String loginId = paramMap.get("userId");
		String password = paramMap.get("userPassword");
		String passwordCheck =  paramMap.get("userPasswordCheck");
		String email = assembleEmail(paramMap);
		
		// check join validation (double check) and set error in attribute
		if(!isValidPassword(password, passwordCheck)) {
			model.put("passwordError", "��й�ȣ�� ��й�ȣ Ȯ���� ��ġ�����ּ���");
		}
		if(!isValidId(loginId)) {
			model.put("idError", "���̵� �ٽ� �Է����ּ���");
		}
		if(!isValidEmail(email)) {
			model.put("emailError", "�̸����� �ٽ� �Է����ּ���");
		}
		
		// if error happend, dispatcher forward to join page
		if(!model.isEmpty()) {
			return "join";
		}
		
		
		Member member = new Member(loginId,password,email);
		member.createSalt();
		int result = memberService.join(member);
		
		// db error (last Check)
		if(result == MemberConst.JOIN_FAILED) {
			return "join";
		}
		return "login";
	}

	
	
	private boolean isValidPassword(String password, String passwordCheck) {
		if(!memberService.isPassWordSameWithPasswordCheck(password, passwordCheck)||
		!memberService.isPasswordSatisfiedGivenCondition(password)) {
			return false;
		}
		return true;
	}
	
	private boolean isValidId(String loginId) {
		if(memberService.findByLoginId(loginId) == null && loginId.length() > MemberConst.LOGIN_ID_MIN_LENGTH && loginId.length() < MemberConst.LOGIN_ID_MAX_LENGTH) {
			return true;
		}
		return false;
	}
	
	private boolean isValidEmail(String email) {
		if(memberService.isValidEmail(email) && memberService.findByEmail(email) == null) {
			return true;
		}
		return false;
	}
	
	private String assembleEmail(Map<String, String> paramMap) {
		return paramMap.get("userEmailId")+"@" +paramMap.get("userEmailAddr");
			
	}

}
