package com.homework.travel;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.support.PersistenceAnnotationBeanPostProcessor;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
public class AppRepositoryConfig {

    @Bean
    PlatformTransactionManager transactionManager(LocalContainerEntityManagerFactoryBean entityManagerFactory) {
    	JpaTransactionManager transactionManager = new JpaTransactionManager(entityManagerFactory.getObject());
    	return transactionManager;
    }
    
    @Bean
    PersistenceAnnotationBeanPostProcessor exceptionTranslation() {
    	return new PersistenceAnnotationBeanPostProcessor();
    }
}
