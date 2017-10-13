package com.yash.shoppingcart.configuration;

import java.util.Properties;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.yash.shoppingcart.dao.AccountDao;
import com.yash.shoppingcart.dao.OrderDao;
import com.yash.shoppingcart.dao.ProductDao;
import com.yash.shoppingcart.daoimpl.AccountDaoImpl;
import com.yash.shoppingcart.daoimpl.OrderDaoImpl;
import com.yash.shoppingcart.daoimpl.ProductDaoImpl;

@Configuration
@ComponentScan("com.yash.shoppingcart.*")
@EnableTransactionManagement
@PropertySource("classpath:hibernate.cfg.properties")
public class ApplicationContextConfig {
	@Autowired
    private Environment env;
	
	@Bean(name = "viewResolver")
	public InternalResourceViewResolver getViewResolver(){
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setPrefix("/WEB-INF/views/");
		viewResolver.setSuffix(".jsp");
		return viewResolver;	
	}
	
	@Bean(name = "dataSource")
	public DataSource getDataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(env.getProperty("ds.database-driver"));
        dataSource.setUrl(env.getProperty("ds.url"));
        dataSource.setUsername(env.getProperty("ds.username"));
        dataSource.setPassword(env.getProperty("ds.password"));   
        System.out.println("---getDataSource:--- " + dataSource);  
        return dataSource;
    }
	
	@Bean(name = "multipartResolver")
    public CommonsMultipartResolver multipartResolver() {
        CommonsMultipartResolver commonsMultipartResolver = new CommonsMultipartResolver();
        return commonsMultipartResolver;
    }
	
	@Autowired
    @Bean(name = "sessionFactory")
    public SessionFactory getSessionFactory(DataSource dataSource) throws Exception {
        Properties properties = new Properties();
        properties.put("hibernate.dialect", env.getProperty("hibernate.dialect"));
        properties.put("hibernate.show_sql", env.getProperty("hibernate.show_sql"));
        properties.put("current_session_context_class", env.getProperty("current_session_context_class"));
        LocalSessionFactoryBean factoryBean = new LocalSessionFactoryBean();
        factoryBean.setPackagesToScan(new String[] { "com.yash.shoppingcart.entity" });
        factoryBean.setDataSource(dataSource);
        factoryBean.setHibernateProperties(properties);
        factoryBean.afterPropertiesSet();
        SessionFactory session = factoryBean.getObject();
        System.out.println("---getSessionFactory: " + session);
        return session;
    }
	
	@Autowired
    @Bean(name = "transactionManager")
    public HibernateTransactionManager getTransactionManager(SessionFactory sessionFactory) {
        HibernateTransactionManager transactionManager = new HibernateTransactionManager(sessionFactory);
        return transactionManager;
    }
	
	@Bean
	public ResourceBundleMessageSource messageSource() {
	   ResourceBundleMessageSource resource = new ResourceBundleMessageSource();
	   resource.setBasenames(new String[] {"messages/validator"});
	   return resource;
	}
	
	@Bean(name = "accountDao")
    public AccountDao getApplicantDao() {
        return new AccountDaoImpl();
    }
 
    @Bean(name = "productDao")
    public ProductDao getProductDao() {
        return new ProductDaoImpl();
    }
 
    @Bean(name = "orderDao")
    public OrderDao getOrderDao() {
        return new OrderDaoImpl();
    }
}
