package web.controller.member;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import domain.member.Member;
import utils.MemberConst;

public class MemberJoinController implements MemberController{

	@Override
	public String process(Map<String, String> paramMap, Map<String, Object> model,HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		
		//check session (if it is entered, if memberId session is valid)
		if(session != null) {
			Member member = (Member)session.getAttribute(MemberConst.MY_SESSION_ID);
			if(member != null) {
				model.put("member", member);
				return "asklogout";
			}
		}
		return "join";
	}

}
