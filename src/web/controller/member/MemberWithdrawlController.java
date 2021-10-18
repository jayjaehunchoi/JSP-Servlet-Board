package web.controller.member;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import domain.member.Member;
import service.CommentService;
import service.MemberService;
import utils.MemberConst;
import utils.ShaEncoder;

public class MemberWithdrawlController implements MemberController{

	private MemberService memberService = MemberService.getInstance();
	private CommentService commentService = CommentService.getInstance(); 
	
	@Override
	public String process(Map<String, String> paramMap, Map<String, Object> model, HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		Member member = isMemberSessionValid(request,session);
		if(member == null) {
			return "login";
		}

		if(request.getMethod().equals("POST")){
			if(isPasswordSameCheck(paramMap, member)) {
				int memberDelRes = memberService.deleteMember(member);
				commentService.deleteCommentByMember(member.getId());
				if(memberDelRes == -1) {
					return "checkPw";
				}
				session.removeAttribute(MemberConst.MY_SESSION_ID);;
				return "login";
			}
		}
		
		return "checkPw";
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
	
	private boolean isPasswordSameCheck(Map<String,String> paramMap, Member member) {
		String salt = member.getSalt();
		String password = paramMap.get("userPassword");
		String encodedPW = ShaEncoder.transferToSHA_256(password, salt);
		if(member.getPassword().equals(encodedPW)) {
			return true;
		}
		return false;
	}
	

}
