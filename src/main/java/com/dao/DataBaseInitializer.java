package com.dao;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.dao.newscategory.NewsCategoryDao;
import com.dao.newsentry.NewsEntryDao;
import com.dao.user.UserDao;
import com.entity.NewsCategory;
import com.entity.NewsEntry;
import com.entity.User;

import org.springframework.security.crypto.password.PasswordEncoder;


/**
 * Initialize the database with some test entries.
 * 
 * @author Philip W. Sorst <philip@sorst.net>
 */
public class DataBaseInitializer
{

	private NewsEntryDao newsEntryDao;
	private UserDao userDao;
	private NewsCategoryDao newsCategoryDao;

	private PasswordEncoder passwordEncoder;



	protected DataBaseInitializer()
	{
		/* Default constructor for reflection instantiation */
	}


	public DataBaseInitializer(UserDao userDao, NewsEntryDao newsEntryDao, NewsCategoryDao newsCategoryDao, PasswordEncoder passwordEncoder)
	{
		this.userDao = userDao;
		this.newsEntryDao = newsEntryDao;
		this.newsCategoryDao = newsCategoryDao;
		this.passwordEncoder = passwordEncoder;
	}


	public void initDataBase()
	{
		User userUser = new User("user", this.passwordEncoder.encode("user"));
		userUser.addRole("user");
		this.userDao.save(userUser);

		User adminUser = new User("admin", this.passwordEncoder.encode("admin"));
		adminUser.addRole("user");
		adminUser.addRole("admin");
		this.userDao.save(adminUser);

        User adminUser2 = new User("muksia", this.passwordEncoder.encode("muksia"));
        adminUser2.addRole("user");
        adminUser2.addRole("admin");
        this.userDao.save(adminUser2);

		NewsCategory newsCategory = new NewsCategory();
		newsCategory.setName("Category Name");
		this.newsCategoryDao.save(newsCategory);

		Set<NewsCategory> newsCategories = new HashSet<NewsCategory>();
		newsCategories.add(newsCategory);
		long timestamp = System.currentTimeMillis() - 1000 * 60 * 60 * 24;
		for (int i = 0; i < 2; i++) {
			NewsEntry newsEntry = new NewsEntry();
			newsEntry.setContent("This is example content " + i);
			newsEntry.setDate(new Date(timestamp));
			newsEntry.setTitle("Some title");
			newsEntry.setCategories(newsCategories);
			this.newsEntryDao.save(newsEntry);
			timestamp += 1000 * 60 * 60;
		}
	}

}
