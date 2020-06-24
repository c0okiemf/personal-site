package com.site.app;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;


@SpringBootApplication(scanBasePackages={"com.site.app"})
public class AppApplication extends SpringBootServletInitializer {

	@Value("${app.mysql.username}")
	private String username;
	@Value("${app.mysql.pwd}")
	private String pwd;
	@Value("${app.mysql.url}")
	private String url;

	private static final Logger log = LoggerFactory.getLogger(AppApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(AppApplication.class);
	}

	@Bean
	public DataSource dataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();

		dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
		dataSource.setUsername(username);
		dataSource.setPassword(pwd);
		dataSource.setUrl(url);

		return dataSource;
	}

}
