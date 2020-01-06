package com.tpjad.ejb_jpa.servlets;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tpjad.ejb_jpa.beans.category.CategoryManagerBeanLocal;

public class CreateServlet extends HttpServlet {

	private static final long serialVersionUID = 5503598584107822184L;

	@EJB
	private CategoryManagerBeanLocal categoryManager;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// returns the jsp containing the form for adding a todo list item
		RequestDispatcher rp = request.getRequestDispatcher("create.jsp");

		List<String> categories = this.categoryManager.getAll().stream().map(c -> c.getName())
				.collect(Collectors.toList());
		
		request.setAttribute("categories", categories);

		rp.forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}
