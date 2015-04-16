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
    public List<BlogPost> getAll() {
        List<NewsEntry> newsEntries = newsEntryDao.findAll();
        List<BlogPost> posts = newsEntries.stream().map(p->new BlogPost.BlogPostBuilder().buildFromEntity(p).build()).collect(Collectors.toList());
        return posts;
    }

    @Override
    @Transactional
    public BlogPost save(final BlogPost post) {
        NewsEntry entry = newsEntryDao.save(new NewsEntry(post));
        return new BlogPost.BlogPostBuilder().buildFromEntity(entry).build();
    }

    @Override
    @Transactional
    public BlogPost findById(final Long id) {
        NewsEntry entry = newsEntryDao.find(id);
        return new BlogPost.BlogPostBuilder().buildFromEntity(entry).build();
    }

    @Override
    @Transactional
    public void delete(final Long id) {
        newsEntryDao.delete(id);
    }

    public void setNewsEntryDao(final NewsEntryDao newsEntryDao) {
        this.newsEntryDao = newsEntryDao;
    }
}
