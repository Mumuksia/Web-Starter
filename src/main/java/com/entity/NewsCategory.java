/**
 * Copyright Flexpay AB
 */
package com.entity;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@javax.persistence.Entity
public class NewsCategory implements Entity {


	@Id
	@GeneratedValue
	private Long id;

	@Column
	private String name;

	@ManyToMany(targetEntity = NewsEntry.class, mappedBy = "categories")
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

	public Set<NewsEntry> getNewsEntries() {
		return newsEntries;
	}

	public void setNewsEntries(
			final Set<NewsEntry> newsEntries) {
		this.newsEntries = newsEntries;
	}

	@Override
	public String toString() {
		return "NewsCategory{" +
			   "id=" + id +
			   ", name='" + name + '\'' +
			   '}';
	}
}
