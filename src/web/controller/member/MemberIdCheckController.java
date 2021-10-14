package web.controller.member;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

public class MemberIdCheckController implements MemberController{

	@Override
	public String process(Map<String, String> paramMap, Map<String, Object> model, HttpServletRequest request) {
		
		return "idCheckForm";
	}

}
