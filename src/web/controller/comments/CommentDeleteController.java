package web.controller.comments;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import domain.comment.Comment;
import domain.member.Member;
import service.CommentService;
import utils.MemberConst;

@WebServlet("/comments/delete")
public class CommentDeleteController extends HttpServlet{
	private CommentService commentService = CommentService.getInstance();
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		Member member = (Member)session.getAttribute(MemberConst.MY_SESSION_ID);
		request.setCharacterEncoding("UTF-8");
		Long bbsId = (Long) session.getAttribute("bbsId");
		
		// double check , if member is a person who write comment which requested to delete.
		if(member == null) {
			session.setAttribute("from", "/Matrip/boards/board?bbsID="+bbsId);
			response.sendRedirect("/Matrip/member/login");
			return;
		}
		Long id =  Long.parseLong(request.getParameter("commentId"));
		
		commentService.deleteComment(id);
		response.sendRedirect("/Matrip/boards/board?bbsID="+bbsId);
		
	}
}
