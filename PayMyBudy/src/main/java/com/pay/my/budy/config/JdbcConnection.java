package com.pay.my.budy.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class JdbcConnection {
	

	public static Connection connectionToBdd() {
		
		Connection connection = null;
		
		try {
			
			connection = DriverManager.getConnection(Params.URL, Params.USER, Params.PASSWORD);
			System.out.println("Connection établie");

			 
		}catch(SQLException e){
			
			System.out.println("Connection échoué");
			e.printStackTrace();
		}
		
		return connection;
		
	}
}
