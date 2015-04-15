/**
 * Copyright Flexpay AB
 */
package com.service;

import com.entity.NewsCategory;

public class Category {

	private long id;
	private String name;

	private Category(CategoryBuilder builder){
		this.id = builder.id;
		this.name = builder.name;
	}

	public static class CategoryBuilder{
		public long id;
		public String name;

		CategoryBuilder buildFromEntity(NewsCategory newsCategory){
			this.id = newsCategory.getId();
			this.name = newsCategory.getName();
			return this;
		}

		Category build(){
			return new Category(this);
		}
	}

}
