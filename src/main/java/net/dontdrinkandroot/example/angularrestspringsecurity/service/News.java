/**
 * Copyright Flexpay AB
 */
package net.dontdrinkandroot.example.angularrestspringsecurity.service;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import net.dontdrinkandroot.example.angularrestspringsecurity.entity.NewsEntry;

public class News {

	private Long id;
	private Date date;
	private String content;
	private String title;
	private Set<Category> categories;

	public News(final NewsBuilder newsBuilder) {
		this.id = newsBuilder.id;
		this.date = newsBuilder.date;
		this.content = newsBuilder.content;
		this.title = newsBuilder.title;
	}

	public static class NewsBuilder{
		private Long id;
		private Date date;
		private String content;
		private String title;
		private Set<Category> categories;

		NewsBuilder buildContent(String content, String title){
			this.content = content;
			this.title = title;
			return this;
		}

		NewsBuilder buildBasic(Long id, Date date){
			this.id = id;
			this.date = date;
			return this;
		}

		NewsBuilder buildCategories(Set<Category> categories){
			this.categories = new HashSet<Category>(categories);
			return this;
		}

		NewsBuilder buildFromEntity(NewsEntry newsEntry){
			this.id = newsEntry.getId();
			this.date = newsEntry.getDate();
			this.content = newsEntry.getContent();
			this.title = newsEntry.getTitle();
			return this;
		}

		News build(){
			return new News(this);
		}
	}
}
