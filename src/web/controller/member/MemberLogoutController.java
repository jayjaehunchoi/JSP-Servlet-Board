package web.controller.member;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/logout")
public class MemberLogoutController extends HttpServlet {
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);

		if(session != null) {
			session.invalidate();
		}
		
		// if request has param named "from" , check if its value named "join", if it is, sendRedirect.
		// it means that mapped from join page
		if(request.getParameterNames().hasMoreElements()) {
			if(request.getParameter("from").equals("join")) {
				response.sendRedirect("/Matrip/member/join");
			}
		}
		else {
			response.sendRedirect("/Matrip");
		}
		
		
	}
}
