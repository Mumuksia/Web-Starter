package net.dontdrinkandroot.example.angularrestspringsecurity.entity;

import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import net.dontdrinkandroot.example.angularrestspringsecurity.JsonViews;

import org.codehaus.jackson.map.annotate.JsonView;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.springframework.context.annotation.Lazy;


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

	@ManyToMany(targetEntity = NewsCategory.class, fetch = FetchType.LAZY)
	@Cascade(CascadeType.ALL)
	private Set<NewsCategory> categories;


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

	public Set<NewsCategory> getCategories() {
		return categories;
	}

	public void setCategories(
			final Set<NewsCategory> categories) {
		this.categories = categories;
	}

	@Override
	public String toString()
	{
		return String.format("NewsEntry[%d, %s]", this.id, this.content);
	}

}
