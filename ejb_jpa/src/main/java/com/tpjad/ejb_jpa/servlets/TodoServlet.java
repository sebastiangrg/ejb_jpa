package com.tpjad.ejb_jpa.servlets;

import java.io.IOException;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tpjad.ejb_jpa.beans.todo.TodoManagerBeanLocal;
import com.tpjad.ejb_jpa.entities.Todo;

public class TodoServlet extends HttpServlet {

	private static final long serialVersionUID = 6918398445243360240L;

	@EJB
	private TodoManagerBeanLocal todoManager;

	@Override
	// returns a web page containing the todo list items
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		ServletOutputStream out = response.getOutputStream();
		out.println("<html><head><title>TODO List</title></head>");
		out.println("<body style='text-align:center;'>");
		out.println("<h3>TODOS</h3>");
		out.println("<ul style='display:inline-block;'>");

		List<Todo> todos = todoManager.getAll();

		todos.forEach(todo -> {
			try {
				out.println("<li>");
				out.println("<b>" + todo.getTitle() + "</b>");
				out.println("<br>");
				out.println(todo.getContent());
				out.println("</li>");
				out.println("<br>");
			} catch (IOException e) {
				e.printStackTrace();
			}
		});

		out.println("</ul></br>");
		out.println("<a href='/create'><h4>Create</h4></a>");
		out.println("</body></html>");
		out.close();
	}

	private static boolean validate(Todo todo) {
		if (todo.getTitle() == null) {
			return false;
		}

		if (todo.getTitle().length() < 3) {
			return false;
		}

		if (todo.getContent() == null) {
			return false;
		}

		if (todo.getContent().length() < 3) {
			return false;
		}

		return true;
	}

	@Override
	// adds a new item to the todo list
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Todo todo = new Todo();
		todo.setTitle("");
		todo.setContent("");

		try {
			todo.setTitle(request.getParameter("title"));
			todo.setContent(request.getParameter("content"));
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (validate(todo)) {
			todoManager.persist(todo);
		}

		doGet(request, response);
	}
}
