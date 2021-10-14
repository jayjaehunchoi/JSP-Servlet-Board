package web.controller.bbs;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import domain.member.Member;
import service.BbsService;
import utils.MemberConst;

@WebServlet("/boards")
public class BoardListController extends HttpServlet {
	
	private BbsService bbsService = BbsService.getInstance();
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		Member member = null;
		if(session != null) {
			member = (Member)session.getAttribute(MemberConst.MY_SESSION_ID);
		}
		
		response.setCharacterEncoding("UTF-8");
		int pageNumber = Integer.parseInt(request.getParameter("page"));
		
		request.setAttribute("bbs", bbsService.findAll(pageNumber));
		request.setAttribute("member", member);
		
		setPrevAndNextPage(pageNumber, request);
		setPagination(request);
		
		// don't need bbsId anymore when user at the bbs list page.
		if(session != null) {
			if(session.getAttribute("bbsId") != null) {
				session.removeAttribute("bbsId");
			}
			session.setAttribute("page", pageNumber);
		}
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/board/boardList.jsp");
		dispatcher.forward(request, response);
		
	}
	
	// set previous and next navigator if it is existed.
	private void setPrevAndNextPage(int pageNumber, HttpServletRequest request) {
		if(pageNumber != 1) {
			request.setAttribute("previousPage", pageNumber-1);
		}
		if(bbsService.checkNextPage(pageNumber)) {
			request.setAttribute("nextPage", pageNumber+1);
		}
	}
	
	
	/**
	 * need update pagination if page size is over 5 or 10.
	 */
	private void setPagination(HttpServletRequest request) {
		int totalBbs = bbsService.getCount();
		List<Integer> pageList = new ArrayList<Integer>();
		int pages = totalBbs / 10 +1;
		if(pages > 0) {
			for(int i = 1 ; i <= pages; i++) {
				pageList.add(i);
			}
			request.setAttribute("pageList", pageList);
		}
	}
	
}
