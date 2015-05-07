/**
 * Copyright Flexpay AB
 */
package com.rest.resources;

import java.io.IOException;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.ObjectWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.JsonViews;
import com.service.CategoryService;
import com.service.model.Category;

@Component
@Path("/categories")
public class CategoryResource {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private ObjectMapper mapper;
	@Autowired
	private CategoryService categoryService;


	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String list() throws JsonGenerationException, JsonMappingException, IOException
	{
		this.logger.info("listCategories()");

		ObjectWriter viewWriter;
		if (this.isAdmin()) {
			viewWriter = this.mapper.writerWithView(JsonViews.Admin.class);
		} else {
			viewWriter = this.mapper.writerWithView(JsonViews.User.class);
		}
		List<Category> categories = categoryService.findAllCategories();

		return viewWriter.writeValueAsString(categories);
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("{id}")
	public Category read(@PathParam("id") Long id)
	{
		this.logger.info("read(id)");

		Category category = categoryService.getCategory(id);
		if (category == null) {
			throw new WebApplicationException(404);
		}
		return category;
	}


	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Category create(Category category)
	{
		this.logger.info("create(): " + category);

		return categoryService.updateCategory(category);
	}


	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("{id}")
	public Category update(@PathParam("id") Long id, Category blogPost)
	{
		this.logger.info("update(): " + blogPost);

		return categoryService.updateCategory(blogPost);
	}


	@DELETE
	@Produces(MediaType.APPLICATION_JSON)
	@Path("{id}")
	public void delete(@PathParam("id") Long id)
	{
		this.logger.info("delete(id)");

		categoryService.deleteCategory(id);
	}

	private boolean isAdmin()
	{
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		Object principal = authentication.getPrincipal();
		if (principal instanceof String && principal.equals("anonymousUser")) {
			return false;
		}
		UserDetails userDetails = (UserDetails) principal;


		for (GrantedAuthority authority : userDetails.getAuthorities()) {
			if (authority.toString().equals("admin")) {
				return true;
			}
		}

		return false;
	}

}
