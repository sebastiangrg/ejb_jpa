package com.tpjad.jndi_client;

import java.util.Properties;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.tpjad.ejb_jpa.beans.category.CategoryManagerBeanRemote;
import com.tpjad.ejb_jpa.beans.todo.TodoManagerBeanRemote;

public class JBOSSClient {
	static final Properties JNDI = new Properties();

	static {
		JNDI.put("java.naming.factory.initial", "org.jboss.naming.remote.client.InitialContextFactory");
		JNDI.put("java.naming.provider.url", "http-remoting://localhost:8084");
	}

	static final String JNDITodoManagerBean = "ejb_jpa/TodoManagerBeanImpl!com.tpjad.ejb_jpa.beans.todo.TodoManagerBeanRemote";
	static final String JNDICategoryManagerBean = "ejb_jpa/CategoryManagerBeanImpl!com.tpjad.ejb_jpa.beans.category.CategoryManagerBeanRemote";

	public static void main(String args[]) throws NamingException {
		TodoManagerBeanRemote todoManager = (TodoManagerBeanRemote) (new InitialContext(JNDI)
				.lookup(JNDITodoManagerBean));
		CategoryManagerBeanRemote categoryManager = (CategoryManagerBeanRemote) (new InitialContext(JNDI)
				.lookup(JNDICategoryManagerBean));

		categoryManager.getAll().forEach(c -> {
			System.out.println("Category: " + c.getName());
		});

		todoManager.getAll().forEach(t -> {
			System.out.println("Todo: " + t.getTitle());
		});
	}
}
