/**
 * Copyright Flexpay AB
 */
package com.dao.model.entity;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.service.model.Category;

@javax.persistence.Entity
public class NewsCategory implements Entity {


	@Id
	@GeneratedValue
	private Long id;

	@Column(unique = true)
	private String name;

	@OneToMany
	private Set<NewsEntry> newsEntries;

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(final String name) {
		this.name = name;
	}

	public void setNewsEntries(final Set<NewsEntry> newsEntries) {
		this.newsEntries = newsEntries;
	}

	@Override
	public String toString() {
		return "NewsCategory{" +
			   "id=" + id +
			   ", name='" + name + '\'' +
			   '}';
	}

	public NewsCategory(final Category category) {
		this.name = category.getName();
		this.id = category.getId();
	}

	public NewsCategory(final String name, final Set<NewsEntry> newsEntries) {
		this.name = name;
		this.newsEntries = newsEntries;
	}

	public NewsCategory() {
	}
}
