package com.pay.my.budy.config;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Component;

import com.pay.my.budy.model.Relationships;
import com.pay.my.budy.model.User;

@Component
public class HibernateFactory {
	
	public HibernateFactory() {}

	public void getSessionFactory(String hibernateconfig) {

		SessionFactory factory = new Configuration().configure(hibernateconfig).buildSessionFactory();

		Session session = factory.getCurrentSession();

		try {
			// Create object
			List<Relationships> friend = new ArrayList<Relationships>();
			User tempUser = new User("stevens", "Tavares", LocalDate.now(), "Tattoine", "jedi@star.fr", "abcd1234", friend , 35.5);

			// start transaction
			session.beginTransaction();

			// save the object
			session.save(tempUser);

			// commit the transaction
			session.getTransaction().commit();

		} finally {

			factory.close();
		}
	}

}
