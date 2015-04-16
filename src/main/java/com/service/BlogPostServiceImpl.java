package com.service;

import com.dao.newsentry.NewsEntryDao;
import com.entity.NewsEntry;
import com.service.model.BlogPost;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * User: Muksia
 * Date: 15/04/15
 * Time: 20:54
*/
@Component
public class BlogPostServiceImpl implements BlogPostService {


    @Autowired
    private NewsEntryDao newsEntryDao;

    @Override
    @Transactional
    public List<BlogPost> getAllPosts() {
        List<NewsEntry> newsEntries = newsEntryDao.findAll();
        List<BlogPost> posts = newsEntries.stream().map(p->new BlogPost.BlogPostBuilder().buildFromEntity(p).build()).collect(Collectors.toList());
        return posts;
    }

    public void setNewsEntryDao(final NewsEntryDao newsEntryDao) {
        this.newsEntryDao = newsEntryDao;
    }
}
