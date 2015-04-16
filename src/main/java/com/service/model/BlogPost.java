/**
 * Copyright Flexpay AB
 */
package com.service.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import com.entity.NewsCategory;
import com.entity.NewsEntry;

public class BlogPost {

	private Long id;
	private Date date;
	private String content;
	private String title;
	private Set<Category> categories;

	public Long getId() {
		return id;
	}

	public Date getDate() {
		return date;
	}

	public String getContent() {
		return content;
	}

	public String getTitle() {
		return title;
	}

	public Set<Category> getCategories() {
		return categories;
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
