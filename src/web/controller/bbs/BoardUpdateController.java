package web.controller.bbs;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import domain.bbs.Bbs;
import domain.member.Member;
import service.BbsService;
import utils.MemberConst;

@WebServlet("/boards/update")
public class BoardUpdateController extends HttpServlet{
	
	private BbsService bbsService = BbsService.getInstance();
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession(false);
		Member member = (Member) session.getAttribute(MemberConst.MY_SESSION_ID);
		if(member == null) {
			response.sendRedirect("/Matrip/member/login");
			return;
		}
		
		Long bbsId = Long.parseLong(request.getParameter("bbsID"));
		Bbs bbs = bbsService.getBbs(bbsId);
		
		request.setAttribute("bbs", bbs);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/board/update.jsp");
		dispatcher.forward(request, response);
		
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Bbs bbs = new Bbs();
		HttpSession session = request.getSession(false);
		Member member = (Member) session.getAttribute(MemberConst.MY_SESSION_ID);
		request.setCharacterEncoding("UTF-8");
		
		Long bbsId = Long.parseLong(request.getParameter("bbsID"));
		String bbsTitle = request.getParameter("bbsTitle");
		String bbsContent = request.getParameter("bbsContent");
		
		// Not empty
		if(bbsTitle.equals("") || bbsContent.equals("") || isInputEmpty(bbsTitle) || isInputEmpty(bbsContent)) {
			response.sendRedirect("/Matrip/boards/update?bbsID="+bbsId);
			return;
		}
		int update = bbsService.update(bbsId, bbsTitle, bbsContent);
		
		// last check (db)
		if(update != 1) {
			response.sendRedirect("/Matrip/boards/update?bbsID="+bbsId);
			return;
		}
		response.sendRedirect("/Matrip/boards/board?bbsID="+bbsId);
	}
	
	private boolean isInputEmpty(String input) {
		
		int length = input.length(); 
		int cnt = 0;
		for(int i = 0 ; i < length; i++) {
			if(input.substring(i,i+1).equals(" ")) {
				cnt++;
			}
		}
		if(cnt == length) {
			return true;
		}
		return false;
	}
}
