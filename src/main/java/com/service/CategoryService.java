/**
 * Copyright Flexpay AB
 */
package com.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.service.model.BlogPost;
import com.service.model.Category;

@Service
public interface CategoryService {

	Category updateCategory(final String categoryName);

	Category updateCategory(final Category category);

	void deleteCategory(final long categoryId);

	List<Category> findAllCategories();

	Category getCategory(final long categoryId);

	List<BlogPost> getPostsByCategory(final Category category);

}
