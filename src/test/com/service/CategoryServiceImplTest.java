package com.service;

import org.junit.Before;

import com.service.model.Category;

public class CategoryServiceImplTest{

	private CategoryService categoryService;

	@Before
	public void init(){
		categoryService = new CategoryServiceImpl();
	}

	public void testUpdateCategory() throws Exception {
		Category category =	mockCategory();
		category = categoryService.updateCategory(category);
	}


	public void testDeleteCategory() throws Exception {

	}

	public void testFindAllCategories() throws Exception {

	}

	public void testGetCategory() throws Exception {

	}

	private Category mockCategory() {
		return null;
	}
}
