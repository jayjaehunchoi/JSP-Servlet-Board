package web.controller.member;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.member.Member;
import service.MemberService;

// get HTTP body data(js/join.js) and response with HTTP body
@WebServlet("/duplicateCheck")
public class MemberIdDuplicateCheckController extends HttpServlet{
	MemberService memberService = MemberService.getInstance();
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String loginId = request.getParameter("id");
		Member findMember = memberService.findByLoginId(loginId);
		PrintWriter out = response.getWriter();

		if(findMember == null) {
			out.print(1);
		}else {
			out.print(0);
		}
		
	}

}
