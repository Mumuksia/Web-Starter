/**
 * Copyright Flexpay AB
 */
package com.service.model;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import org.codehaus.jackson.map.annotate.JsonView;

import com.JsonViews;
import com.entity.NewsCategory;
import com.entity.NewsEntry;

public class BlogPost implements Serializable{

	private Long id;
	private Date date;
	private String content;
	private String title;
	private Set<Category> categories;
	private String categoriesString;

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

	public Set<Category> getCategories() {
		return categories;
	}

	@JsonView(JsonViews.User.class)
	public String getCategoriesString(){
		categoriesString = categories.stream().map(Category::getName).reduce((t, u) -> t + " | " + u).get();
		return categoriesString;
	}

	public BlogPost() {
	}

	public BlogPost(final BlogPostBuilder blogPostBuilder) {
		this.id = blogPostBuilder.id;
		this.date = blogPostBuilder.date;
		this.content = blogPostBuilder.content;
		this.title = blogPostBuilder.title;
		this.categories = blogPostBuilder.categories;
	}

	public static class BlogPostBuilder {
		private Long id;
		private Date date;
		private String content;
		private String title;
		private Set<Category> categories;

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

        public BlogPostBuilder buildCategories(Set<Category> categories){
			this.categories = new HashSet<Category>(categories);
			return this;
		}

        public BlogPostBuilder buildFromEntity(NewsEntry newsEntry){
			this.id = newsEntry.getId();
			this.date = newsEntry.getDate();
			this.content = newsEntry.getContent();
			this.title = newsEntry.getTitle();
			if (newsEntry.getCategories() == null){
				newsEntry.setCategories(new HashSet<>());
			}
			this.categories = newsEntry.getCategories().stream().map(this::getCategory).collect(Collectors.toSet());
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
