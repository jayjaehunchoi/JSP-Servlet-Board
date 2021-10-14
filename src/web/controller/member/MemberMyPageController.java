package web.controller.member;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import domain.bbs.Bbs;
import domain.member.Member;
import service.MemberService;
import utils.MemberConst;

public class MemberMyPageController implements MemberController{
	private MemberService memberService = MemberService.getInstance();
	@Override
	public String process(Map<String, String> paramMap, Map<String, Object> model, HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		if(session == null) {
			return "login";
		}
		Member member = (Member)session.getAttribute(MemberConst.MY_SESSION_ID);  // session < ¾ÏÈ£È­ / session attr ( key, value) -> key : mySessionId
		if(member == null) {
			return "login";
		}
		
		model.put("member", member);
		List<Bbs> bbs = memberService.findBbsByMemberId(member.getId());
		model.put("bbs", bbs);
		
		return "mypage";
	}

}
