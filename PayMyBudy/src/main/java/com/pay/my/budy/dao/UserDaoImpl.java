package com.pay.my.budy.dao;

import java.util.ArrayList;


import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.hibernate.SessionFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.pay.my.budy.config.HibernateFactory;
import com.pay.my.budy.config.Params;
import com.pay.my.budy.model.User;
import com.pay.my.budy.services.ServicesUser;

@Component
public class UserDaoImpl implements UserDao {

	public UserDaoImpl() {
		// TODO Auto-generated constructor stub
	}

	@Autowired
	HibernateFactory hibernateFactory;

	@Autowired
	ServicesUser servicesUser;

	private static Session session;
	
	private Transaction transaction;
	

	public static void initSession() {
		
		session = HibernateFactory.getSessionFactory(Params.HIBERNATECONFIG).openSession();
		System.out.println("Opening session ... " + session);

	}
	
	
	public void closeSession() {
	
		session.clear();
		session.close();
		System.out.println("session close successfully!! " + session );

	}
	

	@Override
	public void add(User user) {

		initSession();

		try {

			// start a transaction
			transaction = session.beginTransaction();

			// save the user object
			session.save(user);

			// commit transaction
			session.getTransaction().commit();

		} catch (Exception e) {

			if (transaction != null) {

				transaction.rollback();
			}

			e.printStackTrace();

		} finally {

			// close the session
			closeSession();

		}

	}

	@Override
	public void update(User user) {

		initSession();

		try {

			// start a transaction
			transaction = session.beginTransaction();

			// update the user object
			session.merge(user);

			// commit transaction
			session.getTransaction().commit();

		} catch (Exception e) {

			if (transaction != null) {

				transaction.rollback();
			}

			e.printStackTrace();

		} finally {

			// close the session
			closeSession();

		}

	}

	@Override
	public void delete(User user) {

		initSession();

		try {

			// start a transaction
			transaction = session.beginTransaction();

			// delete the user object
			session.delete(user);

			// commit transaction
			session.getTransaction().commit();

		} catch (Exception e) {

			if (transaction != null) {

				transaction.rollback();
			}

			e.printStackTrace();

		} finally {

			closeSession();

		}

	}

	@Override
	public User getUserById(int id) {

		initSession();
		
		ArrayList<User> userList = new ArrayList<>();
		User user = new User();

		try {

			Query<User> query = session.createQuery(Params.SQLREQFINDALL);
			userList = (ArrayList<User>) query.list();

			for (int i = 0; i < userList.size(); i++) {

				if (id == userList.get(i).getId()) {

					user = userList.get(i);

				}

			}

		} catch (Exception e) {

			e.printStackTrace();
			
		} finally {

			closeSession();


		}

		return user;
	}

	@Override
	public ArrayList<User> getAllUser() {

		initSession();
		
		ArrayList<User> userList = new ArrayList<>();

		try {

			Query<User> query = session.createQuery(Params.SQLREQFINDALL);
			userList = (ArrayList<User>) query.list();

		} catch (Exception e) {

			e.printStackTrace();
			
		} finally {
			
			closeSession();

		}

		return userList;
	}

}
