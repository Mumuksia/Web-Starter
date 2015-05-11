/**
 * Copyright Flexpay AB
 */
package com.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.dao.newscategory.NewsCategoryDao;
import com.entity.NewsCategory;
import com.service.model.BlogPost;
import com.service.model.Category;

@Component
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private NewsCategoryDao newsCategoryDao;

	@Autowired
	private  BlogPostService blogPostService;

	@Override
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
	public Category updateCategory(final Category category) {
		return updateCategory(category.getName());
	}

	@Override
	public void deleteCategory(final long categoryId) {
		newsCategoryDao.delete(categoryId);
	}

	@Override
	public List<Category> findAllCategories() {
		return newsCategoryDao.findAll().stream().map(p->new Category.CategoryBuilder().buildFromEntity(p).build()).collect(
				Collectors.toList());
	}

	@Override
	public Category getCategory(final long categoryId) {
		return new Category.CategoryBuilder().buildFromEntity(newsCategoryDao.find(categoryId)).build();
	}

	@Override
	public List<BlogPost> getPostsByCategory(final Category category) {
		List<BlogPost> blogPosts = blogPostService.getAll();

		return blogPosts.stream().filter(p->p.getCategory().equals(category)).collect(Collectors.toList());
	}

	public void setNewsCategoryDao(final NewsCategoryDao newsCategoryDao) {
		this.newsCategoryDao = newsCategoryDao;
	}
}
