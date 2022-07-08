package com.mazdausa.ssc.common.db;


import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;


@Configuration
@ComponentScan("com.mazdausa.ssc")
public class JdbcTemplateConfig {
	@Autowired
	private Environment env;
	
	@Bean
    public DataSource sscDataSource() {
		
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(env.getProperty("database.jdbc.mssc_db.driver"));
        dataSource.setUrl(env.getProperty("database.jdbc.mssc_db.url"));
        dataSource.setUsername(env.getProperty("database.jdbc.mssc_db.user"));
        dataSource.setPassword(env.getProperty("database.jdbc.mssc_db.pass"));

        return dataSource;
    }
	
	@Bean
	public JdbcTemplate jdbcTemplate() {
		return new JdbcTemplate(this.sscDataSource());
	}
}
