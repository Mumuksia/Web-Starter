/**
 * Copyright Flexpay AB
 */
package com.rest.resources;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.service.BlogPostService;
import com.service.model.BlogPost;

@Component
@Path("/post")
public class PostResource{


	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private BlogPostService blogPostService;

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("{id}")
	public BlogPost read(@PathParam("id") Long id)
	{
		this.logger.info("read(id)");

		BlogPost post = this.blogPostService.findById(id);
		if (post == null) {
			throw new WebApplicationException(404);
		}
		return post;
	}


	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public BlogPost create(BlogPost blogPost)
	{
		this.logger.info("create(): " + blogPost);

		return this.blogPostService.save(blogPost);
	}


	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("{id}")
	public BlogPost update(@PathParam("id") Long id, BlogPost blogPost)
	{
		this.logger.info("update(): " + blogPost);

		return this.blogPostService.save(blogPost);
	}


	@DELETE
	@Produces(MediaType.APPLICATION_JSON)
	@Path("{id}")
	public void delete(@PathParam("id") Long id)
	{
		this.logger.info("delete(id)");

		this.blogPostService.delete(id);
	}
}
