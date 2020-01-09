package com.tpjad.jndi_client;

import java.util.Properties;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.tpjad.ejb_jpa.beans.category.CategoryManagerBeanRemote;
import com.tpjad.ejb_jpa.beans.todo.TodoManagerBeanRemote;

public class GFClient {
	static final Properties JNDI = new Properties();

	static {
        JNDI.put("java.naming.factory.initial", "com.sun.enterprise.naming.impl.SerialInitContextFactory");
        JNDI.put("org.omg.CORBA.ORBInitialHost", "localhost");
        JNDI.put("org.omg.CORBA.ORBInitialPort", "3700");
	}

	static final String JNDITodoManagerBean = "java:global/ejb_jpa/TodoManagerBeanImpl!com.tpjad.ejb_jpa.beans.todo.TodoManagerBeanRemote";
	static final String JNDICategoryManagerBean = "java:global/ejb_jpa/CategoryManagerBeanImpl!com.tpjad.ejb_jpa.beans.category.CategoryManagerBeanRemote";

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
