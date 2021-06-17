package com.pay.my.budy.repository;


import static org.junit.Assert.assertEquals;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.ibatis.common.jdbc.ScriptRunner;
import com.pay.my.budy.config.HibernateFactory;
import com.pay.my.budy.config.Params;
import com.pay.my.budy.dao.UserDaoImpl;
import com.pay.my.budy.model.Friends;
import com.pay.my.budy.model.User;

@SpringBootTest
public class UserRepositoryImplTestIT {
	
	//Instance of Class
	@Autowired
	UserRepositoryImpl userRepoImpl;
	
	@Autowired
	UserDaoImpl userDaoImpl;

	static Session session;
	String hql = "select user.firstName from com.pay.my.budy.model.User user";
	String hqlgetAll = "select user from com.pay.my.budy.model.User user";

	
	@BeforeAll
	static void dropDatabasedev() throws SQLException, IOException {
		
	    //Initialize the script runner
		Connection connection = DriverManager.getConnection(Params.URLTEST, Params.USER, Params.PASSWORD);
		ScriptRunner scriptrunner = new ScriptRunner(connection, false, false);
		
		//Run script
		System.out.println("Script Exec...");
		Reader scriptSql = new BufferedReader(new FileReader(Params.SCRIPTSQL));
		scriptrunner.runScript(scriptSql);
		System.out.println("Script exec Succesfull !!");

		
	}


	@BeforeAll
	public void init() {
		System.out.println("Begin Init!!");
		session = HibernateFactory.getSessionFactory(Params.HIBERNATECONFIGTEST).openSession();
		System.out.println("Init Succesfull !!");
	}
	
	
	@Test
	public void createTest() {
		
		//GIVEN
		Set<Integer> friend = new HashSet<Integer>();
		User userTest = new User("Stevens", "Tavares", LocalDate.now(), "Draveil", "saxg@dev.fr", "abc123", friend, 19000.0);
		
		//WHEN
		userRepoImpl.create(userTest);
		
		Query<?> query = session.createQuery(hql);
		List<?> result = query.list();
		
		
		//THEN
		assertEquals("Stevens", result.get(3));

		
	}
	
	@Test
	public void getByIdTest() {
		
		
		//GIVEN
		Set<Integer> friend = new HashSet<Integer>();				
		User userTest = new User("Stevens", "Tavares", LocalDate.now(), "Draveil", "saxg@dev.fr", "abc123", friend, 19000.0);
		userRepoImpl.create(userTest);

		int id = 1;

		
		//WHEN		
		User actual = userRepoImpl.getById(id);

		
		//THEN
		assertEquals(id, actual.getId());
	}
	
	
	
	@Test
	public void getByNameTest() {
		
		
		//GIVEN
		Set<Integer> friend = new HashSet<Integer>();
		User userTest = new User("Stevens", "Tavares", LocalDate.now(), "Draveil", "saxg@dev.fr", "abc123", friend, 19000.0);		
		userRepoImpl.create(userTest);
		User actual = new User();	
		
		//WHEN
		actual = userRepoImpl.getByName("Stevens");
		
		//THEN
		assertEquals("Stevens", actual.getFirstName());
	}
	
	
	@Test
	public void saveTest() {
		
		
		//GIVEN
		Set<Integer> friend = new HashSet<Integer>();		
		User userTest = new User("Stevens", "Tavares", LocalDate.now(), "Draveil", "saxg@dev.fr", "abc123", friend, 19000.0);
		userRepoImpl.create(userTest);
		
		User userToUpdate = session.get(User.class, 1L);
		userToUpdate.setFisrtName("Laurence");
		userToUpdate.setMoneyAvailable(2000);
		
		//WHEN
		userRepoImpl.save(userToUpdate);
		
			
		//THEN
		assertEquals("Laurence", userToUpdate.getFirstName());
		
		
	}
	
	
	@Test
	public void deleteTest() {
		
		//GIVEN
		User userToDel = session.get(User.class, 1L);
		
		//WHEN
		userRepoImpl.delete(userToDel);
		Query<?> query = session.createQuery(hql);
		List<?> userList = query.getResultList();
		
		//THEN
		assertEquals(3, userList.size());
		
	}

	
	
}
