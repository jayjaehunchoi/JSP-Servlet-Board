package web;

import java.io.IOException;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MyView {
	private String viewPath;
	
	public MyView(String viewPath) {
		this.viewPath = viewPath;
	}

	
	public void render(Map<String, Object> model, HttpServletRequest request ,HttpServletResponse response) throws ServletException, IOException {
		modeltoRequestAttribute(model, request);
		RequestDispatcher requestDispatcher = request.getRequestDispatcher(viewPath);
		requestDispatcher.forward(request, response);
	}

	public void modeltoRequestAttribute(Map<String, Object> model, HttpServletRequest request) {
		model.forEach((key,value) -> request.setAttribute(key, value));
	}
}
