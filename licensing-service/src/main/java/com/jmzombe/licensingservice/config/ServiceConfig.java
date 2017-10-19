package com.jmzombe.licensingservice.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ServiceConfig{

  @Value("${example.property}")
  private String exampleProperty;
  
  @Value("${spring.jpa.database}")
  private String database;
  	  
  @Value("${spring.datasource.platform}")	  
  private String dataSource;
	  
  @Value("${spring.jpa.show-sql}")	  
  private Boolean showSQL;
	  
  @Value("${spring.database.driverClassName}")	  
  private String driverClassName;
	  	  
  @Value("${spring.datasource.url}")	  
  private String dataSourceURL;
    
  @Value("${spring.datasource.username}")
  private String userName;
	  
  @Value("${spring.datasource.password}")
  private String userPassword;
	  
  @Value("${spring.datasource.testWhileIdle}")
  private Boolean testWhilIdle;
	  
  @Value("${spring.datasource.validationQuery}")
  private String validationQuery;
	  
  @Value("${spring.jpa.properties.hibernate.dialect}") 
  private String hibernateDialect;
		  
  @Value("${redis.server}")  
  private String redisServer;
	  
  @Value("${redis.port}")
  private String redisPort;
	  
  @Value("${signing.key}")
  private String singingKey;
  
  
  public String getExampleProperty(){
      return exampleProperty;
  }
  
  public String getDatabase() {
	  return database;
  }

  public String getDataSource() {
	  return dataSource;
  }

  public Boolean getShowSQL() {
	  return showSQL;
  }

  public String getDriverClassName() {
	  return driverClassName;
  }

  public String getDataSourceURL() {
	  return dataSourceURL;
  }

  public String getUserName() {
	  return userName;
  }

  public String getUserPassword() {
	  return userPassword;
  }

  public Boolean getTestWhilIdle() {
	  return testWhilIdle;
  }

  public String getValidationQuery() {
	  return validationQuery;
  }

  public String getHibernateDialect() {
	  return hibernateDialect;
  }

  public String getRedisServer() {
	  return redisServer;
  }

  public String getRedisPort() {
	  return redisPort;
  }

  public String getSingingKey() {
	  return singingKey;
  }
  
}