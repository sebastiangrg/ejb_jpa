package com.tpjad.ejb_jpa.beans.todo;

import java.util.List;

import com.tpjad.ejb_jpa.entities.Todo;


public interface TodoManagerBeanRemote {
	List<Todo> getAll();

	Todo persist(Todo todo);
	
	void deleteById(Integer id);
}
