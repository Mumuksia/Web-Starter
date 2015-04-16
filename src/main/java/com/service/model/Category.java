/**
 * Copyright Flexpay AB
 */
package com.service.model;

import java.io.Serializable;

import org.codehaus.jackson.map.annotate.JsonView;

import com.JsonViews;
import com.entity.NewsCategory;

public class Category implements Serializable{

	@JsonView(JsonViews.Admin.class)
	private Long id;
	@JsonView(JsonViews.User.class)
	private String name;

	public Category() {
	}

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
