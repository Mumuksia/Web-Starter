package com.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import com.dao.model.entity.NewsCategory;
import com.dao.model.entity.NewsEntry;
import com.dao.newsentry.NewsEntryDao;
import com.service.model.BlogPost;

/**
 * User: Muksia
 * Date: 15/04/15
 * Time: 21:03
 */
public class BlogPostServiceImplTest {

	private BlogPostServiceImpl blogPostService;
	private NewsEntryDao newsEntryDao;

	@Before
	public void init() {
		blogPostService = new BlogPostServiceImpl();
		newsEntryDao = Mockito.mock(NewsEntryDao.class);
		blogPostService.setNewsEntryDao(newsEntryDao);

	}

	@Test
	public void getAllNews_emptyList() {
		Mockito.when(newsEntryDao.findAll()).thenReturn(new ArrayList<>());
		List<BlogPost> posts = blogPostService.getAll();
		Assert.assertEquals(posts.size(), 0);

	}

	@Test
	public void getAllNews_noCategories() {
		Mockito.when(newsEntryDao.findAll()).thenReturn(mockNewsEntriesList(false));
		List<BlogPost> posts = blogPostService.getAll();
		Assert.assertEquals(posts.size(), 3);
		Assert.assertEquals(posts.get(0).getContent(), "content 1");
		Assert.assertEquals(posts.get(0).getTitle(), "title 1");
		Assert.assertEquals(posts.get(1).getContent(), "content 2");
		Assert.assertEquals(posts.get(1).getTitle(), "title 2");
		Assert.assertEquals(posts.get(2).getContent(), "content 3");
		Assert.assertEquals(posts.get(2).getTitle(), "title 3");

	}

	@Test
	public void getAllNews_Categories() {
		Mockito.when(newsEntryDao.findAll()).thenReturn(mockNewsEntriesList(true));
		List<BlogPost> posts = blogPostService.getAll();
		Assert.assertEquals(posts.size(), 3);
		Assert.assertEquals(posts.get(0).getContent(), "content 1");
		Assert.assertEquals(posts.get(0).getTitle(), "title 1");
		Assert.assertNotNull(posts.get(0).getCategory());
		Assert.assertEquals(posts.get(1).getContent(), "content 2");
		Assert.assertEquals(posts.get(1).getTitle(), "title 2");
		Assert.assertEquals(posts.get(2).getContent(), "content 3");
		Assert.assertEquals(posts.get(2).getTitle(), "title 3");

	}

	private List<NewsEntry> mockNewsEntriesList(boolean includeCategories) {
		List<NewsEntry> newsEntries = new ArrayList<>();
		NewsCategory newsCategory = new NewsCategory();

		if (includeCategories) {
			newsCategory.setName("category name");
		}

		newsEntries.add(mockNewsEntry("content 1", "title 1", newsCategory, new Date()));
		newsEntries.add(mockNewsEntry("content 2", "title 2", newsCategory, new Date()));
		newsEntries.add(mockNewsEntry("content 3", "title 3", newsCategory, new Date()));

		return newsEntries;
	}

	private NewsEntry mockNewsEntry(final String content, final String title, final NewsCategory categories,
									final Date date) {
		NewsEntry newsEntry = new NewsEntry();
		newsEntry.setContent(content);
		newsEntry.setTitle(title);
		newsEntry.setNewsCategory(categories);
		newsEntry.setDate(date);
		return newsEntry;
	}
}
