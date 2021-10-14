package web.controller.member;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

// memberController interface. to set session, add HttpServletRequest Parameter
public interface MemberController {
	String process(Map<String, String> paramMap, Map<String,Object> model, HttpServletRequest request);
}
