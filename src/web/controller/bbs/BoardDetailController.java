package web.controller.bbs;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import domain.bbs.Bbs;
import domain.file.MyFile;
import domain.member.Member;
import service.BbsService;
import service.MyFileService;
import utils.MemberConst;

@WebServlet("/boards/board")
public class BoardDetailController extends HttpServlet {
	
	private BbsService bbsService = BbsService.getInstance();
	private MyFileService fileService = MyFileService.getInstance();
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		Member member = null;
		if(session != null) {
			member = (Member)session.getAttribute(MemberConst.MY_SESSION_ID);
		}

		Long bbsID = Long.parseLong(request.getParameter("bbsID"));
		Bbs bbs = bbsService.getBbs(bbsID);
		int order = bbsService.getCurOrder(bbsID);
		
		request.setAttribute("bbs", bbs);
		request.setAttribute("member", member);
		request.setAttribute("commentList", bbs.getComments());
		fileDownload(request, bbsID);
		
		session.setAttribute("bbsId", bbs.getBbsID()); // set current bbsId, can come back after login (to write comment , login needed)
		session.setAttribute("page", order/10 + 1); // set current page session
		RequestDispatcher dispatcher = request.getRequestDispatcher("/board/boarddetail.jsp");
		dispatcher.forward(request, response);
		
	}
	
	public void fileDownload(HttpServletRequest request, Long bbsID) throws UnsupportedEncodingException {
		ServletContext application = getServletConfig().getServletContext();
		String directory = application.getRealPath("/upload/");
		String[] files = new File(directory).list();

		MyFile file = fileService.findFileByBbsId(bbsID);
		request.setAttribute("file", file);
		
		
		String encodedFile = null;
		if(file == null) {
			return;
		}
		for(int i = 0 ; i < files.length; i++) {
			if(file.getFileName().equals(files[i])) {
				encodedFile =  URLEncoder.encode(files[i], "UTF-8");
				break;
			}
		}
		request.setAttribute("files", encodedFile);

	}
	
}
