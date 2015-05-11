/**
 * Copyright Flexpay AB
 */
package com.service.model;

import java.io.Serializable;
import java.util.Date;

import org.codehaus.jackson.map.annotate.JsonView;

import com.JsonViews;
import com.entity.NewsCategory;
import com.entity.NewsEntry;

public class BlogPost implements Serializable{

	private Long id;
	private Date date;
	private String content;
	private String title;
	private Category category;
	private String categoryName;

	@JsonView(JsonViews.Admin.class)
	public Long getId() {
		return id;
	}

	@JsonView(JsonViews.Admin.class)
	public Date getDate() {
		return date;
	}

	@JsonView(JsonViews.User.class)
	public String getContent() {
		return content;
	}

	@JsonView(JsonViews.User.class)
	public String getTitle() {
		return title;
	}

	public Category getCategory() {
		return category;
	}

	@JsonView(JsonViews.User.class)
	public String getCategoryName(){
		return category.getName();
	}

	public BlogPost() {
	}

	public BlogPost(final BlogPostBuilder blogPostBuilder) {
		this.id = blogPostBuilder.id;
		this.date = blogPostBuilder.date;
		this.content = blogPostBuilder.content;
		this.title = blogPostBuilder.title;
		this.category = blogPostBuilder.categories;
	}

	public static class BlogPostBuilder {
		private Long id;
		private Date date;
		private String content;
		private String title;
		private Category categories;

		public BlogPostBuilder buildContent(String content, String title){
			this.content = content;
			this.title = title;
			return this;
		}

        public BlogPostBuilder buildBasic(Long id, Date date){
			this.id = id;
			this.date = date;
			return this;
		}

        public BlogPostBuilder buildCategories(Category categories){
			this.categories = categories;
			return this;
		}

        public BlogPostBuilder buildFromEntity(NewsEntry newsEntry){
			this.id = newsEntry.getId();
			this.date = newsEntry.getDate();
			this.content = newsEntry.getContent();
			this.title = newsEntry.getTitle();
			this.categories = getCategory(newsEntry.getNewsCategory());
			return this;
		}

		private Category getCategory(NewsCategory newsCategory){
			return new Category.CategoryBuilder().buildFromEntity(newsCategory).build();
		}

        public BlogPost build(){
			return new BlogPost(this);
		}
	}
}
