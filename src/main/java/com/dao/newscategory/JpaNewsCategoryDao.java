/**
 * Copyright Flexpay AB
 */
package com.dao.newscategory;

import com.dao.JpaDao;
import com.dao.model.entity.NewsCategory;

public class JpaNewsCategoryDao extends JpaDao<NewsCategory, Long> implements NewsCategoryDao {

	public JpaNewsCategoryDao() {
		super(NewsCategory.class);
	}

	@Override
	public NewsCategory createCategory(final String categoryName) {
		NewsCategory newsCategory = new NewsCategory();
		newsCategory.setName(categoryName);
		return save(newsCategory);
	}
}
