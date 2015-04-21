/**
 * Copyright Flexpay AB
 */
package com.entity;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import com.service.model.Category;

@javax.persistence.Entity
public class NewsCategory implements Entity {


	@Id
	@GeneratedValue
	private Long id;

	@Column
	private String name;

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(final String name) {
		this.name = name;
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

	public NewsCategory() {
	}
}
