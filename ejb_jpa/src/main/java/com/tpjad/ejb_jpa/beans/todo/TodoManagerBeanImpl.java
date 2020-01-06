package com.tpjad.ejb_jpa.beans.todo;

import java.util.List;

import javax.ejb.EJBException;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.OptimisticLockException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import com.tpjad.ejb_jpa.entities.Todo;

@Stateless
public class TodoManagerBeanImpl implements TodoManagerBeanLocal, TodoManagerBeanRemote {

	@PersistenceContext(unitName = "tpjad")
	private EntityManager em;

	@Override
	public List<Todo> getAll() {
		TypedQuery<Todo> query = em.createQuery("SELECT t FROM Todo t", Todo.class);
		return query.getResultList();
	}

	@Override
	public Todo persist(Todo todo) {
		em.persist(todo);
		return todo;
	}

	@Override
	public void deleteById(Integer id) {
		em.createQuery("DELETE FROM Todo t where t.id=:id").setParameter("id", id).executeUpdate();
	}
}
