/**
 * Copyright Flexpay AB
 */
package com.service.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.entity.NewsEntry;

public class BlogPost {

	private Long id;
	private Date date;
	private String content;
	private String title;
	private Set<Category> categories;

	public BlogPost(final BlogPostBuilder blogPostBuilder) {
		this.id = blogPostBuilder.id;
		this.date = blogPostBuilder.date;
		this.content = blogPostBuilder.content;
		this.title = blogPostBuilder.title;
	}

	public static class BlogPostBuilder {
		private Long id;
		private Date date;
		private String content;
		private String title;
		private Set<Category> categories;

		BlogPostBuilder buildContent(String content, String title){
			this.content = content;
			this.title = title;
			return this;
		}

		BlogPostBuilder buildBasic(Long id, Date date){
			this.id = id;
			this.date = date;
			return this;
		}

		BlogPostBuilder buildCategories(Set<Category> categories){
			this.categories = new HashSet<Category>(categories);
			return this;
		}

		BlogPostBuilder buildFromEntity(NewsEntry newsEntry){
			this.id = newsEntry.getId();
			this.date = newsEntry.getDate();
			this.content = newsEntry.getContent();
			this.title = newsEntry.getTitle();
			return this;
		}

		BlogPost build(){
			return new BlogPost(this);
		}
	}
}
