/**
 * Copyright Flexpay AB
 */
package com.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.service.model.BlogPost;

@Service
public interface BlogPostService {

	List<BlogPost> getAll();

	BlogPost save(BlogPost post);

	BlogPost findById(Long id);

	void delete(Long id);

	BlogPost create(BlogPost post);

	List<BlogPost> getPostsByCategory(String categoryName);

}
