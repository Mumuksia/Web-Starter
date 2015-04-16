/**
 * Copyright Flexpay AB
 */
package com.service.model;

import com.entity.NewsCategory;

public class Category {

	private Long id;
	private String name;

	private Category(CategoryBuilder builder){
		this.id = builder.id;
		this.name = builder.name;
	}

	public static class CategoryBuilder{
		public Long id;
		public String name;

		public CategoryBuilder buildFromEntity(NewsCategory newsCategory){
			this.id = newsCategory.getId();
			this.name = newsCategory.getName();
			return this;
		}

		public Category build(){
			return new Category(this);
		}
	}

}
