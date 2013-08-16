package com.anazzubair.techy.web.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
@EnableJpaRepositories(basePackages = { "com.anazzubair.techy.entity" })
public class PersistenceContext {

	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactoryBean() {
		LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
		em.setDataSource(dataSource());
		em.setPackagesToScan(new String[] { "org.baeldung.persistence.model" });

		JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
		em.setJpaVendorAdapter(vendorAdapter);
		em.setJpaProperties(additionalProperties());

		return em;
	}
	
	 @Bean
	   public DataSource dataSource(){
	      DriverManagerDataSource dataSource = new DriverManagerDataSource();
	      dataSource.setDriverClassName("com.mysql.jdbc.Driver");
	      dataSource.setUrl("jdbc:mysql://localhost:3306/spring_jpa");
	      dataSource.setUsername( "tutorialuser" );
	      dataSource.setPassword( "tutorialmy5ql" );
	      return dataSource;
	   }
	 
	   @Bean
	   public PlatformTransactionManager transactionManager(){
	      JpaTransactionManager transactionManager = new JpaTransactionManager();
	      transactionManager.setEntityManagerFactory(
	       entityManagerFactoryBean().getObject() );
	 
	      return transactionManager;
	   }
	 
	   @Bean
	   public PersistenceExceptionTranslationPostProcessor exceptionTranslation(){
	      return new PersistenceExceptionTranslationPostProcessor();
	   }
	 
	   Properties additionalProperties() {
	      return new Properties() {
			private static final long serialVersionUID = -4560379667896825386L;

			{  // Hibernate Specific:
	            setProperty("hibernate.hbm2ddl.auto", "create-drop");
	            setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect");
	         }
	      };
	   }
}
