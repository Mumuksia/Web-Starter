package com.service;

import com.service.model.BlogPost;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

/**
 * User: Muksia
 * Date: 15/04/15
 * Time: 21:03
 */
public class BlogPostServiceImplTest {

    private BlogPostService blogPostService;

    @Before
    public void init(){
        blogPostService = new BlogPostServiceImpl();
    }

    @Test
    public void getAllNews(){
        List<BlogPost> posts = blogPostService.getAllPosts();

    }
}
