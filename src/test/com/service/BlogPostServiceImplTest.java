package com.service;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.dao.model.entity.NewsCategory;
import com.dao.model.entity.NewsEntry;
import com.dao.newsentry.NewsEntryDao;
import com.service.model.BlogPost;
import com.service.model.Category;

/**
 * User: Muksia
 * Date: 15/04/15
 * Time: 21:03
 */
public class BlogPostServiceImplTest {

	public static final String CATEGORY_NAME = "SomeName";
	public static final long ID = 1l;
	private BlogPostServiceImpl blogPostService;
	private NewsEntryDao newsEntryDao;
	private CategoryService categoryService;

	@Before
	public void init() {
		blogPostService = new BlogPostServiceImpl();
		newsEntryDao = mock(NewsEntryDao.class);
		blogPostService.setNewsEntryDao(newsEntryDao);
		categoryService = mock(CategoryService.class);
		when(categoryService.updateCategory(mockCategory().getName())).thenReturn(mockCategory());
		blogPostService.setCategoryService(categoryService);

	}

	@Test
	public void getAllNews_emptyList() {
		when(newsEntryDao.findAll()).thenReturn(new ArrayList<>());
		List<BlogPost> posts = blogPostService.getAll();
		Assert.assertEquals(posts.size(), 0);

	}

	@Test
	public void getAllNews_noCategories() {
		when(newsEntryDao.findAll()).thenReturn(mockNewsEntriesList(false));
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
		when(newsEntryDao.findAll()).thenReturn(mockNewsEntriesList(true));
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

	@Test
	public void save(){
		blogPostService.save(mockBlogPost("Content", "title", ID));

		verify(newsEntryDao, times(1)).save(any());
	}

	@Test
	public void delete(){
		blogPostService.delete(ID);

		verify(newsEntryDao, times(1)).delete(ID);
	}

	@Test
	public void create(){
		BlogPost blogPost = mockBlogPost("Content", "title", ID);
		when(newsEntryDao.save(any())).thenReturn(new NewsEntry(blogPost));
		blogPostService.create(blogPost);

		verify(newsEntryDao, times(1)).save(any());
	}

	private BlogPost mockBlogPost(final String content, final String title, final long id) {
		return new BlogPost.BlogPostBuilder().buildBasic(id, new Date()).
				buildContent(content, title).buildCategory(mockCategory()).build();
	}

	private Category mockCategory() {
		return new Category.CategoryBuilder().buildNameAndId(CATEGORY_NAME, 2l).build();
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
