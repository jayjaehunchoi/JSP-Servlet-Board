package web.controller.member;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import domain.member.Member;
import service.MemberService;
import utils.MemberConst;

public class MemberUpdateController implements MemberController {

	private MemberService memberService = MemberService.getInstance();
	
	@Override
	public String process(Map<String, String> paramMap, Map<String, Object> model, HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		Member member = isMemberSessionValid(request, session);
		if(member == null) {
			return "login";
		}
		
		if(request.getMethod().equals("POST")) {
			String loginId = paramMap.get("userId");
			String userPassword = paramMap.get("userPassword");
			String email = assembleEmail(paramMap);
			
			if(!isValidEmail(email)) {
				model.put("emailError", "이메일을 다시 입력해주세요.");
				return "modifyInfo";
			}
			if(member.getLoginId().equals(loginId)) {
				member.setLoginId(loginId);
				member.setPassword(userPassword);
				member.setEmail(email);
				memberService.updateMember(member);
				return "mypage";
			}
			return "update"; // passed all validation
		}
		return "modifyInfo";
	}
	
	private String assembleEmail(Map<String, String> paramMap) {
		return paramMap.get("userEmailId")+"@" +paramMap.get("userEmailAddr");
			
	}
	private boolean isValidEmail(String email) {
		if(memberService.isValidEmail(email) && memberService.findByEmail(email) == null) {
			return true;
		}
		return false;
	}
	
	
	private Member isMemberSessionValid(HttpServletRequest request , HttpSession session) {
		if(session == null) {
			return null;
		}
		Member member = (Member) session.getAttribute(MemberConst.MY_SESSION_ID);
		if(member == null) {
			return null;
		}
		return member;
	}
}
