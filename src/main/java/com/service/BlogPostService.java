/**
 * Copyright Flexpay AB
 */
package com.service;

import com.service.model.BlogPost;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BlogPostService {

	public List<BlogPost> getAllPosts();

}
