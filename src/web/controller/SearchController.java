package web.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.SearchService;
import service.searchServiceImpl.SearchBlogService;
import service.searchServiceImpl.SearchCafeService;
import service.searchServiceImpl.SearchKinService;
import service.searchServiceImpl.SearchLocalService;

@WebServlet("/search")
public class SearchController extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String input = request.getParameter("search");
		String searchType = request.getParameter("searchType");
		
		
		SearchService sc = getSearchService(searchType,request);
		
		List<Object> searchResults = sc.setSearchApi(input); 
		
		request.setAttribute("input", input);
		request.setAttribute("searchResults",searchResults);

		RequestDispatcher dispatcher = request.getRequestDispatcher("/search.jsp");
		dispatcher.forward(request, response);
		
	}
	
	private SearchService getSearchService(String searchType, HttpServletRequest request) {
		SearchService sc = null;
		
		switch (searchType) {
		case "blog":
			sc = new SearchBlogService();
			request.setAttribute("type", "��α�");
			break;
		case "cafearticle":
			sc = new SearchCafeService();
			request.setAttribute("type", "ī��");
			break;
		case "kin":
			sc = new SearchKinService();
			request.setAttribute("type", "������");
			break;
		case "local":
			sc = new SearchLocalService();
			request.setAttribute("type", "����");
			break;

		default:
			break;
		}
		
		return sc;
			
	}
	
}
