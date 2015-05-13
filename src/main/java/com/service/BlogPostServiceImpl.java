package com.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.dao.model.entity.NewsCategory;
import com.dao.model.entity.NewsEntry;
import com.dao.newsentry.NewsEntryDao;
import com.service.model.BlogPost;
import com.service.model.Category;

/**
 * User: Muksia
 * Date: 15/04/15
 * Time: 20:54
*/
@Component
public class BlogPostServiceImpl implements BlogPostService {


    @Autowired
    private NewsEntryDao newsEntryDao;
	@Autowired
	private CategoryService categoryService;

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
		Category category = categoryService.updateCategory(post.getCategoryName());
		post.setCategory(category);
		NewsEntry entry = new NewsEntry(post);
		newsEntryDao.save(entry);
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

    @Override
	@Transactional
    public BlogPost create(final BlogPost post) {
		final String categoryName = post.getCategoryName();
		Category category = categoryService.updateCategory(categoryName);
		NewsEntry entry = new NewsEntry(post);
		entry.setNewsCategory(new NewsCategory(category));
        return new BlogPost.BlogPostBuilder().buildFromEntity(newsEntryDao.save(entry)).build();

    }

	@Override
	@Transactional
	public List<BlogPost> getPostsByCategory(final String categoryName) {
		List<BlogPost> blogPosts = getAll();
		return blogPosts.stream().filter(p->p.getCategory().getName().equals(categoryName)).collect(Collectors.toList());
	}

	public void setNewsEntryDao(final NewsEntryDao newsEntryDao) {
        this.newsEntryDao = newsEntryDao;
    }

    public void setCategoryService(final CategoryService categoryService) {
        this.categoryService = categoryService;
    }
}
