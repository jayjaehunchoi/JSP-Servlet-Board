package web.controller.bbs;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.sun.glass.ui.Application;

import domain.bbs.Bbs;
import domain.file.MyFile;
import domain.member.Member;
import service.BbsService;
import service.MyFileService;
import utils.MemberConst;

@WebServlet("/boards/write")
public class BoardWriteController extends HttpServlet{
	
	private BbsService bbsService = BbsService.getInstance();
	private MyFileService fileService = MyFileService.getInstance();
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession(false);
		Member member = (Member) session.getAttribute(MemberConst.MY_SESSION_ID);
		
		if(member == null) {
			session.setAttribute("from", "/Matrip/boards/write");
			response.sendRedirect("/Matrip/member/login");
			return;
		}
		RequestDispatcher dispatcher = request.getRequestDispatcher("/board/write.jsp");
		dispatcher.forward(request, response);
		
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Bbs bbs = new Bbs();
		HttpSession session = request.getSession(false);
		Member member = (Member) session.getAttribute(MemberConst.MY_SESSION_ID);
		request.setCharacterEncoding("UTF-8");
		
		// for file upload
		MultipartRequest multi = prepareFileUpload(request);

		bbs.setBbsID(bbsService.getNext());
		Long bbsId = bbs.getBbsID();
		
		MyFile file = makeFile(multi, bbsId);
		bbs = makeBbs(multi, bbs);

		// Not empty
		if(bbs.getBbsTitle().equals("") || bbs.getBbsContent().equals("") || isInputEmpty(bbs.getBbsTitle()) || isInputEmpty(bbs.getBbsContent()) ) {
			response.sendRedirect("/Matrip/boards/write");
			return;
		}
		
		Long write = bbsService.write(bbs, member.getId());
		if(write == -1) {
			response.sendRedirect("/Matrip/boards/write");
			return;
		}

		fileService.fileUpload(file);
		
		response.sendRedirect("/Matrip/boards?page="+session.getAttribute("page"));
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
	
	private MultipartRequest prepareFileUpload(HttpServletRequest request) throws IOException {
		ServletContext application = getServletConfig().getServletContext();
		String directory = application.getRealPath("/upload/");
		int maxSize = 1024 * 1024 * 100;
		String encoding = "UTF-8";
		
		MultipartRequest multipartRequest = new MultipartRequest(request, directory, maxSize, encoding, new DefaultFileRenamePolicy());
		
		return multipartRequest;
	}
	
	private MyFile makeFile(MultipartRequest multi, Long bbsId) {
		String fileName = multi.getOriginalFileName("file");
		String fileRealName = multi.getFilesystemName("file");
		
		MyFile file = new MyFile(bbsId,fileName,fileRealName);
		return file;
	}
	
	private Bbs makeBbs(MultipartRequest multi, Bbs bbs) {
		bbs.setBbsTitle(multi.getParameter("bbsTitle"));
		bbs.setBbsContent(multi.getParameter("bbsContent"));
		bbs.setBbsDate(bbsService.getDate());
		return bbs;
	}
}
