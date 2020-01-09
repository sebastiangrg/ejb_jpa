package com.tpjad.ejb_jpa.beans.category;

import java.util.List;

import com.tpjad.ejb_jpa.entities.Category;

public interface CategoryManagerBeanRemote {

	List<Category> getAll();
	
	Category getByName(String name);
}
