package com.dao.newscategory;

import org.springframework.transaction.annotation.Transactional;

import com.dao.Dao;
import com.entity.NewsCategory;

@Transactional
public interface NewsCategoryDao  extends Dao<NewsCategory, Long> {

}
