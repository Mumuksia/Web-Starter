package com.dao.newscategory;

import org.springframework.transaction.annotation.Transactional;

import com.dao.Dao;
import com.dao.model.entity.NewsCategory;

@Transactional
public interface NewsCategoryDao  extends Dao<NewsCategory, Long> {

	NewsCategory createCategory(final String categoryName);
}
