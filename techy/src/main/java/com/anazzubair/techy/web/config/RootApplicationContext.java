package com.anazzubair.techy.web.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@ComponentScan(basePackages = { "com.anazzubair.techy.business.service" })
@Import(PersistenceContext.class)
@EnableTransactionManagement
public class RootApplicationContext {

}
