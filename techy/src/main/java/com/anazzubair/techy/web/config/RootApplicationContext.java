package com.anazzubair.techy.web.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.anazzubair.techy.business.service.ServicePackageMarkerInterface;

@Configuration
@ComponentScan(basePackageClasses = { ServicePackageMarkerInterface.class })
@Import(PersistenceContext.class)
@EnableTransactionManagement
public class RootApplicationContext {
	
}
