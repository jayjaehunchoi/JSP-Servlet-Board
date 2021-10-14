package web.controller.member;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import domain.member.Member;
import service.MemberService;
import utils.MemberConst;

public class MemberWelcomeController implements MemberController{
	
	private MemberService memberService = MemberService.getInstance();
	
	@Override
	public String process(Map<String, String> paramMap, Map<String, Object> model, HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		if(session == null) {
			return "login";
		}
		Member member = (Member) session.getAttribute(MemberConst.MY_SESSION_ID);
		if( member == null) {
			return "login";
		}
		
		model.put("member", member);
		return "welcomelogin";
	}

}
