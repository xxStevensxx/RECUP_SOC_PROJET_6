package com.pay.my.budy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

import com.pay.my.budy.config.HibernateFactory;
import com.pay.my.budy.config.Params;

@SpringBootApplication(exclude={DataSourceAutoConfiguration.class})
public class PayMyBudyApplication {

	public static void main(String[] args) {
		SpringApplication.run(PayMyBudyApplication.class, args);
		
		HibernateFactory factory = new HibernateFactory();
		factory.getSessionFactory(Params.HIBERNATECONFIG);
	}

}
