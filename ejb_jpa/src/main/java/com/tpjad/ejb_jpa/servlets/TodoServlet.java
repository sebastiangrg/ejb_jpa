package com.tpjad.ejb_jpa.servlets;

import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tpjad.ejb_jpa.beans.category.CategoryManagerBeanLocal;
import com.tpjad.ejb_jpa.beans.todo.TodoManagerBeanLocal;
import com.tpjad.ejb_jpa.entities.Category;
import com.tpjad.ejb_jpa.entities.Todo;

public class TodoServlet extends HttpServlet {

	private static final long serialVersionUID = 6918398445243360240L;

	@EJB
	private TodoManagerBeanLocal todoManager;

	@EJB
	private CategoryManagerBeanLocal categoryManager;

	@Override
	// returns a web page containing the todo list items
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		ServletOutputStream out = response.getOutputStream();
		out.println("<html><head><title>TODO List</title></head>");
		out.println("<body style='text-align:center;'>");
		out.println("<h3>TODOS</h3>");
		out.println("<div style='display:inline-block;'>");

		List<Todo> todos = todoManager.getAll();

		Collections.sort(todos, new Comparator<Todo>() {
			public int compare(Todo t1, Todo t2) {
				return t1.getCategory().getName().compareTo(t2.getCategory().getName());
			}
		});

		todos.forEach(todo -> {
			try {
				out.println("<div style='border: 1px solid black; box-sizing: border-box; width 240px; height: 80px'>");
				out.println("<div style='border-bottom: 1px solid black; width: 690px'>");
				out.println("<div style='display:inline-block; width: 98px'>");
				out.println("<i>" + todo.getCategory().getName() + "</i>");
				out.println("</div>");
				out.println("<div style='display:inline-block; width: 548px; text-align: left'>");
				out.println("<b>" + todo.getTitle() + "</b>");
				out.println("</div>");
				out.println("<div style='display:inline-block; width: 18px'>");
				out.println("<form method='post' action='/ejb_jpa/todos'>");
				out.println("<input type='hidden' id='todoID' name='todoID' value=" + todo.getId() + ">");
				out.println("<button type='submit'>x</button>");
				out.println("</form>");
				out.println("</div>");
				out.println("</div>");
				out.println(todo.getContent());
				out.println("</div>");
				out.println("<br>");
			} catch (IOException e) {
				e.printStackTrace();
			}
		});

		out.println("</div></br>");
		out.println("<a href='/ejb_jpa/create'><h4>Create</h4></a>");
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

		try {
			String id = request.getParameter("todoID");
			if (id != null) {
				todoManager.deleteById(Integer.parseInt(id));

				doGet(request, response);
				return;
			}

			Todo todo = new Todo();

			String stringCategory = request.getParameter("category");
			Category category = categoryManager.getByName(stringCategory);

			todo.setTitle(request.getParameter("title"));
			todo.setContent(request.getParameter("content"));
			todo.setCategory(category);

			if (validate(todo)) {
				todoManager.persist(todo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		doGet(request, response);
	}
}
