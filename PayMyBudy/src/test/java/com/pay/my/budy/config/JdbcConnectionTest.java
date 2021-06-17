package com.pay.my.budy.config;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.sql.Connection;
import java.sql.SQLException;

import org.junit.jupiter.api.Test;


public class JdbcConnectionTest {

	
	@Test
	public void connectionToBddTest() throws SQLException {
		//WHEN
		Connection connection = JdbcConnection.connectionToBdd();
		
		//GIVEN
		String dbUrl = connection.getMetaData().getURL();
		
		//THEN
		assertEquals( "jdbc:mysql://localhost:3306/paymybudy", dbUrl);
	}
}
