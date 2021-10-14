package web.controller;

import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import utils.MemberConst;
import web.MyView;
import web.controller.member.MemberController;
import web.controller.member.MemberIdCheckController;
import web.controller.member.MemberJoinController;
import web.controller.member.MemberLoginController;
import web.controller.member.MemberLoginCorrectController;
import web.controller.member.MemberUpdateController;
import web.controller.member.MemberMyPageController;
import web.controller.member.MemberSaveController;
import web.controller.member.MemberWelcomeController;
import web.controller.member.MemberWithdrawlController;

/**
 * @author JaehunChoi
 * use front controller (dispatcher servlet)
 */
@WebServlet("/member/*")
public class MemberFrontController extends HttpServlet{

	private static final long serialVersionUID = 1L;
	
	private Map<String,MemberController> controllerMap = new HashMap<>();
	
	// put uri in map when MemberFrontController called or servlet container started
	public MemberFrontController() {
		controllerMap.put("/Matrip/member/login", new MemberLoginController());
		controllerMap.put("/Matrip/member/join", new MemberJoinController());
		controllerMap.put("/Matrip/member/save", new MemberSaveController());
		controllerMap.put("/Matrip/member/ok", new MemberLoginCorrectController());
		controllerMap.put("/Matrip/member/mypage", new MemberMyPageController());
		controllerMap.put("/Matrip/member/idCheckForm", new MemberIdCheckController());
		controllerMap.put("/Matrip/member/welcomelogin", new MemberWelcomeController());
		controllerMap.put("/Matrip/member/update", new MemberUpdateController());
		controllerMap.put("/Matrip/member/checkPw", new MemberWithdrawlController());
	}
	
	// when get, use requestDispatcher 
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		MemberController controller = createController(request);
		
		if(controller == null) {
			response.setStatus(HttpServletResponse.SC_NOT_FOUND);
			return;
		}
		
		Map<String, String> paramMap = createParamMap(request);
		Map<String, Object> model = new HashMap<String, Object>();
		
		String viewName = controller.process(paramMap, model, request);
		
		
		MyView view = viewResolver(viewName);
		view.render(model, request, response);
	}
	
	// when post, if use dispatcher forward, it has risk(send request (save, update, delete) repeatedly) when refresh the page. set procedure as POST -> redirect -> GET
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		MemberController controller = createController(request);
		
		if(controller == null) {
			response.setStatus(HttpServletResponse.SC_NOT_FOUND);
			return;
		}
		
		Map<String, String> paramMap = createParamMap(request);
		Map<String, Object> model = new HashMap<String, Object>();
		
		String viewName = controller.process(paramMap, model, request);
		
		HttpSession session = request.getSession(false);
		if(session.getAttribute("from") != null) {
			String viewPath = (String)session.getAttribute("from") ;
			session.removeAttribute("from");
			response.sendRedirect(viewPath);
			return;
		}
		if(!model.isEmpty()) {
			MyView view = viewResolver(viewName);
			view.render(model, request, response);
			return;
		}
		
		response.sendRedirect(redirectViewResolver(viewName));
		
	}
	
	// to get Controller which is called
	private MemberController createController(HttpServletRequest request) {
		String requestUri = request.getRequestURI();
		return controllerMap.get(requestUri);
	}
	
	// not to use httpservlet except this class
	private Map<String,String> createParamMap(HttpServletRequest request){
		Map<String, String> paramMap = new HashMap<String, String>();
		Enumeration params = request.getParameterNames();
		while(params.hasMoreElements()) {
			String name = (String)params.nextElement();
			paramMap.put(name, request.getParameter(name));
		}
		return paramMap;
	}
	
	private MyView viewResolver(String viewName) {
		return new MyView("/members/"+viewName + ".jsp");
	}
	
	// for redirect when post called
	private String redirectViewResolver(String viewName) {
		return "/Matrip/member/"+viewName;
	}
	
	// not to use httpservlet except this class
	public void modeltoRequestAttribute(Map<String, Object> model, HttpServletRequest request) {
		if(!model.isEmpty()) {
			model.forEach((key,value) -> request.setAttribute(key, value));
			
		}
		
	}
}
