package com.service;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import com.dao.model.entity.NewsCategory;
import com.dao.newscategory.NewsCategoryDao;
import com.service.model.Category;
import com.service.model.Category.CategoryBuilder;

public class CategoryServiceImplTest{

	private static final String CATEGORY_NAME = "Name1";
	private static final String CATEGORY_NAME1 = CATEGORY_NAME + "Test";
	private static final long CATEGORY_ID = 1L;
	private CategoryServiceImpl categoryService;
	private NewsCategoryDao newsCategoryDao;

	@Before
	public void init(){
		categoryService = new CategoryServiceImpl();
		newsCategoryDao = Mockito.mock(NewsCategoryDao.class);
		Mockito.when(newsCategoryDao.findAll()).thenReturn(mockCategories());
		Mockito.when(newsCategoryDao.find(CATEGORY_ID)).thenReturn(mockNewsCategory(CATEGORY_NAME));
		Mockito.when(newsCategoryDao.createCategory(CATEGORY_NAME1)).thenReturn(mockNewsCategory(CATEGORY_NAME1));
		categoryService.setNewsCategoryDao(newsCategoryDao);
	}


	@Test
	public void testUpdateCategory_AlreadyExists() throws Exception {
		Category category =	mockCategory(CATEGORY_NAME);
		categoryService.updateCategory(category);

		verify(newsCategoryDao, never()).createCategory(CATEGORY_NAME);
	}

	@Test
	public void testUpdateCategory_NewCategory() throws Exception {
		Category category =	mockCategory(CATEGORY_NAME1);
		categoryService.updateCategory(category);

		verify(newsCategoryDao, times(1)).createCategory(CATEGORY_NAME1);
	}

	@Test
	public void testDeleteCategory() throws Exception {
		Category category =	mockCategory(CATEGORY_NAME1);
		categoryService.deleteCategory(category.getId());

		verify(newsCategoryDao, times(1)).delete(category.getId());
	}

	@Test
	public void testFindAllCategories() throws Exception {
		List<Category> categories = categoryService.findAllCategories();
		assertThat(categories.size(), is(4));
		assertThat(categories.get(0), notNullValue());
		assertThat(categories.get(0).getName(), is(CATEGORY_NAME));
		assertThat(categories.get(3), notNullValue());
		assertThat(categories.get(3).getName(), is("Name4"));
	}

	@Test
	public void testGetCategory() throws Exception {
		Category category = categoryService.getCategory(CATEGORY_ID);
		assertThat(category, notNullValue());
		assertThat(category.getName(), is(CATEGORY_NAME));
	}

	private Category mockCategory(final String name) {
		return new CategoryBuilder().buildNameAndId(name, CATEGORY_ID).build();
	}

	private NewsCategory mockNewsCategory(final String name) {
		return new NewsCategory(name, null);
	}

	private List<NewsCategory> mockCategories() {
		List<NewsCategory> categories = new ArrayList<>();
		categories.add(mockNewsCategory(CATEGORY_NAME));
		categories.add(mockNewsCategory("Name2"));
		categories.add(mockNewsCategory("Name3"));
		categories.add(mockNewsCategory("Name4"));
		return categories;
	}
}
