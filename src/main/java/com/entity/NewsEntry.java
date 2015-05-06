package com.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.codehaus.jackson.map.annotate.JsonView;

import com.JsonViews;
import com.service.model.BlogPost;


@javax.persistence.Entity
public class NewsEntry implements Entity
{

	@Id
	@GeneratedValue
	private Long id;

	@Column
	private Date date;

	@Column
	private String content;

	@Column
	private String title;

	@ManyToOne(targetEntity = NewsCategory.class, fetch = FetchType.LAZY)
	private NewsCategory categories;


	public NewsEntry()
	{
		this.date = new Date();
	}


	@JsonView(JsonViews.Admin.class)
	public Long getId()
	{
		return this.id;
	}


	@JsonView(JsonViews.User.class)
	public Date getDate()
	{
		return this.date;
	}


	public void setDate(Date date)
	{
		this.date = date;
	}


	@JsonView(JsonViews.User.class)
	public String getContent()
	{
		return this.content;
	}


	public void setContent(String content)
	{
		this.content = content;
	}

	@JsonView(JsonViews.User.class)
	public String getTitle() {
		return title;
	}

	public void setTitle(final String title) {
		this.title = title;
	}

	public NewsCategory getCategories() {
		return categories;
	}

	public void setCategories(final NewsCategory categories) {
		this.categories = categories;
	}

	@Override
	public String toString()
	{
		return String.format("NewsEntry[%d, %s]", this.id, this.content);
	}

	public NewsEntry(BlogPost blogPost) {
		this.date = blogPost.getDate();
		this.content = blogPost.getContent();
		this.title = blogPost.getTitle();
		this.id = blogPost.getId();
		this.categories = new NewsCategory(blogPost.getCategories());
	}
}
