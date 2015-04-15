/**
 * Copyright Flexpay AB
 */
package net.dontdrinkandroot.example.angularrestspringsecurity.dao.newscategory;

import net.dontdrinkandroot.example.angularrestspringsecurity.dao.JpaDao;
import net.dontdrinkandroot.example.angularrestspringsecurity.entity.NewsCategory;

public class JpaNewsCategoryDao extends JpaDao<NewsCategory, Long> implements NewsCategoryDao {

	public JpaNewsCategoryDao() {
		super(NewsCategory.class);
	}
}
