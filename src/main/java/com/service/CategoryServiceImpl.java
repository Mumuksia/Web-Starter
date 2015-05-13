/**
 * Copyright Flexpay AB
 */
package com.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.dao.model.entity.NewsCategory;
import com.dao.newscategory.NewsCategoryDao;
import com.service.model.Category;


@Component
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private NewsCategoryDao newsCategoryDao;

	@Override
	@Transactional
	public Category updateCategory(final String categoryName) {
		List<Category> categories = findAllCategories();
		Optional<Category> optional = categories.stream().filter(p -> p.getName().equals(categoryName)).findAny();
		if (optional.isPresent()){
			return optional.get();
		}
		NewsCategory newsCategory = newsCategoryDao.createCategory(categoryName);
		return new Category.CategoryBuilder().buildFromEntity(newsCategory).build();
	}

	@Override
	@Transactional
	public Category updateCategory(final Category category) {
		return updateCategory(category.getName());
	}

	@Override
	@Transactional
	public void deleteCategory(final long categoryId) {
		newsCategoryDao.delete(categoryId);
	}

	@Override
	@Transactional
	public List<Category> findAllCategories() {
		return newsCategoryDao.findAll().stream().map(p -> new Category.CategoryBuilder().buildFromEntity(
				p).build()).collect(
				Collectors.toList());
	}

	@Override
	@Transactional
	public Category getCategory(final long categoryId) {
		NewsCategory newsCategory = newsCategoryDao.find(categoryId);
		return new Category.CategoryBuilder().buildFromEntity(newsCategory).build();
	}

	public void setNewsCategoryDao(final NewsCategoryDao newsCategoryDao) {
		this.newsCategoryDao = newsCategoryDao;
	}
}
