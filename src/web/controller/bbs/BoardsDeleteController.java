package web.controller.bbs;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import domain.bbs.Bbs;
import domain.member.Member;
import service.BbsService;
import service.MemberService;
import utils.MemberConst;

@WebServlet("/boards/delete")
public class BoardsDeleteController extends HttpServlet{
	private BbsService bbsService = BbsService.getInstance();

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		Member member = (Member) session.getAttribute(MemberConst.MY_SESSION_ID);
		
		Long bbsID = Long.parseLong(request.getParameter("bbsID"));
		Bbs bbs = bbsService.getBbs(bbsID);
		String memberId = bbs.getLoginId();
		
		// check if member who delete bbs is same with writer. 
		if(member == null) {
			response.sendRedirect("/Matrip/member/login");
			return;
		}
		if(!member.getLoginId().equals(memberId)) {
			response.sendRedirect("/Matrip/member/login");
			return;
		}
		
		
		int delete = bbsService.delete(bbsID);
		
		if(delete != 1) {
			response.sendRedirect("/Matrip/boards?bbsID="+bbsID);
			return;
		}
		
		int page = (int)session.getAttribute("page");
		if(!bbsService.checkNextPage(page-1)) {
			page -= 1;
		}
		response.sendRedirect("/Matrip/boards?page="+page);
	}
}
