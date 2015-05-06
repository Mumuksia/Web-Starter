/**
 * Copyright Flexpay AB
 */
package com.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.service.model.Category;

@Service
public interface CategoryService {

	Category updateCategory(final Category category);

	void deleteCategory(final Category category);

	List<Category> findAllCategories();

}
