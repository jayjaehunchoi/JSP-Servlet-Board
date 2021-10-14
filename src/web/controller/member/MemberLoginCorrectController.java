package web.controller.member;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import domain.member.Member;
import service.MemberService;
import utils.MemberConst;

public class MemberLoginCorrectController implements MemberController {
	
	private MemberService memberService = MemberService.getInstance();
	
	@Override
	public String process(Map<String, String> paramMap, Map<String, Object> model, HttpServletRequest request) {
		String loginId = paramMap.get("userId");
		String password = paramMap.get("userPassword");

		Member findMember = memberService.checkIdAndPassword(loginId, password);
		
		// set error info 
		if(findMember == null) {
			model.put("loginError", "���̵� Ȥ�� ��й�ȣ�� �ٽ� Ȯ���ϼ���.");
			return "login";
		}
		
		// set session if login succeed
		model.put("member", findMember);
		HttpSession session = request.getSession(); 
		session.setAttribute(MemberConst.MY_SESSION_ID, findMember);
		return "welcomelogin";
	}

}
