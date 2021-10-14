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

@WebServlet("/comments/save")
public class CommentSaveController extends HttpServlet{
	private CommentService commentService = CommentService.getInstance();
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		Member member = (Member)session.getAttribute(MemberConst.MY_SESSION_ID);
		request.setCharacterEncoding("UTF-8");
		Long bbsId = (Long) session.getAttribute("bbsId");
		
		
		if(member == null) {
			session.setAttribute("from", "/Matrip/boards/board?bbsID="+bbsId);
			response.sendRedirect("/Matrip/member/login");
			return;
		}

		String commentContent = request.getParameter("commentContent");
		
		// Not empty
		if(commentContent.equals("") || isCommentEmpty(commentContent)) {
			response.sendRedirect("/Matrip/boards/board?bbsID="+bbsId);
			return;
		}
		
		Comment comment = new Comment(member.getLoginId(), commentContent);
		comment.setBbsId(bbsId);
		commentService.saveComment(comment);
		response.sendRedirect("/Matrip/boards/board?bbsID="+bbsId);
		
	}
	
	// check blank
	private boolean isCommentEmpty(String commentContent) {
		
		int length = commentContent.length();
		int cnt = 0;
		for(int i = 0 ; i < length; i++) {
			if(commentContent.substring(i,i+1).equals(" ")) {
				cnt++;
			}
		}
		if(cnt == length) {
			return true;
		}
		return false;
	}
}
