package com.pay.my.budy.config;

import javax.sql.DataSource;

import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataSourceConfig {
	
	@Bean
	public DataSource getDataSource() {
		
		DataSourceBuilder<?> dataSourceBuilder = DataSourceBuilder.create();
		
		dataSourceBuilder.driverClassName(Params.DRIVERNAME);
		dataSourceBuilder.url(Params.URL);
		dataSourceBuilder.username(Params.USER);
		dataSourceBuilder.password(Params.PASSWORD);
		
			return dataSourceBuilder.build();
		
	}

}
