/**
 * Copyright Flexpay AB
 */
package com.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.dao.newscategory.NewsCategoryDao;
import com.entity.NewsCategory;
import com.service.model.Category;

@Component
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private NewsCategoryDao newsCategoryDao;

	@Override
	public Category updateCategory(final Category category) {
		NewsCategory newsCategory = newsCategoryDao.save(new NewsCategory(category));
		return new Category.CategoryBuilder().buildFromEntity(newsCategory).build();
	}

	@Override
	public void deleteCategory(final long categoryId) {

	}

	@Override
	public List<Category> findAllCategories() {
		return newsCategoryDao.findAll().stream().map(p->new Category.CategoryBuilder().buildFromEntity(p).build()).collect(
				Collectors.toList());
	}

	@Override
	public Category getCategory(final long categoryId) {
		return null;
	}

	public void setNewsCategoryDao(final NewsCategoryDao newsCategoryDao) {
		this.newsCategoryDao = newsCategoryDao;
	}
}
