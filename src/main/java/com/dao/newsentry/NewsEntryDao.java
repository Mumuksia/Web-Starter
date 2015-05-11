package com.dao.newsentry;

import org.springframework.transaction.annotation.Transactional;

import com.dao.Dao;
import com.dao.model.entity.NewsEntry;

@Transactional
public interface NewsEntryDao extends Dao<NewsEntry, Long>
{

}
