package web.controller.member;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import domain.bbs.Bbs;
import domain.member.Member;
import service.MemberService;
import utils.MemberConst;

public class MemberLoginController implements MemberController{
	
	private MemberService memberService = MemberService.getInstance();
	@Override
	public String process(Map<String, String> paramMap, Map<String, Object> model, HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		
		//check session (if it is entered, if memberId session is valid)
		if(session == null) {
			return "login";
		}
		Member member = (Member)session.getAttribute(MemberConst.MY_SESSION_ID);
		if(member == null) {
			return "login";
		}
		
		// if it is entered , set parameter member, bbs info, dispatcher forward to mypage
		model.put("member", member);
		List<Bbs> bbs = memberService.findBbsByMemberId(member.getId());
		model.put("bbs", bbs);
		session.setAttribute("page", 1);
		return "mypage";
	}

}
