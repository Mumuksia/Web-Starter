/**
 * Copyright Flexpay AB
 */
package com.dao.newscategory;

import com.dao.JpaDao;
import com.entity.NewsCategory;

public class JpaNewsCategoryDao extends JpaDao<NewsCategory, Long> implements NewsCategoryDao {

	public JpaNewsCategoryDao() {
		super(NewsCategory.class);
	}

}
