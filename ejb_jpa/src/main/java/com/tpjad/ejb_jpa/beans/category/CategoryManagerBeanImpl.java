package com.tpjad.ejb_jpa.beans.category;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import com.tpjad.ejb_jpa.entities.Category;

@Stateless
public class CategoryManagerBeanImpl implements CategoryManagerBeanLocal, CategoryManagerBeanRemote {

	@PersistenceContext(unitName = "tpjad")
	private EntityManager em;

	@Override
	public List<Category> getAll() {
		TypedQuery<Category> query = em.createQuery("SELECT c FROM Category c", Category.class);
		return query.getResultList();
	}

	@Override
	public Category getByName(String name) {
		TypedQuery<Category> query = em.createQuery("SELECT c FROM Category c WHERE c.name = :name", Category.class);
		query.setParameter("name", name);
		return query.getSingleResult();
	}
}
