package com.second.CollaborationBack.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBuilder;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.second.CollaborationBack.DAO.BlogDAOImpl;
import com.second.CollaborationBack.DAO.CommentDAOImpl;
import com.second.CollaborationBack.DAO.ForumDAOImpl;
import com.second.CollaborationBack.DAO.FriendListDAOImpl;
import com.second.CollaborationBack.DAO.JobDAOImpl;
import com.second.CollaborationBack.DAO.UserDAOImpl;
import com.second.CollaborationBack.model.Blog;
import com.second.CollaborationBack.model.Comment;
import com.second.CollaborationBack.model.Forum;
import com.second.CollaborationBack.model.FriendList;
import com.second.CollaborationBack.model.Jobs;
import com.second.CollaborationBack.model.User;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "com.second.*")
@EnableTransactionManagement
public class AppConfig {

	@Autowired
	@Bean(name = "dataSource")
	public DataSource getH2DataSource(){
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName("org.h2.Driver");
		dataSource.setUrl("jdbc:h2:tcp://localhost/~/Collaboration");
		dataSource.setUsername("sa");
		dataSource.setPassword("");
		return dataSource;
		} 
	
	private Properties getHibernateProperties(){
		Properties properties = new Properties();
		properties.put("hibernate.show_sql", "true");
		properties.put("hibernate.format_sql", "true");
		properties.put("hibernate.hbm2ddl.auto", "update");
		properties.put("hibernate.dialect","org.hibernate.dialect.H2Dialect");
		
		
		return properties;
		}

	@Autowired
	@Bean(name = "sessionFactory")
	public SessionFactory getSessionFactory(DataSource dataSource){
		LocalSessionFactoryBuilder sessionBuilder = new LocalSessionFactoryBuilder(dataSource);
		sessionBuilder.addProperties(getHibernateProperties());
		sessionBuilder.addAnnotatedClass(User.class);
		sessionBuilder.addAnnotatedClass(Blog.class);
		sessionBuilder.addAnnotatedClass(Forum.class);
		sessionBuilder.addAnnotatedClass(Comment.class);
		sessionBuilder.addAnnotatedClass(FriendList.class);
		sessionBuilder.addAnnotatedClass(Jobs.class);
		return sessionBuilder.buildSessionFactory();
		}
	
	@Autowired
	@Bean(name = "transactionManager")
	public HibernateTransactionManager getTransactionManager(SessionFactory sessionFactory){
		HibernateTransactionManager transactionManager = new HibernateTransactionManager(sessionFactory);
		return transactionManager;
		
	}
	@Autowired(required=true)
	@Bean(name="userDAO")
	public UserDAOImpl getUserDAOImpl(SessionFactory sessionFactory){
		return new UserDAOImpl(sessionFactory);
	}
	@Autowired(required=true)
	@Bean(name="blogDAO")
	public BlogDAOImpl getBlogDAOImpl(SessionFactory sessionFactory){
		return new BlogDAOImpl(sessionFactory);
	}
	@Autowired
	@Bean(name="forumDAO")
	public ForumDAOImpl getForumDAOImpl(SessionFactory sessionFactory){
		return new ForumDAOImpl(sessionFactory);
	}
	@Autowired
	@Bean(name="commentDAO")
	public CommentDAOImpl getCommentDAOImpl(SessionFactory sessionFactory){
		return new CommentDAOImpl(sessionFactory);
	}
	@Autowired(required=true)
	@Bean(name="friendlist")
	public FriendListDAOImpl getFriendDAOImpl(SessionFactory sessionFactory){
		return new FriendListDAOImpl(sessionFactory);
	}
	@Autowired(required=true)
	@Bean(name="jobs")
	public JobDAOImpl getJobDAOImpl(SessionFactory sessionFactory){
		return new JobDAOImpl(sessionFactory);
	}
}
