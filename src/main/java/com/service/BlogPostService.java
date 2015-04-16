/**
 * Copyright Flexpay AB
 */
package com.service;

import com.service.model.BlogPost;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BlogPostService {

	List<BlogPost> getAll();

	BlogPost save(BlogPost post);

	BlogPost findById(Long id);

	void delete(Long id);

}
