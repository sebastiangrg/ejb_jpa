package com.tpjad.ejb_jpa.beans.category;

import java.util.List;

import com.tpjad.ejb_jpa.entities.Category;

public interface CategoryManagerBean {
	List<Category> getAll();
	
	Category getByName(String name);
}
